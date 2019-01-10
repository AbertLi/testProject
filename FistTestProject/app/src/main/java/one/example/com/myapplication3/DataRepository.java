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
    private static DataRepository sInstance;
    private final AppDataBase mDatabase;
    private MediatorLiveData<List<PersonEntity>> mObservableProducts;

    private DataRepository(final AppDataBase database) {
        mDatabase = database;
        mObservableProducts = new MediatorLiveData<>();

        mObservableProducts.addSource(mDatabase.personDao().loadAllProducts(),
                personEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableProducts.postValue(personEntities);
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
        return mObservableProducts;
    }

    public LiveData<PersonEntity> loadPersons(final int productId) {
        return mDatabase.personDao().loadProduct(productId);
    }

    public LiveData<List<FamilyEntity>> loadFamilys(final int productId) {
        return mDatabase.familyDao().loadComments(productId);
    }

//    public LiveData<List<FamilyEntity>> searchProducts(String query) {
//        return mDatabase.familyDao().searchAllProducts(query);
//    }
}
