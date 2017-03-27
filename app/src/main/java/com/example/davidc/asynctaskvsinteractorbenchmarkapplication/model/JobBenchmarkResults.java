package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model;

public class JobBenchmarkResults {
    private final int jobCount;
    private final long asyncTaskBenchmarkMs;
    private final long interactorBenchmarkMs;

    public JobBenchmarkResults(int jobCount, long asyncTaskBenchmarkMs, long interactorBenchmarkMs) {
        this.jobCount = jobCount;
        this.asyncTaskBenchmarkMs = asyncTaskBenchmarkMs;
        this.interactorBenchmarkMs = interactorBenchmarkMs;
    }

    public int jobCount() {
        return jobCount;
    }

    public long asyncTaskBenchmarkMs() {
        return asyncTaskBenchmarkMs;
    }

    public long interactorBenchmarkMs() {
        return interactorBenchmarkMs;
    }
}
