package one.example.com.myapplication3.ui.async.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Looper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
public class MyAsyncTask extends AsyncTask<String,Integer,Bitmap> {
    IResultCallBack mCallBack;

    public MyAsyncTask(IResultCallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        publishProgress(100);//在任务线程中根据具体的进度返回进度值大小
        return downloadUrlBitmap(params[0]);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //此方法在主线程中调用
        //values就是publishProgress返回的值。
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        //在UI中调用，返回结果
        mCallBack.ResultCallback(result);
    }

    @Override
    protected void onCancelled(Bitmap result) {
        super.onCancelled(result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPreExecute() {
        //在执行异步操作的时候调用。
        super.onPreExecute();
    }

    interface IResultCallBack {
        public void ResultCallback(Bitmap result);
    }

    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
