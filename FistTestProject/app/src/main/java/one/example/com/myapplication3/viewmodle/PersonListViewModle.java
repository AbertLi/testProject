package one.example.com.myapplication3.viewmodle;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.db.entity.PersonBean;

public class PersonListViewModle extends AndroidViewModel {
    public PersonListViewModle(@NonNull Application application) {
        super( application );
    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    // 创建LiveData
    private MutableLiveData<List<PersonBean>> mAccount = new MutableLiveData<>();


    public void setPerson(String name, String age) {

    }

    public MutableLiveData<List<PersonBean>> getPerson() {
        return mAccount;
    }

    // 当MyActivity被销毁时，Framework会调用ViewModel的onCleared()
    @Override
    protected void onCleared() {
        Logs.eprintln( "PersonListViewModle  ==========onCleared()==========" );
        super.onCleared();
    }
}
