package one.example.com.myapplication3.ui.wheelview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.ViewGroup

class MyViewGroup(context: Context?, attrs: AttributeSet) : ViewGroup(context, attrs) {
    var paint = Paint()
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var path = Path()
        path.moveTo((width/2).toFloat(), (height/2).toFloat())

        path.lineTo(1100f, 10f)
        val linearGradient = LinearGradient(
                (width/2).toFloat(), (height/2).toFloat(),
                100f, 10f,
                Color.RED
                , Color.BLUE
                , Shader.TileMode.MIRROR
        )
//        paint.color = Color.RED
        paint.strokeWidth = 1f
        paint.style = Paint.Style.STROKE
        paint.shader = linearGradient
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.FILL
        paint.color = getColorWithAlpha(0.3f,Color.RED)
        canvas.drawCircle((100).toFloat(), (height/2).toFloat(),20f,paint)

        paint.color = getColorWithAlpha(0.5f,Color.RED)
        canvas.drawCircle((200).toFloat(), (height/2).toFloat(),20f,paint)

        paint.color = getColorWithAlpha(0.7f,Color.RED)
        canvas.drawCircle((300).toFloat(), (height/2).toFloat(),20f,paint)

        paint.color = getColorWithAlpha(1f,Color.RED)
        canvas.drawCircle((400).toFloat(), (height/2).toFloat(),20f,paint)
    }

    /**
     * 对rgb色彩加入透明度
     * @param alpha     透明度，取值范围 0.0f -- 1.0f.
     * @param baseColor
     * @return a color with alpha made from base color
     */
    private fun getColorWithAlpha(alpha: Float, baseColor: Int): Int {
        val a = Math.min(255, Math.max(0, (alpha * 255).toInt())) shl 24
        val rgb = 0x00ffffff and baseColor
        return a + rgb
    }
}