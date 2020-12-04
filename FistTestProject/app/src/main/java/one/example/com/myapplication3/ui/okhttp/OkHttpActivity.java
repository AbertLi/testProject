package one.example.com.myapplication3.ui.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.utile.logutile.LogWriteUtile;

/**
 * 用于研究OkHttp
 * 用法
 * 源码
 */
public class OkHttpActivity extends AppCompatActivity {
    private String TAG = "OkHttpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
    }

    public void btn(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                request1();
                break;
            case R.id.btn2:
                request2();
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }


    private void request1() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().method("POST",null).url("www.baidu.com").build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logs.eprintln(TAG + "异步Exception=" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logs.eprintln(TAG + "异步=" + response.body().toString());
            }
        });
    }

    private void request2() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("www.baidu.com").build();
        Call call = client.newCall(request);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Response response = call.execute();
                    Logs.eprintln(TAG + "同步=" + response.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void requestCache() {
        //表示缓存大小为20M
        OkHttpClient client = new OkHttpClient.Builder().cache(new Cache(new File("catch"),20*1024L*1024L)).build();
        Request request = new Request.Builder().url("www.baidu.com").build();
        Call call = client.newCall(request);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Response response = call.execute();
                    Logs.eprintln(TAG + "同步=" + response.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
