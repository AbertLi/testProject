package one.example.com.myapplication3.sample.main.webservice;

import io.reactivex.Single;
import retrofit2.CallAdapter;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit接口类
 * 命名规范：业务逻辑名+WebService
 * 返回数据命名规范：业务逻辑名+数据格式，如TopMovieJson, TopMoviePb
 */
@BaseUrl(value = "https://api.douban.com/v2/movie/"
        /*,test = "https://api.douban.com/v2/movie/"
        ,dev = "https://api.douban.com/v2/movie/"*/)
@ConvertFactory({ConvertFactory.DataType.Gson/*, ConvertFactory.DataType.OppoPb*/})
@CallAdapter(CallAdapter.CallAdapterType.RxJava2)
public interface TopMovieWebService {
    /**
     * 获取电影列表
     * 等同于 https://api.douban.com/v2/movie/top250?start=xxx&count=xxx
     */
    @GET("top250")
    public Single<TopMovieJson> getTopMovie(@Query("start") int start, @Query("count") int count);
}