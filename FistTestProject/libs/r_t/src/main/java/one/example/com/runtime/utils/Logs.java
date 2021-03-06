package one.example.com.runtime.utils;

import android.util.Log;

public class Logs {
    private static final String TAG = "RT_Logs ";

    public static void dprintln(String str) {
        dprintln( "", str );
    }
    public static void iprintln(String str) {
        iprintln( "", str );
    }

    public static void eprintln(String str) {
        eprintln( "", str );
    }
    public static void dprintln(String tag, String str) {
        Log.d( TAG + tag, str );
    }

    public static void iprintln(String tag, String str) {
        Log.i( TAG + tag, str );
    }

    public static void eprintln(String tag, String str) {
        Log.e( TAG + tag, str );
    }
}
