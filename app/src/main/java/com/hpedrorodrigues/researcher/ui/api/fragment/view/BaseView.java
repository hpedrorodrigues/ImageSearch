package com.hpedrorodrigues.researcher.ui.api.fragment.view;

import android.content.Context;
import android.view.View;

import com.hpedrorodrigues.researcher.ui.fragment.BaseFragment;

import javax.inject.Inject;

abstract class BaseView<T extends BaseFragment> {

    protected final T fragment;

    @Inject
    public Context context;

    protected BaseView(T fragment) {
        this.fragment = fragment;
    }

    protected abstract void onView(View view);
}