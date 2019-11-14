package com.example.chapter2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent1 = getIntent();
        Toast.makeText(this,intent1.getStringExtra("data"),Toast.LENGTH_SHORT).show();

        Button button = (Button) findViewById(R.id.button_2);
        button.setText("百度");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivityForResult(intent,1);
            }
        });
    }

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
        super.onActivityResult(requestCode, resultCode, data);
    }
}
