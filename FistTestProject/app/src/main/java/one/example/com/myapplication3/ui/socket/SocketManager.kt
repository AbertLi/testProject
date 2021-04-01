package one.example.com.myapplication3.ui.socket

import android.os.Handler
import android.os.Message
import one.example.com.myapplication3.Logs
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.nio.charset.Charset


class SocketManager {
    private constructor() {
    }

    companion object {
        private val TAG = "OkSocketManager"
        private var mSocketManager = SingleHolder.socketManager
        fun getInstance(): SocketManager {
            return mSocketManager
        }
    }

    object SingleHolder {
        var socketManager = SocketManager()
    }

    var socket = Socket()
    private val HANDLER_MSG_TELL_RECV = 0x124
    private var outputStream: OutputStream? = null

    /**
     * 连接服务器并且监听
     *
     * @param handler
     * @param host
     * @param port
     */
    fun startNetThread(handler: Handler, host: String?, port: Int) {
        object : Thread() {
            override fun run() {
                try {
                    socket.connect(InetSocketAddress(host, port), 3000)
                    val inputStream: InputStream = socket.getInputStream()
                    val bytes = ByteArray(2048)
                    var n: Int = inputStream.read(bytes)
                    while (true) {
                        Logs.iprintln(TAG," 获取的内容 len = $n =${String(bytes, 0, n)}\n " +
                                " ${Logs.iprintln("返回结果中的byte=${BytesHexStrTranslate.bytesToHexFun3(bytes)}")}" )
                        val msg: Message = handler.obtainMessage(HANDLER_MSG_TELL_RECV, String(bytes, 0, n))
                        var byteArray = ByteArray(n)
                        saveByteArray(bytes,byteArray)
                        msg.obj = byteArray
                        msg.sendToTarget()
                        n = inputStream.read(bytes)
                    }
                } catch (e: Exception) {
                    Logs.eprintln("SocketManager excepting e=${e.toString()}")
                }
            }
        }.start()
    }

    fun saveByteArray(bytes: ByteArray, byteArray: ByteArray) {
        for (i in byteArray.indices) {
            byteArray[i] = bytes[i]
        }
    }

    /**
     * 异步发送信息
     *
     * @param data
     */
    fun sendData(data: ByteArray) {
        Logs.iprintln(TAG,"sendData = ${data.toString(Charset.defaultCharset())}")
        object : Thread() {
            override fun run() {
                try {
                    outputStream = socket.getOutputStream()
                    outputStream?.write(data)
                    outputStream?.flush()
                } catch (e: Exception) {
                }
            }
        }.start()
    }

    /**
     * 断开连接
     */
    fun closeSocket() {
        try {
            socket.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}