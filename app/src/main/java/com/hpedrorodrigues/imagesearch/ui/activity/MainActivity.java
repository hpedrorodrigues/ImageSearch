package com.hpedrorodrigues.imagesearch.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.api.activity.presenter.MainPresenter;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.AndroidNavigator;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item) && super.onOptionsItemSelected(item);
    }

    public Navigator getNavigator() {
        return navigator;
    }
}