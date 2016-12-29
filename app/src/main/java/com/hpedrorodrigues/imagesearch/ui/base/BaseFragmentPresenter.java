package com.hpedrorodrigues.imagesearch.ui.base;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.hpedrorodrigues.imagesearch.data.event_tracker.EventTracker;
import com.hpedrorodrigues.imagesearch.data.manager.PreferenceManager;
import com.hpedrorodrigues.imagesearch.data.service.GenericService;
import com.hpedrorodrigues.imagesearch.ui.common.navigation.Navigator;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragmentPresenter<T extends BaseFragment> {

    protected final T fragment;

    protected final Navigator navigator;

    @Inject
    public Context context;

    @Inject
    public GenericService genericService;

    @Inject
    public PreferenceManager preferences;

    @Inject
    protected EventTracker eventTracker;

    private CompositeSubscription compositeSubscription;

    protected BaseFragmentPresenter(T fragment, Navigator navigator) {
        this.fragment = fragment;
        this.navigator = navigator;
        this.compositeSubscription = new CompositeSubscription();
    }

    public abstract void onViewCreated(View view);

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    public void onResume() {
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