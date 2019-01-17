package one.example.com.myapplication3.ui.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

import one.example.com.myapplication3.Logs;

public class ListenerNotificationBrodcaseRecever extends BroadcastReceiver {
    private static Map<Integer, ICallBack> map = new HashMap<>();

    public static void ListenerNotificationBrodcaseRecever(int notificationId, ICallBack iCallBack) {
        map.put( notificationId, iCallBack );
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String flag = intent.getAction();
        int notificationId = intent.getIntExtra( "key", 0 );
        Logs.eprintln( "ListenerNotificationBrodcaseRecever  ", "  key=" + notificationId );
        if (flag != null && !"".equals( flag )) {
            if ("close".equals( flag )) {
                ICallBack mCallBack = map.get( notificationId );
                if (mCallBack != null) {
                    mCallBack.callBack( 1 );
                }
            }
        }

    }

    interface ICallBack {
        public void callBack(int key);
    }
}
