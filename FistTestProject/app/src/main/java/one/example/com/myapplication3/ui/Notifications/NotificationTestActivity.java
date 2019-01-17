package one.example.com.myapplication3.ui.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;

import android.os.Bundle;
import android.view.View;

public class NotificationTestActivity extends AppCompatActivity {
    NotificationTools notificationTools;
    NotifacationTools3 notifacationTools3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification_test );
        notificationTools = NotificationTools.getInstance( this.getApplicationContext() );
        notifacationTools3 = NotifacationTools3.getInstance( this );
    }


    public void btnOnclick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                NotificationTools.notificationIds++;
                Logs.eprintln( "notification=" + NotificationTools.notificationIds );
                notificationTools.sendCustomNotification( NotificationTools.notificationIds );
                break;
            case R.id.btn_2:
                notifacationTools3.sendNotification( "https://www.baidu.com", H5DetailActivity.class );
                break;
            case R.id.btn_3:
                notificationTools.xungaunNotification();
                break;

            case R.id.btn_4:
                notificationTools.a(1);
                break;
        }
    }


}
