package com.hpedrorodrigues.researcher.ui.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.answers.ContentViewEvent;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hpedrorodrigues.researcher.R;
import com.hpedrorodrigues.researcher.dagger.application.ISApplication;
import com.hpedrorodrigues.researcher.dagger.component.ISComponent;
import com.hpedrorodrigues.researcher.util.general.ISAnswer;
import com.hpedrorodrigues.researcher.util.general.PreferenceUtil;

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