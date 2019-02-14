package one.example.com.myapplication3.ui.my_ui_view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Hooyee on 2017/10/2.
 * mail: hooyee_moly@foxmail.com
 */

public class TextDrawable extends Drawable {
    private String mContent;
    private int mWidth;
    private int mHeight;
    private TextPaint mPaint;
    private int innerPadingH = 16;
    private int textWidth;
    private int rectWidth;
    private String backgrandColor;
    private int linsSpaceExtra;
    RectF rectF = new RectF();

    public TextDrawable(String content, int textSize, int width, int height, int color, String backgrandColor) {
        this.mContent = content;
        this.mWidth = width;
        this.mHeight = height;
        this.backgrandColor = backgrandColor;
        this.mPaint = new TextPaint();
        mPaint.setColor(color);
        mPaint.setTextSize(textSize);
        mPaint.setFakeBoldText(true);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        textWidth = (int) mPaint.measureText(content);
        rectWidth = 2 * innerPadingH + textWidth;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mPaint.setColor(Color.parseColor(backgrandColor));
        rectF.set(0,-mHeight-linsSpaceExtra,rectWidth,-linsSpaceExtra);
        canvas.drawRoundRect(rectF,16,16,mPaint);
//        int baseLine = (int)()-2;
    }

    public int getRectWidth() {
        return rectWidth;
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

}
