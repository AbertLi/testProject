package one.example.com.myapplication3.ui.bindings.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.databinding.ActivityDetailBinding;
import one.example.com.myapplication3.db.entity.PersonEntity;
import one.example.com.myapplication3.modle.IFamily;
import one.example.com.myapplication3.viewmodle.FamilyViewModle;

import android.content.Intent;
import android.os.Bundle;


public class DetailActivity extends AppCompatActivity {
    private static String TAG = "DetailActivity   ";
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_detail );
        Intent intent = getIntent();
        PersonEntity personEntity = (PersonEntity) intent.getSerializableExtra( "person" );
        binding.setPersonBean( personEntity );
        //查询数据库
        FamilyListAdapter adapter = new FamilyListAdapter( callback );
        addDataToAdapter( adapter, personEntity );
        binding.recyclerview.setLayoutManager( new LinearLayoutManager( this ) );
        binding.recyclerview.setAdapter( adapter );
    }


    /**
     * 通过ViewModle从数据库里面获取数据。
     * 数据是数据在创建的时候创建的。
     */
    public void addDataToAdapter(FamilyListAdapter adapter, PersonEntity personEntity) {
        final FamilyViewModle viewModel = ViewModelProviders.of( this ).get( FamilyViewModle.class );//  final PersonListViewModle viewModel = ViewModelProviders.of( this ).get( PersonListViewModle.class ); Fragment的数据获取方式

        // Update the list when the data changes
        viewModel.getFamilyByPersonId( personEntity.getId() ).observe( this, listFamily -> {
            Logs.eprintln( TAG, "subscribeUi  onChanged  listFamily==null " + (listFamily == null) );
            if (listFamily != null) {
                adapter.addFamily( listFamily );
                Logs.eprintln( TAG, "subscribeUi  onChanged=" + listFamily.size() );
            } else {

            }
            binding.executePendingBindings();
        } );
    }


    IFamilyCallBack callback = new IFamilyCallBack() {
        @Override
        public void onClick(IFamily person) {
            Logs.eprintln( TAG, "选择了 " + person.getText() + "\n" + person.getLike() );
        }
    };
}
