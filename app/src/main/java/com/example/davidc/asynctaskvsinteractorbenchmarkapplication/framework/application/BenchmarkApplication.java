package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.application;

import android.app.Application;

import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.UiWrapperRepositoryImpl;
import com.example.davidc.uiwrapper.UiWrapperRepositoryFactory;

public class BenchmarkApplication extends Application implements UiWrapperRepositoryFactory<UiWrapperRepositoryImpl> {
    private UiWrapperRepositoryFactory<UiWrapperRepositoryImpl> uiWrapperRepositoryFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        uiWrapperRepositoryFactory = ApplicationDependencyProvider.createUiWrapperRepositoryFactory();
    }

    @Override
    public UiWrapperRepositoryImpl create() {
        return uiWrapperRepositoryFactory.create();
    }
}
