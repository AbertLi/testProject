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
import java.util.zip.CRC32

//https://github.com/mrniko/netty-socketio
class SocketActivity : AppCompatActivity(), IClickListener, NettyListener {
    private val TAG = "SocketActivity"

    private var nettyClient: NettyClient? = null  //socket操作连接对象
    lateinit var binding: ActivitySocketBinding
    var en = EncryptionImp()
    var de = DectyptionImp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_socket)
        binding.onClickListener = this
        SocketManager.getInstance().startNetThread(handler, WifiUtil.getWIFILYIP(this.application), 9000)
        initSocketTcp();
    }

    /*
     socket 端口号以及开始连接，配置接口监听
     */
    private fun initSocketTcp() {
        nettyClient = NettyClient(WifiUtil.getWIFILYIP(this.application), Const.TCP_PORT)
        if (!nettyClient?.connectStatus!!) {
            nettyClient?.setListener(this@SocketActivity)
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
            Logs.iprintln(TAG," 解密结果 = $results")
        }
    }

    override fun onClick(id: Int) {
        when (id) {
            1 -> {
                var sourceStr = "{\"cmdType\":1101,\"equipNo\":\"00000000\",\"type\":0,\"timeStamp\":1606966212,\"snno\":1,\"len\":84,\"crc\":\"A474C16F\",\"dataArea\":{\"opt\":0,\"minProtocolVer\":\"V1.00.00\"}}"
//                var sendStr = EncryptionDecryptionImp().decode(sourceStr)
                SocketManager.getInstance().sendData(en.encodeToByteArray())
            }
            2 -> {

            }
            3 -> {
                Logs.iprintln("result = ${NumOfCallUtil.getInstance().getCallNumAdd()}")
            }
            4 -> {
                var crC32 = CRC32()
                crC32.update("{\"opt\":1,\"timezone\":8,\"GPS\":[22.58615,113.9092]}".toByteArray())
                Logs.iprintln("crc32 result = ${crC32.value.toString(16)}")

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
        val ss = String(result1)

        runOnUiThread { Toast.makeText(this@SocketActivity, "接收成功${de.decode(ss.toByteArray())}", Toast.LENGTH_SHORT).show() }
    }

    override fun onServiceStatusConnectChanged(statusCode: Int) {
        runOnUiThread {
            if (statusCode == NettyListener.STATUS_CONNECT_SUCCESS) {
                Log.e(TAG, "STATUS_CONNECT_SUCCESS:")
                if (nettyClient!!.connectStatus) {
                    Toast.makeText(this@SocketActivity, "连接成功", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.e(TAG, "onServiceStatusConnectChanged:$statusCode")
                if (!nettyClient!!.connectStatus) {
                    Toast.makeText(this@SocketActivity, "网路不好，正在重连", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}