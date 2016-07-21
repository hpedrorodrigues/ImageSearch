package com.hpedrorodrigues.imagesearch.dagger.module;

import com.hpedrorodrigues.imagesearch.activity.BaseActivity;

import dagger.Provides;

public abstract class BaseModule<T extends BaseActivity> {

    private T activity;

    public BaseModule(T activity) {
        this.activity = activity;
    }

    @Provides
    public BaseActivity activity() {
        return activity;
    }
}