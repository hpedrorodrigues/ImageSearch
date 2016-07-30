package com.hpedrorodrigues.imagesearch.ui.api.navigation;

import com.hpedrorodrigues.imagesearch.ui.activity.BaseActivity;

public interface Navigator {

    void toScreen(Class<? extends BaseActivity> clazz);

    void toScreenWithClearedHistory(Class<? extends BaseActivity> clazz);

    void toMainScreen();

    void toParent();
}