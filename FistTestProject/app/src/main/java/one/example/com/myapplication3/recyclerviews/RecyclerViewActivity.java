package one.example.com.myapplication3.recyclerviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import one.example.com.myapplication3.R;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Item使用卡片式布局实现RecyclerView的简单效果
 */
public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recycler_view );
        //初始化数据
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add( "item " + i );
        }
        recyclerView = findViewById( R.id.recyclerview );
        //设置LayoutManager为LinearLayoutManager
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        //设置Adapter
        recyclerView.setAdapter( new GeneralAdapter( this, datas ) );
    }
}
