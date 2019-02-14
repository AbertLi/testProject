package one.example.com.myapplication3.ui.my_ui_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import one.example.com.myapplication3.utile.FontUtil;

public class StickerSpan extends ImageSpan {
    private int mcFontLineSpacingExtra;
    private Context mContext;

    public StickerSpan(Drawable b, int verticalAlignment,int cFontLineSpacingExtra, Context context) {
        super(b, verticalAlignment);
        mcFontLineSpacingExtra=cFontLineSpacingExtra;
        mContext=context;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text,
            int start, int end, float x,
            int top, int y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        canvas.save();
        int transY = bottom - b.getBounds().bottom - FontUtil.dip2px(mContext,mcFontLineSpacingExtra);
        if (mVerticalAlignment == ALIGN_BASELINE) {
            int textLength = text.length();
            for (int i = 0; i < textLength; i++) {
                if (Character.isLetterOrDigit(text.charAt(i))) {
                    transY -= paint.getFontMetricsInt().descent;
                    break;
                }
            }
        }
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}