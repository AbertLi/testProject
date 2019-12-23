package one.example.com.myapplication3.ui.imageload.myImageLoad;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static InputStream download(String key) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(key).openConnection();
        return urlConnection.getInputStream();
    }
}