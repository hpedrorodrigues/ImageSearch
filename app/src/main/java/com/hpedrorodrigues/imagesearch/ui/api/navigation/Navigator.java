package com.hpedrorodrigues.imagesearch.ui.api.navigation;

import android.os.Bundle;

import com.hpedrorodrigues.imagesearch.ui.activity.base.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.fragment.BaseFragment;

public interface Navigator {

    void toActivityScreen(Class<? extends BaseActivity> clazz);

    void toActivityScreen(Class<? extends BaseActivity> clazz, Bundle arguments);

    void toActivityScreenWithClearedHistory(Class<? extends BaseActivity> clazz);

    void toMainActivityScreen();

    void toActivityParent();

    void toFragmentScreen(BaseFragment fragment);

    void toFirstFragmentScreen(BaseFragment fragment);
}