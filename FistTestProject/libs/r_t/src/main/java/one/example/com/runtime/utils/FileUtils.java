package one.example.com.runtime.utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileUtils {
    private static final String TAG = "FileUtils";

    /**
     * 写入文件
     * （当data = “” 的时候情况文件内容）
     *
     * @param filePath 文件地址
     * @param data     写入文件的数据内容
     * @return 是否写入成功
     */
    public static boolean writeData(String filePath, String data) {
        if (!isFilePath(filePath)) {
            Logs.eprintln(TAG, "Wrong address written to file");
            return false;
        }

        if (data == null) {
            Logs.eprintln(TAG, "Data is null");
            return false;
        }
        File file = new File(filePath);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes(Charset.forName("UTF-8")));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     *
     * @param filePath
     * @return
     */
    public static String readData(String filePath) {
        File file = new File(filePath);
        BufferedReader fr = null;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            fr = new BufferedReader(new FileReader(file));
            while ((line = fr.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 判断是否是文件路径。
     * 如果没有创建文件夹就创建文件的存放的文件夹。
     *
     * @param filePath
     * @return
     */
    private static boolean isFilePath(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }

        File file = new File(filePath);
        File fileParents = new File(file.getParent());
        if (file.isDirectory()) {
            Logs.eprintln(TAG, "filepath is Directory fail");
            return false;
        }
        if (!fileParents.exists()) {
            fileParents.mkdirs();
        }
        return true;
    }


    /**
     * 是否创建
     * @param path
     * @return
     */
    public static boolean isCreate(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        return file.exists();
    }
}
