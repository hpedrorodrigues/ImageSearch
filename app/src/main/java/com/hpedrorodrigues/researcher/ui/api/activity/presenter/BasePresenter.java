package com.hpedrorodrigues.researcher.ui.api.activity.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.hpedrorodrigues.researcher.ui.activity.base.BaseActivity;
import com.hpedrorodrigues.researcher.ui.api.navigation.Navigator;
import com.hpedrorodrigues.researcher.util.general.ISAnswer;
import com.hpedrorodrigues.researcher.util.general.PreferenceUtil;

import javax.inject.Inject;

abstract class BasePresenter<T extends BaseActivity> {

    protected final T activity;
    protected final Navigator navigator;

    @Inject
    protected ISAnswer answer;

    @Inject
    protected PreferenceUtil preferences;

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

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
    }

    public boolean onBackPressed() {
        return true;
    }
}