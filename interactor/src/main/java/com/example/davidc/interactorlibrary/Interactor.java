package com.example.davidc.interactorlibrary;

public class Interactor {
    private final TaskScheduler taskScheduler;

    protected Interactor(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    protected void executeOnBoundThread(final Task task) {
        taskScheduler.executeOnBoundThread(task);
    }

    protected void executeOnWorkerThread(final Task task) {
        taskScheduler.executeOnWorkerThread(task);
    }
}
