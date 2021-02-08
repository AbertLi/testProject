package one.example.com.myapplication3.ui.async.IntentHandler

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.R

class IntentHandlerActivity : AppCompatActivity(), IntentServerImpl.UpdateUI {
    private val TAG = "IntentServerImpl"
    private lateinit var act:IntentHandlerActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_handler)
        act = this
        var list = ArrayList<String>()
        list.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3795757253,2048194183&fm=26&gp=0.jpg")
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg3.redocn.com%2Ftupian%2F20140910%2Fchangfazhongfenmeinvzhaopian_3014557.jpg&refer=http%3A%2F%2Fimg3.redocn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614682734&t=e24351c88bb4d2f2eafba51bb64e2edc")
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg01.taopic.com%2F161120%2F235110-161120135U733.jpg&refer=http%3A%2F%2Fimg01.taopic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614682734&t=9b2b02b684988874f8645bffa1661f13")
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fattachments.gfan.net.cn%2Fforum%2Fattachments2%2F201301%2F27%2F2025395o1m44co41oxgk9d.jpg&refer=http%3A%2F%2Fattachments.gfan.net.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614682734&t=2414401f88da3e301d62b8bff850f31f")
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fmobile%2F2019-01-30%2F5c511a2c0dc0d.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614682734&t=1a5f5227d674fbcfef7f6958843c33ac")
        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1923476360,102453652&fm=26&gp=0.jpg")
        list.add("https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/a50f4bfbfbedab647331f9dff436afc379311e69.jpg")
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F01.minipic.eastday.com%2F20170207%2F20170207145729_d643e7de3fcaf066fe54e19b3293e074_4.jpeg&refer=http%3A%2F%2F01.minipic.eastday.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614682734&t=1a053aee259a1e9054496b996e1a1798")
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3030325702,1144960403&fm=26&gp=0.jpg")
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.daimg.com%2Fuploads%2Fallimg%2F150124%2F3-150124224208.jpg&refer=http%3A%2F%2Fimg.daimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614682850&t=2ac188c245112357f916b0c70929c9c3")

        findViewById<Button>(R.id.btn_start_download).setOnClickListener {
            Logs.eprintln(TAG," Onclick btn_start_download")
            var intent = Intent(act, IntentServerImpl::class.java)
            for (i in 0 until list.size) {
                Logs.eprintln(TAG," for  index=$i")
                intent.putExtra(IntentServerImpl.DOWNLOAD_URL, list[i])
                intent.putExtra(IntentServerImpl.INDEX_FLAG, i)
                startService(intent)
            }
        }

        findViewById<Button>(R.id.btn_test_Server).setOnClickListener{
            var intent = Intent(act, MyService::class.java)
            startService(intent)
        }
        IntentServerImpl.setUpdateUI(this)
    }

    private val mUIHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            findViewById<ImageView>(R.id.imageview).setImageBitmap(msg?.obj as Bitmap)
        }
    }

    override fun updateUI(message: Message?) {
        Logs.eprintln(TAG,"message.what=${message?.what}  message.obj=${message?.obj}")
        message?.let {
            mUIHandler.sendMessage(message)
        }
    }
}
