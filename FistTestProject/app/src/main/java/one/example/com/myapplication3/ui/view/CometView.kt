package one.example.com.myapplication3.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CometView : View {
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attributeSet, defStyleAttr)

    constructor(
        context: Context,
        attributeSet: AttributeSet
    ) : super(context, attributeSet)

    constructor(
        context: Context
    ) : super(context)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
    }



    override fun onTouchEvent(event: MotionEvent): Boolean {

//        Point(x,y)

        when(event.action and MotionEvent.ACTION_MASK){
            MotionEvent.ACTION_DOWN->{}
            MotionEvent.ACTION_MOVE->{}
            MotionEvent.ACTION_UP->{}
        }
        return super.onTouchEvent(event)
    }
}