package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedOutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button okHttpSendRequest = (Button) findViewById(R.id.okHttp_send_request);
        Button httpUrlSendRequest = (Button) findViewById(R.id.httpUrlConnection_send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        okHttpSendRequest.setOnClickListener(this);
        httpUrlSendRequest.setOnClickListener(this);

        Button xmlPull = (Button) findViewById(R.id.xml_pull);
        xmlPull.setOnClickListener(this);
        Button xmlSAX = (Button) findViewById(R.id.xml_sax);
        xmlSAX.setOnClickListener(this);

        Button json_gson = (Button) findViewById(R.id.json_gson);
        json_gson.setOnClickListener(this);
        Button json_josnobject = (Button) findViewById(R.id.json_josnobject);
        json_josnobject.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        String address = "https://www.baidu.com";
        String result = null;
        switch (view.getId()) {
            case R.id.okHttp_send_request:
                HttpUtil.sendOKHttpRequest(address, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) { }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        showResponse(responseData);
                    }
                });
                break;
            case R.id.httpUrlConnection_send_request:
                HttpUtil.sendRequestWithHttpURLConnection(address, new HttpUtil.HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        showResponse(response);
                    }

                    @Override
                    public void onError(Exception e) { }
                });
                break;
            case R.id.xml_pull:
            case R.id.xml_sax:
                try {
                    InputStream is = getAssets().open("test.xml");
                    int length = is.available();
                    byte[]  buffer = new byte[length];
                    is.read(buffer);
                    result =  new String(buffer, "utf8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(view.getId() == R.id.xml_pull) {
                    parseXMLWithPull(result);
                }else {
                    parseXMLWithSAX(result);
                }
                break;
            case R.id.json_gson:
            case R.id.json_josnobject:
                try {
                    InputStream is = getAssets().open("test.json");
                    int length = is.available();
                    byte[]  buffer = new byte[length];
                    is.read(buffer);
                    result =  new String(buffer, "utf8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(view.getId() == R.id.json_josnobject) {
                    parseJSONWithJSONObject(result);
                }else {
                    parseJSONWithGSON(result);
                }
                break;
            default:
                Log.d(TAG, "onClick: Error");
                break;
        }
    }

    private void sendRequestWithOKHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
//                    JSON的JSONObject解析
//                    parseJSONWithJSONObject(responseData);
//                    JSON的GSON解析
//                    parseJSONWithGSON(responseData);


//                    XML的SAX解析
//                    parseXMLWithSAX(responseData);

//                    XML的PULL解析
//                    parseXMLWithPull(responseData);

//                    POST方法
//                    RequestBody requestBody = new FormBody.Builder()
//                            .add("username", "admin")
//                            .add("password", "123456")
//                            .build();
//                    Request request1 = new Request.Builder()
//                            .url("https://www.baidu.com")
//                            .post(requestBody)
//                            .build();
//                    Response response1 = client.newCall(request).execute();

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection =null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);//连接超时
                    connection.setReadTimeout(8000);//读取超时
//                    提交数据到服务器
//                    connection.setRequestMethod("POST");
//                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//                    out.writeBytes("username=admin&password=123456");


                    InputStream in = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(reader != null) {
                        try {
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        // Android 不允许子线程中进行 UI 操作，需要利用这个 runOnUiThread 将线程切换到主线程。
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }

    private void parseXMLWithSAX(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler(new ContentHandler.SAXListener() {
                @Override
                public void onEndDocument(String res) {
                    showResponse(res);
                }
            });
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWithPull(String xmlData) {
        Log.d(TAG, "parseXMLWithPull() called with: xmlData = [" + xmlData + "]");
        StringBuilder res = new StringBuilder();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        }else if("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        }else if("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if("app".equals(nodeName)) {
                            Log.d(TAG, "parseXMLWithPull: " + id);
                            String item = id + name + version + "\n";
                            res.append(item);
                        }
                        break;
                    default:break;
                }
                eventType = xmlPullParser.next();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        showResponse(res.toString());
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String version = jsonObject.getString("version");
                String name = jsonObject.getString("name");
                res.append(id);
                res.append(version);
                res.append(name);
                res.append("\n");
            }
            showResponse(res.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>()
        {}.getType());
        StringBuilder res = new StringBuilder();
        for(App app : appList) {
            res.append(app);
            res.append("\n");
        }
        showResponse(res.toString());
    }
}
