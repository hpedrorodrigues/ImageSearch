package com.hpedrorodrigues.imagesearch.ui.api.navigation;

import com.hpedrorodrigues.imagesearch.ui.activity.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.api.activity.navigation.ActivityNavigator;
import com.hpedrorodrigues.imagesearch.ui.api.activity.navigation.AndroidActivityNavigator;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.navigation.AndroidFragmentNavigator;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.navigation.FragmentNavigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.BaseFragment;

public class AndroidNavigator implements Navigator {

    private final ActivityNavigator activityNavigator;
    private final FragmentNavigator fragmentNavigator;

    public AndroidNavigator(int containerId, BaseActivity activity) {
        activityNavigator = new AndroidActivityNavigator(activity);
        fragmentNavigator = new AndroidFragmentNavigator(containerId, activity);
    }

    @Override
    public void toActivityScreen(Class<? extends BaseActivity> clazz) {
        activityNavigator.toScreen(clazz);
    }

    @Override
    public void toActivityScreenWithClearedHistory(Class<? extends BaseActivity> clazz) {
        activityNavigator.toScreenWithClearedHistory(clazz);
    }

    @Override
    public void toMainActivityScreen() {
        activityNavigator.toMainScreen();
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