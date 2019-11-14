package com.example.chapter2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class ThirdActivity extends BaseActivity implements View.OnClickListener{

    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent();
        intent2.putExtra("return","Hello First from Third");
        setResult(RESULT_OK,intent2);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button button = (Button) findViewById(R.id.button_3);
        button.setText("call to 10086");
        button.setOnClickListener(this);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);
//            }
//        });
        final Intent intent=getIntent();
        if(intent.getStringExtra("data3") != null) {
            Toast.makeText(this,intent.getStringExtra("data3"),Toast.LENGTH_SHORT).show();
        }
        Button button5 = (Button) findViewById(R.id.button_5);
        button5.setText("goto Before");
        button5.setOnClickListener(this);
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2 = new Intent();
//                intent2.putExtra("return","Hello from Third");
//                setResult(RESULT_OK,intent2);
//                finish();
//            }
//        });
        Button quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(this);
//        quit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ActivityCollector.finishAll();
//                android.os.Process.killProcess(android.os.Process.myPid());
//            }
//        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_3:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
                break;
            case R.id.button_5:
                Intent intent2 = new Intent();
                intent2.putExtra("return","Hello from Third");
                setResult(RESULT_OK,intent2);
                finish();
                break;
            case R.id.quit:
                ActivityCollector.finishAll();
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
                default:
                    break;

        }
    }
}
