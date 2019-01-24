package one.example.com.myapplication3.modle.Repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.db.AppDataBase;
import one.example.com.myapplication3.db.entity.FamilyEntity;
import one.example.com.myapplication3.db.entity.PersonEntity;

/**
 * Repository handling the work with products and comments.
 * 此处的Repository用于两个数据对象其实不太合理违背写了开闭原则。大项目不建议这样写。
 */
public class PersonRepository {
    private String TAG = "PersonRepository   ";
    private static PersonRepository sInstance;
    private final AppDataBase mDatabase;
    private MediatorLiveData<List<PersonEntity>> mObservablePersons;

    private PersonRepository(final AppDataBase database) {
        mDatabase = database;
        mObservablePersons = new MediatorLiveData<>();

        mObservablePersons.addSource( mDatabase.personDao().loadAllPerson(),
                personEntities -> {
                    Logs.eprintln( TAG, "personEntities size=" + personEntities.size() );
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservablePersons.postValue( personEntities );
                    }
                } );
    }

    public static PersonRepository getInstance(final AppDataBase database) {
        if (sInstance == null) {
            synchronized (PersonRepository.class) {
                if (sInstance == null) {
                    sInstance = new PersonRepository( database );
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<PersonEntity>> getPersons() {
        return mObservablePersons;
    }

    public LiveData<List<PersonEntity>> searchProducts(String query) {
        return mDatabase.personDao().searchAllPerson( query );
    }
}
