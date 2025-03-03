package com.example.samplemenu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.samplemenu.R;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private WifiManager wifiManager;

    private ArrayAdapter<String> adapter;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 123;

    ProgressDialog progressDialog;

    //sean


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        progressDialog = new ProgressDialog(MainActivity2.this);
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);


        //this code for list the available wifi list
        ListView wifiList = findViewById(R.id.wifiList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        wifiList.setAdapter(adapter);



        wifiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String selectedItem = displayList.get(position);
                Toast.makeText(MainActivity2.this, "클릭한 아이템: " + position, Toast.LENGTH_SHORT).show();
                openBTSCAN2(position,wifiList);
            }
        });

        Button buttonScan = findViewById(R.id.scanBtn);

        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(MainActivity2.this, "Turn on your wifi", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        registerReceiver(wifiScanReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));



        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Scanning...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                checkLocationPermission();
            }
        });
    }



    // BroadcastReceiver to receive WiFi scan results
    private final BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {


                List<ScanResult> scanResults = wifiManager.getScanResults();
                Log.d("haha", "scanResults");
                // Clear previous results
                adapter.clear();

                // Display the WiFi networks in the ListView
                for (ScanResult result : scanResults) {

                    String wifiInfo = result.SSID + " (" + result.capabilities + ")";

//                    String wifiInfo = result.SSID;
//
                    //Toast.makeText(MainActivity.this, wifiInfo, Toast.LENGTH_LONG).show();
                    Log.d("haha","result."+result.SSID);
                    adapter.add(wifiInfo);
                }

                // Notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();

                progressDialog.dismiss();
                // Start a new scan for continuous updates
                wifiManager.startScan();

            }
        }
    };
    private final androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
//                        Uri imageUri = data.getData();
//                        imageView.setImageURI(imageUri); // Example: Set image to an ImageView
                        Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_LONG).show();
                    }
                }
            });
    private void openBTSCAN2( int position,ListView wifiList) {

        switch (position) {
            case 0: {

               Intent intent = new Intent(this, MainActivity3.class);
                Log.d("haha", "wifiList.getTransitionName()"+wifiList.getTransitionName());
                Log.d("haha", "openBTSCAN2::scanResults"+wifiList);
                Log.d("haha", "openBTSCAN2::scanResults"+wifiList);
                intent.putExtra("address","192.168.4.1") ;
                intent.putExtra("port","80") ;
                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 0", Toast.LENGTH_LONG).show();
            }
            break;
            case 1: {

                Toast.makeText(getApplicationContext(), "position 1", Toast.LENGTH_LONG).show();
            }
            break;
            case 2: {
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.setClassName(packageName3, className3);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 2", Toast.LENGTH_LONG).show();
            }
            break;
            case 3: {
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.setClassName(packageName3, className3);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 3", Toast.LENGTH_LONG).show();
            }
            break;
            case 4: {
//                Intent intent = new Intent(this, MainActivity7.class);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 4", Toast.LENGTH_LONG).show();
            }
            break;
            case 5: {
//                Intent intent = new Intent(this, MainActivity8.class);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 5", Toast.LENGTH_LONG).show();
            }
            break;
            case 6: {
//                Intent intent = new Intent(this, MainActivity2.class);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position 6", Toast.LENGTH_LONG).show();
            }
            break;
            default: {
//                Intent intent = new Intent(this, MainActivity10.class);
//                ActivityResultLauncher.launch(intent);
                Toast.makeText(getApplicationContext(), "position default", Toast.LENGTH_LONG).show();
            }
            break;
        }
    }
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            progressDialog.dismiss();
        } else {
            // Permission already granted, proceed with WiFi scanning
            wifiManager.startScan();
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiScanReceiver);
    }
}