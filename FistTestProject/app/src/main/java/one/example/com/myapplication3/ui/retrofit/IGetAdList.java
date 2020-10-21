package one.example.com.myapplication3.ui.retrofit;

import retrofit2.Call;
import retrofit2.http.POST;

public interface IGetAdList {
    @POST("cdsededd8xszsource")
    public Call<AdBean> getAdList(String body);
}
