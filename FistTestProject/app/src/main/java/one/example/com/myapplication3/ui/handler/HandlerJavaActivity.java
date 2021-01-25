package one.example.com.myapplication3.ui.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;

public class HandlerJavaActivity extends AppCompatActivity {
    private static String TAG = "HandlerJavaActivity   ";
    private static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_java);
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    private static Handler handler = new Handler() {
        @SuppressLint(value = "HandlerLeak")//表示有可能出现内存泄漏
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            index++;
            Logs.iprintln(TAG, "   handlerMessage index = " + index);
            handler.sendEmptyMessageDelayed(1, 1000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handler2.sendEmptyMessage(1);
    }



    private static Handler handler2 = new Handler() {
        @SuppressLint(value = "HandlerLeak")//表示有可能出现内存泄漏
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Logs.iprintln(TAG, "   handlerMessage index = " + index);
        }
    };
}
