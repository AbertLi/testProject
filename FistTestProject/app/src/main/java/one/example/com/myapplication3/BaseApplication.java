package one.example.com.myapplication3;

import android.app.Application;


import one.example.com.myapplication3.runtime.RunTimeInit;
import one.example.com.myapplication3.ui.Notifications.NotificationTools2;
import one.example.com.myapplication3.utile.ApplicationUtile;
import one.example.com.myapplication3.utile.logutile.LogWriteUtile;
import one.example.com.runtime.host.HostInit;
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppExecutors.getInstance();
        ApplicationUtile.setApplication(this);//启动一个Application的上下文用于全局
        NotificationTools2.registerBoradcastReceiver(this);//启动广播监听
        LogWriteUtile.getInstance().init();

        HostInit.attachBeseContext(this);//初始化中间件
        RunTimeInit.init();//初始化公共能力
    }
}
