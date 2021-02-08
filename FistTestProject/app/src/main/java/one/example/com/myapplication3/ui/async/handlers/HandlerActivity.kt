package one.example.com.myapplication3.ui.async.handlers

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.R

class HandlerActivity : AppCompatActivity {
    private var TAG = "HandlerActivity   "
    private lateinit var handler: Handler
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
        handler.sendEmptyMessage(1)
    }

    constructor() {
        @SuppressLint("HandlerLeak")
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                index++
                handler.sendEmptyMessageDelayed(1, 2000)
                Logs.iprintln(TAG, "   handlerMessage  index = $index");
            }
        }
    }
}
