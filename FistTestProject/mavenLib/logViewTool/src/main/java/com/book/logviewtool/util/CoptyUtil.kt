package com.book.logviewtool.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.util.Log
import com.book.logviewtool.LogToolManager
import com.example.logviewtool.R
import java.io.*
import java.nio.charset.StandardCharsets
import kotlin.concurrent.thread

object CoptyUtil {
    private var isCopying = false
    fun copyText(context: Context, lineNum: Int, filePath: String) {
        if (!isCopying) {
            isCopying = true
            copyFileText(lineNum, filePath) { targetStr ->
                HandlerUtils.postDelayed(0) {
                    if (TextUtils.isEmpty(targetStr)) {
                        showShort(context.getString(if (targetStr == null)
                            R.string.dev_tool_dir_cant_copy
                        else R.string.dev_tool_content_does_not_exist), context)
                    } else {
                        val cm =
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    context.getSystemService(ClipboardManager::class.java)
                                } else {
                                    context.getSystemService(Context.CLIPBOARD_SERVICE)
                                } as ClipboardManager
                        // 把数据集设置（复制）到剪贴板
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//7.0以上手机才支持大文件的复制
                            val clip: ClipData = ClipData.newPlainText(null, targetStr)
                            cm?.setPrimaryClip(clip)
                        } else {
                            cm?.text = targetStr
                        }
                        showShort(context.getString(R.string.dev_tool_copy_suc), context)
                    }
                    isCopying = false
                }
            }
        } else {
            showShort(context.getString(R.string.dev_tool_copying), context)
        }
    }

    private fun copyFileText(lineNum: Int, filePath: String, callBack: (String?) -> Unit) {
        thread {
            var sb = StringBuilder()
            var file = File(filePath)
            if (!file.exists()) {
                callBack("")
            } else if (file.isDirectory) {
                callBack(null)
            } else {
                var mFis = FileInputStream(file)
                var mReader = BufferedReader(InputStreamReader(mFis, StandardCharsets.UTF_8))
                var allLine = readFileLineNumber(file)
                if (allLine < lineNum) {
                    var readLineNum = 0
                    var line: String? = null
                    while (mReader.readLine().also { line = it } != null) {
                        sb.append(line)
                        sb.append("\n")
                        readLineNum++
                        if (readLineNum > lineNum) {
                            break
                        }
                    }
                } else {
                    var startReadLine = allLine - lineNum
                    var readLineNum = 0
                    var notReadLineNum = 0
                    var line: String? = null
                    while (mReader.readLine().also { line = it } != null) {
                        notReadLineNum++
                        if (notReadLineNum >= startReadLine) {
                            sb.append(line)
                            sb.append("\n")
                            readLineNum++
                            if (readLineNum > lineNum) {
                                break
                            }
                        }
                    }
                }
                callBack(sb.toString())
            }
        }
    }

    private fun readFileLineNumber(file: File): Int {
        var lineNumberReader: LineNumberReader? = null
        try {
            lineNumberReader = LineNumberReader(FileReader(file))
            lineNumberReader.skip(Long.MAX_VALUE)
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("CopyUtil", "readFileLineNumber e =$e")
        }
        //注意加1，实际上是读取换行符，所以需要+1
        return lineNumberReader?.lineNumber ?: 0 + 1
    }

    private fun showShort(str: String, context: Context) {
        LogToolManager.get().getConfig()?.callBack?.callBack(str, context)
    }
}
