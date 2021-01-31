package one.example.com.myapplication3.ui.handler.IntentHandler

import android.app.Service
import android.content.Intent
import android.os.IBinder
import one.example.com.myapplication3.Logs

class MyService : Service() {
    private var TAG = "MyService"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logs.eprintln(TAG,"onStartCommand")
        return START_REDELIVER_INTENT
    }

    override fun onCreate() {
        Logs.eprintln(TAG,"onCreate")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Logs.eprintln(TAG,"onStart")
    }

    override fun onDestroy() {
        Logs.eprintln(TAG,"onDestroy")
    }

    override fun onBind(intent: Intent): IBinder? {
        Logs.eprintln(TAG,"onBind")
        return null
    }
}
