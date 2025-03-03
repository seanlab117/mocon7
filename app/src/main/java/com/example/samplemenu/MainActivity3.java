package com.example.samplemenu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import android.os.AsyncTask;
import android.widget.Toast;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple UI demonstrating how to open a serial communication link to a
 * remote host over WiFi, send and receive messages, and update the display.
 *
 * Author: Hayk Martirosyan
 */
public class MainActivity3 extends Activity {

    // Tag for logging
    private final String TAG = getClass().getSimpleName();

    // AsyncTask object that manages the connection in a separate thread
    WiFiSocketTask wifiTask = null;

    // UI elements
    TextView textStatus, textRX, textTX;
    EditText editTextAddress, editTextPort, editSend;
    Button buttonConnect, buttonSend;

    private WifiManager wifiManager;

    private ArrayAdapter<String> adapter;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 123;

    ProgressDialog progressDialog;
    String address,port;
    EditText Et_text;
    Button button;
    TextView Text_view;

    private Spinner types;
    private Button show;
    private ListView drinks;
    private MockupDA da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);

        // Save references to UI elements
        textStatus = (TextView)findViewById(R.id.textStatus);
        textRX = (TextView)findViewById(R.id.textRX);
        textTX = (TextView)findViewById(R.id.textTX);
        editTextAddress = (EditText)findViewById(R.id.address);
        editTextPort = (EditText)findViewById(R.id.port);
        editSend = (EditText)findViewById(R.id.editSend);
        buttonConnect = (Button)findViewById(R.id.connect);

        buttonSend = (Button)findViewById(R.id.buttonSend);

        // Disable send button until a connection is made
        buttonSend.setEnabled(false);

        //
        // editSend.setText("senario 1");

        Log.d("haha","MainActivity3");
        Intent secondIntent = getIntent();
        secondIntent.getIntExtra("address", 0);
        address =secondIntent.getStringExtra("address");
        port =secondIntent.getStringExtra("port");
        Log.d("haha","address"+address+"port"+port);
        editTextAddress.setText(address);
        editTextPort.setText(port);
        Log.d("haha","address"+address+"port"+port);
        
        Et_text = findViewById(R.id.inputText);
        Text_view = findViewById(R.id.outputText);
        Et_text.setText("scenario 1");
        button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String numberString ="0"; // Et_text.getText().toString();
                    int number = Integer.parseInt(numberString);

                    StringBuilder result = new StringBuilder();

