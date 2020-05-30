package com.example.uilayouttest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by zl on 19-10-15.
 */
public class TitleLayout extends LinearLayout implements View.OnClickListener{

    private static final String TAG = "TitleLayout";

    public TitleLayout (Context context, AttributeSet attrs) {
        super(context,attrs);
        //动态加载一个布局文件R.layout.title，将加载文件的父布局指定为TitleLayout
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button titleBack = (Button) findViewById(R.id.title_back);
        Button titleEdit = (Button) findViewById(R.id.title_edit);
        titleBack.setOnClickListener(this);
        titleEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                Log.d(TAG, "onClick: back");
                break;
            case R.id.title_edit:
                Log.d(TAG, "onClick: edit");
                break;
                default:break;
        }
    }
}
