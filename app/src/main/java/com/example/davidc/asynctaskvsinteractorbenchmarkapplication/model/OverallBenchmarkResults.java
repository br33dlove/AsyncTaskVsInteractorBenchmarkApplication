package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model;

public class OverallBenchmarkResults {
    private final JobBenchmarkResults shortJobBenchmarkResults;
    private final JobBenchmarkResults longJobBenchmarkResults;

    private OverallBenchmarkResults(JobBenchmarkResults shortJobBenchmarkResults, JobBenchmarkResults longJobBenchmarkResults) {
        this.shortJobBenchmarkResults = shortJobBenchmarkResults;
        this.longJobBenchmarkResults = longJobBenchmarkResults;
    }

    public static OverallBenchmarkResults fromResults(JobBenchmarkResults shortJobBenchmarkResults, JobBenchmarkResults longJobBenchmarkResults) {
        return new OverallBenchmarkResults(shortJobBenchmarkResults, longJobBenchmarkResults);
    }

    public static OverallBenchmarkResults emptyResults() {
        return new OverallBenchmarkResults(null, null);
    }

    public JobBenchmarkResults shortJobBenchmarkResults() {
        return shortJobBenchmarkResults;
    }

    public JobBenchmarkResults longJobBenchmarkResults() {
        return longJobBenchmarkResults;
    }

    public boolean areValid() {
        return shortJobBenchmarkResults != null && longJobBenchmarkResults != null;
    }
}
