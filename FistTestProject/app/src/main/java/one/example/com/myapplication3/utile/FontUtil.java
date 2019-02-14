package one.example.com.myapplication3.utile;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * 文字处理工具
 */
public class FontUtil {
    //dp转px
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //px转dp
    public static int px2dip(Context context, int pxValue) {
        return ((int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context.getResources().getDisplayMetrics()));
    }


    public static boolean isOneLine(Context context,String title,int labelWidth){


        return  false;
    }

    /**
     * TextView每一行最对可以存放多少个字
     *
     * @param wight         TextView总宽度
     * @param letterSpacing 文字间距
     * @param textSize      文字大小
     * @return 文字个数
     */
    public static int getOneLineMaxFont(float wight, float letterSpacing, float textSize) {
        return (int) (wight / (letterSpacing + textSize));
    }


    public static int getScreenWidth(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth();
    }

    /**
     * 英文个数
     *
     * @param str
     * @return
     */
    public static int ywNum(String str) {
        int ywnum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (checkChar(str.charAt(i))){
                ywnum++;
            }
        }
        return ywnum;
    }

    /**
     * 英文占1byte，非英文（可认为是中文）占2byte，根据这个特性来判断字符
     *
     * @param ch
     * @return
     */
    public static boolean checkChar(char ch) {
        if ((ch + "").getBytes().length == 1) {
            return true;//英文
        }
        else {
            return false;//中文
        }
    }
}
