package com.example.samplemenu;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.net.Network;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.net.NetworkCapabilities;

import android.net.TransportInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiInfo;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Map;

public class MainActivity5 extends AppCompatActivity {

    private static final String logTag = "WiFiScan";

    // to manage missing permissions
    private final ArrayList<String> missingPermissions = new ArrayList<>();
    private static final int MY_PERMISSION_REQUEST_CODE = 100;

    private WifiManager wifiManager;
    private WifiBroadcastReceiver wifiReceiver;

    private ConnectivityManager connectivityManager;

    // UI elements
    private LinearLayout layoutWifiInfo;
    private TextView textViewWifiInfoSummary;
    private LinearLayout layoutScanInfo;
    private TextView textViewWifiScanSummary;

    // provide information about Android API levels and 802.11 Information Elements (IEs)
    private static final AndroidAPILevelInfo androidAPILevelInfo = new AndroidAPILevelInfo();
    private static final IEEE802Dot11IEInfo ieInfo = new IEEE802Dot11IEInfo();

    // descriptive strings for Wi-Fi state
    private static final Map<Integer, String> wifiStateString = new Hashtable<>();
    static {
        wifiStateString.put(WifiManager.WIFI_STATE_DISABLING, "\"Disabling\"");
        wifiStateString.put(WifiManager.WIFI_STATE_DISABLED, "\"Disabled\"");
        wifiStateString.put(WifiManager.WIFI_STATE_ENABLING, "\"Enabling\"");
        wifiStateString.put(WifiManager.WIFI_STATE_ENABLED, "\"Enabled\"");
        wifiStateString.put(WifiManager.WIFI_STATE_UNKNOWN, "\"Unknown\"");
    }

