package com.hpedrorodrigues.researcher.ui.api.fragment.navigation;

import com.hpedrorodrigues.researcher.ui.fragment.BaseFragment;

public interface FragmentNavigator {

    void toScreen(BaseFragment fragment);

    void toFirstScreen(BaseFragment fragment);
}