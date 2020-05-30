package com.example.uiwidgettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;
    private ProgressBar progressBar1;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button_1);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.img1);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar1 = (ProgressBar) findViewById(R.id.progress_bar1);
        button.setOnClickListener(this);
        Button button1 = (Button) findViewById(R.id.delete);
        button1.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.download);
        button2.setOnClickListener(this);
    }

    int progress = 0;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_1:
                String data = editText.getText().toString();
                Toast.makeText(this, "Hello World! " + data, Toast.LENGTH_SHORT).show();
                if (flag) {
                    imageView.setImageResource(R.drawable.img2);
                    progressBar.setVisibility(View.GONE);
                    flag = false;
                } else {
                    imageView.setImageResource(R.drawable.img1);
                    progressBar.setVisibility(View.VISIBLE);
                    flag = true;
                }
                progress = progressBar1.getProgress();
                if (progress != 100) {
                    progress += 10;
                }
                progressBar1.setProgress(progress);
                break;
            case R.id.delete:
                progress = progressBar1.getProgress();
                if (progress != 0) {
                    progress -= 10;
                }
                progressBar1.setProgress(progress);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("This is Dialog");
                dialog.setMessage("Something important.");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;
            case R.id.download:
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}