    // helper for quick Toast messages
    private void toast(final String message) {
        Toast.makeText(MainActivity5.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Log.d("haha", "activity_main5 changed");

        // ask for initial required permissions
        requestPermissions();
        Log.d("haha", "        requestPermissions()");

        // WifiManager - for synchronous calls to get connection and scan information
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        Log.d("haha", "        wifiManager()"+wifiManager);
        // WifiBroadcastReceiver - for solicited/unsolicited scan requests (always asynchronous)
        // scan results can come in as soon as receiver is registered
        wifiReceiver = new WifiBroadcastReceiver();
        Log.d("haha", "        wifiReceiver()"+wifiReceiver);
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.d("haha", "        wifiReceiver()2");
        // ConnectivityManager - preferred way to get Wi-Fi connection information, which makes
        // sense given dynamic changes to Wi-Fi state and performance impact of synchronous calls
        // https://developer.android.com/reference/android/net/wifi/WifiManager#getConnectionInfo()
        connectivityManager = (ConnectivityManager) getApplicationContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.d("haha", "        connectivityManager");
        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build();
        Log.d("haha", "        NetworkRequest");

        // starting with API level 31, objects do not contain any location sensitive information
        // by default (even if app holds the required permissions) -- this frees the system from
        // tracking app location usage (just because it has location access permission); with new
        // model app should request location sensitive information to be included in the object
        // explicitly (thorough FLAG_INCLUDE_LOCATION_INFO), in which case system will check
        // location permission and the location toggle state, and if all is met will take note of
        // app location usage only if any such information is returned
        // https://developer.android.com/reference/android/net/ ..
        // .. ConnectivityManager.NetworkCallback.html#FLAG_INCLUDE_LOCATION_INFO

        // ConnectivityManager.NetworkCallback older constructor (level < 31) takes no argument,
        // and its new incompatible constructor takes an integer (flags) argument; this poor API
        // design requires implementing both versions (under a Build.VERSION.SDK_INT check), and
        // even worse the whole long body (anonymous inner class) that overrides network callbacks
        // needs to be copied for each constructor (or define a separate named class),
        //    networkCallback = new ConnectivityManager.NetworkCallback() { @override ... }
        //    networkCallback = new ConnectivityManager.NetworkCallback(flag) { @override ... }
        // we just keep the second copy here for API level >= 31; for lower API levels comment out
        // or remove the the flag argument below and rebuild (code will crash otherwise)
        Log.d("haha", "        ConnectivityManager");
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.
                NetworkCallback(ConnectivityManager.NetworkCallback.FLAG_INCLUDE_LOCATION_INFO) {

            // NetworkCallback class is extended (not inherited), i.e., respective parent
            // callbacks (super.xyz()) need to be called; see https://developer.android.com/ ..
            // .. training/monitoring-device-state/connectivity-status-type#ConfigureCallback
            @Override
            public void onAvailable(Network network) {
                String fName = "NetworkCallback() -> onAvailable(): ";
                Log.i(logTag, fName + network.toString());
                Log.d("haha", "        onAvailable");
                super.onAvailable(network);
            }

            @Override
            public void onCapabilitiesChanged(Network network,
                                              NetworkCapabilities capabilities) {
                String fName = "NetworkCallback() -> onCapabilitiesChanged(): ";
                Log.i(logTag, fName + ((network != null && capabilities != null) ?
                        "Network " + network + " capabilities: " + capabilities :
                        "No network or capabilities"));
                Log.d("haha", "        onCapabilitiesChanged");

                if (Build.VERSION.SDK_INT >= 29) {
                    TransportInfo transportInfo;
                    if (network != null && capabilities != null &&
                            (transportInfo = capabilities.getTransportInfo()) != null &&
                            (transportInfo instanceof WifiInfo)) {
                        showConnectionInfo((WifiInfo) transportInfo);
                        Log.d("haha", "        showConnectionInfo");
                        toast("Connection information updated\n   (network capability change)");
                    }
                } else {
                    if (capabilities != null) {
                        // no access to Wi-Fi specific information for API levels 24-28; only,
                        // capabilities.getLinkDownstreamBandwidthKbps()
                        // capabilities.getLinkUpstreamBandwidthKbps()
                        // capabilities.getSignalStrength()
                        Log.i(logTag, fName + "No Wi-Fi specific information");
                        Log.d("haha", "        capabilities != null");
                    }
                }
                super.onCapabilitiesChanged(network, capabilities);
            }

            @Override
            public void onLost(Network network) {
                String fName = "NetworkCallback() -> onLost(): ";
                Log.i(logTag, fName + "Network lost");
                Log.d("haha", "        onLost");
                showConnectionInfo(null);
                toast("Connection information updated\n              (network lost)");
                super.onLost(network);
            }
        };
        connectivityManager.requestNetwork(networkRequest, networkCallback);

        // UI elements for connected BSS information area (WifiInfo)
        Button buttonWifiInfo = findViewById(R.id.button_wifi_info);
        buttonWifiInfo.setOnClickListener(v -> requestWifiInfo());
        layoutWifiInfo = findViewById(R.id.layout_wifi_info);
        textViewWifiInfoSummary = findViewById(R.id.textView_wifi_info_summary);
        textViewWifiInfoSummary.setGravity(Gravity.CENTER_VERTICAL);

        // UI elements for scan information area (ScanResults)
        Button buttonWifiScan = findViewById(R.id.button_wifi_scan);
        buttonWifiScan.setOnClickListener(v -> requestWifiScan());
        layoutScanInfo = findViewById(R.id.layout_scan_info);
        textViewWifiScanSummary = findViewById(R.id.textView_wifi_scan_summary);
        textViewWifiScanSummary.setGravity(Gravity.CENTER_VERTICAL);
    }

    @Override
    protected void onStop() {
        // no need to release ConnectivityManager resources that were set up without intent
        // connectivityManager.unregisterNetworkCallback(...);
        // connectivityManager.releaseNetworkRequest(...);
        // but unregister this broadcast receiver that was set up with an intent
        unregisterReceiver(wifiReceiver);
        super.onStop();
    }

    // Wi-Fi events listener
    class WifiBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String fName = "WifiBroadcastReceiver.OnReceive(): ";
            String action = intent.getAction();
            Log.i(logTag, fName + "action = " + action);
            Log.d("haha", fName + "action = " + action);
            if (action != null) {
                if (action.startsWith(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                    int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                            WifiManager.WIFI_STATE_UNKNOWN);
                    showWifiState(wifiState);
                }
                else if (action.startsWith(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                    boolean scanSuccessful = intent.getBooleanExtra(
                            WifiManager.EXTRA_RESULTS_UPDATED, false);
                    if (scanSuccessful) {
                        Log.i(logTag,fName + "Wi-Fi scan successful");
                        toast(getString(R.string.wifi_scan_successful));
                        // permissions was requested before registering this listener
                        @SuppressLint("MissingPermission")
                        List<ScanResult> scanResults = wifiManager.getScanResults();
                        showScanResults(scanResults);
                    } else {
                        Log.i(logTag,fName + "Wi-Fi scan denied or failed");
                        textViewWifiScanSummary.setText(R.string.wifi_scan_unsuccessful);
                        toast("    " + getString(R.string.wifi_scan_unsuccessful));
                    }
                }
                else if (action.startsWith(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                    Log.i(logTag, fName + "Wi-Fi network state changed");
                }
            }
        }
    }

    // retrieve Wi-Fi connection information (on-demand)
    // this won't be possible in future, moving to asynchronous model using callbacks only
    private void requestWifiInfo() {
        // String fName = "requestWifiInfo(): ";

        // starting with API level 31 Android is encouraging Wi-Fi information access through
        // ConnectivityManager, and for both synchronous (on-demand, as in here) and asynchronous
        // (callbacks) models; however, location sensitive ssid and bssid information are wiped
        // with the code below, even with required permissions; as a result, unless there is no
        // interest in ssid and bssid information, there is no point in using ConnectivityManager
        // for synchronous calls: use either WifiManager with synchronous calls
        // or ConnectivityManager with asynchronous calls, which is preferred moving forward
        boolean useWifiManagerRegardless = true;

        // use WifiManager.getConnectionInfo() for older SDKs (deprecated now)
        if (Build.VERSION.SDK_INT < 31 || useWifiManagerRegardless) { // deprecated, still useful
            showConnectionInfo(wifiManager.getConnectionInfo());
            toast("Connection information updated\n              (on demand)");
        } else {
            // recommended way, although location sensitive ssid and bssid are wiped
            Network network = connectivityManager.getActiveNetwork();
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            TransportInfo transportInfo;
            if (network != null && capabilities != null &&
                    (transportInfo = capabilities.getTransportInfo()) != null &&
                    (transportInfo instanceof WifiInfo)) {
                showConnectionInfo((WifiInfo) transportInfo);
                toast("Connection information updated\n              (on demand)");
            }
        }
    }

    private void showWifiState(int wifiState) {
        String fName = "showWifiState(): ";
        String stateString = wifiStateString.getOrDefault(wifiState, "not clear!");
        Log.i(logTag, fName + "new Wi-F state is: " + stateString);
        Toast.makeText(this, "Wi-Fi state is " + stateString, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint({"HardwareIds"})
    private void showConnectionInfo(WifiInfo wifiInfo) {
        // String fName = "showWifiInfo()";

        // post to main thread if called from ConnectivityManager callbacks
        // on "ConnectivityThread" thread
        if (!Looper.getMainLooper().isCurrentThread()) {
            Handler mainHandler = new Handler(Looper.getMainLooper());
            // anonymous new Runnable() (that has only one argument) replaced with lambda
            Runnable myRunnable = () -> showConnectionInfo(wifiInfo);
            mainHandler.post(myRunnable);
            return;
        }

        layoutWifiInfo.removeAllViews();

        if (wifiInfo == null) {
            textViewWifiInfoSummary.setText("");
            StringBuilder sb = new StringBuilder();
            sb.append("wifiInfo = null");
            showText(layoutWifiInfo, sb);
            return;
        }

        // show the supplicant state
        textViewWifiInfoSummary.setText(String.format(Locale.US,
                "Wi-Fi Supplicant state:\n%s",
                wifiInfo.getSupplicantState().toString()));

        if (wifiInfo.getSupplicantState() != SupplicantState.COMPLETED) {
            StringBuilder sb = new StringBuilder();
            sb.append("No Wi-Fi connection");
            showText(layoutWifiInfo, sb);
            return;
        }

        // basic information - ssid, bssid
        TextView basicView = new TextView(this);
        basicView.setText(String.format(Locale.US," SSID: %s\n BSSID: %s",
                wifiInfo.getSSID(), wifiInfo.getBSSID()));
        basicView.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_700));
        layoutWifiInfo.addView(basicView);

        // level 1
        /* if (Build.VERSION.SDK_INT >= 1) */ { // required minimum SDK: 24
            showAPILevel(layoutWifiInfo, 1);
            StringBuilder sb = new StringBuilder();
            // getMacAddress() always returns 02:00:00:00:00:00 starting with level 23
            sb.append(  "  getMacAddress(): ").append(wifiInfo.getMacAddress());
            sb.append("\n  getLinkSpeed(): ").append(wifiInfo.getLinkSpeed());
            sb.append("\n  getRssi(): ").append(wifiInfo.getRssi());
            sb.append("\n  toString(): ").append(wifiInfo);
            showText(layoutWifiInfo, sb);
        }

        // level 21
        /* if (Build.VERSION.SDK_INT >= 21) */ { // required minimum SDK: 24
            showAPILevel(layoutWifiInfo, 21);
            StringBuilder sb = new StringBuilder();
            sb.append("  getFrequency(): ").append(wifiInfo.getFrequency());
            showText(layoutWifiInfo, sb);
        }

        // level 29
        if (Build.VERSION.SDK_INT >= 29) {
            showAPILevel(layoutWifiInfo, 29);
            StringBuilder sb = new StringBuilder();
            sb.append(  "  getRxLinkSpeedMbps(): ").append(wifiInfo.getRxLinkSpeedMbps());
            sb.append("\n  getTxLinkSpeedMbps(): ").append(wifiInfo.getTxLinkSpeedMbps());
            showText(layoutWifiInfo, sb);
        }

        // level 30
        if (Build.VERSION.SDK_INT >= 30) {
            showAPILevel(layoutWifiInfo, 30);
            StringBuilder sb = new StringBuilder();
            sb.append(  "  getWifiStandard(): ").append(wifiInfo.getWifiStandard());
            sb.append("\n  getMaxSupportedRxLinkSpeedMbps(): ").
                    append(wifiInfo.getMaxSupportedRxLinkSpeedMbps());
            sb.append("\n  getMaxSupportedTxLinkSpeedMbps(): ").
                    append(wifiInfo.getMaxSupportedTxLinkSpeedMbps());
            showText(layoutWifiInfo, sb);
        }

        // level 31
        // IEs for scan results level 30+, for connected network level 31+
        if (Build.VERSION.SDK_INT >= 31) {
            showAPILevel(layoutWifiInfo, 31);
            StringBuilder sb = new StringBuilder();
            List<ScanResult.InformationElement> ies = wifiInfo.getInformationElements();
            if (ies != null) {
                sb.append("  getInformationElements (length in decimal):");
                for (ScanResult.InformationElement ie : ies) {
                    int ieId = ie.getId();
                    int ieIdExt = ie.getIdExt();
                    ByteBuffer ieByteBuffer = ie.getBytes();
                    String name = ieInfo.getName(ieId, ieIdExt, ieByteBuffer);
                    int length = ieByteBuffer.limit();
                    // drop the last ghost IE; strange bug showing a Qualcomm IE on Samsung S20
                    if (!(name.equals("Vendor Specific (Qualcomm)") && length == 25)) {
                        sb.append(String.format(Locale.US, "\n  > %s (%d): ", name, length));
                        while (ieByteBuffer.hasRemaining()) { // may not have a backing array
                            sb.append(String.format("%02x ", ieByteBuffer.get()));
                        }
                    }
                }
            } else {
                sb.append("  getInformationElements(): ").append("null");
            }
            showText(layoutWifiInfo, sb);
        }
    }

    // request to initiate a new Wi-Fi scan (on-demand)
    // this won't be possible in future, moving to asynchronous model using callbacks only
    private void requestWifiScan() {
        // String fName = "requestWifiScan(): ";
        layoutScanInfo.removeAllViews();
        textViewWifiScanSummary.setText(R.string.wifi_scan_initiate_text);
        wifiManager.startScan();
    }

    private void showScanResults(@NonNull List<ScanResult> scanResults) {
        String fName = "showScanResults()";
        layoutScanInfo.removeAllViews();

        // scan summary; show number of discovered BSSs in each band
        int nBSS = scanResults.size(), nBSS2 = 0, nBSS5 = 0, nBSS6 = 0;
        for (ScanResult bss : scanResults) {
            int f = bss.frequency;  // primary channel center frequency (MHz)
            if (2412 <= f && f <= 2484) nBSS2++; // 2.4 GHz band
            else if (5035 <= f && f <= 5980) nBSS5++; // 5 GHz band
            else if (5955 <= f && f <= 7115) nBSS6++; // 6 GHz band
        }
        textViewWifiScanSummary.setText(String.format(Locale.US,
                "Found %d network%s on\n2.4GHz (%d), 5GHz (%d), 6GHz (%d)",
                nBSS, nBSS == 1 ? "" : "s", nBSS2, nBSS5, nBSS6));

        // iterate over scan results
        for (ScanResult bss : scanResults) {

            // basic information - ssid, bssid
            TextView basicView = new TextView(this);
            basicView.setText(String.format(Locale.US,
                    " SSID: \"%s\"\n BSSID: %s", bss.SSID, bss.BSSID));
            basicView.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_700));
            layoutScanInfo.addView(basicView);

            // level 1
            /* if (Build.VERSION.SDK_INT >= 1) */ { // required minimum SDK: 24
                showAPILevel(layoutScanInfo, 1);
                StringBuilder sb = new StringBuilder();
                sb.append(  "  bss.capabilities: ").append(bss.capabilities);
                sb.append("\n  bss.frequency: ").append(bss.frequency);
                sb.append("\n  bss.level: ").append(bss.level);
                showText(layoutScanInfo, sb);
            }

            // level 23
            /* if (Build.VERSION.SDK_INT >= 23) */ { // required minimum SDK: 24
                showAPILevel(layoutScanInfo, 23);
                StringBuilder sb = new StringBuilder();
                sb.append(  "  bss.centerFreq0: ").append(bss.centerFreq0);
                sb.append("\n  bss.centerFreq1: ").append(bss.centerFreq1);
                sb.append("\n  bss.venueName: ").append(bss.venueName);
                sb.append("\n  bss.operatorFriendlyName: ").append(bss.operatorFriendlyName);
                sb.append("\n  bss.channelWidth: ").append(bss.channelWidth);
                showText(layoutScanInfo, sb);
            }

            // level 30
            if (Build.VERSION.SDK_INT >= 30) {
                showAPILevel(layoutScanInfo, 30);
                StringBuilder sb = new StringBuilder();
                sb.append(  "  bss.standard: ").append(bss.getWifiStandard());
                List<ScanResult.InformationElement> ies = bss.getInformationElements();
                if (ies != null) {
                    sb.append("\n  Elements (length in decimal):");
                    for (ScanResult.InformationElement ie : ies) {
                        int ieId = ie.getId();
                        int ieIdExt = ie.getIdExt();
                        ByteBuffer ieByteBuffer = ie.getBytes();
                        String name = ieInfo.getName(ieId, ieIdExt, ieByteBuffer);
                        int length = ieByteBuffer.limit();
                        if (!(name.equals("Vendor Specific (Qualcomm)") && length == 25)) { // drop ghost IE (bug)
                            sb.append(String.format(Locale.US, "\n  > %s (%d): ", name, length));
                            while (ieByteBuffer.hasRemaining()) { // may not have a backing array
                                sb.append(String.format("%02x ", ieByteBuffer.get()));
                            }
                        }
                    }
                } else {
                    sb.append("\n  getInformationElements(): ").append("null");
                }
                sb.append("\n");
                showText(layoutScanInfo, sb);
            }
        }
    }

    // helper to show API level information
    private void showAPILevel(@NonNull LinearLayout layout, int apiLevel) {
        TextView apiView = new TextView(this);
        apiView.setText(String.format(Locale.US, "API Level %d (Android %s (%s), %d)",
                androidAPILevelInfo.getLevel(apiLevel),
                androidAPILevelInfo.getPlatformVersionNumber(apiLevel),
                androidAPILevelInfo.getCodeName(apiLevel),
                androidAPILevelInfo.getYear(apiLevel)));
        apiView.setBackgroundColor(ContextCompat.getColor(this,
                androidx.cardview.R.color.cardview_dark_background));
        layout.addView(apiView);
    }

    // helper to show general text
    private void showText(@NonNull LinearLayout layout, @NonNull StringBuilder sb) {
        TextView textView = new TextView(this);
        textView.setText(sb.toString());
        layout.addView(textView);
    }

    // helper function to check if need to request a permission
    private void addToPermissionRequestListIfNeeded(final String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != PERMISSION_GRANTED &&
                !missingPermissions.contains(permission)) {
            missingPermissions.add(permission);
        }
    }

    // request initial permissions -- these are permissions that are required inside
    // event listeners that could receive unsolicited events as soon as registered
    private void requestPermissions() {
        String fName = "requestPermissions(): ";

        // ConnectivityManager.requestNetwork() permissions
        // ------------------------------------------------
        // * level 20 and below: call did not exist
        // * level 21 and above:
        //   - permissions: CHANGE_NETWORK_STATE
        /* if (android.os.Build.VERSION.SDK_INT >= 21) */ { // required minimum SDK: 24
            addToPermissionRequestListIfNeeded(permission.CHANGE_NETWORK_STATE);
        }

        // ConnectivityManager.registerNetworkCallback() permissions
        // ---------------------------------------------------------
        // * level 20 and below: call did not exist
        // * level 21 and above:
        //   - permissions: ACCESS_NETWORK_STATE
        /* if (android.os.Build.VERSION.SDK_INT >= 21) */ { // required minimum SDK: 24
            addToPermissionRequestListIfNeeded(permission.ACCESS_NETWORK_STATE);
        }

        // WifiManager.startScan() required permissions
        // --------------------------------------------
        // * level 27 and below: none
        // * level 28 and above:
        //   - WiFiManager.startScan() deprecated; applications won't be able to initiate
        //     scan in future releases, but can still listen to broadcast scan events
        //   - permissions: (ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION) and CHANGE_WIFI_STATE
        //   - other requirements: location service enabled
        // * level 29 and above:
        //   - permissions: ACCESS_FINE_LOCATION and CHANGE_WIFI_STATE
        //   - other requirements: location service enabled
        if (android.os.Build.VERSION.SDK_INT == 28) {
            addToPermissionRequestListIfNeeded(permission.ACCESS_COARSE_LOCATION);
            addToPermissionRequestListIfNeeded(permission.CHANGE_WIFI_STATE);
        } else if (android.os.Build.VERSION.SDK_INT >= 29) {
            addToPermissionRequestListIfNeeded(permission.ACCESS_FINE_LOCATION);
            addToPermissionRequestListIfNeeded(permission.CHANGE_WIFI_STATE);
        }

        // WifiManager.getScanResults() permissions
        // ----------------------------------------
        // * level 28 and below: none
        // * level 29 and above:
        //   - permissions: ACCESS_FINE_LOCATION and ACCESS_WIFI_STATE
        //   - other requirements: location service enabled
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            addToPermissionRequestListIfNeeded(permission.ACCESS_FINE_LOCATION);
            addToPermissionRequestListIfNeeded(permission.ACCESS_WIFI_STATE);
        }

        // request permissions; user faced with multiple requests is not a good experience
        if (!missingPermissions.isEmpty()) {
            Log.i(logTag, fName + "Asking for missing permissions:");
            for (String p: missingPermissions) Log.i(logTag, fName + "-> " + p);
            ActivityCompat.requestPermissions(this,
                    missingPermissions.toArray(new String[0]), MY_PERMISSION_REQUEST_CODE);
        } else {
            Log.i(logTag, fName + "All required permissions granted already");
        }
    }

    // permission request callback
    // used for debugging at this time
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grants) {
        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
            String fName = "onRequestPermissionsResult(): "; int i = 0; // java is archaic
            Log.i(logTag, fName + "Requested permissions update:");
            for (String p : permissions) { Log.i(logTag, fName + "-> " + p + ": " +
                    (grants[i++] == PERMISSION_GRANTED ? "granted" : "denied"));
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grants);
    }
}