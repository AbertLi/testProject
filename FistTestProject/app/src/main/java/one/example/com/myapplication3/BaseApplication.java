package one.example.com.myapplication3;

import android.app.Application;

import one.example.com.myapplication3.db.AppDataBase;
import one.example.com.myapplication3.ui.Notifications.NotificationTools;
import one.example.com.myapplication3.utile.ApplicationUtile;

public class BaseApplication extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtile.setApplication( this );
        mAppExecutors = new AppExecutors();
        NotificationTools.registerBoradcastReceiver( this );
    }



    public AppDataBase getDatabase() {
        return AppDataBase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}
