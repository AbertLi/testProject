package one.example.com.myapplication3.ui.socket

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.databinding.DataBindingUtil
import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.R
import one.example.com.myapplication3.databinding.ActivitySocketBinding
import one.example.com.myapplication3.ui.socket.ende.EncryptionImp
import one.example.com.myapplication3.ui.socket.ende.NumOfCallUtil
import one.example.com.myapplication3.utile.WifiUtil
import java.util.zip.CRC32

class SocketActivity : AppCompatActivity(), IClickListener {
    lateinit var binding: ActivitySocketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_socket)
        binding.onClickListener = this
        SocketManager.getInstance().startNetThread(handler,WifiUtil.getWIFILYIP(this.application),9000)
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            Logs.iprintln("SocketActivity msg = $msg")
        }
    }

    override fun onClick(id: Int) {
        when (id) {
            1 -> {
                var sourceStr = "{\"cmdType\":1101,\"equipNo\":\"00000000\",\"type\":0,\"timeStamp\":1606966212,\"snno\":1,\"len\":84,\"crc\":\"A474C16F\",\"dataArea\":{\"opt\":0,\"minProtocolVer\":\"V1.00.00\"}}"
//                var sendStr = EncryptionDecryptionImp().decode(sourceStr)
                var a = EncryptionImp()
                SocketManager.getInstance().sendData(a.encode())

            }
            2 -> {
//                var ende = EncryptionDecryptionImp()
//                Logs.iprintln("result = ${ende.getEquipNoSUM("00000000").toByte()}")
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
                Logs.iprintln("result = ${String(a.encode())}")
            }
        }
    }
}