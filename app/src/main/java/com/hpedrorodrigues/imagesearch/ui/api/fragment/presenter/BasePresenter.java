package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.hpedrorodrigues.imagesearch.api.service.GenericService;
import com.hpedrorodrigues.imagesearch.ui.activity.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.BaseFragment;
import com.hpedrorodrigues.imagesearch.util.general.ISAnswer;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

abstract class BasePresenter<T extends BaseFragment> {

    protected final T fragment;

    protected final Navigator navigator;

    @Inject
    public Context context;

    @Inject
    public GenericService genericService;

    @Inject
    protected ISAnswer answer;

    private CompositeSubscription compositeSubscription;

    protected BasePresenter(T fragment, Navigator navigator) {
        this.fragment = fragment;
        this.navigator = navigator;
        this.compositeSubscription = new CompositeSubscription();
    }

    public abstract void onViewCreated(View view);

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    protected void bindSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void cancelPendingProcesses() {
        compositeSubscription.unsubscribe();
    }

    protected BaseActivity getActivity() {
        return (BaseActivity) fragment.getActivity();
    }
}