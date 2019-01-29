package one.example.com.myapplication3.sample.main.model;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import one.example.com.myapplication3.sample.db.AppDatabase;
import one.example.com.myapplication3.sample.main.db.Subject;
import one.example.com.myapplication3.sample.main.db.SubjectDao;
import one.example.com.myapplication3.sample.main.webservice.TopMovieJson;
import one.example.com.myapplication3.sample.main.webservice.TopMovieWebService;

public class TopMovieRepository {

    private static final String TAG = "TopMovieRepository";

    private static final int GET_COUNT = 20;
    private static final int MAX_COUNT = 250;


    private final TopMovieWebService mWebService;
    private final SubjectDao mDao;

    public TopMovieRepository() {
        mWebService = WebServiceFactory.create(TopMovieWebService.class);
        mDao = AppDatabase.getInstance().subjectDao();
    }

    // 完整代码的写法
    public Single<List<Subject>> getTopMovies1() {
        return mDao.getAll()
                   // 在db线程处理
                   .subscribeOn(Schedulers.single())
                   // 如果为空则从网络中获取
                   .flatMap(new Function<List<Subject>, SingleSource<List<Subject>>>() {
                       @Override
                       public SingleSource<List<Subject>> apply(List<Subject> subjects) throws Exception {
                           return subjects != null || subjects.isEmpty()
                                   ? Single.just(subjects)
                                   : getTopMoviesFromWeb();
                       }
                   }) // 异常记录
                   .doOnError(new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           printError(throwable);
                       }
                   });

    }

    // 使用lambda语法优化
    // 推荐写法，首选
    public Single<List<Subject>> getTopMovies() {
        return mDao.getAll()
                   .subscribeOn(Schedulers.single()) // 在db线程查询
                   .flatMap(l -> l != null && !l.isEmpty() ? Single.just(l) : getTopMoviesFromWeb()) // 数据为空则从网络线程获取
                   .doOnError(this::printError); // 在db线程打印
    }

    // 不如上面的好
    @SuppressLint("CheckResult")
    public void getTopMovies(MutableLiveData<List<Subject>> to, Consumer<Throwable> onError) {
        mDao.getAll()
            .subscribeOn(Schedulers.single()) // 在db线程查询
            .flatMap(l -> l != null ? Single.just(l) : getTopMoviesFromWeb()) // 数据为空则从网络线程获取
            .doOnError(this::printError) // 在db线程打印
            .subscribe(to::postValue, onError);
    }

    // 也可以设计成这种方式, 不推荐
    @SuppressLint("CheckResult")
    public LiveData<List<Subject>> getTopMovies(Consumer<Throwable> onError) {
        MutableLiveData<List<Subject>> result = new MutableLiveData<>();
        mDao.getAll()
            .subscribeOn(Schedulers.single()) // 在db线程查询
            .flatMap(l -> l != null ? Single.just(l) : getTopMoviesFromWeb()) // 数据为空则从网络线程获取
            .doOnError(this::printError) // 在db线程打印
            .subscribe(result::postValue, onError);
        return result;
    }

    // 或者这种方式
    @SuppressLint("CheckResult")
    public void getTopMovies(Consumer<List<Subject>> onSuccess, Consumer<Throwable> onError) {
        mDao.getAll()
            .subscribeOn(Schedulers.single()) // 在db线程查询
            .flatMap(l -> l != null ? Single.just(l) : getTopMoviesFromWeb()) // 数据为空则从网络线程获取
            .doOnError(this::printError) // 在db线程打印
            .subscribe(onSuccess, onError);
    }

    @SuppressLint("CheckResult")
    public Single<List<Subject>> refresh() {
        return getTopMoviesFromWeb();
    }

    public Single<List<Subject>> getTopMoviesFromWeb() {
        int start = (int) (Math.random() * 200);
        return mWebService.getTopMovie(/*start*/start, GET_COUNT)
                          .flatMapObservable(moviesJson -> Observable.fromIterable(moviesJson.getSubjects())) // 拆成数组
                          .map(TopMovieRepository::convert) // 转成entity
                          .toList() // 合并成一个list
                          .subscribeOn(Schedulers.io()) // io线程处理上面这些事情
                          .doOnError(this::printError)
                          .observeOn(Schedulers.single()) // db线程写入
                          .doOnSuccess(l -> mDao.nukeTable()) // 炸掉db
                          .doOnSuccess(mDao::insertSubjects); // 插入db
    }

    /**
     * 将webservice数据转成entity数据
     */
    private static Subject convert(TopMovieJson.Subject pojo) {
        Subject subject = new Subject();
        subject.setImage(pojo.getImages().getSmall());
        subject.setTitles(new String[]{pojo.getTitle(), pojo.getOriginalTitle()});
        String[] directors = new String[pojo.getDirectors().size()];
        for (int i = 0; i < directors.length; i++) {
            directors[i] = pojo.getDirectors().get(i).getName();
        }
        subject.setDirectors(directors);
        String[] casts = new String[pojo.getCasts().size()];
        for (int i = 0; i < casts.length; i++) {
            casts[i] = pojo.getCasts().get(i).getName();
        }
        subject.setCasts(casts);
        subject.setYears(pojo.getYear());
        String[] genres = new String[pojo.getGenres().size()];
        for (int i = 0; i < genres.length; i++) {
            genres[i] = pojo.getGenres().get(i);
        }
        subject.setAlt(pojo.getAlt());
        subject.setSubjectId(pojo.getId());
        subject.setRating(pojo.getRating().getAverage());
        return subject;
    }

    private void printError(Throwable e) {
        Log.e(TAG, "", e);
    }
}