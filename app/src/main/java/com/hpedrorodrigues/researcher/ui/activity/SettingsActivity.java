package com.hpedrorodrigues.researcher.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.hpedrorodrigues.researcher.R;
import com.hpedrorodrigues.researcher.ui.activity.base.BaseActivity;
import com.hpedrorodrigues.researcher.ui.api.activity.presenter.SettingsPresenter;
import com.hpedrorodrigues.researcher.ui.api.navigation.AndroidNavigator;

public class SettingsActivity extends BaseActivity {

    private SettingsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpPresenter() {
        presenter = new SettingsPresenter(this, new AndroidNavigator(this));
        getComponent().inject(presenter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected String getScreenName() {
        return "Settings";
    }
}