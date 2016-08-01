package com.hpedrorodrigues.imagesearch.ui.api.activity.presenter;

import android.os.Bundle;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.ui.activity.AboutActivity;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;

public class AboutPresenter extends BasePresenter<AboutActivity> {

    public AboutPresenter(AboutActivity activity, Navigator navigator) {
        super(activity, navigator);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigator.toActivityParent();
        }

        return super.onOptionsItemSelected(item);
    }
}