package one.example.com.myapplication3.ui.retrofit

import android.app.Activity
import android.os.Bundle
import android.view.View
import one.example.com.myapplication3.R
import one.example.com.myapplication3.ui.retrofit.eg.NetRequestRetrofitUtil.netRequestRetrofit

class RetrofitActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_test)
    }

    fun btn(view: View) {
        when (view.id) {
            R.id.btn1 -> {
                netRequestRetrofit();
            }

            R.id.btn2 -> {

            }

            R.id.btn3 -> {
            }

            R.id.btn4 -> {
            }

            R.id.btn5 -> {
            }
        }
    }
}