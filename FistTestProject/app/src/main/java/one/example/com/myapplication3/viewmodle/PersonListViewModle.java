package one.example.com.myapplication3.viewmodle;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import one.example.com.myapplication3.BaseApplication;
import one.example.com.myapplication3.DataRepository;
import one.example.com.myapplication3.db.entity.PersonEntity;

public class PersonListViewModle extends AndroidViewModel {

//    public PersonListViewModle(@NonNull Application application) {
//        super( application );
//    }
//
//    @Override
//    protected void onCleared() {
//        super.onCleared();
//    }


    private final DataRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<PersonEntity>> mObservableProducts;

    public PersonListViewModle(Application application) {
        super(application);

        mObservableProducts = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableProducts.setValue(null);

        mRepository = ((BaseApplication) application).getRepository();
        LiveData<List<PersonEntity>> products = mRepository.getPersons();

        // observe the changes of the products from the database and forward them
        mObservableProducts.addSource(products, mObservableProducts::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<PersonEntity>> getProducts() {
        return mObservableProducts;
    }

//    public LiveData<List<PersonEntity>> searchProducts(String query) {
//        return mRepository.searchProducts(query);
//    }
}
