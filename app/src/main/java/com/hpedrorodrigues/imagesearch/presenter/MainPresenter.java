package com.hpedrorodrigues.imagesearch.presenter;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.activity.MainActivity;
import com.hpedrorodrigues.imagesearch.navigation.AndroidNavigator;
import com.hpedrorodrigues.imagesearch.view.MainView;

public class MainPresenter extends BasePresenter<MainActivity> {

    private final MainView view;

    public MainPresenter(MainActivity activity) {
        super(activity, new AndroidNavigator(activity));
        this.view = new MainView(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        view.setUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (view.getDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onBackPressed() {
        if (view.getDrawer().isDrawerOpen(GravityCompat.START)) {
            view.getDrawer().closeDrawer(GravityCompat.START);
            return false;
        }

        return super.onBackPressed();
    }
}