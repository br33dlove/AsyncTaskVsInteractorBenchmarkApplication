package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model;

class JobBenchmarkResults {
    private final int jobCount;
    private final long asyncTaskBenchmarkMs;
    private final long interactorBenchmarkMs;
    private final String benchmarkType;

    JobBenchmarkResults(int jobCount, long asyncTaskBenchmarkMs, long interactorBenchmarkMs, String benchmarkType) {
        this.jobCount = jobCount;
        this.asyncTaskBenchmarkMs = asyncTaskBenchmarkMs;
        this.interactorBenchmarkMs = interactorBenchmarkMs;
        this.benchmarkType = benchmarkType;
    }

    String results() {
        return String.format(
                "%1$s: %2$s\nAsyncTask duration: %3$s\nThreadPoolExecutor and Handler duration: %4$s",
                benchmarkType,
                String.format("%1$s %2$s", jobCount, jobCount == 1 ? "job" : "jobs"),
                asyncTaskBenchmarkMs,
                interactorBenchmarkMs
        );
    }
}
