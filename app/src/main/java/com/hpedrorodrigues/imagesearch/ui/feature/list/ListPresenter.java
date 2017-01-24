package com.hpedrorodrigues.imagesearch.ui.feature.list;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.data.constant.DrawerItem;
import com.hpedrorodrigues.imagesearch.ui.common.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.feature.list.api.GenericFragment;
import com.hpedrorodrigues.imagesearch.ui.feature.settings.SettingsActivity;

public class ListPresenter implements ListContract.Presenter {

    private static final long DRAWER_REPLACE_SCREEN_DELAY = 400L;

    private final ListContract.View view;
    private final Navigator navigator;

    public ListPresenter(@NonNull final ListContract.View view,
                         @NonNull final Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    @Override
    public void start() {
        setUpDrawerListener();
        setUpFirstFragment();
    }

    @Override
    public void stop() {
    }

    private void setUpDrawerListener() {
        view.setDrawerItemSelectedListener(new ListContract.View.OnDrawerItemSelectedListener() {

            @Override
            public void onSelected(final DrawerItem item) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (item.equals(DrawerItem.SETTINGS)) {

                            navigator.toActivityScreen(SettingsActivity.class);
                        } else {

                            navigator.toFragmentScreen(GenericFragment.create(item.getApi()));
                        }
                    }
                }, DRAWER_REPLACE_SCREEN_DELAY);
            }
        });
    }

    private void setUpFirstFragment() {
        view.setTitle(R.string.all_provider);
        view.getFirstMenuItem().setChecked(true);

        navigator.toFirstFragmentScreen(GenericFragment.create());
    }
}