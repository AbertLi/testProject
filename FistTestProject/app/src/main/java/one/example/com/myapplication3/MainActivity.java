package one.example.com.myapplication3;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import one.example.com.myapplication3.bindings.ListActivity;
import one.example.com.myapplication3.net2.ApiManager;
import one.example.com.myapplication3.net2.LoginResult;
import one.example.com.myapplication3.net.GetRequest_Interface;
import one.example.com.myapplication3.net.Translation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String TAG="MyApplication";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn(View view){
//        request();
//        login();
          switch (view.getId()){
              case R.id.button:
                  System.out.println("ThreadName="+Thread.currentThread().getName());
                  break;
              case R.id.button2:
                  kotlin();
                  break;
              case R.id.button3:
                  Intent intent=new Intent(this,ListActivity.class);
                  startActivity(intent);
                  break;
              case R.id.button4:
                  select();
                  break;
              case R.id.button5:
                  insert();
                  break;
          }
    }


    private void  insert(){

    }

    private void  select(){

    }






    private void  kotlin(){

    }
    public void login(){
             Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://localhost:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
              ApiManager apiService=retrofit.create(ApiManager.class);

              Call<LoginResult> call = apiService.getData("lyk", "1234");
              call.enqueue(new Callback<LoginResult>() {
                  @Override
                  public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                  }

                  @Override
                  public void onFailure(Call<LoginResult> call, Throwable t) {

                  }
              });
    }

    public static void request() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
                response.body().show();
                System.out.println("ThreadName ="+Thread.currentThread().getName());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }
}

//



