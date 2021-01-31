package one.example.com.myapplication3.ui.handler.handlerthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.R

class HandlerThreadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_thread)
        var downLoadM = DownLoadTaskManager()
        findViewById<Button>(R.id.button1).setOnClickListener {
            downLoadM.download("task1")
            downLoadM.download("task2")
            downLoadM.download("task3")
            downLoadM.download("task4")
            downLoadM.download("task5")
            downLoadM.download("task6")
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            Logs.eprintln("", "DownloadTask get HandlerThread getThreadId " + downLoadM.threadId)
            if (downLoadM.threadId != -1) {
                downLoadM.quit()
            }
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            Logs.eprintln("", "DownloadTask get HandlerThread getThreadId " + downLoadM.threadId)
        }
    }
}
