package com.hpedrorodrigues.imagesearch.navigation;

import com.hpedrorodrigues.imagesearch.activity.BaseActivity;

public interface Navigator {

    void toScreen(Class<? extends BaseActivity> clazz);

    void toScreenWithClearedHistory(Class<? extends BaseActivity> clazz);

    void toMainScreen();

    void toParent();
}