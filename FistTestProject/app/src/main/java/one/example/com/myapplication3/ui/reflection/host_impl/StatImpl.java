package one.example.com.myapplication3.ui.reflection.host_impl;

import android.content.Context;
import android.widget.Toast;

import java.util.Map;

import one.example.com.myapplication3.ui.reflection.api.IStat;

public class StatImpl implements IStat {
    @Override
    public void stat(Context context, String who, Map<String, Object> map) {
        Toast.makeText(context, "who=" + who + "   map = " + map.toString(), Toast.LENGTH_SHORT).show();
    }
}
