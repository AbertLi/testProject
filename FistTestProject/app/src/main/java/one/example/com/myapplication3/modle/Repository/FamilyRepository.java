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
public class FamilyRepository {
    private String TAG = "PersonRepository   ";
    private static FamilyRepository sInstance;
    private final AppDataBase mDatabase;
    ;
    private MediatorLiveData<List<FamilyEntity>> mObservableFamilys;

    private FamilyRepository(final AppDataBase database) {
        mDatabase = database;
        mObservableFamilys = new MediatorLiveData<>();


        mObservableFamilys.addSource( mDatabase.familyDao().loadFamilys(),
                familys -> {
                    Logs.eprintln( TAG, "familys size=" + familys.size() );
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableFamilys.postValue( familys );
                    }
                } );
    }

    public static FamilyRepository getInstance(final AppDataBase database) {
        if (sInstance == null) {
            synchronized (FamilyRepository.class) {
                if (sInstance == null) {
                    sInstance = new FamilyRepository( database );
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<FamilyEntity>> getAllFamilys() {
        return mObservableFamilys;
    }

    public LiveData<List<FamilyEntity>> getFamilyBypersonId(final int personId) {
        return mDatabase.familyDao().loadFamilyByPersonId( personId );
    }
}
