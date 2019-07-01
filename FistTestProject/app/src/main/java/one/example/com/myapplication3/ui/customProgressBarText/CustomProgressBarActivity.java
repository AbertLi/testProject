package one.example.com.myapplication3.ui.customProgressBarText;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import one.example.com.myapplication3.R;
import one.example.com.myapplication3.ui.customProgressBarText.progress.ViewProgressBar;

public class CustomProgressBarActivity extends Activity implements View.OnClickListener {

    private ViewProgressBar mProgressView;
    private final int FOR_SCROLL = 1;
    private final int DELAY = 500;
    private float mProgress;
    private int mStateType = Constant.DOWNLOAD;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case FOR_SCROLL:
                    if(mProgress < 100) {
                        mProgress += 4.0;
                        mProgressView.setProgress(mProgress);
                        mProgressView.setStateType(Constant.DOWNLOAD);
                        mHandler.sendEmptyMessageDelayed(FOR_SCROLL, DELAY);
                    } else {
                        mStateType = Constant.FINISH;
                        mProgressView.setProgress(100);
                        mProgressView.setStateType(mStateType);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progress_bar);
        mProgressView = (ViewProgressBar) findViewById(R.id.textProgress);
        mProgressView.setOnClickListener(this);
        mProgressView.setStateType(Constant.DEFAULT);
        String text = String.format("%.1f%%", 53.6897);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v == null) {
            return;
        }
        if(v.getId() == R.id.textProgress) {
            switch (mStateType) {
                case Constant.DOWNLOAD:
                    mStateType = Constant.PAUSE;
                    mHandler.sendEmptyMessageDelayed(FOR_SCROLL, DELAY);
                    break;
                case Constant.PAUSE:
                    mStateType = Constant.DOWNLOAD;
                    mProgressView.setStateType(Constant.PAUSE);
                    mHandler.removeMessages(FOR_SCROLL);
                    break;
                case Constant.FINISH:
                    mProgressView.setStateType(Constant.OPENT);
                    mProgressView.setProgress(0f);
                    Toast.makeText(this, getResources().getString(R.string.ok), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(FOR_SCROLL);
    }
}
