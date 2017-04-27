package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.application;

import android.app.Application;
import android.support.annotation.NonNull;

import com.davidc.benchmarktemplate.UiWrapperRepository;
import com.davidc.uiwrapper.UiWrapperRepositoryFactory;


public class BenchmarkApplication extends Application implements UiWrapperRepositoryFactory<UiWrapperRepository> {
    private UiWrapperRepositoryFactory<UiWrapperRepository> uiWrapperRepositoryFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        uiWrapperRepositoryFactory = ApplicationDependencyProvider.createUiWrapperRepositoryFactory();
    }

    @Override
    @NonNull
    public UiWrapperRepository create() {
        return uiWrapperRepositoryFactory.create();
    }
}
