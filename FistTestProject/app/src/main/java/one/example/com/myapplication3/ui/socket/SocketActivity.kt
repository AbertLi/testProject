package one.example.com.myapplication3.ui.socket

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.netty.buffer.ByteBuf
import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.R
import one.example.com.myapplication3.databinding.ActivitySocketBinding
import one.example.com.myapplication3.ui.socket.ende.DectyptionImp
import one.example.com.myapplication3.ui.socket.ende.EncryptionImp
import one.example.com.myapplication3.ui.socket.ende.inner.NumOfCallUtil
import one.example.com.myapplication3.ui.socket.socket.Const
import one.example.com.myapplication3.ui.socket.socket.NettyClient
import one.example.com.myapplication3.ui.socket.socket.NettyListener
import one.example.com.myapplication3.utile.WifiUtil

class SocketActivity : AppCompatActivity(), IClickListener, NettyListener {
    private val TAG = "SocketActivity"
    lateinit var binding: ActivitySocketBinding
    private var nettyClient: NettyClient? = null //socket操作连接对象
    var en = EncryptionImp()
    var de = DectyptionImp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_socket)
        binding.onClickListener = this
        SocketManager.getInstance().startNetThread(handler, WifiUtil.getWIFILYIP(this.application), 9000)
        initSocketTcp()
    }

    /*
     socket 端口号以及开始连接，配置接口监听
     */
    private fun initSocketTcp() {
        var host = WifiUtil.getWIFILYIP(this.application)
        nettyClient = NettyClient(host, Const.TCP_PORT)
        if (!nettyClient?.connectStatus!!) {
            nettyClient?.setListener(this)
            nettyClient?.connect()
        } else {
            nettyClient?.disconnect()
        }
    }


    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            Logs.iprintln("SocketActivity msg = $msg")
            var resultByteArray = msg.obj as ByteArray
            var results = de.decode(resultByteArray)
            Logs.iprintln(TAG, " 解密结果 = $results")
        }
    }

    override fun onClick(id: Int) {
        when (id) {
            1 -> {
                SocketManager.getInstance().sendData(en.encodeToByteArray())
            }
            2 -> {
                /*
                    调用的发送。
                     */nettyClient?.sendMsgToServer(en.encodeToByteArray()) { channelFuture ->
                    if (channelFuture.isSuccess) {                //4
                        Log.d(TAG, "Write auth successful")
                    } else {
                        Log.d(TAG, "Write auth error")
                    }
                }
            }
            3 -> {
                Logs.iprintln("result = ${NumOfCallUtil.getInstance().getCallNumAdd()}")
            }
            4 -> {

            }
            5 -> {
                var a = EncryptionImp()
                Logs.iprintln("result = ${String(a.encodeToByteArray())}")
            }
        }
    }

    override fun onMessageResponse(msg: Any?) {
        val result = msg as ByteBuf
        val result1 = ByteArray(result.readableBytes())
        result.readBytes(result1)
        result.release()

        runOnUiThread {
            var resultssss = de.decode(result1)
            Logs.iprintln(TAG, " Netty解密结果 = $resultssss")
            Toast.makeText(this, "接收解密成功$resultssss", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onServiceStatusConnectChanged(statusCode: Int) {
        runOnUiThread {
            if (statusCode == NettyListener.STATUS_CONNECT_SUCCESS) {
                Log.e(TAG, "STATUS_CONNECT_SUCCESS:")
                if (nettyClient?.connectStatus!!) {
                    Toast.makeText(this, "连接成功", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.e(TAG, "onServiceStatusConnectChanged:$statusCode")
                if (!nettyClient?.connectStatus!!) {
                    Toast.makeText(this, "网路不好，正在重连", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}