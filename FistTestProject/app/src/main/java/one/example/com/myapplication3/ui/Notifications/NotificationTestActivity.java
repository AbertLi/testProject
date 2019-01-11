package one.example.com.myapplication3.ui.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import one.example.com.myapplication3.R;

import android.os.Bundle;
import android.view.View;

public class NotificationTestActivity extends AppCompatActivity {
    NotificationTools notificationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification_test );
        notificationUtils = NotificationTools.getInstance( this.getApplicationContext() );
    }

    public void sendNot() {
    }


    public void btnOnclick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                notificationUtils.sendCustomNotification();
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                notificationUtils.xungaunNotification();
                break;
        }
    }


}
