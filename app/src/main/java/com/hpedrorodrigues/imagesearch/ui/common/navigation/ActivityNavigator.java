package com.hpedrorodrigues.imagesearch.ui.common.navigation;

import android.os.Bundle;

import com.hpedrorodrigues.imagesearch.ui.base.BaseActivity;

public interface ActivityNavigator {

    void toScreen(Class<? extends BaseActivity> clazz);

    void toScreen(Class<? extends BaseActivity> clazz, Bundle arguments);

    void toParent();
}