package one.example.com.myapplication3.ui.customProgressBarText.progress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;

/**
 * 自定义进度条
 */
public class ViewProgressBar extends View {
    private float mProgress;
    private int mTEXT_SIZE;
    private int mStateType;
    private float mProgressWidth;


    private String mDefualtText = "";//下载字样
    private String mPercentText = "0.00%";//百分数字样
    private String mPauseText = "";  //暂停字样
    private String mInstallationText = "";//安装中字样
    private String mOpenText = "";        //打开字样

    private Rect mDefualt;//下载字样
    private Rect mPercent;//百分数字样
    private Rect mPause;  //暂停字样
    private Rect mInstallation;//安装中字样
    private Rect mOpen;        //打开字样

    private Paint mPaint;//画笔
    private PorterDuffXfermode mPorterDuffXfermode;//混合绘制模式


    public class Constant {
        public static final int DEFAULT = 0; //默认状态
        public static final int PAUSE = 1;   //暂停
        public static final int DOWNLOAD = 2;//下载中
        public static final int INSTALLING = 3;  //安装中
        public static final int OPENT = 4;   //打开
    }

    public ViewProgressBar(Context context) {
        super(context);
        init();
    }

    public ViewProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setProgress(float progress) {
        mProgress = progress;
        invalidate();
    }

    public void setTextSize(int textSize) {
        mTEXT_SIZE = textSize;
        invalidate();
    }

    public void setStateType(int stateType) {
        mStateType = stateType;
        invalidate();
    }

    private void init() {
        mDefualt = new Rect();//下载字样
        mPercent = new Rect();//百分数字样
        mPause = new Rect();  //暂停字样
        mInstallation = new Rect();//安装中字样
        mOpen = new Rect();        //打开字样
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#007AFF"));
        mPaint.setTextSize(mTEXT_SIZE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setXfermode(null);
        mPaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mProgressWidth = getWidth() * mProgress / 100;//进度的宽度

        mPaint.getTextBounds(mDefualtText, 0, mDefualtText.length(), mDefualt);
        mPaint.getTextBounds(mPercentText, 0, mPercentText.length(), mPercent);
        mPaint.getTextBounds(mPauseText, 0, mPauseText.length(), mPause);
        mPaint.getTextBounds(mInstallationText, 0, mInstallationText.length(), mInstallation);


        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int defualtWidth = centerX - mDefualt.centerX();
        int defualtHight = centerY - mDefualt.centerY();

        int percentWidth = centerX - mPercent.centerX();
        int percentHight = centerY - mPercent.centerY();

        int pauseWidth = centerX - mPause.centerX();
        int pauseHight = centerY - mPause.centerY();

        int installationWidth = centerX - mInstallation.centerX();
        int installationHight = centerY - mInstallation.centerY();

        int openWidth = centerX - mOpen.centerX();
        int openHight = centerY - mOpen.centerY();

        Bitmap srcBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas srcCanvas = new Canvas(srcBitmap);
        switch (mStateType) {
            case Constant.DEFAULT:
                drawUi(canvas, defualtWidth, defualtHight, mDefualtText, srcBitmap, srcCanvas);
                break;
            case Constant.DOWNLOAD:
                drawUi(canvas, percentWidth, percentHight, mPercentText, srcBitmap, srcCanvas);
                break;
            case Constant.PAUSE:
                drawUi(canvas, pauseWidth, pauseHight, mPauseText, srcBitmap, srcCanvas);
                break;
            case Constant.INSTALLING:
                drawUi(canvas, installationWidth, installationHight, mInstallationText, srcBitmap, srcCanvas);
                break;
            case Constant.OPENT:
                drawUi(canvas, openWidth, openHight, mOpenText, srcBitmap, srcCanvas);
                break;
        }
    }

    private void drawUi(Canvas canvas, int x, int y, String textContent, Bitmap srcBitmap, Canvas srcCanvas) {
        mPaint.setColor(Color.parseColor("#007AFF"));
        canvas.drawText(textContent, x, y, mPaint);
        srcCanvas.drawText(textContent, x, y, mPaint);
        // 设置混合模式
        mPaint.setXfermode(mPorterDuffXfermode);
        mPaint.setColor(Color.WHITE);
        RectF rectF = new RectF(0, 0, mProgressWidth, getHeight());//mWidth是不断变化的
        // 绘制源图形
        srcCanvas.drawRect(rectF, mPaint);
        // 绘制目标图
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        // 清除混合模式
        mPaint.setXfermode(null);
        // 恢复画笔颜色
        mPaint.setColor(Color.parseColor("#007AFF"));
    }
}
