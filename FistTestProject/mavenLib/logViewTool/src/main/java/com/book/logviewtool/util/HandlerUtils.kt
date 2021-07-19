package com.book.logviewtool.util

import android.os.Handler
import android.os.Looper

object HandlerUtils {
    private var handler = Handler(Looper.getMainLooper())
    fun postDelayed(delayMillis: Long, callback: () -> Unit) {
        handler.postDelayed({
            callback()
        }, delayMillis)
    }
}