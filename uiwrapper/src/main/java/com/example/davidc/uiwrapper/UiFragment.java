package com.example.davidc.uiwrapper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.UUID;

public abstract class UiFragment<R extends UiWrapperRepository, L extends Ui.EventsListener> extends Fragment {
    private final static String ARG_SAVED_INSTANCE_STATE_INSTANCE_ID = "instance id";
    private UiWrapperRepositoryProvider<R> repositoryProvider;
    private boolean isBound = false;
    private L eventsListener;
    private String instanceId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        repositoryProvider = CastHelper.repositoryProvider(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instanceId = savedInstanceState == null ? UUID.randomUUID().toString() : savedInstanceState.getString(ARG_SAVED_INSTANCE_STATE_INSTANCE_ID);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(savedInstanceState);
    }

    private void bind(final Bundle savedInstanceState) {
        eventsListener = bind(repositoryProvider.get(), instanceId, savedInstanceState);
        isBound = true;
    }

    @Override
    public void onStart() {
        super.onStart();
        bindIfUnbound(null);
    }

    private void bindIfUnbound(final Bundle savedInstanceState) {
        if (!isBound) {
            bind(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_SAVED_INSTANCE_STATE_INSTANCE_ID, instanceId);
        unbind(outState);
    }

    private void unbind(final Bundle outState) {
        unbind(repositoryProvider.get(), instanceId, outState, getActivity().isChangingConfigurations());
        eventsListener = null;
        isBound = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        unbindIfBound(null);
    }

    private void unbindIfBound(final Bundle outState) {
        if (isBound) {
            unbind(outState);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        repositoryProvider = null;
    }

    protected abstract L bind(final R uiWrapperRepository, final String instanceId, final Bundle savedInstanceState);

    protected abstract void unbind(final R uiWrapperRepository, final String instanceId, final Bundle outState, final boolean isConfigurationChange);

    protected boolean hasEventsListener() {
        return eventsListener != null;
    }

    protected L eventsListener() {
        return eventsListener;
    }
}
