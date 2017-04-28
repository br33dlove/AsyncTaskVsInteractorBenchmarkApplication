package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.asynctasks.LongJobBenchmarkAsyncTask;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.asynctasks.ShortJobBenchmarkAsyncTask;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.interactors.BenchmarkInteractor;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.interactors.InteractorFactory;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.interactors.LongJobBenchmarkInteractor;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.interactors.ShortJobBenchmarkInteractor;

class Benchmarker implements Cancelable {
    private final InteractorFactory interactorFactory;
    private final static int COUNT_SMALL_JOBS = 100;
    private final static int COUNT_LARGE_JOBS = 1;
    private final Callback callback;
    private boolean stop = false;
    private JobBenchmarker jobBenchmarker;
    private JobBenchmarkResults shortJobsBenchmarkResults;
    private JobBenchmarkResults longJobsBenchmarkResults;

    Benchmarker(InteractorFactory interactorFactory, Callback callback) {
        this.interactorFactory = interactorFactory;
        this.callback = callback;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                startBenchmarking();
            }
        });
    }

    private void startBenchmarking() {
        startBenchmarkingSmallJobs();
    }

    private void startBenchmarkingSmallJobs() {
        benchmarkSmallJobs(new JobBenchmarker.Callback() {
            @Override
            public void onFinish(JobBenchmarkResults benchmarkResults) {
                if (!stop) {
                    shortJobsBenchmarkResults = benchmarkResults;
                    startBenchmarkingLongJobs();
                }
            }

            @Override
            public void onError(String error) {
                Benchmarker.this.onError(error);
            }
        });
    }

    private void benchmarkSmallJobs(final JobBenchmarker.Callback callback) {
        jobBenchmarker = new ShortJobBenchmarker(COUNT_SMALL_JOBS, interactorFactory.createShortBenchmarkInteractor(), callback);
        jobBenchmarker.start();
    }

    private void startBenchmarkingLongJobs() {
        benchmarkLongJobs(new JobBenchmarker.Callback() {
            @Override
            public void onFinish(JobBenchmarkResults benchmarkResults) {
                if (!stop) {
                    longJobsBenchmarkResults = benchmarkResults;
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                Benchmarker.this.onError(error);
            }
        });
    }

    private void benchmarkLongJobs(final JobBenchmarker.Callback callback) {
        jobBenchmarker = new LongJobBenchmarker(COUNT_LARGE_JOBS, interactorFactory.createLongBenchmarkInteractor(), callback);
        jobBenchmarker.start();
    }

    private void finish() {
        callback.onFinish(OverallBenchmarkResults.fromResults(shortJobsBenchmarkResults, longJobsBenchmarkResults));
    }

    private void onError(final String error) {
        callback.onError(error);
    }

    @Override
    public void cancel() {
        stop = true;
        if (jobBenchmarker != null) {
            jobBenchmarker.stop();
        }
    }

    interface Callback {
        void onFinish(final OverallBenchmarkResults overallBenchmarkResults);
        void onError(final String error);
    }

    private static abstract class JobBenchmarker {
        private final int jobCount;
        private final BenchmarkInteractor interactor;
        private final Callback callback;
        private int asyncTaskJobsCompleted = 0;
        private int interactorJobsCompleted = 0;
        private long asyncTaskStartMs;
        private long asyncTaskFinishMs;
        private long interactorStartMs;
        private long interactorFinishMs;
        private boolean stop = false;

        JobBenchmarker(int jobCount, BenchmarkInteractor interactor, Callback callback) {
            this.jobCount = jobCount;
            this.interactor = interactor;
            this.callback = callback;
        }

        private void start() {
            asyncTaskStartMs = System.currentTimeMillis();
            nextAsyncTaskJob();
        }

        private void nextAsyncTaskJob() {
            if (asyncTaskJobsCompleted < jobCount) {
                jobBenchmarkAsyncTask(asyncTaskJobCallback).execute();
            } else {
                asyncTaskFinishMs = System.currentTimeMillis();
                if (!stop) {
                    interactorStartMs = System.currentTimeMillis();
                    nextInteractorJob();
                }
            }
        }

        abstract AsyncTask<Void, ?, ?> jobBenchmarkAsyncTask(final JobCallback callback);

        private final JobCallback asyncTaskJobCallback = new JobCallback() {
            @Override
            public void onFinish(boolean interrupted) {
                if (interrupted) {
                    JobBenchmarker.this.onError("Thread sleep interrupted");
                } else {
                    asyncTaskJobsCompleted++;
                    nextAsyncTaskJob();
                }
            }
        };

        private void nextInteractorJob() {
            if (interactorJobsCompleted < jobCount) {
                interactor.startJob(interactorJobCallback);
            } else {
                interactorFinishMs = System.currentTimeMillis();
                if (!stop) {
                    finish();
                }
            }
        }

        private final JobCallback interactorJobCallback = new JobCallback() {
            @Override
            public void onFinish(boolean interrupted) {
                if (interrupted) {
                    JobBenchmarker.this.onError("Thread sleep interrupted");
                } else {
                    interactorJobsCompleted++;
                    nextInteractorJob();
                }
            }
        };

        private void onError(final String error) {
            callback.onError(error);
        }

        private void finish() {
            callback.onFinish(new JobBenchmarkResults(jobCount, asyncTaskFinishMs - asyncTaskStartMs, interactorFinishMs - interactorStartMs, benchmarkType()));
        }

        abstract String benchmarkType();

        private void stop() {
            stop = true;
        }

        interface Callback {
            void onFinish(final JobBenchmarkResults benchmarkResults);
            void onError(final String error);
        }
    }

    private class LongJobBenchmarker extends JobBenchmarker {

        private LongJobBenchmarker(int jobCount, LongJobBenchmarkInteractor interactor, Callback callback) {
            super(jobCount, interactor, callback);
        }

        @Override
        AsyncTask<Void, ?, ?> jobBenchmarkAsyncTask(JobCallback callback) {
            return new LongJobBenchmarkAsyncTask(callback);
        }

        @Override
        String benchmarkType() {
            return "Long job benchmark";
        }
    }

    private class ShortJobBenchmarker extends JobBenchmarker {

        private ShortJobBenchmarker(int jobCount, ShortJobBenchmarkInteractor interactor, Callback callback) {
            super(jobCount, interactor, callback);
        }

        @Override
        AsyncTask<Void, ?, ?> jobBenchmarkAsyncTask(JobCallback callback) {
            return new ShortJobBenchmarkAsyncTask(callback);
        }

        @Override
        String benchmarkType() {
            return "Short job benchmark";
        }
    }
}
