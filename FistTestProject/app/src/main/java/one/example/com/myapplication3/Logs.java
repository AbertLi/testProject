package one.example.com.myapplication3;

import android.util.Log;

public class Logs {
    private static final String TAG = "Logs";

    public static void iprintln(String str) {
        iprintln( TAG, str );
    }

    public static void eprintln(String str) {
        eprintln( TAG, str );
    }

    public static void iprintln(String tag, String str) {
        Log.i( tag, str );
    }

    public static void eprintln(String tag, String str) {
        Log.e( tag, str );
    }
}
