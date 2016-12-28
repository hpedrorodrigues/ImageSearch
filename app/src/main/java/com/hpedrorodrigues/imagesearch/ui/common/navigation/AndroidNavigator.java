package com.hpedrorodrigues.imagesearch.ui.common.navigation;

import android.os.Bundle;

import com.hpedrorodrigues.imagesearch.ui.base.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.base.BaseFragment;

public class AndroidNavigator implements Navigator {

    private final ActivityNavigator activityNavigator;
    private final FragmentNavigator fragmentNavigator;

    public AndroidNavigator(BaseActivity activity) {
        activityNavigator = new AndroidActivityNavigator(activity);
        fragmentNavigator = null;
    }

    public AndroidNavigator(int containerId, BaseActivity activity) {
        activityNavigator = new AndroidActivityNavigator(activity);
        fragmentNavigator = new AndroidFragmentNavigator(containerId, activity);
    }

    @Override
    public void toActivityScreen(Class<? extends BaseActivity> clazz) {
        activityNavigator.toScreen(clazz);
    }

    @Override
    public void toActivityScreen(Class<? extends BaseActivity> clazz, Bundle arguments) {
        activityNavigator.toScreen(clazz, arguments);
    }

    @Override
    public void toActivityParent() {
        activityNavigator.toParent();
    }

    @Override
    public void toFragmentScreen(BaseFragment fragment) {
        fragmentNavigator.toScreen(fragment);
    }

    @Override
    public void toFirstFragmentScreen(BaseFragment fragment) {
        fragmentNavigator.toFirstScreen(fragment);
    }
}