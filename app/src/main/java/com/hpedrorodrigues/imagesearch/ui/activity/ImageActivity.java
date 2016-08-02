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

import is.arontibo.library.ElasticDownloadView;
import timber.log.Timber;

public class ImageActivity extends BaseActivity {

    private ImageView imageView;
    private ElasticDownloadView downloadView;

    protected Image image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        downloadView.startIntro();

        Ion.with(this)
                .load(image.getImageUrl())
                .progressHandler((downloaded, total) -> {
                    int percent = (int) (100 * downloaded / total) % 100;

                    Timber.d("Progress: Total - %d - Downloaded - %d - Percent - %d", total, downloaded, percent);
                    downloadView.setProgress(percent);
                })
                .intoImageView(imageView)
                .setCallback((error, view) -> {
                    if (error == null) {
                        downloadView.success();
                    } else {
                        downloadView.fail();
                    }
                });
    }

    @Override
    protected void onView() {
        super.onView();

        imageView = (ImageView) findViewById(R.id.image);
        downloadView = (ElasticDownloadView) findViewById(R.id.elastic_download_view);
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