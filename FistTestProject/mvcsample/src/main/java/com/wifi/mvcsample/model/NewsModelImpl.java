//package com.wifi.mvcsample.model;
//
//import android.util.Log;
//
//import com.wifi.mvcsample.bean.News;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class NewsModelImpl implements NewsModel {
//    private final String baseUrl = "http://v.juhe.cn";
//    private final String key = "这个地方就写你在聚合数据上面申请的今日头条的KEY";
//
//    @Override
//    public void getWeather(String name, final OnNewsListener mlistener) {
//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(client.build())
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        MyService service = retrofit.create(MyService.class);
//        service.getNews(key, name).enqueue(new Callback<News>() {
//            @Override
//            public void onResponse(Call<News> call, Response<News> response) {
//                News news = response.body();
//                if (news.getReason().equals("成功的返回")) {
//                    Log.e("news", "成功");
//                    mlistener.onSuccess(news);
//                } else {
//                    Log.e("news", "失败");
//                    mlistener.onError();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<News> call, Throwable t) {
//                t.getStackTrace();
//                mlistener.onError();
//            }
//        });
//    }
//}