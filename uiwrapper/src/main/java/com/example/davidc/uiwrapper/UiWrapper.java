package com.example.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

public abstract class UiWrapper<U extends Ui, L extends Ui.EventsListener> {
    private boolean resourcesRegistered = false;
    private U ui;

    L bind(final U ui) {
        if (!resourcesRegistered) {
            registerResources();
        }
        this.ui = ui;
        showCurrentUiState(ui);
        return eventsListener();
    }

    @CallSuper
    protected void registerResources() {
        resourcesRegistered = true;
    }

    protected abstract void showCurrentUiState(final U ui);

    protected abstract L eventsListener();

    void unbind() {
        ui = null;
    }

    protected abstract void saveState(final Bundle outState);

    @CallSuper
    protected void unregisterResources() {
        resourcesRegistered = false;
    }

    @Nullable
    protected U ui() {
        return ui;
    }
}
