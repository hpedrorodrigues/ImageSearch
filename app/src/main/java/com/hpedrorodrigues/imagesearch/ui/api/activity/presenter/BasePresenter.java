package com.hpedrorodrigues.imagesearch.ui.api.activity.presenter;

import android.os.Bundle;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.ui.activity.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;

abstract class BasePresenter<T extends BaseActivity> {

    protected final T activity;
    protected final Navigator navigator;

    public BasePresenter(T activity, Navigator navigator) {
        this.activity = activity;
        this.navigator = navigator;
    }

    public abstract void onCreate(Bundle savedInstanceState);

    public void onResume() {
    }

    public void onPause() {
    }

    public void onDestroy() {
    }

    public boolean onBackPressed() {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}