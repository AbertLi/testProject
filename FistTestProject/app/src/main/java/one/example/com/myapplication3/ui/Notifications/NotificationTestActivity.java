package one.example.com.myapplication3.ui.Notifications;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import one.example.com.myapplication3.R;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class NotificationTestActivity extends AppCompatActivity {
    NotificationTools1 notificationTools1;
    NotificationTools2 notificationTools2;
    NotificationTools3 notificationTools3;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification_test );
        notificationTools1 = NotificationTools1.getInstance( this );
        notificationTools2 = NotificationTools2.getInstance( this );
        notificationTools3 = NotificationTools3.getInstance( this );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void btnOnclick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                notificationTools1.sendNotification( "https://www.baidu.com", MscActivity.class );
                break;
            case R.id.btn_2:
                notificationTools2.sendCustomNotification( "https://www.baidu.com" );
                break;
            case R.id.btn_3:
                notificationTools3.fullScreenNotification1( 2, "https://www.baidu.com" );
                break;
            case R.id.btn_4:
                notificationTools3.fullScreenNotification( 1, "https://www.baidu.com/" );
                break;
            case R.id.btn_5:
                break;

            case R.id.btn_6:
                break;
        }
    }
}
