package com.book.logviewtool.mvvmunit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.logviewtool.R
import com.example.logviewtool.databinding.ActivityLogDetailBinding

class LogDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_detail)
    }
}