package one.example.com.myapplication3.ui.Notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;

import static android.app.Notification.VISIBILITY_SECRET;

public class NotificationTools2 {
    private Context context;
    private static NotificationTools2 utils;

    public NotificationTools2(Context context) {
        this.context = context;
    }

    public static NotificationTools2 getInstance(Context context) {
        if (utils == null) {
            synchronized (NotificationTools2.class) {
                if (utils == null) {
                    utils = new NotificationTools2( context );
                }
            }
        }
        return utils;
    }

    @NonNull
    public NotificationCompat.Builder getNotificationBuilder() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel( "channel_id", "channel_name",
                    NotificationManager.IMPORTANCE_DEFAULT );
            //是否绕过请勿打扰模式
            channel.canBypassDnd();
            //闪光灯
            channel.enableLights( true );
            //锁屏显示通知
            channel.setLockscreenVisibility( VISIBILITY_SECRET );
            //闪关灯的灯光颜色
            channel.setLightColor( Color.RED );
            //桌面launcher的消息角标
            channel.canShowBadge();
            //是否允许震动
            channel.enableVibration( true );
            //获取系统通知响铃声音的配置
            channel.getAudioAttributes();
            //获取通知取到组
            channel.getGroup();
            //设置可绕过  请勿打扰模式
            channel.setBypassDnd( true );
            //设置震动模式
            channel.setVibrationPattern( new long[]{100, 100, 200} );
            //是否会有灯光
            channel.shouldShowLights();
            NotificationUtile.getNotificationManager( context ).createNotificationChannel( channel );
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder( context, "channel_id" );
        notification.setContentTitle( "新消息来了" );
        notification.setContentText( "周末到了，不用上班了" );
        notification.setSmallIcon( R.mipmap.ic_launcher );
        notification.setAutoCancel( true );
        return notification;
    }

    /**
     * 自定义布局。RemoteViews介绍：https://www.jianshu.com/p/13d56fb221e2
     */
    private static boolean isCreate = false;
    private int notificationId = 1212121;

    public void sendCustomNotification(String url) {
        if (!NotificationUtile.isOpenPermission( context )) {
            return;
        }

        if (isCreate) {
            Logs.eprintln( "只能创建一个自定义Notification" );
            isCreate = true;
            return;
        }
        NotificationCompat.Builder builder = getNotificationBuilder();
        RemoteViews remoteViews = new RemoteViews( context.getPackageName(), R.layout.layout_custom_notifition );
        remoteViews.setTextViewText( R.id.notification_title, "标题" );
        remoteViews.setTextViewText( R.id.notification_content, "内容：XXXXXXXXXX" );

        Intent intent = new Intent( context, H5DetailActivity.class );
        intent.putExtra( "key", url );
        PendingIntent pendingIntent = PendingIntent.getActivity( context, -1, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        remoteViews.setOnClickPendingIntent( R.id.turn_next, pendingIntent );

        Intent intent2 = new Intent( "close" );//过滤action为close
        PendingIntent pendingIntentClose = PendingIntent.getBroadcast( context, 0, intent2, 0 );
        remoteViews.setOnClickPendingIntent( R.id.iv_close, pendingIntentClose );

        ListenerNotificationBrodcaseRecever.ListenerNotificationBrodcaseRecever( key -> {
            Logs.eprintln( "关闭通知栏" );
            NotificationUtile.getNotificationManager( context ).cancel( notificationId );
            isCreate = false;
        } );
        builder.setOngoing( true );//设置无法取消
        builder.setAutoCancel( false );//点击后不取消
        builder.setCustomContentView( remoteViews );
        NotificationUtile.getNotificationManager( context ).notify( notificationId, builder.build() );
    }


    /**
     * 自定义布局的按钮广播注册
     *
     * @param con
     */
    public static void registerBoradcastReceiver(Context con) {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction( "close" );//如果之定义布局有多个按钮则可以定义多个过滤条件
        myIntentFilter.addAction( "next" );
        myIntentFilter.addAction( "previous" );
        //注册广播
        con.registerReceiver( new ListenerNotificationBrodcaseRecever(), myIntentFilter );
    }
}
