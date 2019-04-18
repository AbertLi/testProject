package one.example.com.myapplication3.utile.logutile;

import android.content.Context;

import java.io.File;

public class PathUtile {
    public static String getLogFilePath(Context context, String app_folderName) {
        String filePath;
        File folder = context.getDir(app_folderName, Context.MODE_PRIVATE);
        filePath = folder.getPath();
        return filePath;
    }
}
