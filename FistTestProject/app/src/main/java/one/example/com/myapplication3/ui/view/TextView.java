package one.example.com.myapplication3.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import one.example.com.myapplication3.Logs;

public class TextView extends View {
    private String TAG = "TextView";
    private Paint paint = new Paint();
    /**
     * new TextView的时候会调用到
     * @param context
     */
    public TextView(Context context) {
        this(context,null);
    }

    /**
     * 自定义属性会调用到
     * @param context
     * @param attrs
     */
    public TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 自定义样式会用到
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Logs.iprintln(TAG,"onMeasure  widthMeasureSpec="+widthMeasureSpec+"   heightMeasureSpec="+heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logs.iprintln(TAG,"onDraw  canvas=");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Logs.iprintln(TAG,"onLayout  changed ="+changed+"   left="+left+"   top="+top+"   right="+right+"   bottom="+bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logs.iprintln(TAG,"onTouchEvent  event =");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

}
