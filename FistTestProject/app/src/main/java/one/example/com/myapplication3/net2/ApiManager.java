package one.example.com.myapplication3.net2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiManager {
    @GET("login/")
    Call<LoginResult> getData(@Query("name") String name, @Query("password") String pw);
}
