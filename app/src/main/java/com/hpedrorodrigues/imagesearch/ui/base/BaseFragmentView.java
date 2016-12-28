package com.hpedrorodrigues.imagesearch.ui.base;

import android.content.Context;
import android.view.View;

import javax.inject.Inject;

public abstract class BaseFragmentView<T extends BaseFragment> {

    protected final T fragment;

    @Inject
    public Context context;

    protected BaseFragmentView(T fragment) {
        this.fragment = fragment;
    }

    protected abstract void onView(View view);
}