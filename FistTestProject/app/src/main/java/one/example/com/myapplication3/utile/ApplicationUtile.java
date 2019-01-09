package one.example.com.myapplication3.utile;

import android.content.Context;

public class ApplicationUtile {
    private static Context mContext;

    public static Context getApplication() {
        return mContext;
    }

    public static void setApplication(Context context) {
        mContext = context;
    }
}
