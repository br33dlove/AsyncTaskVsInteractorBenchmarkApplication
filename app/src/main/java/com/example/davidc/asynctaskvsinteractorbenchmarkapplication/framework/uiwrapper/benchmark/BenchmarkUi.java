package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.benchmark;

import com.example.davidc.uiwrapper.Ui;

public interface BenchmarkUi extends Ui {
    void showStartBenchmarking();
    void showLoadingBenchmarks();
    void showBenchmarkText(final String benchmarkText);

    interface EventsListener extends Ui.EventsListener {
        void startBenchmarking(final BenchmarkUi ui);
    }
}
