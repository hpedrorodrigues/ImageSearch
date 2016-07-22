package com.hpedrorodrigues.imagesearch.view;

import com.hpedrorodrigues.imagesearch.activity.BaseActivity;

public abstract class BaseView<T extends BaseActivity> {

    protected final T activity;

    public BaseView(T activity) {
        this.activity = activity;
        onView();
    }

    protected abstract void onView();
}