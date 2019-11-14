package com.example.chapter2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setText("goto Second");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "Hello SecondActivity from First";
                FirstActivity.actionStart(FirstActivity.this,data);
//                Intent intent1 = new Intent(FirstActivity.this,SecondActivity.class);
//                intent1.putExtra("data",data);
//                Intent intent = new Intent("com.example.chapter2.ACTION_START");
//                intent.addCategory("com.example.chapter2.MY_CATEGORY");
//                startActivity(intent1);
            }
        });
        Button button4 = (Button) findViewById(R.id.button_4);
        button4.setText("goto Third");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this,ThirdActivity.class);
                intent.putExtra("data3","Hello Third from First");
                startActivityForResult(intent,1);
            }
        });
    }

    //活动的启动优化
    public static void actionStart(Context context, String data) {
        Intent intent = new Intent(context,SecondActivity.class);
        intent.putExtra("data",data);
        context.startActivity(intent);
    }

    //返回消息类
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    Toast.makeText(this,data.getStringExtra("return"),Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    //添加菜单功能
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

}
