package com.example.davidc.interactorlibrary;

import java.util.concurrent.ThreadPoolExecutor;

public interface ThreadPoolExecutorTaskSchedulerFactory {
    TaskScheduler create(final ThreadPoolExecutor threadPoolExecutor);
}
