package one.example.com.myapplication3.sample.main.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import one.example.com.myapplication3.sample.main.db.Subject;
import one.example.com.myapplication3.sample.main.model.TopMovieRepository;

public class SampleViewModel extends AndroidViewModel { // or extends ViewModel
    private static final String TAG = "SampleViewModel";

    private MutableLiveData<List<Subject>> mMovies;

    private TopMovieRepository mRepository;

    public SampleViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TopMovieRepository();
        mMovies = new MutableLiveData<>();
        mMovies.setValue(null);
        load();
    }

    public LiveData<List<Subject>> getMovies() {
        return mMovies;
    }

    @SuppressLint("CheckResult")
    public void load() {
        mRepository.getTopMovies()
                   .observeOn(AndroidSchedulers.mainThread()) // ui线程通知
                   .subscribe(mMovies::setValue); // 更新livedata数据
    }

    @SuppressLint("CheckResult")
    public void refresh() {
        mRepository.refresh()
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(mMovies::setValue, this::printError);
    }

    @Override
    protected void onCleared() {
        // 当viewmodel生命周期结束时触发
        // 用户主动离开后， 在Activity/Fragment的onDestroy时调用
        super.onCleared();
    }

    private void printError(Throwable e) {
        Log.e(TAG, "", e);
    }
}