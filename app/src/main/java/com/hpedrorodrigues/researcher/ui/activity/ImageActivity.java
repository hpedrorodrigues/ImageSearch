package com.hpedrorodrigues.researcher.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.hpedrorodrigues.researcher.R;
import com.hpedrorodrigues.researcher.ui.activity.base.BaseActivity;
import com.hpedrorodrigues.researcher.ui.api.activity.presenter.ImagePresenter;
import com.hpedrorodrigues.researcher.ui.api.navigation.AndroidNavigator;

public class ImageActivity extends BaseActivity {

    private ImagePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        presenter.onCreate(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return presenter.onCreateOptionsMenu(menu) || super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onIntent(Intent intent, Bundle extras) {
        presenter.onIntent(intent, extras);
    }

    @Override
    protected void setUpPresenter() {
        presenter = new ImagePresenter(this, new AndroidNavigator(this));
        getComponent().inject(presenter);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();

        super.onDestroy();
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected String getScreenName() {
        return "Image";
    }
}