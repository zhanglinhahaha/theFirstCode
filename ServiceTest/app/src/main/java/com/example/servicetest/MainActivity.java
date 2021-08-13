package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean mIsBound = false;
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (MyService.DownloadBinder) iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) { }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService = (Button) findViewById(R.id.start_service);
        Button stopService = (Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);

        Button bindService = (Button) findViewById(R.id.bind_service);
        Button unbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

        Button startIntentService = (Button) findViewById(R.id.satrt_intent_service);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.start_service) {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        }else if(view.getId() == R.id.stop_service) {
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
        }else if(view.getId() == R.id.bind_service) {
            Intent intent = new Intent(this, MyService.class);
            bindService(intent, connection, BIND_AUTO_CREATE);
            mIsBound = true;
        }else if(view.getId() == R.id.unbind_service) {
            if(mIsBound) {
                unbindService(connection);
                mIsBound = false;
            }
        }else {
            Log.d("thread", "onClick: " + Thread.currentThread().getId());
            Intent intent = new Intent(this, MyInterService.class);
            startService(intent);
        }
    }
}
