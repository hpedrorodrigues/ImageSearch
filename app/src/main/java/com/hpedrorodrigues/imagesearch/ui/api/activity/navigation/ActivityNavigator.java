package com.hpedrorodrigues.imagesearch.ui.api.activity.navigation;

import com.hpedrorodrigues.imagesearch.ui.activity.BaseActivity;

public interface ActivityNavigator {

    void toScreen(Class<? extends BaseActivity> clazz);

    void toScreenWithClearedHistory(Class<? extends BaseActivity> clazz);

    void toMainScreen();

    void toParent();
}