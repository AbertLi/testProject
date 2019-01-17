package one.example.com.myapplication3.ui.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import one.example.com.myapplication3.Logs;

public class ListenerNotificationBrodcaseRecever extends BroadcastReceiver {
    private static ICallBack mCallBack;

    public static void ListenerNotificationBrodcaseRecever(ICallBack iCallBack) {
        mCallBack=iCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String flag = intent.getAction();
        if (flag != null && !"".equals( flag )) {
            if ("close".equals( flag )) {
                if (mCallBack != null) {
                    Logs.eprintln( "ListenerNotificationBrodcaseRecever  ", "  key=");
                    mCallBack.callBack( 1 );
                }
            }
        }

    }

    interface ICallBack {
        public void callBack(int key);
    }
}
