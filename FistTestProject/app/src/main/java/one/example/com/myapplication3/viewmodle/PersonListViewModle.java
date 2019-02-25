package one.example.com.myapplication3.viewmodle;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import one.example.com.myapplication3.AppExecutors;
import one.example.com.myapplication3.db.AppDataBase;
import one.example.com.myapplication3.modle.Repository.PersonRepository;
import one.example.com.myapplication3.db.entity.PersonEntity;
import one.example.com.myapplication3.utile.ApplicationUtile;

public class PersonListViewModle extends AndroidViewModel {
    private final PersonRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<PersonEntity>> mObservableProducts;

    public PersonListViewModle(Application application) {
        super(application);

        mObservableProducts = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableProducts.setValue(null);

        mRepository = PersonRepository.getInstance();
        LiveData<List<PersonEntity>> products = mRepository.getPersons();

        // observe the changes of the products from the database and forward them
        mObservableProducts.addSource(products, mObservableProducts::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<PersonEntity>> getPerson() {
        return mObservableProducts;
    }

    /**
     * 2019-01-11 暂时不做模糊搜索
     *
     * @param query
     * @return
     */
    public LiveData<List<PersonEntity>> searchPerson(String query) {
        return mRepository.searchProducts(query);
    }
}
