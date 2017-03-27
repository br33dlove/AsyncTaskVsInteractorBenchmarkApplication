package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.interactors;

import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.JobCallback;

public interface BenchmarkInteractor {
    void startJob(final JobCallback callback);
}
