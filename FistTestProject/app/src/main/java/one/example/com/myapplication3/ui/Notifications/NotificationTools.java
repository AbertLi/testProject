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
import one.example.com.myapplication3.MainActivity;
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


    /**
     * 悬挂通知栏启动浏览器
     *
     * @param notifyId
     * @param url      跳转浏览器的地址
     */
    public void fullScreenNotification1(int notifyId, String url) {
        if (!NotificationUtile.isOpenPermission( context )) {
            return;
        }
        NotificationManager nm = NotificationUtile.getNotificationManager( context );
        NotificationCompat.Builder builder3 = getNotificationBuilder();
        Intent intent3 = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
        PendingIntent pendingIntent3 = PendingIntent.getActivity( context, 0, intent3, 0 );
        builder3.setContentIntent( pendingIntent3 );
        builder3.setSmallIcon( R.mipmap.ic_launcher );
        builder3.setLargeIcon( BitmapFactory.decodeResource( context.getResources(), R.mipmap.ic_launcher ) );
        builder3.setAutoCancel( true );
        builder3.setContentTitle( "自定义消息标题" );
        builder3.setContentText( "自定义消息内容" );
        nm.notify( (int) System.currentTimeMillis(), builder3.build() );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//SDK版本大于等于21才有悬挂式通知栏
            Intent XuanIntent = new Intent();
            XuanIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            PendingIntent xuanpengdIntent = PendingIntent.getActivity( context, 0, XuanIntent, PendingIntent.FLAG_CANCEL_CURRENT );
            builder3.setFullScreenIntent( xuanpengdIntent, true );
            String notifyTag = notifyId + "10";
            nm.notify( notifyTag, notifyId, builder3.build() );
            new Thread( new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep( 3000 );//五秒后悬挂式通知消失
                        nm.cancel( notifyTag, notifyId );//按tag id 来清除消息
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } ).start();
        }
    }

    /**
     * 悬挂通知栏跳转自定义Activity的H5界面
     *
     * @param notifyId
     * @param url
     */
    public void fullScreenNotification(int notifyId, String url) {
        if (!NotificationUtile.isOpenPermission( context )) {
            return;
        }
        String notifyTag;
        NotificationManager nm = NotificationUtile.getNotificationManager( context );
        final NotificationCompat.Builder builder = getNotificationBuilder();
        Intent intent = new Intent( context, H5DetailActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.putExtra( "key", url );
        PendingIntent pendingIntent = PendingIntent.getActivity( context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT );
        builder.setContentIntent( pendingIntent );//普通消息
        builder.setWhen( System.currentTimeMillis() );
        builder.setSmallIcon( R.mipmap.ic_launcher_round );
        builder.setLargeIcon( BitmapFactory.decodeResource( context.getResources(), R.mipmap.richudongfang_4202829 ) );
        builder.setAutoCancel( true );
        builder.setDefaults( NotificationCompat.DEFAULT_ALL );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setVisibility( NotificationCompat.VISIBILITY_PUBLIC );
        }
        builder.setContentTitle( "自定义消息标题" );
        builder.setContentText( "自定义消息内容" );
        nm.notify( notifyId, builder.build() );//普通消息提示  显示在状态栏

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//SDK版本大于等于21才有悬挂式通知栏
            Intent xintent = new Intent();
            xintent.putExtra( "key", url );
            xintent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            PendingIntent hangIntent = PendingIntent.getActivity( context, 0, xintent, PendingIntent.FLAG_CANCEL_CURRENT );
            builder.setFullScreenIntent( hangIntent, true );//悬挂式通知  悬挂在手机屏上方
            notifyTag = notifyId + "jpush";//由于同一条消息  id 一样  ，有针对悬挂式通知打了一个tag；
            nm.notify( notifyTag, notifyId, builder.build() );
            new Thread( new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep( 3000 );//五秒后悬挂式通知消失
                        nm.cancel( notifyTag, notifyId );//按tag id 来清除消息
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } ).start();
        }
    }
}
