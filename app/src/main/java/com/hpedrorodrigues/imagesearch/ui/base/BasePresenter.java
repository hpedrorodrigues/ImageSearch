package com.hpedrorodrigues.imagesearch.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.util.general.ISAnswer;
import com.hpedrorodrigues.imagesearch.util.general.PreferenceUtil;

import javax.inject.Inject;

public abstract class BasePresenter<T extends BaseActivity> {

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