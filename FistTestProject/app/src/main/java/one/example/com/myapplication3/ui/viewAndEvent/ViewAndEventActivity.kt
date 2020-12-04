package one.example.com.myapplication3.ui.viewAndEvent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import one.example.com.myapplication3.R
import one.example.com.myapplication3.utile.logutile.Log

class ViewAndEventActivity : AppCompatActivity() {
    private val TAG: String = "ViewAndEventActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_and_event)
        findViewById<RelativeLayout>(R.id.View1).setOnClickListener {
            Log.e(TAG, "view = ${it.id}")
        }
    }
}
