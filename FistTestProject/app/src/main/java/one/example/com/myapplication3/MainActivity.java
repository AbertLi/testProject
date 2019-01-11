package one.example.com.myapplication3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import one.example.com.myapplication3.ui.bindings.ActivityTestFragment;
import one.example.com.myapplication3.ui.bindings.list.ListActivity;
import one.example.com.myapplication3.ui.recyclerviews.RecyclerViewActivity;

/**
 * 该项目参考google提供的sample的基础架构组件，Architecture Components Basic
 *
 */
public class MainActivity extends Activity {
    public static String TAG = "MyApplication";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void btn(View view) {
        switch (view.getId()) {
            case R.id.button:
                System.out.println( "ThreadName=" + Thread.currentThread().getName() );
                Intent intent = new Intent( this, ListActivity.class );
                startActivity( intent );
                break;
            case R.id.button2:
                Intent intent2 = new Intent( this, ActivityTestFragment.class );
                startActivity( intent2 );
                break;
            case R.id.button3:
                Intent intent3 = new Intent( this, RecyclerViewActivity.class );
                startActivity( intent3 );
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
        }
    }
}




