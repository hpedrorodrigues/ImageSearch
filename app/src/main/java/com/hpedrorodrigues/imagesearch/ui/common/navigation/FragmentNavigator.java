package com.hpedrorodrigues.imagesearch.ui.common.navigation;

import com.hpedrorodrigues.imagesearch.ui.base.BaseFragment;

public interface FragmentNavigator {

    void toScreen(BaseFragment fragment);

    void toFirstScreen(BaseFragment fragment);
}