package com.book.logviewtool

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.book.logviewtool.mvvmunit.folder.FolderActivity

class LogToolManager {
    private constructor() {}

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

    private var mConfig: LogToolConfig = LogToolConfig.getDefault()

    fun getConfig(): LogToolConfig {
        return mConfig
    }

    fun init(config: LogToolConfig) {
        this.mConfig = config
    }

    fun start(context: Context, path: String = "") {
        var bundle = Bundle()
        bundle.putString("path", path)
        var intent = Intent(context, FolderActivity::class.java)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }
}