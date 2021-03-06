package com.hpedrorodrigues.imagesearch.ui.feature.image;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.base.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.common.navigation.AndroidNavigator;

public class ImageActivity extends BaseActivity {

    private ImagePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        presenter.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return presenter.onCreateOptionsMenu(menu) || super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return presenter.onPrepareOptionsMenu(menu) || super.onPrepareOptionsMenu(menu);
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