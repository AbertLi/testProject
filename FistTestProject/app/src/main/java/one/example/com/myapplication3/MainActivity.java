package one.example.com.myapplication3;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.heytap.advert.dev_console.ConsoleConstant;
import com.heytap.advert.dev_console.LogConsoleActivity;

import androidx.annotation.Nullable;
import one.example.com.myapplication3.ui.Notifications.NotificationTestActivity;
import one.example.com.myapplication3.ui.animations.AnimationActivity;
import one.example.com.myapplication3.ui.bindings.ActivityTestFragment;
import one.example.com.myapplication3.ui.bindings.list.ListActivity;
import one.example.com.myapplication3.ui.customProgressBarText.CustomProgressBarActivity;
import one.example.com.myapplication3.ui.dbactivity.DbActivity;
import one.example.com.myapplication3.ui.kotlinAct.Main2Activity;
import one.example.com.myapplication3.ui.loadBundle.bundletest.BundleTestActivity;
import one.example.com.myapplication3.ui.loadBundle.listviewBundlestyle2.LoadBundleActivity;
import one.example.com.myapplication3.ui.loadBundle.listviewBundle.BundleActivity;
import one.example.com.myapplication3.ui.recyclerviews.RecyclerViewActivity;
import one.example.com.myapplication3.ui.reflection.plugin.ReflectionActivity;

/**
 * 该项目参考google提供的sample的基础架构组件，Architecture Components Basic
 */
public class MainActivity extends Activity {
    public static String TAG = "MyApplication";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(android.R.color.white);//通知栏所需颜色
//        }


        setContentView(R.layout.activity_main);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        }
        else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    public void btn(View view) {
        switch (view.getId()) {
            case R.id.button:
                System.out.println("ThreadName=" + Thread.currentThread().getName());
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this, ActivityTestFragment.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent3);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(this, NotificationTestActivity.class);
                startActivity(intent4);
                break;
            case R.id.button5:
                Intent intent5 = new Intent(this, DbActivity.class);
                startActivity(intent5);
                break;
            case R.id.button6:
                Intent intent6 = new Intent(this, AnimationActivity.class);
                startActivity(intent6);
                break;
            case R.id.button7:
                Intent intent7 = new Intent(this, Main2Activity.class);
                startActivity(intent7);
                break;
            case R.id.button8:
                Intent intent8 = new Intent(this, LoadBundleActivity.class);
                startActivity(intent8);
                break;
            case R.id.button9:
                Intent intent9 = new Intent(this, BundleActivity.class);
                startActivity(intent9);
                break;
            case R.id.button10:
//                Intent intent10 = new Intent(this, AppListActivity.class);
//                startActivity(intent10);
                break;
            case R.id.button11:
                Intent intent11 = new Intent(this, CustomProgressBarActivity.class);
                startActivity(intent11);
                break;

            case R.id.button12:
                Intent intent12 = new Intent(this, BundleTestActivity.class);
                startActivity(intent12);
                break;

            case R.id.button13:
                Intent intent13 = new Intent(this, ReflectionActivity.class);
                startActivity(intent13);
                break;

            case R.id.button14://提前打开文件夹访问权限
                String sdDir = "";
                boolean sdCardExist = false;
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    //为真则SD卡已装入，
                    sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
                }
                if (sdCardExist) {
                    sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();//获取跟目录
                    //查找SD卡根路径
                    sdDir.toString();
                    Logs.eprintln("main", "得到的根目录路径:" + sdDir);
                }

                Intent intent14 = new Intent(this, LogConsoleActivity.class);
                intent14.putExtra(ConsoleConstant.THEME, 0);//0为默认样式，1,为定制样式
                intent14.putExtra(ConsoleConstant.FOLDERPATH, sdDir);//文件夹路径
                startActivity(intent14);
                break;
        }
    }
}




