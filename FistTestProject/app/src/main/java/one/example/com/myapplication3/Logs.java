package one.example.com.myapplication3;

import android.util.Log;

public class Logs {
    private static final String TAG = "Logs";

    public static void iprintln(String str) {
        Log.i(TAG, "" + str);
    }

    public static void eprintln(String str) {
        Log.e(TAG, "" + str);
    }
}
