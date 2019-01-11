package one.example.com.myapplication3.ui.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.databinding.ActivityH5DetailBinding;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class H5DetailActivity extends AppCompatActivity {
    ActivityH5DetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_h5_detail );

        WebView webView = binding.webView;
        webView.getSettings().setJavaScriptEnabled( true );
        webView.loadUrl( "https://www.baidu.com" );
    }


    public void btn(View view) {
        finish();
    }
}
