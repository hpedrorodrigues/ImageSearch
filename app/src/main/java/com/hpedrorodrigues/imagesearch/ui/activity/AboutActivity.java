package com.hpedrorodrigues.imagesearch.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hpedrorodrigues.imagesearch.R;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void setUpPresenter() {
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected String getScreenName() {
        return "About";
    }
}