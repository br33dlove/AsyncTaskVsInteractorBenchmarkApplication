package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.R;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.androidhelpers.FragmentManagerHelper;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.ui.BenchmarkFragment;
import com.example.davidc.uiwrapper.UiWrapperRepositoryActivity;

public class BenchmarkActivity extends UiWrapperRepositoryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_with_content);
        setupToolbar();
        addBenchmarkFragment();
    }

    private void setupToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    private void addBenchmarkFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (FragmentManagerHelper.noFragmentBoundToView(fragmentManager, getContentFragmentViewContainer())) {
            FragmentManagerHelper.addFragment(fragmentManager, BenchmarkFragment.newInstance(), getContentFragmentViewContainer());
        }
    }

    @IdRes
    private int getContentFragmentViewContainer() {
        return R.id.content;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (FragmentManagerHelper.hasMoreThanOneNonRetainedFragment(fragmentManager)) {
            super.onBackPressed();
            return;
        }
        finish();
    }
}
