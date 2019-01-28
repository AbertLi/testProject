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
import one.example.com.myapplication3.db.entity.User;
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
        dataBase = AppDataBase.getInstance( this,  AppExecutors.getInstance() );
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
                User[] userlist = new User[person.size()];
                for (int i = 0; i < person.size(); i++) {
                    User user = new User();
                    user.firstName = person.get( i ).getName();
                    user.lastName = person.get( i ).getAge();
                    user.age="age is "+person.get( i ).getAge();
                    user.uid = i+1;
                    userlist[i]=user;
                }
                dataBase.userDao().insertAll( userlist );
                break;
            case R.id.button2:
//                Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                        Log.d(TAG, "console--> 1");
//                        emitter.onNext(1);
//                        Log.d(TAG, "console--> 2");
//                        emitter.onNext(2);
//                        Log.d(TAG, "console--> 3");
//                        emitter.onNext(3);
//                        Log.d(TAG, "console--> complete");
//                        emitter.onComplete();
//                        Log.d(TAG, "console--> 4");
//                        emitter.onNext(4);
//                    }
//                }).subscribe(new Observer<Integer>() {
//                    private Disposable mDisposable;
//                    private int i;
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "subscribe");
//                        mDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(Integer value) {
//                        Log.d(TAG, "onNext: " + value);
//                        i++;
//                        if (i == 2) {
//                            Log.d(TAG, "dispose");
//                            mDisposable.dispose();
//                            Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "error");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "complete");
//                    }
//                });
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                List<FamilyEntity> familys = dataBase.familyDao().loadFamilysList();
                setAdapterssF( familys );
                break;
            case R.id.button5:
                List<User> personList = dataBase.userDao().getAll();
                setAdapterss( personList );
                break;
        }
    }


    private void setAdapterss(List<User> list) {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            datas.add( list.get( i ).firstName + "  like  " + list.get( i ).lastName +"  agessss "+list.get( i ).age);//
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
