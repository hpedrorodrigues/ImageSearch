package com.hpedrorodrigues.imagesearch.ui.common.navigation;

import com.hpedrorodrigues.imagesearch.ui.base.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.base.BaseFragment;

public class AndroidFragmentNavigator implements FragmentNavigator {

    private final int containerId;
    private final BaseActivity activity;

    public AndroidFragmentNavigator(int containerId, BaseActivity activity) {
        this.containerId = containerId;
        this.activity = activity;
    }

    @Override
    public void toScreen(BaseFragment fragment) {
        activity
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }

    @Override
    public void toFirstScreen(BaseFragment fragment) {
        activity
                .getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, fragment)
                .commit();
    }
}