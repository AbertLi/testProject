package one.example.com.myapplication3.viewmodle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class PersonListViewModle extends AndroidViewModel {

    public PersonListViewModle(@NonNull Application application) {
        super( application );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