//                    for (int i = 1; i <= 10; i++) {
//                        result.append(number).append(" x ").append(i).append(" = ").append(number * i).append("\n");
//                    }
                    for (int i = 1; i <= 3; i++) {
                        int number2=Integer.parseInt("10");;
                        result.append("motor").append("  ").append(i).append(" = ").append(number2 * i).append("\n");
                    }
                    for (int j = 1; j <= 3; j++) {
                        int number3=Integer.parseInt("10");;
                        result.append("RGB").append("  ").append(j).append(" = ").append(number3 * j).append("\n");
                    }
                    for (int k = 1; k <= 1; k++) {
                        int number4=Integer.parseInt("10");;
                        result.append("Display").append("  ").append(k).append(" = ").append(number4 * k).append("\n");
                    }

                    Text_view.setText(result.toString());
                } catch (NumberFormatException e) {
                    Text_view.setText("Please enter a valid number");
                } catch (Exception e) {
                    Text_view.setText("An error occurred");
                }

            }
        });



    }
    private void showDrinksList(List<Drink> drinkList){
        List<String> displayList = new ArrayList<>();
        for (Drink drink : drinkList) {
            String displayString = drink.toString();
            displayList.add(displayString);
        }

        // Create an ArrayAdapter to display the list of drinks in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity3.this, android.R.layout.simple_list_item_1, displayList);
        drinks.setAdapter(adapter);

        drinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = displayList.get(position);
                Toast.makeText(MainActivity3.this, "클릭한 아이템: " + selectedItem, Toast.LENGTH_SHORT).show();
                //openBTSCAN2(position);
            }
        });


    }
    /**
     * Helper function, print a status to both the UI and program log.
     */

    private void bindSpinner() {
        List<String> Dtypes = da.getDrinkTypes();
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Dtypes);
        types.setAdapter(spnAdapter);
        //return spnAdapter;
    }
    void setStatus(String s) {
        Log.v(TAG, s);
        textStatus.setText(s);
    }

    /**
     * Try to start a connection with the specified remote host.
     */
    public void connectButtonPressed(View v) {

        if(wifiTask != null) {
            setStatus("Already connected!");
            return;
        }

        try {
            // Get the remote host from the UI and start the thread
            String host = editTextAddress.getText().toString();
            int port = Integer.parseInt(editTextPort.getText().toString());

            // Start the asyncronous task thread
            setStatus("Attempting to connect...");
            wifiTask = new WiFiSocketTask(host, port);
            wifiTask.execute();

        } catch (Exception e) {
            e.printStackTrace();
            setStatus("Invalid address/port!");
        }
    }

    /**
     * Disconnect from the connection.
     */
    public void disconnectButtonPressed(View v) {

        if(wifiTask == null) {
            setStatus("Already disconnected!");
            return;
        }

        wifiTask.disconnect();
        setStatus("Disconnecting...");
    }

    /**
     * Invoked by the AsyncTask when the connection is successfully established.
     */
    private void connected() {
        setStatus("Connected.");
        Log.v("haha", "Connected.");

        buttonSend.setEnabled(true);
    }

    /**
     * Invoked by the AsyncTask when the connection ends..
     */
    private void disconnected() {
        setStatus("Disconnected.");
        buttonSend.setEnabled(false);
        textRX.setText("");
        textTX.setText("");
        wifiTask = null;
    }

    /**
     * Invoked by the AsyncTask when a newline-delimited message is received.
     */
    private void gotMessage(String msg) {
        textRX.setText(msg);
        Log.v(TAG, "[RX] " + msg);
    }

    /**
     * Send the message typed in the input field using the AsyncTask.
     */
    public void sendButtonPressed(View v) {

        if(wifiTask == null) return;

        String msg = editSend.getText().toString();
        if(msg.length() == 0) return;
        msg="scenario 1";
        wifiTask.sendMessage(msg);

        editSend.setText("");

        textTX.setText(msg);
        Log.v(TAG, "[TX] " + msg);
    }

    /**
     * AsyncTask that connects to a remote host over WiFi and reads/writes the connection
     * using a socket. The read loop of the AsyncTask happens in a separate thread, so the
     * main UI thread is not blocked. However, the AsyncTask has a way of sending data back
     * to the UI thread. Under the hood, it is using Threads and Handlers.
     */
    public class WiFiSocketTask extends AsyncTask<Void, String, Void> {

        // Location of the remote host
        String address;
        int port;

        // Special messages denoting connection status
        private static final String PING_MSG = "SOCKET_PING";
        private static final String CONNECTED_MSG = "SOCKET_CONNECTED";
        private static final String DISCONNECTED_MSG = "SOCKET_DISCONNECTED";

        Socket socket = null;
        BufferedReader inStream = null;
        OutputStream outStream = null;

        // Signal to disconnect from the socket
        private boolean disconnectSignal = false;

        // Socket timeout - close if no messages received (ms)
        private int timeout = 5000;

        // Constructor
        WiFiSocketTask(String address, int port) {
            this.address = address;
            this.port = port;
        }

        /**
         * Main method of AsyncTask, opens a socket and continuously reads from it
         */
        @Override
        protected Void doInBackground(Void... arg) {

            try {

                // Open the socket and connect to it
                Intent secondIntent = getIntent();
                secondIntent.getIntExtra("address", 0);
                secondIntent.getIntExtra("port", 0);
                socket = new Socket();
                socket.connect(new InetSocketAddress(address, port), timeout);
                Log.d("haha", "address"+address);
                Log.d("haha", "port"+port);
                // Get the input and output streams
                inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outStream = socket.getOutputStream();

                // Confirm that the socket opened
                if(socket.isConnected()) {

                    // Make sure the input stream becomes ready, or timeout
                    long start = System.currentTimeMillis();
                    while(!inStream.ready()) {
                        long now = System.currentTimeMillis();
                        if(now - start > timeout) {
                            Log.e(TAG, "Input stream timeout, disconnecting!");
                            disconnectSignal = true;
                            break;
                        }
                    }
                } else {
                    Log.e(TAG, "Socket did not connect!");
                    disconnectSignal = true;
                }

                // Read messages in a loop until disconnected
                while(!disconnectSignal) {

                    // Parse a message with a newline character
                    String msg = inStream.readLine();

                    // Send it to the UI thread
                    publishProgress(msg);
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Error in socket thread!");
            }

            // Send a disconnect message
            publishProgress(DISCONNECTED_MSG);

            // Once disconnected, try to close the streams
            try {
                if (socket != null) socket.close();
                if (inStream != null) inStream.close();
                if (outStream != null) outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * This function runs in the UI thread but receives data from the
         * doInBackground() function running in a separate thread when
         * publishProgress() is called.
         */
        @Override
        protected void onProgressUpdate(String... values) {

            String msg = values[0];
            if(msg == null) return;

            // Handle meta-messages
            if(msg.equals(CONNECTED_MSG)) {
                connected();
            } else if(msg.equals(DISCONNECTED_MSG))
                disconnected();
            else if(msg.equals(PING_MSG))
            {}

            // Invoke the gotMessage callback for all other messages
            else
                gotMessage(msg);

            super.onProgressUpdate(values);
        }

        /**
         * Write a message to the connection. Runs in UI thread.
         */
        public void sendMessage(String data) {

            try {
                outStream.write(data.getBytes());
                outStream.write('\n');
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Set a flag to disconnect from the socket.
         */
        public void disconnect() {
            disconnectSignal = true;
        }
    }
}

