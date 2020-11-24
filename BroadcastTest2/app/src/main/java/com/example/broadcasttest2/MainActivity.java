package com.example.broadcasttest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private AnotherBroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastReceiver = new AnotherBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.broadcasttest.MY_BROADCAST");
        filter.setPriority(100);
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
