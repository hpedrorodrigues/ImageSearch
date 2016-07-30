package com.hpedrorodrigues.imagesearch.ui.api.activity.view;

abstract class BaseView<T extends BaseActivity> {

    protected final T activity;

    public BaseView(T activity) {
        this.activity = activity;
        onView();
    }

    protected abstract void onView();
}