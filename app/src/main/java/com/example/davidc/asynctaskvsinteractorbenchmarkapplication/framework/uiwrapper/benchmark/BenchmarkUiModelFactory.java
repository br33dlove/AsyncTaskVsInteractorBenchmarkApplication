package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.benchmark;

import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.OverallBenchmarkResults;

public class BenchmarkUiModelFactory {
    private final static BenchmarkUiModel.ButtonState DEFAULT_BUTTON_STATE = BenchmarkUiModel.ButtonState.START_BENCHMARKING;
    private final static OverallBenchmarkResults DEFAULT_BENCHMARK_RESULTS = OverallBenchmarkResults.emptyResults();

    BenchmarkUiModel create() {
        return new BenchmarkUiModel(DEFAULT_BUTTON_STATE, DEFAULT_BENCHMARK_RESULTS);
    }
}
