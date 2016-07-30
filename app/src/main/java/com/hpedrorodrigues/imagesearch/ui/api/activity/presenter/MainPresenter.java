package com.hpedrorodrigues.imagesearch.ui.api.activity.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.activity.MainActivity;
import com.hpedrorodrigues.imagesearch.ui.api.activity.navigation.AndroidActivityNavigator;
import com.hpedrorodrigues.imagesearch.ui.api.activity.view.MainView;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;

public class MainPresenter extends BasePresenter<MainActivity> {

    private static final long DRAWER_REPLACE_SCREEN_DELAY = 400L;

    private final MainView view;

    public MainPresenter(MainActivity activity) {
        super(activity, new AndroidActivityNavigator(activity));
        this.view = new MainView(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        view.setUp();
        setUpDrawerListener();
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

    private void setUpDrawerListener() {
        view.setDrawerItemSelectedListener(item -> new Handler().postDelayed(() -> {
            GenericFragment fragment = GenericFragment.create(item.getApi());

            activity.getComponent().inject(fragment);

            activity
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }, DRAWER_REPLACE_SCREEN_DELAY));
    }
}