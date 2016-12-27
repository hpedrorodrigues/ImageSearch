package com.hpedrorodrigues.imagesearch.ui.api.activity.navigation;

import android.content.Intent;
import android.os.Bundle;

import com.hpedrorodrigues.imagesearch.ui.base.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.feature.list.MainActivity;

public class AndroidActivityNavigator implements ActivityNavigator {

    private final BaseActivity activity;

    public AndroidActivityNavigator(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    public void toScreen(Class<? extends BaseActivity> clazz) {
        activity.startActivity(new Intent(activity, clazz));
    }

    @Override
    public void toScreen(Class<? extends BaseActivity> clazz, Bundle arguments) {
        Intent intent = new Intent(activity, clazz);

        intent.putExtras(arguments);

        activity.startActivity(intent);
    }

    @Override
    public void toScreenWithClearedHistory(Class<? extends BaseActivity> clazz) {
        toScreen(clazz);
        activity.finish();
    }

    @Override
    public void toMainScreen() {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void toParent() {
        activity.finish();
    }
}