/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain fullScreenNotification copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package one.example.com.runtime.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Global executor pools for the whole application.
 * <p>
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
public class AppExecutors {
    private static AppExecutors mAppExecutors;

    public static AppExecutors getInstance() {
        if (mAppExecutors == null) {
            synchronized (AppExecutors.class) {
                if (mAppExecutors == null) {
                    mAppExecutors = new AppExecutors();
                }
            }
        }
        return mAppExecutors;
    }

    private final Executor mDiskIO;

    private final Executor mNetworkIO;

    private final Executor mMainThread;

    private final Executor newCachedT;

    private final HandlerThread mBackGroundThread;
    private final Handler mHandler;


    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread,Executor abc) {
        this.mDiskIO = diskIO;//单线程
        this.mNetworkIO = networkIO;//指定线程个数
        this.mMainThread = mainThread;
        this.newCachedT=abc;//自动判断分配线程数
        mBackGroundThread = new HandlerThread("AppExecutors_backgroundthread");
        mBackGroundThread.start();
        mHandler = new Handler(mBackGroundThread.getLooper());
    }

    private AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), new MainThreadExecutor(),Executors.newCachedThreadPool());
    }

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor networkIO() {
        return mNetworkIO;
    }

    public Executor mainThread() {
        return mMainThread;
    }

    public Executor getNewCachedT() {
        return newCachedT;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
        public void removeRunnable(Runnable runnable){
            mainThreadHandler.removeCallbacks(runnable);
        }
        public void postDelay(Runnable runnable,long delay){
            mainThreadHandler.postDelayed(runnable,delay);
        }
    }

    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }


    public static void runOnBackground(Runnable runnable) {
        getInstance().mHandler.post(runnable);
    }
}


/*
public boolean isMainThread() {
    return Looper.getMainLooper() == Looper.myLooper();
}
或者

public boolean isMainThread() {
    return Looper.getMainLooper().getThread() == Thread.currentThread();
}
或者

public boolean isMainThread() {
    return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
}
 */