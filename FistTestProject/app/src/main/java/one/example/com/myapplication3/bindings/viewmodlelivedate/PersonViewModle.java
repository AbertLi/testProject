package one.example.com.myapplication3.bindings.viewmodlelivedate;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.bindings.PersonBean;

public class PersonViewModle extends AndroidViewModel {
    public PersonViewModle(@NonNull Application application) {
        super( application );
    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    // 创建LiveData
    private MutableLiveData<PersonBean> mAccount = new MutableLiveData<>();


    public void setPerson(String name, String age) {
        mAccount.setValue( new PersonBean( name, age ) );
    }

    public MutableLiveData<PersonBean> getPerson() {
        return mAccount;
    }

    // 当MyActivity被销毁时，Framework会调用ViewModel的onCleared()
    @Override
    protected void onCleared() {
        Logs.eprintln( "AccountModel ==========onCleared()==========" );
        super.onCleared();
    }
}
