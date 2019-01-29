//package one.example.com.myapplication3.viewmodle;
//
//import android.app.Application;
//
//import java.util.List;
//
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MediatorLiveData;
//import one.example.com.myapplication3.AppExecutors;
//import one.example.com.myapplication3.db.AppDataBase;
//import one.example.com.myapplication3.db.entity.User;
//import one.example.com.myapplication3.modle.Repository.UserRepository;
//import one.example.com.myapplication3.utile.ApplicationUtile;
//
//public class UserViewModle extends AndroidViewModel {
//    private final UserRepository mRepository;
//    private final MediatorLiveData<List<User>> mObservableProducts;
//
//    public UserViewModle(Application application) {
//        super(application);
//        mObservableProducts = new MediatorLiveData<>();
//        mObservableProducts.setValue(null);
//        mRepository = UserRepository.getInstance(AppDataBase.getInstance(ApplicationUtile.getApplication(),
//                AppExecutors.getInstance()));
//        LiveData<List<User>> family = mRepository.getUserListLiveData();
////        mObservableProducts.addSource( family, new Observer<List<User>>() {
////            @Override
////            public void onChanged(List<User> users) {
////                mObservableProducts.postValue( users );
////            }
////        } );
//        //上面式子等价于下面式子
//        mObservableProducts.addSource(family, mObservableProducts::setValue);
//
//    }
//
//    public LiveData<List<User>> getAllFamily() {
//        return mObservableProducts;
//    }
//
//
//    public void insert(List<User> list) {
//        mRepository.insertData(list);
//    }
//
//    /**
//     * 2019-01-11 暂时不做模糊搜索
//     *
//     * @param personId
//     * @return
//     */
////    public LiveData<List<User>> getFamilyByPersonId(int personId) {
////    }
//}
