package com.example.playaudiotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String musicName = "say.mp3";
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.music_name);
        Button play = (Button) findViewById(R.id.play);
        Button stop = (Button) findViewById(R.id.stop);
        Button pause = (Button) findViewById(R.id.pause);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        pause.setOnClickListener(this);
        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(this);

        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else {
            initMediaPlayer();
        }
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "");
            String filePath = file.getPath() + "/" + musicName;
            Log.d(TAG, "initMediaPlayer: "+ filePath);
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if(grantResults.length > 0 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                }else {
                    Toast.makeText(this, "U denied permission",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    Log.d(TAG, "onClick: play");
                }
                break;
            case R.id.pause:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    Log.d(TAG, "onClick: pause");
                }
                break;
            case R.id.stop:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    Log.d(TAG, "onClick: stop");
                    initMediaPlayer();
                }
                break;
            case R.id.ok:
                musicName = name.getText().toString();
                musicName = musicName + ".mp3";
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayer();
                    mediaPlayer.start();
                }
                Log.d(TAG, "onClick: " + musicName);
                break;
                default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
