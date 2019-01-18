package one.example.com.myapplication3.viewmodle;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import one.example.com.myapplication3.BaseApplication;
import one.example.com.myapplication3.DataRepository;
import one.example.com.myapplication3.db.entity.FamilyEntity;
import one.example.com.myapplication3.db.entity.PersonEntity;

public class FamilyViewModle extends AndroidViewModel {
    private final DataRepository mRepository;
    private final MediatorLiveData<List<FamilyEntity>> mObservableProducts;

    public FamilyViewModle(Application application) {
        super( application );
        mObservableProducts = new MediatorLiveData<>();
        mObservableProducts.setValue( null );
        mRepository = ((BaseApplication) application).getRepository();
        LiveData<List<FamilyEntity>> family = mRepository.getAllFamilys();
        // observe the changes of the products from the database and forward them
        mObservableProducts.addSource( family, mObservableProducts::setValue );
    }

    public LiveData<List<FamilyEntity>> getAllFamily() {
        return mObservableProducts;
    }

    /**
     * 2019-01-11 暂时不做模糊搜索
     *
     * @param personId
     * @return
     */
    public LiveData<List<FamilyEntity>> getFamilyByPersonId(int personId) {
        return mRepository.getFamilyBypersonId( personId );
    }
}
