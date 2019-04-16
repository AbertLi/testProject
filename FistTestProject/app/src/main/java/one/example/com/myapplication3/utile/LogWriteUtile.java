package one.example.com.myapplication3.utile;

import android.content.Context;
import android.os.Build;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * https://blog.csdn.net/wangmx1993328/article/details/80853443?utm_source=blogxgwz5
 *  Process.killProcess(Process.myPid())杀掉当前进程
 *  Process.myTid（） - 返回调用线程
 */
public class LogWriteUtile {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int ASSERT = 6;

    public static final String V = "V";
    public static final String D = "D";
    public static final String I = "I";
    public static final String W = "W";
    public static final String E = "E";
    public static final String A = "A";


    public static String getLogFilePath(Context context) {
        String filePath;
        File folder = context.getDir("file", 1);
        filePath = folder.getPath();
        return filePath;
    }

    public static String getDelviceInfo(){
        StringBuilder delviceInfo = new StringBuilder();
        delviceInfo.append("[")
                   .append(android.os.Build.BRAND)
                   .append(" ")
                   .append(Build.MODEL)
                   .append(" system_build_version:")
                   .append(Build.VERSION.RELEASE)
                   .append(" system_versionCode:")
                   .append(Build.VERSION.SDK_INT)
                   .append("]");

        return delviceInfo.toString();
    }




    public void test1() {
        try {
            String message = "你好，中国！";
            if (message != null && !"".equals(message)) {
                /** 采用日期做文件名，日期加时间做消息头*/
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

                String logName = simpleDateFormat1.format(new Date());
                String messageHead = simpleDateFormat2.format(new Date());

                /**采用相对路径创建日志目录，即会相对整个项目根目录进行创建*/
                File logDir = new File("logs");
                File logFile = new File(logDir, logName + ".log");

                /**根据文件创建对象，true表示写的内容会追加*/
                FileWriter fileWriter = new FileWriter(logFile, true);
                fileWriter.write(messageHead + "：" + message + " 1\r\n");
                fileWriter.write(messageHead + "：" + message + " 2\r\n");
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
