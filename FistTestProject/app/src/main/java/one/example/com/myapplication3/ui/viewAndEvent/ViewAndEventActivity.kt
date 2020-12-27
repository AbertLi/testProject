package one.example.com.myapplication3.ui.viewAndEvent

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.viewpager.widget.ViewPager
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
        var ll = LinearLayout(this)
        var rl = RelativeLayout(this)
        var frameLayout = FrameLayout(this)
        var gridLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            GridLayout(this)
        } else {
            TODO("VERSION.SDK_INT < ICE_CREAM_SANDWICH")
        }
        var viewPager = ViewPager(this)

        var listView = ListView(this)
//        var window = WindowImpl2(this)
//        var phoneWindow = PhoneWindow()



    }
}
