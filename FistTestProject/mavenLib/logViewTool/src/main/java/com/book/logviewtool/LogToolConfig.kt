package com.book.logviewtool

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.book.logviewtool.interfaces.IToastCallBack

class LogToolConfig(val callBack: IToastCallBack,//Toast
                    val copyLineNum: Int) { //拷贝的行数

    companion object {
        @JvmStatic
        fun getDefault(): LogToolConfig {
            return LogToolConfig(object : IToastCallBack {
                override fun callBack(str: String, context: Context) {
                    Log.e("Toast", "str = $str")
                    Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
                }
            }, 10000)
        }
    }
}