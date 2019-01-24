package one.example.com.myapplication3.utile;

import android.content.Context;

import one.example.com.myapplication3.AppExecutors;

public class ApplicationUtile {
    private static Context mContext;
    private static AppExecutors executors;

    public static AppExecutors getExecutors() {
        return executors;
    }

    public static void setExecutors(AppExecutors executors) {
        ApplicationUtile.executors = executors;
    }

    public static Context getApplication() {
        return mContext;
    }

    public static void setApplication(Context context) {
        mContext = context;
    }
}
