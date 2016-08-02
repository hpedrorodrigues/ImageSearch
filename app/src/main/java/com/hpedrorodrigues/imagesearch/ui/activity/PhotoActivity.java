package com.hpedrorodrigues.imagesearch.ui.activity;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.activity.base.BaseImageActivity;

public class PhotoActivity extends BaseImageActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void setUpPresenter() {

    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected String getScreenName() {
        return "Photo";
    }
}