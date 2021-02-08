package one.example.com.myapplication3.ui.async.asynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import one.example.com.myapplication3.R

class AsyncTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)
        findViewById<Button>(R.id.btn1).setOnClickListener {

        }

        findViewById<Button>(R.id.btn2).setOnClickListener {

        }

        findViewById<Button>(R.id.btn3).setOnClickListener {

        }
    }
}
