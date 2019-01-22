package one.example.com.myapplication3.modle.Repository;

import java.util.List;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import one.example.com.myapplication3.db.AppDataBase;
import one.example.com.myapplication3.db.entity.User;

public class UserRepository {
    AppDataBase mDatabase;
    private static UserRepository sInstance;
    private MediatorLiveData<List<User>> mObservablePersons;


    private UserRepository(final AppDataBase database) {
        mDatabase = database;
        mObservablePersons = new MediatorLiveData<>();

        mObservablePersons.addSource( database.userDao().getAllLiveData(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                mObservablePersons.postValue( users );
            }
        } );
    }

    public static UserRepository getInstance(final AppDataBase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new UserRepository( database );
                }
            }
        }
        return sInstance;
    }


    public MediatorLiveData<List<User>> getUserListLiveData() {
        return mObservablePersons;
    }


    public void insertData(List<User> list) {
        mDatabase.userDao().insertAll( (User[]) list.toArray() );
    }
}
