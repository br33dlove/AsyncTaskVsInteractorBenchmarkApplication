package com.example.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class UiWrapperRepositoryFragment<R extends UiWrapperRepository> extends Fragment implements UiWrapperRepositoryProvider {
    private R repository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initRepository();
    }

    private void initRepository() {
        repository = CastHelper.repositoryFromFactory(getActivity());
    }

    @Override
    public R get() {
        return repository;
    }
}
