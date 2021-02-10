package one.example.com.myapplication3.ui.async.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class SAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    @Override
    protected Bitmap doInBackground(String... strings) {
        return null;
    }


    interface IResultCallBack {
        public void ResultCallback(Bitmap result);
    }

}
