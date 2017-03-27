package com.example.davidc.uiwrapper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class UiWrapperRepositoryActivity<R extends UiWrapperRepository> extends AppCompatActivity implements UiWrapperRepositoryProvider {
    private final static String UI_WRAPPER_FRAGMENT_TAG = "ui wrapper repository";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!FragmentManagerHelper.hasFragment(getSupportFragmentManager(), UI_WRAPPER_FRAGMENT_TAG)) {
            setupUiWrapperRepositoryRetainFragment();
        }
    }

    private void setupUiWrapperRepositoryRetainFragment() {
        FragmentManagerHelper.addFragment(
                getSupportFragmentManager(),
                new UiWrapperRepositoryFragment<>(),
                UI_WRAPPER_FRAGMENT_TAG
        );
    }

    @Override
    public R get() {
        return uiWrapperRepositoryFragment().get();
    }

    private UiWrapperRepositoryFragment<R> uiWrapperRepositoryFragment() {
        //noinspection unchecked
        return (UiWrapperRepositoryFragment<R>) FragmentManagerHelper.fragment(getSupportFragmentManager(), UI_WRAPPER_FRAGMENT_TAG);
    }
}
