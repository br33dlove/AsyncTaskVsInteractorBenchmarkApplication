package com.example.davidc.asynctaskvsinteractorbenchmarkapplication.framework.uiwrapper.benchmark;

import android.os.Parcel;

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
            ui.showBenchmarks(asyncTaskBenchmarkText(overallBenchmarkResults), interactorBenchmarkText(overallBenchmarkResults));
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
            ui.showBenchmarks(asyncTaskBenchmarkText(overallBenchmarkResults), interactorBenchmarkText(overallBenchmarkResults));
        }
    }

    private static String asyncTaskBenchmarkText(final OverallBenchmarkResults overallBenchmarkResults) {
        //TODO
    }

    private static String interactorBenchmarkText(final OverallBenchmarkResults overallBenchmarkResults) {
        //TODO
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
