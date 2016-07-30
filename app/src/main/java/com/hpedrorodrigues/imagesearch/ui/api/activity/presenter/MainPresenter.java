package com.hpedrorodrigues.imagesearch.ui.api.activity.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.api.network.api.Api;
import com.hpedrorodrigues.imagesearch.constant.DrawerItem;
import com.hpedrorodrigues.imagesearch.ui.activity.MainActivity;
import com.hpedrorodrigues.imagesearch.ui.api.activity.view.MainView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;

public class MainPresenter extends BasePresenter<MainActivity> {

    private static final long DRAWER_REPLACE_SCREEN_DELAY = 400L;

    private final MainView view;

    public MainPresenter(MainActivity activity, Navigator navigator) {
        super(activity, navigator);
        this.view = new MainView(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        view.setUp();
        setUpDrawerListener();
        setUpFirstFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return view.getDrawerToggle().onOptionsItemSelected(item)
                || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onBackPressed() {
        if (view.isDrawerOpen()) {
            view.closeDrawer();
            return false;
        }

        return super.onBackPressed();
    }

    private void setUpDrawerListener() {
        view.setDrawerItemSelectedListener(item ->
                new Handler().postDelayed(() -> navigateTo(item), DRAWER_REPLACE_SCREEN_DELAY));
    }

    private void navigateTo(DrawerItem item) {
        GenericFragment fragment = GenericFragment.create(item.getApi());
        navigator.toFragmentScreen(fragment);
    }

    private void setUpFirstFragment() {
        GenericFragment fragment = GenericFragment.create(Api.FLICKR);
        navigator.toFirstFragmentScreen(fragment);
    }
}