package com.hpedrorodrigues.imagesearch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.activity.base.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.api.activity.presenter.ImagePresenter;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.AndroidNavigator;

public class ImageActivity extends BaseActivity {

    private ImagePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter.onCreate(savedInstanceState);
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