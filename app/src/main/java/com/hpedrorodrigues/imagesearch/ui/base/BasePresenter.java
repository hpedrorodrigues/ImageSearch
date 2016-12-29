package com.hpedrorodrigues.imagesearch.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.data.event_tracker.EventTracker;
import com.hpedrorodrigues.imagesearch.data.manager.PreferenceManager;
import com.hpedrorodrigues.imagesearch.ui.common.navigation.Navigator;

import javax.inject.Inject;

public abstract class BasePresenter<T extends BaseActivity> {

    protected final T activity;
    protected final Navigator navigator;

    @Inject
    protected EventTracker eventTracker;

    @Inject
    protected PreferenceManager preferences;

    public BasePresenter(T activity, Navigator navigator) {
        this.activity = activity;
        this.navigator = navigator;
    }

    public abstract void onCreate(Bundle savedInstanceState);

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    public void onIntent(Intent intent, Bundle extras) {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onDestroy() {
    }

    public boolean onBackPressed() {
        return true;
    }
}