package com.example.androidthreadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    public static final int UPDATE_TEXT = 1;
    private TextView text;

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if(message.what == UPDATE_TEXT) {
                text.setText("Nice to meet you");
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        Button changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
        Button start_asynctask = (Button) findViewById(R.id.start_asynctask);
        start_asynctask.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.change_text) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: ");
                    Message message = new Message();
                    message.what = UPDATE_TEXT;
                    handler.sendMessage(message);
                }
            }).start();
        }else if(view.getId() == R.id.start_asynctask) {
            new UpdateTextTask().execute();
        }
    }

    class UpdateTextTask extends AsyncTask<Void, Integer, Boolean> {
        //在后台任务开始执行之前调用，做界面上的初始化操作
        @Override
        protected void onPreExecute() {
            text.setText("start");
        }

        //在子线程中运行，通过 return 返回结果，调用 publishProgress 方法更新 UI
        @Override
        protected Boolean doInBackground(Void... voids) {
            int count = 0;
            while(count != 20) {
                publishProgress(++count);
                Log.d(TAG, "doInBackground: " + count);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return true;
        }

        //publishProgress 调用最终会到这个函数里，在这里对 UI 进行操作。
        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG, "onProgressUpdate() called with: values = [" + values[0] + "]");
            text.setText(""+values[0]);
        }

        //当后台任务执行完毕并 return 返回是会被调用，可以进行 UI 操作。
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            text.setText("end");
        }
    }
}
