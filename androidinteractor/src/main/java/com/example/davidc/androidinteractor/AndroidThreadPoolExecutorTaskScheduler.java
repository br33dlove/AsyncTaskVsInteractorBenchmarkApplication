package com.example.davidc.androidinteractor;

import android.os.Handler;
import android.os.Looper;

import com.example.davidc.interactorlibrary.Task;
import com.example.davidc.interactorlibrary.ThreadPoolExecutorTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

public class AndroidThreadPoolExecutorTaskScheduler extends ThreadPoolExecutorTaskScheduler {
    private final Handler handler;

    public AndroidThreadPoolExecutorTaskScheduler(ThreadPoolExecutor threadPoolExecutor, Looper looper) {
        super(threadPoolExecutor);
        this.handler = new Handler(looper);
    }

    @Override
    public void executeOnBoundThread(final Task task) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        });
    }
}
