//package one.example.com.myapplication3.db;
//
//import android.content.Context;
//import java.util.List;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.ViewModel;
//
//public class UserViewModel extends ViewModel {
//
//    private LiveData<List<User>> mUsers;
//
//    public LiveData<List<User>> getUsers(Context context) {
//        if (mUsers== null) {
//            mUsers= DBUtiles.getAppDatabase(context).userDao().getAllUseLiveData();
//        }
//        return mUsers;
//    }
//
//}
