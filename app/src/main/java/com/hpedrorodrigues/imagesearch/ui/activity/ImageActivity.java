package com.hpedrorodrigues.imagesearch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.constant.IntentKey;
import com.hpedrorodrigues.imagesearch.ui.activity.base.BaseActivity;
import com.koushikdutta.ion.Ion;

public class ImageActivity extends BaseActivity {

    private ImageView imageView;

    protected Image image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Ion.with(this).load(image.getImageUrl()).intoImageView(imageView);
    }

    @Override
    protected void onView() {
        super.onView();

        imageView = (ImageView) findViewById(R.id.image);
    }

    @Override
    protected void onIntent(Intent intent, Bundle extras) {
        super.onIntent(intent, extras);

        image = (Image) extras.getSerializable(IntentKey.IMAGE);
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
        return "Image";
    }
}