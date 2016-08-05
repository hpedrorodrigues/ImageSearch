package com.hpedrorodrigues.researcher.ui.api.activity.view;

import com.hpedrorodrigues.researcher.ui.activity.base.BaseActivity;

abstract class BaseView<T extends BaseActivity> {

    protected final T activity;

    public BaseView(T activity) {
        this.activity = activity;
        onView();
    }

    protected abstract void onView();
}