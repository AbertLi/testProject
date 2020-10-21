package one.example.com.myapplication3.ui.retrofit.eg;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);//如果user变量为空那么默认使用"user"
}
