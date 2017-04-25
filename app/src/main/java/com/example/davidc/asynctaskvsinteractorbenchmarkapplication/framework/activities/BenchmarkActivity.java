package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.davidc.uiwrapper.SingleContentContainerWithAppBarActivity;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.UiWrapperRepository;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.ui.BenchmarkFragment;

public class BenchmarkActivity extends SingleContentContainerWithAppBarActivity<UiWrapperRepository> {

    @Override
    protected void setupActionBar(ActionBar actionBar) {
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected Fragment initialFragment() {
        return BenchmarkFragment.newInstance();
    }
}
