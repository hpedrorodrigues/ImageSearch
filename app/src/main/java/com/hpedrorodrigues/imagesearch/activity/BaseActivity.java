package com.hpedrorodrigues.imagesearch.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hpedrorodrigues.imagesearch.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);

        onView();
        onIntent();
    }

    protected Toolbar getToolbar() {
        return toolbar;
    }

    protected void setUpToolbar() {
        setUpToolbar(R.id.toolbar);
    }

    protected void setUpToolbar(int resourceId) {
        toolbar = (Toolbar) findViewById(resourceId);
        setSupportActionBar(toolbar);
    }

    protected void onView() {
    }

    protected void onIntent() {
    }
}