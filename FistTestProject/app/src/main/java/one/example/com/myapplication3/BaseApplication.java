package one.example.com.myapplication3;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import com.github.moduth.blockcanary.BlockCanary;

import one.example.com.myapplication3.runtime.RunTimeInit;
import one.example.com.myapplication3.ui.Notifications.NotificationTools2;
import one.example.com.myapplication3.utile.ApplicationUtile;
import one.example.com.myapplication3.utile.logutile.LogWriteUtile;
import one.example.com.runtime.host.HostInit;

/**
 *
 *   https://blog.csdn.net/weixin_42042620/article/details/90044630
 */

public class BaseApplication extends Application {
    private static String TAG = "BaseApplication   ";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Logs.eprintln(TAG + "@Override attachBaseContext()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logs.eprintln(TAG + "@Override onCreate()");
        BlockCanary.install(this, new AppBlockContext()).start();//监控UI卡顿
        AppExecutors.getInstance();
        ApplicationUtile.setApplication(this);//启动一个Application的上下文用于全局
        NotificationTools2.registerBoradcastReceiver(this);//启动广播监听
        LogWriteUtile.getInstance().init();

        HostInit.attachBeseContext(this);//初始化中间件
        RunTimeInit.init();//初始化公共能力

        registerActivityLifecycleCallback();
        registerComponentCallback();
        registerOnProvideAssistDataListeners();
    }


    private void registerActivityLifecycleCallback() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Logs.eprintln(TAG + "registerActivityLifecycleCallbacks onActivityCreated ActivityName="+activity.getClass().getName());
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Logs.eprintln(TAG + "registerActivityLifecycleCallbacks onActivityStarted ActivityName="+activity.getClass().getName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Logs.eprintln(TAG + "registerActivityLifecycleCallbacks onActivityResumed ActivityName="+activity.getClass().getName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Logs.eprintln(TAG + "registerActivityLifecycleCallbacks onActivityPaused ActivityName="+activity.getClass().getName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Logs.eprintln(TAG + "registerActivityLifecycleCallbacks onActivityStopped ActivityName="+activity.getClass().getName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Logs.eprintln(TAG + "registerActivityLifecycleCallbacks onActivitySaveInstanceState ActivityName="+activity.getClass().getName());
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Logs.eprintln(TAG + "registerActivityLifecycleCallbacks onActivityDestroyed ActivityName="+activity.getClass().getName());
            }
        });
    }

    private void registerComponentCallback(){
        registerComponentCallbacks(new ComponentCallbacks2() {
            //这个接口实现比起ComponentCallbacks接口多onTrimMemory这个方法
            @Override
            public void onTrimMemory(int level) {
                Logs.eprintln(TAG + "registerComponentCallback  onTrimMemory  level="+level);
            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                Logs.eprintln(TAG + "registerComponentCallback  onConfigurationChanged");
            }

            @Override
            public void onLowMemory() {
                Logs.eprintln(TAG + "registerComponentCallback  onLowMemory");
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void registerOnProvideAssistDataListeners(){
        registerOnProvideAssistDataListener((activity, data) -> {
            Logs.eprintln(TAG + "registerOnProvideAssistDataListeners registerOnProvideAssistDataListener ActivityName="+activity.getClass().getName());
        });
    }

    @Override
    public void onTrimMemory(int level) {//Android4.0后在onLowMemory()方法升级的一个方法
        super.onTrimMemory(level);
        Logs.eprintln(TAG + "@Override onTrimMemory  level="+level);
    }

    @Override
    public void onLowMemory() {//onLowMemory() ==onTrimMemory()的ComponentCallbacks2.TRIM_MEMORY_COMPLETE
        super.onLowMemory();
        Logs.eprintln(TAG + "@Override onLowMemory()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logs.eprintln(TAG + "@Override onConfigurationChanged");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Logs.eprintln(TAG + "@Override onTerminate()");
    }
}
