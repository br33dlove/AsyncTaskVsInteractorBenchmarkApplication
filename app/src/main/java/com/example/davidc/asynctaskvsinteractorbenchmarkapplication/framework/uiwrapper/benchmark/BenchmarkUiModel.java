package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.benchmark;

import android.os.Parcel;

import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.JobBenchmarkResults;
import com.example.davidc.asynctaskvsinteractorbenchmarkapplication.model.OverallBenchmarkResults;
import com.example.davidc.uiwrapper.UiModel;

class BenchmarkUiModel implements UiModel<BenchmarkUi> {
    enum ButtonState {START_BENCHMARKING, LOADING}
    private ButtonState buttonState;
    private OverallBenchmarkResults overallBenchmarkResults;

    BenchmarkUiModel(final ButtonState buttonState, final OverallBenchmarkResults overallBenchmarkResults) {
        this.buttonState = buttonState;
        this.overallBenchmarkResults = overallBenchmarkResults;
    }

    private BenchmarkUiModel(final Parcel parcel) {
        buttonState = (ButtonState) parcel.readSerializable();
        overallBenchmarkResults = OverallBenchmarkResults.emptyResults();//TODO fix parcelling
//        overallBenchmarkResults = OverallBenchmarkResults.fromResults(parcel.readLong(), parcel.readLong());
    }

    @Override
    public void onto(BenchmarkUi ui) {
        if (overallBenchmarkResults.areValid()) {
            ui.showBenchmarkText(benchmarkText(overallBenchmarkResults));
        }
        switch (buttonState) {
            case START_BENCHMARKING: {
                ui.showStartBenchmarking();
                break;
            }
            case LOADING: {
                ui.showStartBenchmarking();
                break;
            }
        }
    }

    void showStartBenchmarking(final BenchmarkUi ui) {
        buttonState = ButtonState.START_BENCHMARKING;
        if (ui != null) {
            ui.showStartBenchmarking();
        }
    }

    void showLoadingBenchmarks(final BenchmarkUi ui) {
        buttonState = ButtonState.LOADING;
        if (ui != null) {
            ui.showLoadingBenchmarks();
        }
    }

    void showBenchmarks(final BenchmarkUi ui, final OverallBenchmarkResults overallBenchmarkResults) {
        this.overallBenchmarkResults = overallBenchmarkResults.areValid() ? overallBenchmarkResults : OverallBenchmarkResults.emptyResults();//TODO show error?
        if (ui != null) {
            ui.showBenchmarkText(benchmarkText(overallBenchmarkResults));
        }
    }

    private static String benchmarkText(final OverallBenchmarkResults overallBenchmarkResults) {
        return String.format("%1$s\n\n%2$s", benchmarkText(overallBenchmarkResults.longJobBenchmarkResults(), true), benchmarkText(overallBenchmarkResults.shortJobBenchmarkResults(), false));
    }

    private static String benchmarkText(final JobBenchmarkResults jobBenchmarkResults, final boolean isLongJobBenchmark) {
        return String.format(
                "%1$s: %2$s\nAsyncTask duration: %3$s\nInteractor duration: %4$s",
                isLongJobBenchmark ? "Long job benchmark" : "Short job benchmark",
                String.format("%1$s %2$s", jobBenchmarkResults.jobCount(), jobBenchmarkResults.jobCount() == 1 ? "job" : "jobs"),
                jobBenchmarkResults.asyncTaskBenchmarkMs(),
                jobBenchmarkResults.interactorBenchmarkMs()
        );
    }

    boolean isInLoadingState() {
        return buttonState == ButtonState.LOADING;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(buttonState);
//        dest.writeLong(overallBenchmarkResults.longJobAsyncTaskBenchmarkMs());
//        dest.writeLong(overallBenchmarkResults.longJobInteractorBenchmarkMs());
    }

    final static Creator<BenchmarkUiModel> CREATOR = new Creator<BenchmarkUiModel>() {
        @Override
        public BenchmarkUiModel createFromParcel(Parcel source) {
            return new BenchmarkUiModel(source);
        }

        @Override
        public BenchmarkUiModel[] newArray(int size) {
            return new BenchmarkUiModel[size];
        }
    };
}
