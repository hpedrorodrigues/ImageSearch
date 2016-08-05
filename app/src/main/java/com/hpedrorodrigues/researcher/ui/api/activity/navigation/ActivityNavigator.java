package com.hpedrorodrigues.researcher.ui.api.activity.navigation;

import android.os.Bundle;

import com.hpedrorodrigues.researcher.ui.activity.base.BaseActivity;

public interface ActivityNavigator {

    void toScreen(Class<? extends BaseActivity> clazz);

    void toScreen(Class<? extends BaseActivity> clazz, Bundle arguments);

    void toScreenWithClearedHistory(Class<? extends BaseActivity> clazz);

    void toMainScreen();

    void toParent();
}