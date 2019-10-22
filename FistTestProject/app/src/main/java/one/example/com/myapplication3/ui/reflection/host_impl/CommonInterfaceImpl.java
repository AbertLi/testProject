package one.example.com.myapplication3.ui.reflection.host_impl;

import one.example.com.myapplication3.utile.logutile.Log;

public class CommonInterfaceImpl {
    private static String TAG = "CommonInterfaceImpl";

    public static Object getHostInterface(Class<?> cla) {
        if (cla == Go2ActImpl.class)
            return new Go2ActImpl();
        if (cla == StatImpl.class)
            return new StatImpl();
        else {
            Log.e(TAG, "did not find a corresponding key value class  key=" + cla);
            return null;
        }
    }
}
