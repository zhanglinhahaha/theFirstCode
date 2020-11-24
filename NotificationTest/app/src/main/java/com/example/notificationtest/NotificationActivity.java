package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
//        关闭通知的另外一种方式,cancel(id),id为创建通知时的id
//        NotificationManager manager = (NotificationManager)
//                getSystemService(NOTIFICATION_SERVICE);
//        manager.cancel(1);
    }
}
