package com.example.notificationtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_notice:
                NotificationManager manager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                NotificationChannel mChannel = new NotificationChannel(
                        "id", "name", NotificationManager.IMPORTANCE_LOW);
                manager.createNotificationChannel(mChannel);

                //添加点击事件
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this,
                        0, intent, 0);

                Notification notification = new NotificationCompat.Builder(this, mChannel.getId())
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(
                                getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent) //点击事件
                        .setAutoCancel(true) //点击时通知消失
//                      设置通知的重要程度
                        .setPriority(NotificationCompat.PRIORITY_MAX)
//                      设置通知音频
//                        .setSound(Uri.fromFile(new File("绝对路径")))
//                      设置手机振动 数组下标为0 2 4 ...表示静止时间ms 下标为1 3 5...表示振动时间
//                      例子表示为收到通知立刻振动1s静止1s然后再振动1s
//                      控制手机振动需要声明权限
//                        .setVibrate(new long[]{0, 1000, 1000, 1000})
//                      设置LED灯,颜色 亮起的时长 暗去的时长
//                        .setLights(Color.GREEN, 1000, 1000)
//                      直接使用通知的默认效果
//                        .setDefaults(NotificationCompat.DEFAULT_ALL)
//                      设置通知长文本
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText(
//                                "singleTask模式 每次该活动启动会检查返回栈中是否存在，如果存在，则会将直接使用改实例，并将这个活动之上的所有活动全部出栈，如果不存在则创建新活动"))
//                      设置通知大图片
//                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
//                                BitmapFactory.decodeResource(getResources(), R.drawable.say)))
                        .build();
                manager.notify(1, notification);
                Log.d("TAG", "onClick: ");
                break;
                default:break;
        }
    }
}
