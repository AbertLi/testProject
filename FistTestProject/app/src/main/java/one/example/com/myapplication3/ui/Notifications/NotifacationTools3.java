package one.example.com.myapplication3.ui.Notifications;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import java.io.PipedOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.annotation.RequiresApi;

public class NotifacationTools3 {
    private Context context;
    private static NotifacationTools3 utils;

    public NotifacationTools3(Context context) {
        this.context = context;
    }

    public static NotifacationTools3 getInstance(Context context) {
        if (utils == null) {
            synchronized (NotificationTools.class) {
                if (utils == null) {
                    utils = new NotifacationTools3( context );
                }
            }
        }
        return utils;
    }


    NotificationManager mNotificationManager;

    private NotificationManager getNotificationManager() {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) context.getSystemService( Context.NOTIFICATION_SERVICE );
        }
        return mNotificationManager;
    }


    private String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean isNotifacationEnabled(Context con) {
        if (Build.VERSION.SDK_INT >= 24) {//7.0
            if (getNotificationManager() != null) {
                return getNotificationManager().areNotificationsEnabled();
            }
        }
        AppOpsManager mAppOps = (AppOpsManager) con.getSystemService( Context.APP_OPS_SERVICE );
        ApplicationInfo info = con.getApplicationInfo();
        String pag = con.getApplicationContext().getPackageName();
        int uid = info.uid;
        Class appOpsClass = null;

        try {
            appOpsClass = Class.forName( AppOpsManager.class.getName() );
            Method meth = appOpsClass.getMethod( CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class );
            Field field = appOpsClass.getDeclaredField( OP_POST_NOTIFICATION );
            int value = (int) field.get( Integer.class );
            return ((int) meth.invoke( mAppOps, value, uid, pag ) == AppOpsManager.MODE_ALLOWED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




}
