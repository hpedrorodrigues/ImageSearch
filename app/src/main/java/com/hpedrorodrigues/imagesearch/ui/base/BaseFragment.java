package com.hpedrorodrigues.imagesearch.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;
import com.hpedrorodrigues.imagesearch.data.event_tracker.EventTracker;
import com.hpedrorodrigues.imagesearch.data.manager.PreferenceManager;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {

    @Inject
    protected EventTracker eventTracker;

    @Inject
    protected PreferenceManager preferences;

    private Toolbar toolbar;
    private ISComponent component;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final BaseActivity activity = (BaseActivity) getActivity();

        toolbar = activity.getToolbar();
        component = activity.getComponent();

        inject();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();

        eventTracker.logFragmentScreen(getScreenName());
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public ISComponent getComponent() {
        return component;
    }

    protected abstract void setUpPresenter();

    protected abstract void inject();

    protected abstract String getScreenName();
}