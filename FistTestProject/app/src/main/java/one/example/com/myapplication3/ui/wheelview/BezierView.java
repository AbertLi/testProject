package one.example.com.myapplication3.ui.wheelview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by YANG on 2019/2/23.
 */
public class BezierView extends View {

    private Paint mPaint;
    private Path mBezierPath;

    private Path mPointPath;

    private Point mStartPoint;
    private Point mControlPoint;
    private Point mEndPoint;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mBezierPath = new Path();
        mPointPath = new Path();

        mStartPoint = new Point();
        mStartPoint.set(100, 300);
        mControlPoint = new Point();
        mControlPoint.set(300, 100);
        mEndPoint = new Point();
        mEndPoint.set(500, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //贝塞尔
        mBezierPath.moveTo(mStartPoint.x, mStartPoint.y);
        mBezierPath.quadTo(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y);

        //连接线
        mPointPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPointPath.lineTo(mControlPoint.x, mControlPoint.y);
        mPointPath.lineTo(mEndPoint.x, mEndPoint.y);

        //绘制起始点、控制点、终点的连线
        canvas.drawPath(mPointPath, mPaint);

        //绘制贝塞尔
        mPaint.setColor(Color.RED);
        canvas.drawPath(mBezierPath, mPaint);
    }
}