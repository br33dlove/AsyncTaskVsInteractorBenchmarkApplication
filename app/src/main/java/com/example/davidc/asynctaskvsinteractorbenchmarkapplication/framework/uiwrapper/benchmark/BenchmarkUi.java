package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.benchmark;


import com.davidc.uiwrapper.Ui;

public interface BenchmarkUi extends Ui {
    void showStartBenchmarking();
    void showLoadingBenchmarks();
    void showBenchmarkText(final String benchmarkText);

    interface Listener extends Ui.Listener {
        void startBenchmarking(final BenchmarkUi ui);
    }
}
