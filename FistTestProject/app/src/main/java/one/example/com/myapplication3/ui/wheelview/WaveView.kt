package one.example.com.myapplication3.ui.wheelview

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import kotlin.properties.Delegates

/**
 * 水波曲线
 */
class WaveView(context: Context, attributeSet: AttributeSet? = null) : View(context, attributeSet) {

    companion object {
        const val RESUME = 0x1
        const val STOP = 0x2
        const val DESTROY = 0x3
    }

    private var mWidth = 0 //控件整体宽度
    private var mHeight = 0 //控件整体高度

    //控件中心位置,x,y坐标
    private var centerX = 0
    private var centerY = 0

    private var innerRadius = 242f//内部圆圈的半径

    private var fWaveShader: LinearGradient? = null
    private var sWaveShader: LinearGradient? = null

    private var wavePath = Path()
    private var waveCirclePath = Path()
    private val waveNum = 2

    private val circle2Color by lazy {
        Color.parseColor("#FF246BFE")
    }

    private val lineColor by lazy {
        Color.parseColor("#26FFFFFF")
    }


    //波浪的渐变颜色数组
    private val waveColors by lazy {
        arrayListOf(
                intArrayOf(
                        Color.parseColor("#FF0F55E6"),
                        Color.parseColor("#FF1E64E9"),
                        Color.parseColor("#FF6EB1F5")
                ),
                intArrayOf(
                        Color.parseColor("#FF0F55E6"),
                        Color.parseColor("#FF1E64E9"),
                        Color.parseColor("#FF66AAF4")
                )
        )
    }

    private val wavePaint by lazy {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.strokeWidth = 1f
        paint
    }

    //波浪高度比例
    private var waveWaterLevelRatio = 0f

    //最低备电波浪高度比例
    private var socReservedRatio = 0f

    //波浪的振幅
    private var waveAmplitude = 0f

    //波浪最大振幅高度
    private var maxWaveAmplitude = 0f

    private val bgCirclePaint by lazy {
        val paint = Paint()
        paint.color = Color.parseColor("#FF558AD5")//绘制圆背景色
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        paint
    }

    private val textPaint by lazy {
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
        paint.isFakeBoldText = true
        paint.isAntiAlias = true
        paint
    }

    fun setSoc(soc: Int, socReserved: Int) {
        percent = soc
        mSocReserved = socReserved
        socReservedRatio = socReserved / 100f
    }

    private var mSocReserved = 0

    //进度 0-100
    private var percent = 0
        set(value) {
            field = value
            waveWaterLevelRatio = value / 100f
            //y = -4 * x2 + 4x抛物线计算振幅，水波纹振幅规律更加真实
            waveAmplitude =
                    (-4 * (waveWaterLevelRatio * waveWaterLevelRatio) + 4 * waveWaterLevelRatio) * maxWaveAmplitude
            fWaveShader = LinearGradient(
                    0f, mHeight.toFloat(), 0f, mHeight * (1 - waveWaterLevelRatio),
                    waveColors[0],
                    null, Shader.TileMode.CLAMP
            )
            sWaveShader = LinearGradient(
                    0f, mHeight.toFloat(), 0f, mHeight * (1 - waveWaterLevelRatio),
                    waveColors[1],
                    null, Shader.TileMode.CLAMP
            )
            invalidate()
        }


    //文本的字体大小
    private var progressTextSize = 24f
    private var progressTextColor = Color.WHITE
    private val progressTextMarginY = dip2pxF(36f)

    private var socTextColor = Color.WHITE
    private var socTextSize = 15f
    private var socText = "SOC"
    private var socReservedText = "SOC Reserved"
    private val socTextMarginY = dip2pxF(12f)
    private val socTextX = dip2pxF(14f)
    private var lineStrokeWidth = dip2pxF(1f)//LineWidth
    private var socCircleRadius = dip2pxF(4.5f)//圆的半径1
    private var socCircleRadius2 = dip2pxF(2.5f) //圆的半径2

    //soc 圆圈中心  soc Reserved 圆圈中心Margin
    private var socCircleX = dip2pxF(19.5f)


    private var fAnimatedValue = 0f
    private var sAnimatedValue = 0f

