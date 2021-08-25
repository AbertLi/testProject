package one.example.com.myapplication3.ui.wheelview

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.R
import one.example.com.myapplication3.ui.wheelview.CircularSeekBar.OnSeekChangeListener
import one.example.com.myapplication3.ui.wheelview.circleprogress.DonutProgress


class WheelViewActivity : Activity() {
//    private var TAG = "WheelViewActivity"
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_wheel_view)
//        var wheelview = findViewById<one.example.com.myapplication3.ui.wheelview.kotlin.WheelView>(R.id.wheelview)
//        var listData = ArrayList<String>()
//        for (i in 1..180) {
//            listData.add("$i")
//        }
//        wheelview.setData(listData)
//        var ll = LinearLayout(this)
//        ll.addView(null)
////        var rl = RelativeLayout(this)
////        rl.addView(null)
//
//
//        var systemClockCurrent = SystemClock.currentThreadTimeMillis()
//        var systemClocUptime = SystemClock.uptimeMillis()
//        var systemTime = System.currentTimeMillis()
//        Logs.iprintln(TAG, "systemClockCurrent = $systemClockCurrent   systemClocUptime = $systemClocUptime   systemTime = $systemTime")
//    }
//
//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        Logs.iprintln(TAG, "dispatchTouchEvent  action even = ${ev?.action}")
//        return super.dispatchTouchEvent(ev)
//    }
//
//    override fun onUserInteraction() {
//        Logs.iprintln(TAG, "onUserInteraction")
//    }

    //原型进度条
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wheel_view)
        var progression = findViewById<DonutProgress>(R.id.donut_progress)
        progression.progress = 30f

    }
}