package com.example.davidc.interactorlibrary;

public interface TaskScheduler {
    void executeOnBoundThread(final Task task);
    void executeOnWorkerThread(final Task task);
}