    //动画
    private val fValueAnimator by lazy {
        val valueAnimator = ValueAnimator()
        valueAnimator.duration = 1500
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.setFloatValues(0f, waveWidth)
        valueAnimator.addUpdateListener { animation ->
            fAnimatedValue = animation.animatedValue as Float
            invalidate()
        }
        valueAnimator
    }
    private val sValueAnimator by lazy {
        val valueAnimator = ValueAnimator()
        valueAnimator.duration = 2000
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.setFloatValues(0f, waveWidth)
        valueAnimator.addUpdateListener { animation ->
            sAnimatedValue = animation.animatedValue as Float
            invalidate()
        }
        valueAnimator
    }

    //一小段完整波浪的宽度
    private var waveWidth = 1f

    var lifeDelegate by Delegates.observable(0) { _, old, new ->
        when (new) {
            RESUME -> onResume()
            STOP -> onPause()
            DESTROY -> onDestroy()
        }
    }


    //设置SOC文本的字体大小
    fun setSocSize(size: Float) {
        socTextSize = size
        invalidate()
    }

    fun setSocColor(color: Int) {
        socTextColor = color
        invalidate()
    }

    fun setSocText(text: String, text2: String) {
        socText = text
        socReservedText = text2
        invalidate()
    }

    //设置中间提示文本的字体大小
    fun setProgressTextSize(size: Float) {
        progressTextSize = size
        invalidate()
    }

    //设置progress文本颜色
    fun setProgressTextColor(color: Int) {
        progressTextColor = color
        invalidate()
    }

    //设置内圆半径
    fun setInnerRadius(radius: Float) {
        innerRadius = radius
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = width - paddingStart - paddingEnd
        mHeight = height - paddingTop - paddingBottom
        centerX = mWidth / 2
        centerY = mHeight / 2
        //waveWidth = mWidth * 1.8f
        waveWidth = innerRadius * 2 * 1.8f//波浪宽度设置为内圆直径的1.8倍宽度
        //maxWaveAmplitude = mHeight * 0.15f
        maxWaveAmplitude = innerRadius * 2 * 0.15f
    }

    private fun onResume() {
        if (fValueAnimator.isStarted) {
            animatorResume()
        } else {
            fValueAnimator.start()
            sValueAnimator.start()
        }
    }

    private fun animatorResume() {
        if (fValueAnimator.isPaused || !fValueAnimator.isRunning) {
            fValueAnimator.resume()
        }
        if (sValueAnimator.isPaused || !sValueAnimator.isRunning) {
            sValueAnimator.resume()
        }
    }

    private fun onPause() {
        if (fValueAnimator.isRunning) {
            fValueAnimator.pause()
        }
        if (sValueAnimator.isRunning) {
            sValueAnimator.pause()
        }
    }

    private fun onDestroy() {
        fValueAnimator.cancel()
        sValueAnimator.cancel()
    }


    override fun onDraw(canvas: Canvas) {
        drawCircle(canvas)
        drawWave(canvas)
        drawText(canvas)
        drawMarking(canvas)
    }

    private fun drawWave(canvas: Canvas) {
        //波浪当前高度= 百分比*直径+空白区域高度
        var emptyAreaHeight = centerY - innerRadius
        var emptyAreaWidth = centerX - innerRadius

        val level = (1 - waveWaterLevelRatio) * innerRadius * 2 + emptyAreaHeight
        //绘制所有波浪
        for (num in 0 until waveNum) {
            //重置path
            wavePath.reset()
            waveCirclePath.reset()
            var startX = if (num == 0) {//第一条波浪的起始位置
                wavePath.moveTo(-waveWidth * fAnimatedValue + emptyAreaWidth, level)
                -waveWidth * fAnimatedValue + emptyAreaWidth
            } else {//第二条波浪的起始位置
                wavePath.moveTo(-waveWidth * sAnimatedValue + emptyAreaWidth, level)
                -waveWidth * sAnimatedValue + emptyAreaWidth
            }
            while (startX < 2 * innerRadius + waveWidth - emptyAreaWidth) {
                wavePath.quadTo(
                        startX + waveWidth / 4,
                        level + waveAmplitude,
                        startX + waveWidth / 2,
                        level
                )
                wavePath.quadTo(
                        startX + waveWidth / 4 * 3,
                        level - waveAmplitude,
                        startX + waveWidth,
                        level
                )
                startX += waveWidth
            }
            wavePath.lineTo(startX, mHeight.toFloat())
            wavePath.lineTo(0f, mHeight.toFloat())
            wavePath.close()

            waveCirclePath.addCircle(
                    centerX.toFloat(),
                    centerY.toFloat(),
                    innerRadius,
                    Path.Direction.CCW
            )
            waveCirclePath.op(wavePath, Path.Op.INTERSECT)
            //绘制波浪渐变色
            wavePaint.shader = if (num == 0) {
                sWaveShader
            } else {
                fWaveShader
            }
            canvas.drawPath(waveCirclePath, wavePaint)
        }
    }

