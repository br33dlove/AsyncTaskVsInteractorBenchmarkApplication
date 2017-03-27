package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.interactors;

import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.JobCallback;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.job.Jobs;
import com.example.davidc.interactorlibrary.Interactor;
import com.example.davidc.interactorlibrary.Task;
import com.example.davidc.interactorlibrary.TaskScheduler;

public class LongJobBenchmarkInteractor extends Interactor implements BenchmarkInteractor {

    public LongJobBenchmarkInteractor(TaskScheduler taskScheduler) {
        super(taskScheduler);
    }

    @Override
    public void startJob(final JobCallback callback) {
        executeOnWorkerThread(new Task() {
            @Override
            public void execute() {
                Jobs.longJob();
                executeOnBoundThread(new Task() {
                    @Override
                    public void execute() {
                        callback.onFinish();
                    }
                });
            }
        });
    }
}
