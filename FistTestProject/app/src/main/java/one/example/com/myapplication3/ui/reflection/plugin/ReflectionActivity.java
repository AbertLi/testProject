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

/**
 * api 为公用部分
 * host为主工程
 * plugin为下层代码。
 */
public class ReflectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HostInterface.getInstance().register(IGo2Act.class, new Go2ActImpl());
        HostInterface.getInstance().register(IStat.class, new StatImpl());
        setContentView(R.layout.activity_reflection);
    }


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
        }
    }

}
