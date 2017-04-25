package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.interactors;

import com.davidc.interactor.Interactor;
import com.davidc.interactor.Task;
import com.davidc.interactor.TaskScheduler;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.JobCallback;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.job.Jobs;

public class LongJobBenchmarkInteractor extends Interactor implements BenchmarkInteractor {

    LongJobBenchmarkInteractor(TaskScheduler taskScheduler) {
        super(taskScheduler);
    }

    @Override
    public void startJob(final JobCallback callback) {
        executeOnWorkerThread(new Task() {
            @Override
            public void execute() {
                try {
                    Jobs.longJob();
                } catch (InterruptedException ie) {
                    executeOnCallbackThread(new Task() {
                        @Override
                        public void execute() {
                            callback.onFinish(true);
                        }
                    });
                    return;
                }
                executeOnCallbackThread(new Task() {
                    @Override
                    public void execute() {
                        callback.onFinish(false);
                    }
                });
            }
        });
    }
}
