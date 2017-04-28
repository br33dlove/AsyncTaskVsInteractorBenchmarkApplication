package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model;

class OverallBenchmarkResults {
    private final JobBenchmarkResults shortJobBenchmarkResults;
    private final JobBenchmarkResults longJobBenchmarkResults;

    private OverallBenchmarkResults(JobBenchmarkResults shortJobBenchmarkResults, JobBenchmarkResults longJobBenchmarkResults) {
        this.shortJobBenchmarkResults = shortJobBenchmarkResults;
        this.longJobBenchmarkResults = longJobBenchmarkResults;
    }

    static OverallBenchmarkResults fromResults(JobBenchmarkResults shortJobBenchmarkResults, JobBenchmarkResults longJobBenchmarkResults) {
        return new OverallBenchmarkResults(shortJobBenchmarkResults, longJobBenchmarkResults);
    }

    String results() {
        return String.format("%1$s\n\n%2$s", shortJobBenchmarkResults.results(), longJobBenchmarkResults.results());
    }

    public boolean areValid() {
        return shortJobBenchmarkResults != null && longJobBenchmarkResults != null;
    }
}
