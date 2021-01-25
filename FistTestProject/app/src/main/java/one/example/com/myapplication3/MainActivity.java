package one.example.com.myapplication3;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.heytap.advert.dev_console.ConsoleConstant;
import com.heytap.advert.dev_console.LogConsoleActivity;
import com.wifi.mvpsample.MVPActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import one.example.com.myapplication3.ui.Notifications.NotificationTestActivity;
import one.example.com.myapplication3.ui.animations.AnimationActivity;
import one.example.com.myapplication3.ui.bindings.ActivityTestFragment;
import one.example.com.myapplication3.ui.bindings.list.ListActivity;
import one.example.com.myapplication3.ui.customProgressBarText.CustomProgressBarActivity;
import one.example.com.myapplication3.ui.dbactivity.DbActivity;
import one.example.com.myapplication3.ui.fresco.FrescoActivity;
import one.example.com.myapplication3.ui.handler.HandlerActivity;
import one.example.com.myapplication3.ui.handler.HandlerJavaActivity;
import one.example.com.myapplication3.ui.imageload.ImageLoadActivity;
import one.example.com.myapplication3.ui.kotlinAct.Main2Activity;
import one.example.com.myapplication3.ui.loadBundle.bundletest.BundleTestActivity;
import one.example.com.myapplication3.ui.loadBundle.listviewBundlestyle2.LoadBundleActivity;
import one.example.com.myapplication3.ui.loadBundle.listviewBundle.BundleActivity;
import one.example.com.myapplication3.ui.loadBundle.listviewBundlestyle2.MyAdapter;
import one.example.com.myapplication3.ui.okhttp.OkHttpActivity;
import one.example.com.myapplication3.ui.recyclerviews.RecyclerViewActivity;
import one.example.com.myapplication3.ui.reflection.plugin.ReflectionActivity;
import one.example.com.myapplication3.ui.retrofit.RetrofitActivity;
import one.example.com.myapplication3.ui.rxjava.RxJavaTestActivity;
import one.example.com.myapplication3.ui.viewAndEvent.ViewAndEventActivity;

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
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter adapter = new MainAdapter(this.getApplication(), getArrayList());
        adapter.setClickListeners(new MianOnClickListener() {
            @Override
            public void onClick(ItemBean itemBean) {
                btn(itemBean);
            }

            @Override
            public void onLongClick(ItemBean itemBean) {
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public List<ItemBean> getArrayList(){
        List<ItemBean> list = new ArrayList();
        list.add(new ItemBean("Test_Binding",0));
        list.add(new ItemBean("Fragment_binding_Test",1));
        list.add(new ItemBean("RecyclerView",2));
        list.add(new ItemBean("Notification",3));
        list.add(new ItemBean("DB",4));
        list.add(new ItemBean("Animation",5));
        list.add(new ItemBean("7,Kotlin",6));
        list.add(new ItemBean("LoadBundle",7));
        list.add(new ItemBean("ListView Bundle",8));
        list.add(new ItemBean("空位",9));
        list.add(new ItemBean("RePlugin 未实行",10));
        list.add(new ItemBean("CustomProgress text",11));
        list.add(new ItemBean("Bundle TEST",12));
        list.add(new ItemBean("Reflection",13));
        list.add(new ItemBean("Log",14));
        list.add(new ItemBean("图片相关 Fresco,Image",15));
        list.add(new ItemBean("RxJava Test",16));
        list.add(new ItemBean("Fresco",17));
        list.add(new ItemBean("Retrofit",18));
        list.add(new ItemBean("MVP TEST",19));
        list.add(new ItemBean("OkHttp Test",20));
        list.add(new ItemBean("View和事件分发",21));
        list.add(new ItemBean("Handler kotlin机制相关",22));
        list.add(new ItemBean("Handler java机制相关",23));
        return list;
    }

    public void btn(ItemBean itemBean) {
        switch (itemBean.getIndex()) {
            case 0:
                System.out.println("ThreadName=" + Thread.currentThread().getName());
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent2 = new Intent(this, ActivityTestFragment.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent3);
                break;
            case 3:
                Intent intent4 = new Intent(this, NotificationTestActivity.class);
                startActivity(intent4);
                break;
            case 4:
                Intent intent5 = new Intent(this, DbActivity.class);
                startActivity(intent5);
                break;
            case 5:
                Intent intent6 = new Intent(this, AnimationActivity.class);
                startActivity(intent6);
                break;
            case 6:
                Intent intent7 = new Intent(this, Main2Activity.class);
                startActivity(intent7);
                break;
            case 7:
                Intent intent8 = new Intent(this, LoadBundleActivity.class);
                startActivity(intent8);
                break;
            case 8:
                Intent intent9 = new Intent(this, BundleActivity.class);
                startActivity(intent9);
                break;
            case 9://ListView Bundle 暂时没有实现
//                Intent intent10 = new Intent(this, AppListActivity.class);
//                startActivity(intent10);
                break;
            case 10://Replugin暂时没有实现
                break;
            case 11:
                Intent intent11 = new Intent(this, CustomProgressBarActivity.class);
                startActivity(intent11);
                break;

            case 12:
                Intent intent12 = new Intent(this, BundleTestActivity.class);
                startActivity(intent12);
                break;

            case 13:
                Intent intent13 = new Intent(this, ReflectionActivity.class);
                startActivity(intent13);
                break;

            case 14://提前打开文件夹访问权限
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

            case 15:
                Intent intent15 = new Intent(this, ImageLoadActivity.class);
                startActivity(intent15);
                break;
            case 16:
                Intent intent16 = new Intent(this, RxJavaTestActivity.class);
                startActivity(intent16);
                break;
            case 17:
                Intent intent17 = new Intent(this, FrescoActivity.class);
                startActivity(intent17);
                break;
            case 18:
                Intent button18 = new Intent(this, RetrofitActivity.class);
                startActivity(button18);
                break;
            case 19:
                Intent intent18 = new Intent(this, MVPActivity.class);
                startActivity(intent18);
                break;

            case 20:
                Intent button20 = new Intent(this, OkHttpActivity.class);
                startActivity(button20);
                break;

            case 21:
                Intent button21 = new Intent(this, ViewAndEventActivity.class);
                startActivity(button21);
                break;
            case 22:
                Intent button22 = new Intent(this, HandlerActivity.class);
                startActivity(button22);
                break;
            case 23:
                Intent button23 = new Intent(this, HandlerJavaActivity.class);
                startActivity(button23);
                break;
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {
        //当前上下文对象
        Context context;
        //RecyclerView填充Item数据的List对象
        List<ItemBean> datas;

        MianOnClickListener mMianOnClickListener;

        public MainAdapter(Context context, List<ItemBean> datas) {
            this.context = context;
            this.datas = datas;
        }

        public void setClickListeners(MianOnClickListener mianOnClickListener) {
            mMianOnClickListener = mianOnClickListener;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = View.inflate(context, R.layout.main_recyclerview_item, null);
            return new MyHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.textView.setText(datas.get(position).itemName);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMianOnClickListener != null) {
                        mMianOnClickListener.onClick(datas.get(position));
                    }
                }
            });
            holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mMianOnClickListener != null) {
                        mMianOnClickListener.onLongClick(datas.get(position));
                    }
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public MyHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text);
            }
        }
    }

    abstract class MianOnClickListener {
        public void onClick(ItemBean itemBean) {
        }

        public void onLongClick(ItemBean itemBean) {
        }
    }

    class ItemBean {
        private String itemName;
        private int index;

        public ItemBean(String itemName, int index) {
            this.itemName = itemName;
            this.index = index;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}




