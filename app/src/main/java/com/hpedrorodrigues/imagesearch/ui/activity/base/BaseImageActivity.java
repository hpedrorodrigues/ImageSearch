package com.hpedrorodrigues.imagesearch.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseImageActivity extends BaseActivity {

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }
}