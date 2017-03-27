package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.job;

public class Jobs {

    public static void longJob() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void shortJob() throws InterruptedException {
        Thread.sleep(10);
    }
}
