package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model;

import com.davidc.benchmarktemplate.BenchmarkService;

import java.util.HashMap;
import java.util.Map;

public class BenchmarkServiceImpl implements BenchmarkService {
    private final BenchmarkerFactory benchmarkerFactory;
    private final Map<Callback, Cancelable> benchmarkerMap = new HashMap<>();

    public BenchmarkServiceImpl(BenchmarkerFactory benchmarkerFactory) {
        this.benchmarkerFactory = benchmarkerFactory;
    }

    @Override
    public void startBenchmarking(final BenchmarkService.Callback callback) {
        benchmarkerMap.put(callback, benchmarkerFactory.create(new Benchmarker.Callback() {
            @Override
            public void onFinish(OverallBenchmarkResults overallBenchmarkResults) {
                callback.onFinish(overallBenchmarkResults.toString());
                benchmarkerMap.remove(callback);
            }

            @Override
            public void onError(String error) {
                callback.onError(error);
            }
        }));
    }

    @Override
    public boolean isBenchmarking(BenchmarkService.Callback callback) {
        return benchmarkerMap.containsKey(callback);
    }

    @Override
    public void cancelBenchmarking(BenchmarkService.Callback callback) {
        benchmarkerMap.remove(callback).cancel();
    }
}
