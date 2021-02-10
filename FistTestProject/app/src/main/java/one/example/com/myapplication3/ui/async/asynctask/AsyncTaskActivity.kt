package one.example.com.myapplication3.ui.async.asynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import one.example.com.myapplication3.R

class AsyncTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_async_task)
        var imageView = findViewById<ImageView>(R.id.imageview)
        findViewById<Button>(R.id.btn1).setOnClickListener {
            var imageUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.08lr.cn%2Fuploads%2Fallimg%2F170513%2F1-1F513164126.jpg&refer=http%3A%2F%2Fwww.08lr.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1615345183&t=c11176a5623e95b8cb0c1c58a5ec8094"
            var asyncTask = MyAsyncTask(MyAsyncTask.IResultCallBack {
                imageView.setImageBitmap(it)
            })

            asyncTask.execute(imageUrl)
//            asyncTask.cancel(true)
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,imageUrl)//可以通过设置执行的形式。串行or并行。
        }

        findViewById<Button>(R.id.btn2).setOnClickListener {

        }

        findViewById<Button>(R.id.btn3).setOnClickListener {

        }
    }
}
