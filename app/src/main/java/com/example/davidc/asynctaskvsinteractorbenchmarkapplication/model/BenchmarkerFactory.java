package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model;

import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.interactors.InteractorFactory;

public class BenchmarkerFactory {
    private final InteractorFactory interactorFactory;

    public BenchmarkerFactory(InteractorFactory interactorFactory) {
        this.interactorFactory = interactorFactory;
    }

    public Benchmarker create(final Benchmarker.Callback callback) {
        return new Benchmarker(interactorFactory, callback);
    }
}
