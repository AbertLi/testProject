package one.example.com.myapplication3.bindings;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

import one.example.com.myapplication3.R;

/**
 * 1，通过Binding框架实现一个List的数据展示。
 * 2，将数据存入到数据库，使用room
 * 3，将list换成RecycleerView展示，
 */
public class ListActivity extends AppCompatActivity  {
    private LifecycleRegistry mLifecycleRegistry=new LifecycleRegistry(ListActivity.this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyLifecycleObserver myLifecycleObserver=new MyLifecycleObserver();
        mLifecycleRegistry.addObserver(myLifecycleObserver);
    }

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }
}
