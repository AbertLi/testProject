package one.example.com.myapplication3.ui.viewAndEvent;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import one.example.com.myapplication3.R;

/**
 * 事件拦截与分发。
 */
public class ViewAndEventJavaActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_event_java);
    }
}
