package com.hpedrorodrigues.imagesearch.ui.activity;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.activity.base.BaseImageActivity;

public class GifActivity extends BaseImageActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gif;
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
        return "GIF";
    }
}