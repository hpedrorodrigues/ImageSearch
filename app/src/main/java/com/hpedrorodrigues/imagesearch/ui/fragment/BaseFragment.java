package com.hpedrorodrigues.imagesearch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.crashlytics.android.answers.ContentViewEvent;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;
import com.hpedrorodrigues.imagesearch.ui.activity.BaseActivity;
import com.hpedrorodrigues.imagesearch.util.general.ISAnswer;
import com.hpedrorodrigues.imagesearch.util.general.PreferenceUtil;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {

    @Inject
    protected ISAnswer answer;

    @Inject
    protected PreferenceUtil preferences;

    private Toolbar toolbar;
    private ISComponent component;
    private Tracker tracker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseActivity activity = (BaseActivity) getActivity();

        toolbar = activity.getToolbar();
        component = activity.getComponent();
        tracker = activity.getTracker();

        inject();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();

        tracker.setScreenName(getScreenName());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        answer.instance()
                .logContentView(new ContentViewEvent().putContentId("Fragment Screen: " + getScreenName()));
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public ISComponent getComponent() {
        return component;
    }

    public Tracker getTracker() {
        return tracker;
    }

    protected abstract void setUpPresenter();

    protected abstract void inject();

    protected abstract String getScreenName();
}