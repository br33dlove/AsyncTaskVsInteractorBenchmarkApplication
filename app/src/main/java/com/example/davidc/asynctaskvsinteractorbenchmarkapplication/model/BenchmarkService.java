package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model;

import java.util.HashMap;
import java.util.Map;

public class BenchmarkService {
    private final BenchmarkerFactory benchmarkerFactory;
    private final Map<Callback, Cancelable> benchmarkerMap = new HashMap<>();

    public BenchmarkService(BenchmarkerFactory benchmarkerFactory) {
        this.benchmarkerFactory = benchmarkerFactory;
    }

    public void startBenchmarking(final Callback callback) {
        benchmarkerMap.put(callback, benchmarkerFactory.create(new Benchmarker.Callback() {
            @Override
            public void onFinish(OverallBenchmarkResults overallBenchmarkResults) {
                callback.onFinish(overallBenchmarkResults);
                benchmarkerMap.remove(callback);
            }
        }));
    }

    public boolean isBenchmarking(final Callback callback) {
        return benchmarkerMap.containsKey(callback);
    }

    public void cancelBenchmarking(final Callback callback) {
        benchmarkerMap.remove(callback).cancel();
    }

    public interface Callback {
        void onFinish(final OverallBenchmarkResults overallBenchmarkResults);
    }
}
