package one.example.com.myapplication3.ui.fresco

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import one.example.com.myapplication3.R

class FrescoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresco)
        Fresco.initialize(this)
    }


    fun btn(view:View){
        val imageUri = "http://pic1.win4000.com/pic/7/00/8e25729063.jpg"
        val uri = Uri.parse(imageUri)
        val draweeView = findViewById<View>(R.id.image) as SimpleDraweeView
        draweeView.setImageURI(uri)
    }
}
