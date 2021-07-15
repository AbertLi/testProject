package one.example.com.myapplication3.ui.sensor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import one.example.com.myapplication3.R
import one.example.com.myapplication3.databinding.ActivitySensorBinding

class SensorActivity : AppCompatActivity() {
    lateinit var binding: ActivitySensorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sensor)

    }
}