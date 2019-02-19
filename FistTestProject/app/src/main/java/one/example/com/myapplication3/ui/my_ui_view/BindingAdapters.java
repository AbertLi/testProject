package one.example.com.myapplication3.ui.my_ui_view;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;

/**
 * 自定义Binding adapters
 */
public class BindingAdapters {

    /**
     * TextView Tag的两种实现方式之方式一。
     * 参考文章https://blog.csdn.net/weixin_28717693/article/details/81811777
     * @param titleTextView
     * @param title
     * @param label
     */
    @BindingAdapter({"title", "label"})
    public static void setSpanText(TextView titleTextView, String title, String label) {
        Logs.eprintln("titleTextView  hight="+titleTextView.getWidth()+"获取TextView的宽度   ="+titleTextView.getWidth());
        if (TextUtils.isEmpty(label)) {
            titleTextView.setText(title);
            return;
        }
        StringBuffer content_buffer = new StringBuffer();
        content_buffer.append(label);
        content_buffer.append(" ");
        content_buffer.append(title);
        SpannableString spannableString = new SpannableString(content_buffer);
        View view = LayoutInflater.from(titleTextView.getContext()).inflate(R.layout.tag_layout, null);//R.layout.tag是每个标签的布局
        TextView tv_tag = view.findViewById(R.id.tv_tag);
        tv_tag.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);
        tv_tag.setBackgroundColor(Color.RED);
        tv_tag.setText(label);
        Bitmap bitmap = convertViewToBitmap(view);
        Drawable d = new BitmapDrawable(bitmap);
        d.setBounds(0, 0, tv_tag.getWidth(), tv_tag.getHeight());//缺少这句的话，不会报错，但是图片不回显示
//        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);//图片将对齐底部边线
        StickerSpan span = new StickerSpan(d, ImageSpan.ALIGN_BOTTOM,5,titleTextView.getContext());//图片将对齐底部边线

        int startIndex;
        int endIndex;
        startIndex = 0;
        endIndex = startIndex + label.length();
        Log.e("tag", "the start is" + startIndex + "the end is" + endIndex);
        spannableString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        titleTextView.setText(spannableString);
        titleTextView.setGravity(Gravity.CENTER_VERTICAL);
    }

    private static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }


    /**
     * TextView Tag的两种实现方式之方式二。
     *
     * @param titleTextView
     * @param title
     * @param label
     */
//    @BindingAdapter({"title", "label"})
//    public static void setSpanText(TextView titleTextView, String title, String label) {
//        if (TextUtils.isEmpty(label)) {
//            titleTextView.setText(title);
//            return;
//        }
//        Context context = titleTextView.getContext();
//        Html.ImageGetter imageGetter = new Html.ImageGetter() {
//            @Override
//            public Drawable getDrawable(String source) {
//                int heigth = (int)Math.ceil(titleTextView.getPaint().getFontMetrics().descent-titleTextView.getPaint().getFontMetrics().top)-10;
//                boolean isOneLine = FontUtil.isOneLine(context,title,);
//                return null;
//            }
//        };
//
//        String string = "<img src=\"" + label + "\">" + title;
//        Spanned spanned = Html.fromHtml(string, imageGetter, null);
//        titleTextView.setText(spanned);
//    }
}
