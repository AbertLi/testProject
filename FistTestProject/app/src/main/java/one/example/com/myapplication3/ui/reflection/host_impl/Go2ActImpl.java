package one.example.com.myapplication3.ui.reflection.host_impl;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import one.example.com.myapplication3.ui.reflection.api.IGo2Act;

public class Go2ActImpl implements IGo2Act {
    @Override
    public void goActivity(Context context, String who, Intent intent) {
        Toast.makeText(context, "who=" + who + "   intent = " + intent, Toast.LENGTH_SHORT).show();
    }
}
