package one.example.com.myapplication3;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import one.example.com.myapplication3.db.AppDataBase;
import one.example.com.myapplication3.db.entity.FamilyEntity;
import one.example.com.myapplication3.db.entity.PersonEntity;
/**
 * Repository handling the work with products and comments.
 */
public class DataRepository {
    private String TAG="DataRepository   ";
    private static DataRepository sInstance;
    private final AppDataBase mDatabase;
    private MediatorLiveData<List<PersonEntity>> mObservablePersons
            ;
    private MediatorLiveData<List<FamilyEntity>> mObservableFamilys;

    private DataRepository(final AppDataBase database) {
        mDatabase = database;
        mObservablePersons = new MediatorLiveData<>();
        mObservableFamilys = new MediatorLiveData<>();

        mObservablePersons.addSource(mDatabase.personDao().loadAllPerson(),
                personEntities -> {
                    Logs.eprintln( TAG,"personEntities size="+personEntities.size() );
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservablePersons.postValue(personEntities);
                    }
                });

        mObservableFamilys.addSource(mDatabase.familyDao().loadFamilys(),
                familys -> {
                    Logs.eprintln( TAG,"familys size="+familys.size() );
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableFamilys.postValue(familys);
                    }
                });
    }

    public static DataRepository getInstance(final AppDataBase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
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
        return mDatabase.personDao().searchAllPerson(query);
    }



    public LiveData<List<FamilyEntity>> getAllFamilys() {
        return mObservableFamilys;
    }

    public LiveData<List<FamilyEntity>> getFamilyBypersonId(final int personId) {
        return mDatabase.familyDao().loadFamilyByPersonId(personId);
    }
}
