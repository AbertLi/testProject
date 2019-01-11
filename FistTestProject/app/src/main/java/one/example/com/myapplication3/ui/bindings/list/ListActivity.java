package one.example.com.myapplication3.ui.bindings.list;

import android.annotation.SuppressLint;
import android.os.Bundle;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.modle.IPersonBean;
import one.example.com.myapplication3.db.entity.PersonEntity;
import one.example.com.myapplication3.databinding.ActivityListBinding;
import one.example.com.myapplication3.viewmodle.PersonListViewModle;


/**
 * 1，通过Binding框架实现绑定Activity功能
 */
public class ListActivity extends FragmentActivity {
    private String TAG = "ListActivity";
    private ActivityListBinding binding;
    private PersonEntity select;
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
            select = new PersonEntity( "暂时么有选择", "" );
        }
        addTitle( select );
    }


    @Override
    protected void onResume() {
        super.onResume();
//        addData( adapter );
        addDataToViewModle( adapter );
        binding.executePendingBindings();//计算挂起的绑定，更新将表达式绑定到已修改变量的任何视图。
    }

    public void addTitle(PersonEntity select) {
        binding.setTitleName( select.getName() );
        binding.setTitleAge( select.getAge() );
    }

    /**
     * 通过ViewModle从数据库里面获取数据。
     * 数据是数据在创建的时候创建的。
     */
    public void addDataToViewModle(PersonListAdapter adapter) {
        final PersonListViewModle viewModel = ViewModelProviders.of( this ).get( PersonListViewModle.class );//  final PersonListViewModle viewModel = ViewModelProviders.of( this ).get( PersonListViewModle.class ); Fragment的数据获取方式
        subscribeUi( viewModel.getPerson(), adapter );
    }


    private void subscribeUi(LiveData<List<PersonEntity>> liveData, PersonListAdapter adapter) {
        Logs.eprintln( TAG, liveData.toString() );
        // Update the list when the data changes
        liveData.observe( this, new Observer<List<PersonEntity>>() {
            @Override
            public void onChanged(@androidx.annotation.Nullable List<PersonEntity> person) {
                Logs.eprintln( TAG, "subscribeUi  onChanged  myProducts==null " + (person == null) );
                if (person != null) {
                    adapter.addPersonList( person );
                    Logs.eprintln( TAG, "subscribeUi  onChanged" + person.size() );
                } else {

                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                binding.executePendingBindings();
            }
        } );
    }

    /**
     * 自己造数据
     *
     * @param adapter
     */
    public void addData(PersonListAdapter adapter) {
        List<PersonEntity> listBean = new ArrayList<>();
        String[] strData = {"AAA", "BBB", "CCC", "DDD", "Jack", "Mary", "Fox", "Albert", "Jason", "FFF", "ggg"};
        for (int j = 0; j < strData.length; j++) {
            PersonEntity personBean = new PersonEntity( strData[j], "" + (21 + j) );
            listBean.add( personBean );
        }
        adapter.addPersonList( listBean );
    }

    IPersonClickCallBack clickCallBack = new IPersonClickCallBack() {
        @Override
        public void onClick(IPersonBean person) {
            select = (PersonEntity) person;
            addTitle( select );
            Logs.eprintln( TAG, "点击：" + person.getName() );
        }
    };
}
