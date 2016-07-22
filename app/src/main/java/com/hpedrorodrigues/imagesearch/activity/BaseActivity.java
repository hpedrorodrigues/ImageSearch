package com.hpedrorodrigues.imagesearch.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.application.ISApplication;
import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ISComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component = ((ISApplication) getApplication()).getComponent();
        component.inject(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        onView();
        setUpToolbar();
        onIntent();
        setUpPresenter();
    }

    public ISComponent getComponent() {
        return component;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void onView() {
    }

    protected void onIntent() {
    }

    protected abstract void setUpPresenter();

    protected abstract void inject();
}