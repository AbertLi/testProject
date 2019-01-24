package one.example.com.myapplication3;

import android.app.Application;

import one.example.com.myapplication3.ui.Notifications.NotificationTools2;
import one.example.com.myapplication3.utile.ApplicationUtile;

public class BaseApplication extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
        ApplicationUtile.setExecutors( mAppExecutors );//启动一个线程池用于全局
        ApplicationUtile.setApplication( this );//启动一个Application的上下文用于全局
        NotificationTools2.registerBoradcastReceiver( this );//启动广播监听
    }
}
