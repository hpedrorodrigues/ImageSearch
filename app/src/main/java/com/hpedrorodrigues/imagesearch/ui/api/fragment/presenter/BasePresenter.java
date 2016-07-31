package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.content.Context;
import android.view.Menu;
import android.view.View;

import com.hpedrorodrigues.imagesearch.api.service.GenericService;
import com.hpedrorodrigues.imagesearch.ui.activity.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.BaseFragment;
import com.hpedrorodrigues.imagesearch.util.general.ISAnswer;

import javax.inject.Inject;

abstract class BasePresenter<T extends BaseFragment> {

    protected final T fragment;

    protected final Navigator navigator;

    @Inject
    public Context context;

    @Inject
    public GenericService genericService;

    @Inject
    protected ISAnswer answer;

    protected BasePresenter(T fragment, Navigator navigator) {
        this.fragment = fragment;
        this.navigator = navigator;
    }

    public abstract void onViewCreated(View view);

    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
    }

    protected BaseActivity getActivity() {
        return (BaseActivity) fragment.getActivity();
    }
}