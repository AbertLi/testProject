package one.example.com.myapplication3.ui.Notifications;

import android.app.Notification;
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

    public static int notificationIds = 0;

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

    /**
     * 自定义布局。RemoteViews介绍：https://www.jianshu.com/p/13d56fb221e2
     */
    private static int notificationId;

    public void sendCustomNotification(int notificationId) {
        if (notificationId > 1) {
            Logs.eprintln( "只能创建一个自定义Notification" );
            return;
        }
        this.notificationId = notificationId;
        NotificationCompat.Builder builder = getNotificationBuilder();
        RemoteViews remoteViews = new RemoteViews( context.getPackageName(), R.layout.layout_custom_notifition );
        remoteViews.setTextViewText( R.id.notification_title, "进入主页" );
        remoteViews.setTextViewText( R.id.notification_content, "进入详情页" );

        Intent intent = new Intent( context, H5DetailActivity.class );
        PendingIntent pendingIntent = PendingIntent.getActivity( context, -1, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        remoteViews.setOnClickPendingIntent( R.id.turn_next, pendingIntent );

        Intent intent2 = new Intent( "close" );
        intent2.putExtra( "key", this.notificationId );
        PendingIntent pendingIntentClose = PendingIntent.getBroadcast( context, 0, intent2, 0 );
        remoteViews.setOnClickPendingIntent( R.id.iv_close, pendingIntentClose );

        ListenerNotificationBrodcaseRecever.ListenerNotificationBrodcaseRecever( this.notificationId, new ListenerNotificationBrodcaseRecever.ICallBack() {
            @Override
            public void callBack(int key) {
                Logs.eprintln( "关闭通知栏" );
                getNotificationManager().cancel( notificationId );
                notificationIds = 0;
            }
        } );
        builder.setOngoing( true );//设置无法取消
        builder.setAutoCancel( false );//点击后不取消
        builder.setCustomContentView( remoteViews );
        getNotificationManager().notify( notificationId, builder.build() );
    }


    public static void registerBoradcastReceiver(Context con) {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction( "close" );
        //注册广播
        con.registerReceiver( new ListenerNotificationBrodcaseRecever(), myIntentFilter );
    }


    /**
     * 悬挂通知栏
     */
    public void xungaunNotification() {
        NotificationManager nm = getNotificationManager();
        NotificationCompat.Builder builder3 = getNotificationBuilder();
        Intent intent3 = new Intent( Intent.ACTION_VIEW, Uri.parse( "https://www.baidu.com" ) );
        PendingIntent pendingIntent3 = PendingIntent.getActivity( context, 0, intent3, 0 );
        builder3.setContentIntent( pendingIntent3 );
        builder3.setSmallIcon( R.mipmap.ic_launcher );
        builder3.setLargeIcon( BitmapFactory.decodeResource( context.getResources(), R.mipmap.ic_launcher ) );
        builder3.setAutoCancel( true );
        builder3.setContentTitle( "悬挂通知" );
        builder3.setContentText( "正文四大金刚经过加热on任何内容" );

        Intent XuanIntent = new Intent();
        XuanIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        XuanIntent.setClass( context, H5DetailActivity.class );

        PendingIntent xuanpengdIntent = PendingIntent.getActivity( context, 0, XuanIntent, PendingIntent.FLAG_CANCEL_CURRENT );
        builder3.setFullScreenIntent( xuanpengdIntent, true );
        nm.notify( (int) System.currentTimeMillis(), builder3.build() );
    }

    /**
     * 悬挂通知栏
     */
   public void a(int notifyId) {
        String notifyTag;
        NotificationManager nm = getNotificationManager();
        final NotificationCompat.Builder builder = getNotificationBuilder();
        Intent intent = new Intent( context, H5DetailActivity.class );
        PendingIntent pendingIntent = PendingIntent.getActivity( context, 0, intent, 0 );
        builder.setContentIntent( pendingIntent );//普通消息
        builder.setWhen( System.currentTimeMillis() );
        builder.setSmallIcon( R.mipmap.ic_launcher_round );
        builder.setLargeIcon( BitmapFactory.decodeResource( context.getResources(), R.mipmap.richudongfang_4202829 ) );
        builder.setAutoCancel( true );
        builder.setDefaults( NotificationCompat.DEFAULT_ALL );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC );
        }

        builder.setContentTitle( "自定义消息标题" );
        builder.setContentText( "自定义消息内容" );

        nm.notify( notifyId, builder.build() );//普通消息提示  显示在状态栏

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//SDK版本大于等于21才有悬挂式通知栏
            Intent xintent = new Intent( context, MainActivity.class );
            xintent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            PendingIntent hangIntent = PendingIntent.getActivity( context, 0, xintent,PendingIntent.FLAG_CANCEL_CURRENT );
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
