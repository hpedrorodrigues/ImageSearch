package com.hpedrorodrigues.imagesearch.ui.api.activity.presenter;

import android.os.Bundle;

import com.hpedrorodrigues.imagesearch.ui.activity.SettingsActivity;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;

public class SettingsPresenter extends BasePresenter<SettingsActivity> {

    public SettingsPresenter(SettingsActivity activity, Navigator navigator) {
        super(activity, navigator);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }
}