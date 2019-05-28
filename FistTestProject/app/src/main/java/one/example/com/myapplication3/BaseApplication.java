package one.example.com.myapplication3;

import com.qihoo360.replugin.RePluginApplication;

import one.example.com.myapplication3.ui.Notifications.NotificationTools2;
import one.example.com.myapplication3.utile.ApplicationUtile;
import one.example.com.myapplication3.utile.logutile.LogWriteUtile;

public class BaseApplication extends RePluginApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        AppExecutors.getInstance();
        ApplicationUtile.setApplication(this);//启动一个Application的上下文用于全局
        NotificationTools2.registerBoradcastReceiver(this);//启动广播监听
        LogWriteUtile.getInstance().init();
    }
}
