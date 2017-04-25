package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.application;

import android.os.Looper;

import com.davidc.interactor.TaskScheduler;
import com.davidc.interactor.ThreadPoolExecutorAndHandlerTaskScheduler;
import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.UiWrapperFactory;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.UiWrapperRepositoryFactoryImpl;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.UiWrapperRepository;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.benchmark.BenchmarkUiModelFactory;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.BenchmarkService;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.BenchmarkerFactory;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.interactors.InteractorFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class ApplicationDependencyProvider {

    private ApplicationDependencyProvider() {

    }

    static UiWrapperRepositoryFactory<UiWrapperRepository> createUiWrapperRepositoryFactory() {
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
        return new ThreadPoolExecutorAndHandlerTaskScheduler(new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1)), Looper.getMainLooper());
    }
}
