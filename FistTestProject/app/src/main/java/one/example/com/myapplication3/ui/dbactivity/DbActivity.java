package one.example.com.myapplication3.ui.dbactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import one.example.com.myapplication3.AppExecutors;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.databinding.ActivityDbBinding;
import one.example.com.myapplication3.db.AppDataBase;
import one.example.com.myapplication3.db.DataGenerator;
import one.example.com.myapplication3.db.entity.FamilyEntity;
import one.example.com.myapplication3.db.entity.PersonEntity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DbActivity extends AppCompatActivity {
    AppDataBase dataBase;
    ActivityDbBinding binding;
    DbListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_db );
        dataBase = AppDataBase.getInstance( this, new AppExecutors() );
    }

    public void btn(View view) {
        String name = binding.name.getText().toString().trim();
        String age = binding.age.getText().toString().trim();
        Logs.eprintln( "Btn+++  " + view.getId() + "  name=" + name + "   age=" + age );
        switch (view.getId()) {
            case R.id.button:
                List<PersonEntity> person = DataGenerator.generatePersons();
                List<FamilyEntity> family = DataGenerator.generateFamilyForPersons( person );
                Logs.eprintln( "family size=" + family.size() );
                dataBase.familyDao().insertAll( family );
                dataBase.personDao().insertAll( person );
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                List<FamilyEntity> familys = dataBase.familyDao().loadFamilysList();
                setAdapterssF( familys );
                break;
            case R.id.button5:
                List<PersonEntity> personList = dataBase.personDao().loadAllPersonList();
                setAdapterss( personList );
                break;
        }
    }


    private void setAdapterss(List<PersonEntity> list) {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            datas.add( list.get( i ).getName() + "  like  " + list.get( i ).getAge() );
        }
        Logs.eprintln( "person size=" + list.size() );
        adapter = new DbListAdapter( this, datas );
        binding.recyclerview.setLayoutManager( new LinearLayoutManager( this ) );
        binding.recyclerview.setAdapter( adapter );
    }

    private void setAdapterssF(List<FamilyEntity> list) {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            datas.add( list.get( i ).getText() + "  like  " + list.get( i ).getLike() );
        }
        Logs.eprintln( "FamilyList size=" + list.size() );
        adapter = new DbListAdapter( this, datas );
        binding.recyclerview.setLayoutManager( new LinearLayoutManager( this ) );
        binding.recyclerview.setAdapter( adapter );
    }
}
