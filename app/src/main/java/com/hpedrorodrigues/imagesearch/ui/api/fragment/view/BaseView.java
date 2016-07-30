package com.hpedrorodrigues.imagesearch.ui.api.fragment.view;

import android.view.View;

import com.hpedrorodrigues.imagesearch.ui.fragment.BaseFragment;

abstract class BaseView<T extends BaseFragment> {

    protected final T fragment;

    protected BaseView(T fragment) {
        this.fragment = fragment;
    }

    protected abstract void onView(View view);
}