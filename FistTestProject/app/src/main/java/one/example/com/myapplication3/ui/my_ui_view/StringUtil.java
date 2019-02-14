package one.example.com.myapplication3.ui.my_ui_view;

import androidx.annotation.NonNull;

/**
 * Created by Hooyee on 2017/10/5.
 * mail: hooyee_moly@foxmail.com
 */

public class StringUtil {
    public static String reverseString(@NonNull String content) {
        StringBuffer buffer = new StringBuffer();
        for (int i = content.length()-1; i >= 0; i--) {
            buffer.append(String.valueOf(content.charAt(i)));
        }
        return buffer.toString();
    }
}
