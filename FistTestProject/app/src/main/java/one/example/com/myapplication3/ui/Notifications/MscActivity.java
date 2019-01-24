package one.example.com.myapplication3.ui.Notifications;

import androidx.appcompat.app.AppCompatActivity;
//import one.example.com.myapplication3.R;

import android.content.Intent;
import android.os.Bundle;

/**
 * 部分手机会拦截广播直接启动Activity。但是实际上Activity已经启动了。
 */
public class MscActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
//        setContentView( R.layout.activity_msc );
        Intent intent = getIntent();
        String url = intent.getStringExtra( "key" );
        Intent intent1 = new Intent( this, H5DetailActivity.class );
        intent1.putExtra( "key", url );
        startActivity( intent1 );
        finish();
    }
}
