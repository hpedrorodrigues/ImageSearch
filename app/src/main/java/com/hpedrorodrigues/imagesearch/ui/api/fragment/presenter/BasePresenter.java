package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.hpedrorodrigues.imagesearch.api.service.GenericService;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.BaseFragment;

import javax.inject.Inject;

abstract class BasePresenter<T extends BaseFragment> {

    protected final T fragment;

    protected final Navigator navigator;

    @Inject
    public Context context;

    @Inject
    public GenericService genericService;

    protected BasePresenter(T fragment, Navigator navigator) {
        this.fragment = fragment;
        this.navigator = navigator;
    }

    public abstract void onViewCreated(View view);

    protected FragmentActivity getActivity() {
        return fragment.getActivity();
    }
}