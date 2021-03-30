package one.example.com.myapplication3.ui.socket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.R
import one.example.com.myapplication3.databinding.ActivitySocketBinding
import one.example.com.myapplication3.ui.socket.ende.EncryptionDecryptionImp
import java.util.zip.CRC32

class SocketActivity : AppCompatActivity(), IClickListener {
    lateinit var binding: ActivitySocketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_socket)
        binding.onClickListener = this
    }

    override fun onClick(id: Int) {
        when (id) {
            1 -> {
                OkSocketManager.getInstance().connect(this.application)
                var sourceStr = "{\"cmdType\":1101,\"equipNo\":\"00000000\",\"type\":0,\"timeStamp\":1606966212,\"snno\":1,\"len\":84,\"crc\":\"A474C16F\",\"dataArea\":{\"opt\":0,\"minProtocolVer\":\"V1.00.00\"}}"
                var sendStr = EncryptionDecryptionImp().decode(sourceStr)
                OkSocketManager.getInstance().sendData(sendStr)
            }
            2 -> {
                var ende = EncryptionDecryptionImp()
                Logs.iprintln("result = ${ende.getEquipNoSUM("00000000").toByte()}")
            }
            3 -> {
            }
            4 -> {
            }
            5 -> {
            }
        }
    }
}