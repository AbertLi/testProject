package one.example.com.myapplication3.ui.wheelview

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.RelativeLayout
import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.R

class WheelViewActivity : Activity() {
    private var TAG = "WheelViewActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wheel_view)
        var wheelview = findViewById<one.example.com.myapplication3.ui.wheelview.kotlin.WheelView>(R.id.wheelview)
        var listData = ArrayList<String>()
        for (i in 1..180) {
            listData.add("$i")
        }
        wheelview.setData(listData)
        var ll = LinearLayout(this)
        ll.addView(null)
//        var rl = RelativeLayout(this)
//        rl.addView(null)


        var systemClockCurrent = SystemClock.currentThreadTimeMillis()
        var systemClocUptime = SystemClock.uptimeMillis()
        var systemTime = System.currentTimeMillis()
        Logs.iprintln(TAG, "systemClockCurrent = $systemClockCurrent   systemClocUptime = $systemClocUptime   systemTime = $systemTime")
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Logs.iprintln(TAG, "dispatchTouchEvent  action even = ${ev?.action}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onUserInteraction() {
        Logs.iprintln(TAG, "onUserInteraction")
    }
}