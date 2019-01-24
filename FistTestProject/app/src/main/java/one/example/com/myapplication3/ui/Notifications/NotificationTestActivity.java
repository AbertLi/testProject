package one.example.com.myapplication3.ui.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import one.example.com.myapplication3.R;

import android.os.Bundle;
import android.view.View;

public class NotificationTestActivity extends AppCompatActivity {
    NotificationTools1 notificationTools1;
    NotificationTools2 notificationTools2;
    NotificationTools3 notificationTools3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification_test );
        notificationTools1 = NotificationTools1.getInstance( this.getApplicationContext() );
        notificationTools2 = NotificationTools2.getInstance( this.getApplicationContext() );
        notificationTools3 = NotificationTools3.getInstance( this.getApplicationContext() );
    }


    public void btnOnclick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                notificationTools1.sendNotification( "https://www.baidu.com", H5DetailActivity.class );
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
        }
    }
}
