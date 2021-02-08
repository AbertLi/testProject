package one.example.com.myapplication3.ui.async.handlerthread;

import android.os.Handler;
import android.os.HandlerThread;

import one.example.com.myapplication3.Logs;

public class DownLoadTaskManager {
    HandlerThread handlerThread;
    Handler handler;

    public DownLoadTaskManager() {
        handlerThread = new HandlerThread("download_thread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }


    public void download(String downloadUrl) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                downLoadTask(downloadUrl);
            }
        });
    }

    /**
     * 模拟耗时的下载任务
     *
     * @param downloadUrl 下载地址
     */
    public void downLoadTask(String downloadUrl) {
        int dataCount = 10;
        for (int i = 1; i <= dataCount; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Logs.iprintln("DownloadTask", downloadUrl + " 进度 =" + (100 * i / dataCount) + "%");
        }
    }

    public void quit() {
        //清空消息队列中的消息。Api1就支持
        handlerThread.quit();
    }

    public void quitSafely() {
        //清空消息队列中的延时消息。Api18才支持
        handlerThread.quitSafely();
    }

    public int getThreadId() {
        return handlerThread.getThreadId();
    }
}
