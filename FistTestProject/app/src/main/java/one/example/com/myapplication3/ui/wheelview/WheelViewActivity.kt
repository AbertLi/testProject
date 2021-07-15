package one.example.com.myapplication3.ui.wheelview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import one.example.com.myapplication3.R

class WheelViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wheel_view)
        var wheelview = findViewById<one.example.com.myapplication3.ui.wheelview.kotlin.WheelView>(R.id.wheelview)
        var listData = ArrayList<String>()
        for (i in 1..180) {
            listData.add("$i")
        }
        wheelview.setData(listData)





    }
}