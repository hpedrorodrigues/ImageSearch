package com.hpedrorodrigues.imagesearch.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.answers.ContentViewEvent;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.dagger.application.ISApplication;
import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;
import com.hpedrorodrigues.imagesearch.util.general.ISAnswer;
import com.hpedrorodrigues.imagesearch.util.general.PreferenceUtil;

import javax.inject.Inject;

public abstract class BaseActivity extends BaseTransitionActivity {

    @Inject
    protected ISAnswer answer;

    @Inject
    protected PreferenceUtil preferences;

    private Toolbar toolbar;
    private ISComponent component;
    private Tracker tracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ISApplication application = (ISApplication) getApplication();

        component = application.getComponent();
        tracker = application.getTracker();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        onView();
        setUpToolbar();
        onIntent();
        setUpPresenter();
        inject();
    }

    @Override
    protected void onResume() {
        super.onResume();

        tracker.setScreenName(getScreenName());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        answer.instance()
                .logContentView(new ContentViewEvent().putContentId("Activity Screen: " + getScreenName()));
    }

    public ISComponent getComponent() {
        return component;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public Tracker getTracker() {
        return tracker;
    }

    protected void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void onView() {
    }

    protected void onIntent() {
    }

    protected abstract void setUpPresenter();

    protected abstract void inject();

    protected abstract String getScreenName();
}