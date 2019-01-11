package one.example.com.myapplication3.ui.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import one.example.com.myapplication3.R;

import static android.app.Notification.VISIBILITY_SECRET;

public class NotificationTools {
    private Context context;
    private static NotificationTools utils;

    public NotificationTools(Context context) {
        this.context = context;
    }

    public static NotificationTools getInstance(Context context) {
        if (utils == null) {
            synchronized (NotificationTools.class) {
                if (utils == null) {
                    utils = new NotificationTools( context );
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
            getNotificationManager().createNotificationChannel( channel );
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder( context, "channel_id" );
        notification.setContentTitle( "新消息来了" );
        notification.setContentText( "周末到了，不用上班了" );
        notification.setSmallIcon( R.mipmap.ic_launcher );
        notification.setAutoCancel( true );
        return notification;
    }

    public void sendCustomNotification() {
        NotificationCompat.Builder builder = getNotificationBuilder();
        RemoteViews remoteViews = new RemoteViews( context.getPackageName(), R.layout.layout_custom_notifition );
        remoteViews.setTextViewText( R.id.notification_title, "custom_title" );
        remoteViews.setTextViewText( R.id.notification_content, "custom_content" );

        Intent intent = new Intent( context, H5DetailActivity.class );
        PendingIntent pendingIntent = PendingIntent.getActivity( context, -1,
                intent, PendingIntent.FLAG_UPDATE_CURRENT );
        remoteViews.setOnClickPendingIntent( R.id.turn_next, pendingIntent );
        builder.setCustomContentView( remoteViews );
        getNotificationManager().notify( (int) System.currentTimeMillis(), builder.build() );
    }

    public void xungaunNotification() {
        NotificationCompat.Builder builder3 = new NotificationCompat.Builder( context );
        Intent intent3 = new Intent( Intent.ACTION_VIEW, Uri.parse( "https://www.baidu.com" ) );
        PendingIntent pendingIntent3 = PendingIntent.getActivity( context, 0, intent3, 0 );
        builder3.setContentIntent( pendingIntent3 );
        builder3.setSmallIcon( R.mipmap.ic_launcher );
        builder3.setLargeIcon( BitmapFactory.decodeResource( context.getResources(), R.mipmap.ic_launcher ) );
        builder3.setAutoCancel( true );
        builder3.setContentTitle( "悬挂通知" );

        Intent XuanIntent = new Intent();
        XuanIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        XuanIntent.setClass( context, H5DetailActivity.class );

        PendingIntent xuanpengdIntent = PendingIntent.getActivity( context, 0, XuanIntent, PendingIntent.FLAG_CANCEL_CURRENT );
        builder3.setFullScreenIntent( xuanpengdIntent, true );
        getNotificationManager().notify( (int) System.currentTimeMillis(), builder3.build() );
    }

}
