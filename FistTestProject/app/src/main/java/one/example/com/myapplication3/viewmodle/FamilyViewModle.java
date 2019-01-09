package one.example.com.myapplication3.viewmodle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.db.entity.PersonEntity;

public class FamilyViewModle extends AndroidViewModel {
    public FamilyViewModle(@NonNull Application application) {
        super( application );
    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    // 创建LiveData
    private MutableLiveData<PersonEntity> mAccount = new MutableLiveData<>();


    public void setPerson(String name, String age) {
        mAccount.setValue( new PersonEntity( name, age ) );
    }

    public MutableLiveData<PersonEntity> getPerson() {
        return mAccount;
    }

    // 当MyActivity被销毁时，Framework会调用ViewModel的onCleared()
    @Override
    protected void onCleared() {
        Logs.eprintln( "AccountModel ==========onCleared()==========" );
        super.onCleared();
    }
}
