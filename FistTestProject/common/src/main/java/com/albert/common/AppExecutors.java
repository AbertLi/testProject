package com.albert.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.heytap.browser.tools.NamedRunnable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executor;

public class AppExecutors {
    private static AppExecutors sInstance;
    private final Executor mDiskIO;
    private final Scheduler mDiskIOScheduler = Schedulers.io();
    private final Executor mNetworkIO;
    private final Scheduler mNetworkIOScheduler;
    private final AppExecutors.HandleExecutor mMainExecutor;
    private final Scheduler mMainScheduler;
    private final Executor mComputationExecutor;
    private final Scheduler mComputationScheduler;
    private final HandlerThread mBackgroundThread;
    private final AppExecutors.HandleExecutor mBackgroundExecutor;
    private final Scheduler mBackgroundScheduler;

    private static AppExecutors getInstance() {
        if (sInstance == null) {
            Class var0 = AppExecutors.class;
            synchronized(AppExecutors.class) {
                if (sInstance == null) {
                    sInstance = new AppExecutors();
                }
            }
        }

        return sInstance;
    }

    private AppExecutors() {
        this.mDiskIO = new AppExecutors.SchedulerExecutor(this.mDiskIOScheduler);
        this.mNetworkIOScheduler = Schedulers.io();
        this.mNetworkIO = new AppExecutors.SchedulerExecutor(this.mNetworkIOScheduler);
        this.mMainExecutor = new AppExecutors.HandleExecutor(new Handler(Looper.getMainLooper()));
        this.mMainScheduler = Schedulers.from(this.mMainExecutor);
        this.mComputationScheduler = Schedulers.computation();
        this.mComputationExecutor = new AppExecutors.SchedulerExecutor(this.mComputationScheduler);
        this.mBackgroundThread = new HandlerThread("AppExecutors_backgroundthread");
        this.mBackgroundThread.start();
        this.mBackgroundExecutor = new AppExecutors.HandleExecutor(new Handler(this.mBackgroundThread.getLooper()));
        this.mBackgroundScheduler = Schedulers.from(this.mBackgroundExecutor);
    }

    public static Executor diskIO() {
        return getInstance().mDiskIO;
    }

    public static Scheduler DISK_IO() {
        return getInstance().mDiskIOScheduler;
    }

    public static void runOnDiskIO(NamedRunnable runnable) {
        getInstance().mDiskIO.execute(runnable);
    }

    public static Executor networkIO() {
        return getInstance().mNetworkIO;
    }

    public static Scheduler NETWORK_IO() {
        return getInstance().mNetworkIOScheduler;
    }

    public static void runOnNetworkIO(NamedRunnable runnable) {
        getInstance().mNetworkIO.execute(runnable);
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static Executor mainThread() {
        return getInstance().mMainExecutor;
    }

    public static Scheduler MAIN_THREAD() {
        return getInstance().mMainScheduler;
    }

    public static void runOnMainThread(NamedRunnable runnable) {
        getInstance().mMainExecutor.execute(runnable);
    }

    public static void runOnMainThread(Runnable runnable) {
        getInstance().mMainExecutor.execute(runnable);
    }

    public static void runOnMainThread(NamedRunnable runnable, Long delay) {
        getInstance().mMainExecutor.postDelay(runnable, delay);
    }

    public static void removeMainRunnable(Runnable runnable) {
        getInstance().mMainExecutor.removeRunnable(runnable);
    }

    public static void runOnMainThread(Runnable runnable, Long delay) {
        getInstance().mMainExecutor.postDelay(runnable, delay);
    }

    public static void removeOnMainThread(Runnable runnable) {
        getInstance().mMainExecutor.removeRunnable(runnable);
    }

    public static Looper backgroundThreadLooper() {
        return getInstance().mBackgroundThread.getLooper();
    }

    public static void runOnBackground(NamedRunnable runnable, long delay) {
        getInstance().mBackgroundExecutor.postDelay(runnable, delay);
    }

    public static void runOnBackground(NamedRunnable runnable) {
        getInstance().mBackgroundExecutor.execute(runnable);
    }

    public static Handler getBackgroundHandler() {
        return getInstance().mBackgroundExecutor.mHandler;
    }

    public static Executor background() {
        return getInstance().mBackgroundExecutor;
    }

    public static Scheduler BACKGROUND() {
        return getInstance().mBackgroundScheduler;
    }

    public static Scheduler COMPUTATION() {
        return getInstance().mComputationScheduler;
    }

    public static Executor computation() {
        return getInstance().mComputationExecutor;
    }

    public static Executor worker() {
        return getInstance().mDiskIO;
    }

    public static Scheduler WORKER() {
        return getInstance().mDiskIOScheduler;
    }

    public static void runOnWorkThread(NamedRunnable runnable) {
        getInstance().mDiskIO.execute(runnable);
    }

    public static void runOnWorkThread(Runnable runnable) {
        getInstance().mDiskIO.execute(runnable);
    }

    private static class SchedulerExecutor implements Executor {
        private final Scheduler mScheduler;

        private SchedulerExecutor(Scheduler scheduler) {
            this.mScheduler = scheduler;
        }

        public void execute(Runnable command) {
            this.mScheduler.scheduleDirect(command);
        }
    }

    private static class HandleExecutor implements Executor {
        final Handler mHandler;

        private HandleExecutor(Handler handler) {
            this.mHandler = handler;
        }

        public void execute(Runnable command) {
            this.mHandler.post(command);
        }

        void postDelay(Runnable runnable, long delay) {
            this.mHandler.postDelayed(runnable, delay);
        }

        void removeRunnable(Runnable runnable) {
            this.mHandler.removeCallbacks(runnable);
        }
    }
}

