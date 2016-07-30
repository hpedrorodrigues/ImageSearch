package com.hpedrorodrigues.imagesearch.ui.api.activity.navigation;

public interface ActivityNavigator {

    void toScreen(Class<? extends BaseActivity> clazz);

    void toScreenWithClearedHistory(Class<? extends BaseActivity> clazz);

    void toMainScreen();

    void toParent();
}