    private fun drawText(canvas: Canvas) {
        //绘制进度文字
        textPaint.isFakeBoldText = true
        textPaint.setTextSizeInternal(context, TypedValue.COMPLEX_UNIT_SP, progressTextSize)
        textPaint.color = progressTextColor
        canvas.drawText(
                "$percent%",
                centerX.toFloat(),
                centerY - progressTextMarginY,
                textPaint
        )
    }

    private fun drawCircle(canvas: Canvas) {
        //绘制球形背景色
        canvas.drawCircle(
                centerX.toFloat(),
                centerY.toFloat(),
                innerRadius,
                bgCirclePaint
        )
    }

    private fun drawMarking(canvas: Canvas) {
        //水波高度Y值
        var socCircleY = centerY + innerRadius - waveWaterLevelRatio * innerRadius * 2
        var socTextY = socCircleY - socTextMarginY
        //SOC Reserved的Y值
        var socReservedCircleY = centerY + innerRadius - socReservedRatio * innerRadius * 2
        var socReservedTextY = socReservedCircleY + socTextMarginY
        //绘制"SOC"字样
        textPaint.textAlign = Paint.Align.LEFT
        textPaint.isFakeBoldText = false
        textPaint.setTextSizeInternal(context, TypedValue.COMPLEX_UNIT_SP, socTextSize)
        textPaint.color = socTextColor
        canvas.drawText(
                socText,
                socTextX,
                socTextY,
                textPaint
        )
        //soc 直线
        textPaint.color = lineColor
        textPaint.strokeWidth = lineStrokeWidth
        canvas.drawLine(socCircleX, socCircleY, centerX.toFloat(), socCircleY, textPaint)

        //soc Reserved 直线
        canvas.drawLine(
                centerX.toFloat(),
                socReservedCircleY,
                mWidth - socCircleX,
                socReservedCircleY,
                textPaint
        )

        //SOC圆圈1
        textPaint.color = Color.WHITE
        canvas.drawCircle(
                socCircleX,
                socCircleY,
                socCircleRadius,
                textPaint
        )
        //SOC圆圈2
        textPaint.color = circle2Color
        canvas.drawCircle(
                socCircleX,
                socCircleY,
                socCircleRadius2,
                textPaint
        )
        //绘制"SOC Reserved"字样
        val rectValue = Rect()
        textPaint.getTextBounds(
                socReservedText,
                0,
                socReservedText.length,
                rectValue
        )
        textPaint.textAlign = Paint.Align.RIGHT
        textPaint.isFakeBoldText = false
        textPaint.setTextSizeInternal(context, TypedValue.COMPLEX_UNIT_SP, socTextSize)
        textPaint.color = socTextColor
        canvas.drawText(
                socReservedText,
                mWidth - socTextX,
                socReservedTextY + rectValue.height(),
                textPaint
        )


        //SOC Reserved圆圈1
        textPaint.color = Color.WHITE
        canvas.drawCircle(
                mWidth - socCircleX,
                socReservedCircleY,
                socCircleRadius,
                textPaint
        )
        //SOC Reserved圆圈2
        textPaint.color = circle2Color
        canvas.drawCircle(
                mWidth - socCircleX,
                socReservedCircleY,
                socCircleRadius2,
                textPaint
        )
        textPaint.textAlign = Paint.Align.CENTER
    }

    private fun dip2px(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }

    private fun dip2pxF(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
    }
}
/**
 * 设置字体大小
 */
fun Paint.setTextSizeInternal(context: Context,unit: Int, size: Float) {
    val c = context
    val r: Resources
    r = if (c == null) {
        Resources.getSystem()
    } else {
        c.resources
    }
    textSize = TypedValue.applyDimension(unit, size, r.displayMetrics)
}