package one.example.com.myapplication3.ui.retrofit.eg;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import one.example.com.myapplication3.ui.retrofit.Content;
import one.example.com.myapplication3.utile.logutile.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRequestRetrofitUtil {

    public static void netRequestRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Content.INSTANCE.getBaseUrl2())
//                .addCallAdapterFactory(cli)//添加拦截器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService server = retrofit.create(GitHubService.class);
        Call call = (Call)server.listRepos("user");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.e("TEST","body="+response.body().toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("TEST","e = "+t.getMessage());
            }
        });
    }
}
