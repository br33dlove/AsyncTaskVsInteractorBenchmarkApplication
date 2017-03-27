package com.example.davidc.uiwrapper;

import android.os.Bundle;

import java.util.Map;

public class BaseUiWrapperRepository {

    protected static <U extends Ui, L extends Ui.EventsListener> L bind(
            final U ui,
            final String instanceId,
            final Map<String, UiWrapper<U, L>> uiWrapperMap,
            final UiWrapperProvider<U, L> uiWrapperProvider
    ) {
        UiWrapper<U, L> uiWrapper = uiWrapperMap.get(instanceId);
        if (uiWrapper == null) {
            uiWrapper = uiWrapperProvider.uiWrapper();
            uiWrapperMap.put(instanceId, uiWrapper);
        }
        return uiWrapper.bind(ui);
    }

    protected static <U extends Ui, L extends Ui.EventsListener> void unbind(
            final String instanceId,
            final Map<String, UiWrapper<U, L>> uiWrapperMap,
            final Bundle outState,
            final boolean isConfigurationChange
    ) {
        final UiWrapper<U, L> uiWrapper = uiWrapperMap.get(instanceId);
        if (uiWrapper != null) {
            uiWrapper.unbind();
            if (!isConfigurationChange) {
                uiWrapper.unregisterResources();
                if (outState != null) {
                    uiWrapper.saveState(outState);
                } else {
                    uiWrapperMap.remove(instanceId);
                }
            }
        } else if (uiWrapperMap.containsKey(instanceId)) {
            uiWrapperMap.remove(instanceId);
        }
    }

    protected interface UiWrapperProvider<V extends Ui, L extends Ui.EventsListener> {
        UiWrapper<V, L> uiWrapper();
    }
}
