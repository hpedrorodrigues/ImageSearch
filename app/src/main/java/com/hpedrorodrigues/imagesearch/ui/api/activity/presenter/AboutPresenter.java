package com.hpedrorodrigues.imagesearch.ui.api.activity.presenter;

import android.os.Bundle;

import com.hpedrorodrigues.imagesearch.ui.activity.AboutActivity;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;

public class AboutPresenter extends BasePresenter<AboutActivity> {

    public AboutPresenter(AboutActivity activity, Navigator navigator) {
        super(activity, navigator);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }
}