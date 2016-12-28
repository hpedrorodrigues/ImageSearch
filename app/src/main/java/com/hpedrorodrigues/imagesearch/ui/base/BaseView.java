package com.hpedrorodrigues.imagesearch.ui.base;

public abstract class BaseView<T extends BaseActivity> {

    protected final T activity;

    public BaseView(T activity) {
        this.activity = activity;
        onView();
    }

    protected abstract void onView();
}