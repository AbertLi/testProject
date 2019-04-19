package one.example.com.myapplication3.utile.logutile;

import android.os.Build;
import android.os.Process;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import android.util.Log;

import java.util.Date;

/**
 * https://blog.csdn.net/wangmx1993328/article/details/80853443?utm_source=blogxgwz5
 * Process.killProcess(Process.myPid())杀掉当前进程
 * Process.myTid（） - 返回调用线程
 */
public class LogWriteUtile {
    private boolean isRelease = false;
    SimpleDateFormat simpleDateFormatFileName = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private static LogWriteUtile logWriteUtile;

    public static LogWriteUtile getInstance() {
        if (logWriteUtile == null) {
            synchronized (LogWriteUtile.class) {
                if (logWriteUtile == null) {
                    logWriteUtile = new LogWriteUtile();
                }
            }
        }
        return logWriteUtile;
    }

    /**
     * 每一天创建一个日志文件
     */
    public void init() {
        write(getFile(), getContent(LogWriteUtile.class.getName(), LevelUtile.INFO, getDelviceInfo()));
        close();
    }

    public void print(String tag, int level, String context) {
        log(level, tag, context);
        write(getFile(), getContent(tag, level, context));
        close();
    }

    public static String getDelviceInfo() {
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


    private File getFile() {
        String logName = simpleDateFormatFileName.format(new Date());
        /**采用相对路径创建日志目录，即会相对整个项目根目录进行创建*/
        File logDir = new File("logs");
        File logFile = new File(logDir, logName + ".log");
        return logFile;
    }


    private String getContent(String tag, int level, String content) {
        String time = simpleDateFormat.format(new Date());
        String levelStr = LevelUtile.getLevel(level);
        StringBuilder sb = new StringBuilder();
        sb.append(time)
                .append(" ")
                .append(Process.myPid())
                .append("-")
                .append(Process.myTid())
                .append(" /")
                .append(levelStr)
                .append("/ ")
                .append(tag)
                .append(content);
        return sb.toString();
    }

    FileWriter fileWriter;

    private void write(File logFile, String str) {
        try {
            if (str != null && !"".equals(str)) {
                /**根据文件创建对象，true表示写的内容会追加*/
                fileWriter = new FileWriter(logFile, true);
                fileWriter.append(str + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (fileWriter == null) {
            return;
        }
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(int level, String tag, String c) {
        if (isRelease) {
            return;
        }
        switch (level) {
            case LevelUtile.VERBOSE:
                Log.v(tag, c);
                break;
            case LevelUtile.DEBUG:
                Log.d(tag, c);
                break;
            case LevelUtile.INFO:
                Log.i(tag, c);
                break;
            case LevelUtile.WARN:
                Log.w(tag, c);
                break;
            case LevelUtile.ERROR:
                Log.e(tag, c);
                break;
            case LevelUtile.ASSERT:
                Log.e(tag, "a" + c);
                break;
        }


    }
}
