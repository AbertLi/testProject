package one.example.com.runtime.host;

import android.app.Application;
import android.content.Context;

import one.example.com.runtime.utils.AppExecutors;

public class HostInit {
    private static Context mHostAppContext;

    public static void setHostAppContext(Application hostAppContext) {
        mHostAppContext = hostAppContext;
    }

    public static Context getHostAppContext() {
        return mHostAppContext;
    }

    public static void attachBeseContext(Application application){
        setHostAppContext(application);
        AppExecutors.runOnBackground(new Runnable() {
            @Override
            public void run() {

            }
        });
    }








//    public static HostInit mHostInit;
//    public static HostInit getInstance() {
//        if (mHostInit == null) {
//            synchronized (HostInit.class) {
//                if (mHostInit == null) {
//                    mHostInit = new HostInit();
//                }
//            }
//        }
//        return mHostInit;
//    }
}
