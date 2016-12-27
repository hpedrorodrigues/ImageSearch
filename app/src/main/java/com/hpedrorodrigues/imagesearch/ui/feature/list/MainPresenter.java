package com.hpedrorodrigues.imagesearch.ui.feature.list;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.constant.DrawerItem;
import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.constant.PreferenceKey;
import com.hpedrorodrigues.imagesearch.ui.api.activity.view.MainView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.base.BaseFragment;
import com.hpedrorodrigues.imagesearch.ui.base.BasePresenter;
import com.hpedrorodrigues.imagesearch.ui.feature.settings.SettingsActivity;

public class MainPresenter extends BasePresenter<MainActivity> {

    private static final long DRAWER_REPLACE_SCREEN_DELAY = 400L;

    private final MainView view;
    private BaseFragment currentFragment;

    private boolean backPressedOnce = false;

    public MainPresenter(MainActivity activity, Navigator navigator) {
        super(activity, navigator);
        this.view = new MainView(activity);

        this.activity.getComponent().inject(view);
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
        } else if (preferences.getBoolean(PreferenceKey.ASK_TO_EXIT, ISConstant.DEFAULT_ASK_TO_EXIT)) {

            if (backPressedOnce) {

                return super.onBackPressed();
            } else {

                backPressedOnce = true;
                Snackbar
                        .make(view.getDrawer(), R.string.back_again_to_exit, Snackbar.LENGTH_SHORT)
                        .setAction(activity.getString(android.R.string.ok), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        backPressedOnce = false;
                    }
                }, 2000L);

                return false;
            }
        }

        return super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (currentFragment != null) {
            currentFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void setUpDrawerListener() {
        view.setDrawerItemSelectedListener(new MainView.OnDrawerItemSelectedListener() {

            @Override
            public void onSelected(final DrawerItem item) {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (item.equals(DrawerItem.SETTINGS)) {

                            navigator.toActivityScreen(SettingsActivity.class);
                        } else {

                            GenericFragment fragment = GenericFragment.create(item.getApi());
                            navigator.toFragmentScreen(fragment);
                        }
                    }
                }, DRAWER_REPLACE_SCREEN_DELAY);
            }
        });
    }

    private void setUpFirstFragment() {
        activity.setTitle(R.string.all_provider);

        view.getNavigationView().getMenu().getItem(0).setChecked(true);

        currentFragment = GenericFragment.create();
        navigator.toFirstFragmentScreen(currentFragment);
    }
}