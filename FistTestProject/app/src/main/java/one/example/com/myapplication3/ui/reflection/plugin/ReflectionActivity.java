package one.example.com.myapplication3.ui.reflection.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.ui.reflection.api.HostInterface;
import one.example.com.myapplication3.ui.reflection.api.IGo2Act;
import one.example.com.myapplication3.ui.reflection.api.IStat;
import one.example.com.myapplication3.ui.reflection.host_impl.Go2ActImpl;
import one.example.com.myapplication3.ui.reflection.host_impl.StatImpl;
import one.example.com.myapplication3.utile.SPUtils;
import one.example.com.myapplication3.utile.logutile.Log;

/**
 * api 为公用部分
 * host为主工程
 * plugin为下层代码。
 */
public class ReflectionActivity extends AppCompatActivity {
    String TAG = "ReflectionActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HostInterface.getInstance().register(IGo2Act.class, new Go2ActImpl());
        HostInterface.getInstance().register(IStat.class, new StatImpl());
        setContentView(R.layout.activity_reflection);
    }

    long time;
    public void btn(View view) {
        switch (view.getId()) {
            case R.id.btn_go2act:
                HostInterface.getInstance().get(IGo2Act.class).goActivity(this, "ACTIVITY", new Intent());
                break;
            case R.id.btn_stat:
                Map<String, Object> map = new HashMap<>();
                map.put("lalal", "djfalj");
                HostInterface.getInstance().get(IStat.class).stat(this, "STAT", map);
                break;
            case R.id.btn_saveData:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        time = System.currentTimeMillis();
                        for (int i = 0; i < 10000000; i++) {
                            SPUtils.asyncPut(ReflectionActivity.this, "key" + i, i);
                        }
                        Log.e(TAG, "存入SP耗时=" + (System.currentTimeMillis() - time));
                    }
                }.start();
                break;
            case R.id.btn_getData:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        time = System.currentTimeMillis();
                        for (int i = 0; i < 2500; i++) {
                            int sp = (int) SPUtils.get(ReflectionActivity.this, "key" + i, 0);
                            Log.i(TAG, "获取的SP=" + sp);
                        }
                        Log.e(TAG, "读取SP耗时=" + (System.currentTimeMillis() - time));
                    }
                }.start();
                break;
        }
    }

}
