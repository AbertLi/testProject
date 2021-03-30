package one.example.com.myapplication3.utile;

import android.os.Looper;

public class ThreadUtil {
    public static boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
