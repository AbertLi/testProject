package one.example.com.myapplication3.ui.loadBundle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import one.example.com.myapplication3.R
import one.example.com.myapplication3.databinding.ActivityLoadBundleBinding

class LoadBundleActivity : AppCompatActivity() {
    var binding: ActivityLoadBundleBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@LoadBundleActivity, R.layout.activity_load_bundle)
    }
}
