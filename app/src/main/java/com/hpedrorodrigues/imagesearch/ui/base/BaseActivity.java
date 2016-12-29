package com.hpedrorodrigues.imagesearch.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.analytics.Tracker;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.data.event_tracker.EventTracker;
import com.hpedrorodrigues.imagesearch.data.manager.PreferenceManager;
import com.hpedrorodrigues.imagesearch.injection.application.ISApplication;
import com.hpedrorodrigues.imagesearch.injection.component.ISComponent;

import javax.inject.Inject;

public abstract class BaseActivity extends BaseTransitionActivity {

    @Inject
    protected EventTracker eventTracker;

    @Inject
    protected PreferenceManager preferences;

    private Toolbar toolbar;
    private ISComponent component;
    private Tracker tracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ISApplication application = (ISApplication) getApplication();

        component = application.getComponent();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        onView();

        if (hasToolbar()) {
            setUpToolbar();
        }

        setUpPresenter();
        inject();

        if (getIntent() != null && getIntent().getExtras() != null) {
            onIntent(getIntent(), getIntent().getExtras());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventTracker.logActivityScreen(getScreenName());
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void onView() {
    }

    protected void onIntent(Intent intent, Bundle extras) {
    }

    protected boolean hasToolbar() {
        return true;
    }

    protected abstract void setUpPresenter();

    protected abstract void inject();

    protected abstract String getScreenName();
}