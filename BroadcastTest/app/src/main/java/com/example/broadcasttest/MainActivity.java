package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.nio.channels.NetworkChannel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private IntentFilter intentFilter;

    private NetworkChangeReceiver networkChangeReceiver;

    private BroadcastReceiver broadcastReceiver ;

    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "receiver local broadcast", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //监视网络状态广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);

        //发送广播
        broadcastReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.broadcasttest.MY_BROADCAST");
        filter.setPriority(1000);
        registerReceiver(broadcastReceiver, filter);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Intent intent = new Intent();
                intent.setAction("com.example.broadcasttest.MY_BROADCAST");
                sendOrderedBroadcast(intent, null);
            }
        });

        localBroadcastManager = localBroadcastManager.getInstance(this);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("local");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        IntentFilter filter1 = new IntentFilter();
        filter1.addAction("local");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, filter1);

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(networkChangeReceiver);
        unregisterReceiver(broadcastReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
        super.onDestroy();
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

