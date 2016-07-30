package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.view.View;

import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.BaseFragment;

abstract class BasePresenter<T extends BaseFragment> {

    protected final T fragment;
    protected final Navigator navigator;

    protected BasePresenter(T fragment, Navigator navigator) {
        this.fragment = fragment;
        this.navigator = navigator;
    }

    public abstract void onViewCreated(View view);
}