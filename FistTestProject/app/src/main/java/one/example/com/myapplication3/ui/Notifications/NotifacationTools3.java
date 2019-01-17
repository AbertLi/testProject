package one.example.com.myapplication3.ui.Notifications;

import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;

public class NotifacationTools3 {
    private String TAG = "NotifacationTools3  ";
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

    /**
     * 判断通知栏的权限是否有打开
     *
     * @param con
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    //表示versionCode=19 也就是4.4的系统以及以上的系统生效。4.4以下系统默认全部打开状态。
    private boolean isNotifacationEnabled(Context con) {
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


    /**
     * 发送通知
     *
     * @param url 启动的H5链接
     * @param cla 通知启动的Activity
     */
    public void sendNotification(String url, Class<?> cla) {
        NotificationManager notificationManager = getNotificationManager();
        if (notificationManager == null) {
            Logs.eprintln( TAG, "NotificationManager is null" );
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//API19+
            if (!isNotifacationEnabled( context )) {
                new AlertDialog.Builder( context )
                        .setIconAttribute( android.R.attr.alertDialogIcon )
                        .setTitle( "权限没有开" ).setMessage( "请手动打开通知栏的权限" )
                        .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent mIntent = new Intent()
                                        .setAction( "android.settings.APPLICATION_DETAILS_SETTINGS" )//启动权限设置
                                        .setData( Uri.fromParts( "package", context.getApplicationContext().getPackageName(), null ) );
                                context.startActivity( mIntent );
                            }
                        } ).show();
                return;
            }
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder( context, "push" );
        Intent intent = new Intent( context, cla );//点击通知的时候启动Activity的意图
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        intent.putExtra( "url", url );//传递字段
        intent.putExtra( "fromPush", "true" );//传递字段
        int pushid = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity( context, pushid, intent, PendingIntent.FLAG_CANCEL_CURRENT );
        builder.setContentTitle( "消息的标题" );
        builder.setContentText( "消息的具体描述：XXXXXXXX" );
        builder.setDefaults( NotificationCompat.DEFAULT_VIBRATE );
        builder.setAutoCancel( true );//点击通知后，状态栏是否自动删除通知。
        builder.setSmallIcon( R.mipmap.ic_launcher_round );//设置小图标
        builder.setLargeIcon( BitmapFactory.decodeResource( context.getResources(), R.mipmap.richudongfang ) );//设置大图
        builder.setStyle( new NotificationCompat.BigPictureStyle().bigPicture( BitmapFactory.decodeResource( context.getResources(), R.mipmap.richudongfang ) ) );
        builder.setOngoing( true );//设置他为一个正在进行的通知。他们通常是用来表示一个后台任务，用户积极参与或以某种方式正在等待，因此占用设备。（当设置为true的时候就无法清除通知栏，若为false则可以清除。）
        builder.setWhen( System.currentTimeMillis() );
        builder.setContentIntent( pendingIntent );
        builder.setChannelId( "push" );
        //判断是否是8.0Android.O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chan1 = new NotificationChannel( "push", "Test Channel", NotificationManager.IMPORTANCE_HIGH );
            chan1.enableLights( true );
            chan1.enableVibration( true );
            chan1.setDescription( "push" );
            chan1.setLockscreenVisibility( Notification.VISIBILITY_PUBLIC );
            notificationManager.createNotificationChannel( chan1 );
        }
        notificationManager.notify( pushid, builder.build() );
    }


}
