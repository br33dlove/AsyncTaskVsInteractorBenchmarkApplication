package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;

public class UiWrapperRepositoryFactoryImpl implements UiWrapperRepositoryFactory<UiWrapperRepository> {
    private final UiWrapperFactory uiWrapperFactory;

    public UiWrapperRepositoryFactoryImpl(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    @Override
    public UiWrapperRepository create() {
        return new UiWrapperRepository(uiWrapperFactory);
    }
}
