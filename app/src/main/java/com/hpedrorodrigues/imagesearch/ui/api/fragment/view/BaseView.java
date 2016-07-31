package com.hpedrorodrigues.imagesearch.ui.api.fragment.view;

import android.content.Context;
import android.view.View;

import com.hpedrorodrigues.imagesearch.ui.fragment.BaseFragment;

import javax.inject.Inject;

abstract class BaseView<T extends BaseFragment> {

    @Inject
    public Context context;

    protected final T fragment;

    protected BaseView(T fragment) {
        this.fragment = fragment;
    }

    protected abstract void onView(View view);
}