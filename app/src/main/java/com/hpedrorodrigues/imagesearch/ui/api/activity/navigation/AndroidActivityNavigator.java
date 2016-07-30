package com.hpedrorodrigues.imagesearch.ui.api.activity.navigation;

import android.content.Intent;

import com.hpedrorodrigues.imagesearch.ui.activity.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.activity.MainActivity;

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