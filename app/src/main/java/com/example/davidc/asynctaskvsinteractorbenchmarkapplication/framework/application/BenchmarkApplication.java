package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.application;

import android.app.Application;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.UiWrapperRepository;

public class BenchmarkApplication extends Application implements UiWrapperRepositoryFactory<UiWrapperRepository> {
    private UiWrapperRepositoryFactory<UiWrapperRepository> uiWrapperRepositoryFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        uiWrapperRepositoryFactory = ApplicationDependencyProvider.createUiWrapperRepositoryFactory();
    }

    @Override
    public UiWrapperRepository create() {
        return uiWrapperRepositoryFactory.create();
    }
}
