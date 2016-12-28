package com.hpedrorodrigues.imagesearch.ui.common.navigation;

import android.content.Intent;
import android.os.Bundle;

import com.hpedrorodrigues.imagesearch.ui.base.BaseActivity;

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
    public void toParent() {
        activity.finish();
    }
}