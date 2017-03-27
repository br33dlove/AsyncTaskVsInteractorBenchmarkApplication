package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.application;

import android.os.Looper;

import com.example.davidc.androidinteractor.AndroidThreadPoolExecutorTaskScheduler;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.UiWrapperFactory;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.UiWrapperRepositoryFactoryImpl;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.UiWrapperRepositoryImpl;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.benchmark.BenchmarkUiModelFactory;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.BenchmarkService;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.BenchmarkerFactory;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.interactors.InteractorFactory;
import com.example.davidc.interactorlibrary.TaskScheduler;
import com.example.davidc.uiwrapper.UiWrapperRepositoryFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class ApplicationDependencyProvider {

    private ApplicationDependencyProvider() {

    }

    static UiWrapperRepositoryFactory<UiWrapperRepositoryImpl> createUiWrapperRepositoryFactory() {
        return new UiWrapperRepositoryFactoryImpl(createUiWrapperFactory());
    }

    private static UiWrapperFactory createUiWrapperFactory() {
        return new UiWrapperFactory(createBenchmarkUiModelFactory(), createBenchmarkService());
    }

    private static BenchmarkUiModelFactory createBenchmarkUiModelFactory() {
        return new BenchmarkUiModelFactory();
    }

    private static BenchmarkService createBenchmarkService() {
        return new BenchmarkService(createBenchmarkerFactory());
    }

    private static BenchmarkerFactory createBenchmarkerFactory() {
        return new BenchmarkerFactory(createInteractorFactory());
    }

    private static InteractorFactory createInteractorFactory() {
        return new InteractorFactory(createTaskScheduler());
    }

    private static TaskScheduler createTaskScheduler() {
        return new AndroidThreadPoolExecutorTaskScheduler(new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1)), Looper.getMainLooper());
    }
}
