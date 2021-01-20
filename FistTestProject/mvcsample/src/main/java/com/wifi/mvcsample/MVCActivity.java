//package com.wifi.mvcsample;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.wifi.mvcsample.bean.News;
//import com.wifi.mvcsample.model.NewsModelImpl;
//import com.wifi.mvcsample.model.OnNewsListener;
////日本人写的demo https://github.com/hkusu/android-mvc-sample
//class MVCActivity extends AppCompatActivity implements OnNewsListener, View.OnClickListener {
//    private EditText editText = null;
//    private Button button = null;
//    private TextView title, date, category, author_name = null;
//
//    private NewsModelImpl weatherModel = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_m_v_c);
//        weatherModel = new NewsModelImpl();
//        initView();
//    }
//
//    public void initView() {
//        editText = (EditText) findViewById(R.id.edit);
//        button = (Button) findViewById(R.id.button);
//        title = (TextView) findViewById(R.id.title);
//        date = (TextView) findViewById(R.id.date);
//        category = (TextView) findViewById(R.id.category);
//        author_name = (TextView) findViewById(R.id.author_name);
//
//        button.setOnClickListener(this);
//    }
//
//    //设置数据
//    public void setView(News news) {
//        title.setText(news.getResult().getData().get(1).getTitle());
//        date.setText(news.getResult().getData().get(1).getDate());
//        category.setText(news.getResult().getData().get(1).getCategory());
//        author_name.setText(news.getResult().getData().get(1).getAuthor_name());
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.button) {
//            String name = editText.getText().toString();
//            if (!name.equals("")) {
//                weatherModel.getWeather(name, this);
//            } else {
//                Toast.makeText(this, "输入的新闻标题不能为空", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @Override
//    public void onSuccess(News news) {
//        setView(news);
//    }
//
//    @Override
//    public void onError() {
//        Toast.makeText(this, "出错了哦！", Toast.LENGTH_SHORT).show();
//    }
//}
