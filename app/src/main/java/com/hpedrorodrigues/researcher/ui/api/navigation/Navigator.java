package com.hpedrorodrigues.researcher.ui.api.navigation;

import android.os.Bundle;

import com.hpedrorodrigues.researcher.ui.activity.base.BaseActivity;
import com.hpedrorodrigues.researcher.ui.fragment.BaseFragment;

public interface Navigator {

    void toActivityScreen(Class<? extends BaseActivity> clazz);

    void toActivityScreen(Class<? extends BaseActivity> clazz, Bundle arguments);

    void toActivityScreenWithClearedHistory(Class<? extends BaseActivity> clazz);

    void toMainActivityScreen();

    void toActivityParent();

    void toFragmentScreen(BaseFragment fragment);

    void toFirstFragmentScreen(BaseFragment fragment);
}