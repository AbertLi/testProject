package one.example.com.myapplication3.bindings.list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.db.dao.IPersonBean;
import one.example.com.myapplication3.db.entity.PersonBean;
import one.example.com.myapplication3.databinding.ActivityListBinding;


/**
 * 1，通过Binding框架实现绑定Activity功能
 */
public class ListActivity extends Activity {
    private String TAG = "ListActivity";
    private ActivityListBinding binding;
    private PersonBean select;
    private PersonListAdapter adapter;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_list );
        adapter = new PersonListAdapter( clickCallBack );
        binding.recyclerview.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false ) );//这个参数必须设置，或者在xml布局中设置也可以
        binding.recyclerview.setAdapter( adapter );

        if (select == null) {
            select = new PersonBean( "暂时么有选择", "" );
        }
        addTitle( select );
    }


    @Override
    protected void onResume() {
        super.onResume();
        addData( adapter );
        binding.executePendingBindings();//计算挂起的绑定，更新将表达式绑定到已修改变量的任何视图。
    }

    public void addTitle(PersonBean select) {
        binding.setTitleName( select.getName() );
        binding.setTitleAge( select.getAge() );
    }

    public void addData(PersonListAdapter adapter) {
        List<PersonBean> listBean = new ArrayList<>();
        String[] strData = {"AAA", "BBB", "CCC", "DDD", "Jack", "Mary", "Fox", "Albert", "Jason", "FFF", "ggg"};
        for (int j = 0; j < strData.length; j++) {
            PersonBean personBean = new PersonBean( strData[j], "" + (21 + j) );
            listBean.add( personBean );
        }
        adapter.addPersonList( listBean );
    }

    IPersonClickCallBack clickCallBack = new IPersonClickCallBack() {
        @Override
        public void onClick(IPersonBean person) {
            select = (PersonBean) person;
            addTitle( select );
            Logs.eprintln( TAG, "点击：" + person.getName() );
        }
    };
}
