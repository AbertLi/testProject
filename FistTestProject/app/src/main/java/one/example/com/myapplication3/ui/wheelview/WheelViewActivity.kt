package one.example.com.myapplication3.ui.wheelview

import android.app.Activity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import one.example.com.myapplication3.R


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
//        var progression = findViewById<DonutProgress>(R.id.donut_progress)
//        progression.progress = 30f
//
//        var halfRing = findViewById<HalfRingProgress>(R.id.halfRing)
//        halfRing.progress = 30
//
//        halfRing.setOnProgressChangeListener(object : HalfRingProgress.OnProgressChangeListener {
//            override fun onProgressChanged(seekBar: HalfRingProgress?, progress: Int, isUser: Boolean) {
//            }
//
//            override fun onStartTrackingTouch(seekBar: HalfRingProgress?) {
//            }
//
//            override fun onStopTrackingTouch(seekBar: HalfRingProgress?) {
//            }
//        })

        var viewPager1 = findViewById<ViewPager>(R.id.viewPager1)
        var adapter1 = ViewPager1Adapter(this)
        viewPager1.adapter = adapter1
        var array = ArrayList<String>()
        for (i in 0..10) {
            array.add("$i")
        }
        adapter1.setDate(array)
        viewPager1.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })






        var viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

            }
        })
        val list: MutableList<String> = ArrayList()
        list.add("页面一")
        list.add("页面二")
        list.add("页面三")
        list.add("页面四")
        viewPager2.adapter = ViewPager2Adapter(this, list, viewPager2)

    }
}