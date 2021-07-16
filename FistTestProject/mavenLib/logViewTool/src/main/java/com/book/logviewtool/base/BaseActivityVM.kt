package com.book.logviewtool.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.book.logviewtool.interfaces.IBackListener
import com.book.logviewtool.util.DensityUtil
import java.lang.reflect.ParameterizedType

abstract class BaseActivityVM<VM : ViewModel> : AppCompatActivity(),
        IBackListener {
    companion object {
        private var TAG = "BaselActivity"
    }

    open val mViewModel: VM by lazy(mode = LazyThreadSafetyMode.NONE) {
        val vmClass: Class<VM> =
                (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        ViewModelProvider(this).get(vmClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DensityUtil.setCustomDensity(this,this.application)
        onCreateView()
        onCreateView(savedInstanceState)
    }

    open fun onCreateView() {

    }

    open fun onCreateView(savedInstanceState: Bundle?) {

    }


    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun Activity.jumpToActivity(
            clz: Class<*>,
            bundle: Bundle? = null,
            vararg pairs: Pair<View, String>
    ) {

        Log.d(TAG, "context is Activity")
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, *pairs
        )
        val intent1 = Intent(this, clz)
        bundle?.let { intent1.putExtras(bundle) }
        if (pairs.size != 0) {
            this.startActivity(
                    intent1,
                    options.toBundle()
            )
        } else {
            this.startActivity(
                    intent1
            )
        }

    }

    override fun back() {
        (this@BaseActivityVM).finish()
    }
}