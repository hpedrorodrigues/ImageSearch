package com.hpedrorodrigues.imagesearch.ui.api.activity.presenter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.ui.activity.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;

import javax.inject.Inject;

abstract class BasePresenter<T extends BaseActivity> {

    protected final T activity;
    protected final Navigator navigator;

    @Inject
    protected SharedPreferences preferences;

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

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
    }

    public boolean onBackPressed() {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}