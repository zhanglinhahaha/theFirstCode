package com.example.weather;

import android.app.Application;
import android.content.Context;
import org.litepal.LitePalApplication;

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LitePalApplication.initialize(mContext);
    }

    public static Context getContext() {
        return mContext;
    }
}
