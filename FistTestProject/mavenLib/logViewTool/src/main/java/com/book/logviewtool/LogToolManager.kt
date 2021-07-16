package com.book.logviewtool

import android.content.Context

class LogToolManager {
    class Holder {
        companion object {
            val logToolManager = LogToolManager()
        }
    }

    companion object {
        @JvmStatic
        fun get(): LogToolManager {
            return Holder.logToolManager
        }
    }

    fun start(context: Context) {

    }
}