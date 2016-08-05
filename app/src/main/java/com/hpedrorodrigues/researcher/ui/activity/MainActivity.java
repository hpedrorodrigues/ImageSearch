package com.hpedrorodrigues.researcher.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.hpedrorodrigues.researcher.R;
import com.hpedrorodrigues.researcher.ui.activity.base.BaseActivity;
import com.hpedrorodrigues.researcher.ui.api.activity.presenter.MainPresenter;
import com.hpedrorodrigues.researcher.ui.api.navigation.AndroidNavigator;
import com.hpedrorodrigues.researcher.ui.api.navigation.Navigator;

public class MainActivity extends BaseActivity {

    private MainPresenter presenter;

    private Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpPresenter() {
        navigator = new AndroidNavigator(R.id.container, this);
        presenter = new MainPresenter(this, navigator);
        getComponent().inject(presenter);
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected String getScreenName() {
        return "Main";
    }

    @Override
    public void onBackPressed() {
        if (presenter.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item) && super.onOptionsItemSelected(item);
    }

    public Navigator getNavigator() {
        return navigator;
    }
}