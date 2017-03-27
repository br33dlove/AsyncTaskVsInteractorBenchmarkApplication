package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper;

import android.os.Bundle;

import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.benchmark.BenchmarkUiModelFactory;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.benchmark.BenchmarkUiWrapper;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.BenchmarkService;

public class UiWrapperFactory {
    private final BenchmarkUiModelFactory benchmarkUiModelFactory;
    private final BenchmarkService benchmarkService;

    public UiWrapperFactory(BenchmarkUiModelFactory benchmarkUiModelFactory, BenchmarkService benchmarkService) {
        this.benchmarkUiModelFactory = benchmarkUiModelFactory;
        this.benchmarkService = benchmarkService;
    }

    public BenchmarkUiWrapper createBenchmarkUiWrapper(final Bundle savedInstanceState) {
        return savedInstanceState == null
                ? BenchmarkUiWrapper.newInstance(benchmarkUiModelFactory, benchmarkService)
                : BenchmarkUiWrapper.savedElseNewInstance(benchmarkUiModelFactory, benchmarkService, savedInstanceState);
    }
}
