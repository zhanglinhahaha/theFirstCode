package com.example.weather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by zl on 19-10-25.
 */
public class HttpUtil {

    private static final String TAG = "ZLTEST";

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        LogUtil.d(TAG, "request success");
        client.newCall(request).enqueue(callback);
    }
}
