package com.example.davidc.interactorlibrary;

import java.util.concurrent.ThreadPoolExecutor;

public abstract class ThreadPoolExecutorTaskScheduler implements TaskScheduler {
    private final ThreadPoolExecutor threadPoolExecutor;

    protected ThreadPoolExecutorTaskScheduler(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    public void executeOnWorkerThread(final Task task) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        });
    }
}
