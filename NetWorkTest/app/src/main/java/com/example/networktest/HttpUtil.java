package com.example.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by zl on 19-10-24.
 */
public class HttpUtil {

    public static void sendRequestWithHttpURLConnection(final String address, final
            HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection =null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);//连接超时
                    connection.setReadTimeout(8000);//读取超时
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
//                  提交数据到服务器
//                  connection.setRequestMethod("POST");
//                  DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//                  out.writeBytes("username=admin&password=123456");
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if(listener != null) {
                        listener.onFinish(response.toString());
                    }
                }catch (Exception e) {
                    if(listener != null) {
                        listener.onError(e);
                    }
                }finally {
                    if(connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }

    public static void sendOKHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public interface HttpCallbackListener {
        void onFinish(String response);
        void onError(Exception e);
    }
}
