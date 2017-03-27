package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.R;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.UiWrapperRepositoryImpl;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.benchmark.BenchmarkUi;
import com.example.davidc.uiwrapper.UiFragment;

public class BenchmarkFragment extends UiFragment<UiWrapperRepositoryImpl, BenchmarkUi.EventsListener> implements BenchmarkUi {
    private TextView benchmarkTextView;
    private View startBenchmarkingButton;
    private ProgressBar loadingBar;

    public static BenchmarkFragment newInstance() {
        return new BenchmarkFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_benchmark, container, false);
        benchmarkTextView = (TextView) view.findViewById(R.id.benchmarkText);
        startBenchmarkingButton = view.findViewById(R.id.startBenchmarkingButton);
        startBenchmarkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasEventsListener()) {
                    eventsListener().startBenchmarking(BenchmarkFragment.this);
                }
            }
        });
        loadingBar = (ProgressBar) view.findViewById(R.id.loadingBar);
        return null;
    }

    @Override
    public void showStartBenchmarking() {
        startBenchmarkingButton.setEnabled(true);
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingBenchmarks() {
        startBenchmarkingButton.setEnabled(false);
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBenchmarkText(String benchmarkText) {
        benchmarkTextView.setText(benchmarkText);
    }

    @Override
    protected BenchmarkUi.EventsListener bind(UiWrapperRepositoryImpl uiWrapperRepository, String instanceId, Bundle savedInstanceState) {
        return uiWrapperRepository.bind(this, instanceId, savedInstanceState);
    }

    @Override
    protected void unbind(UiWrapperRepositoryImpl uiWrapperRepository, String instanceId, Bundle outState, boolean isConfigurationChange) {
        uiWrapperRepository.unbind(this, instanceId, outState, isConfigurationChange);
    }
}
