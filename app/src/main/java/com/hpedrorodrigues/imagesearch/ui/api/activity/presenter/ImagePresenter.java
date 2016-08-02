package com.hpedrorodrigues.imagesearch.ui.api.activity.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.constant.IntentKey;
import com.hpedrorodrigues.imagesearch.ui.activity.ImageActivity;
import com.hpedrorodrigues.imagesearch.ui.api.activity.view.LoadImageView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.koushikdutta.ion.Ion;

import timber.log.Timber;

public class ImagePresenter extends BasePresenter<ImageActivity> {

    private LoadImageView view;

    private Image image;

    public ImagePresenter(ImageActivity activity, Navigator navigator) {
        super(activity, navigator);
        this.view = new LoadImageView(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.view.onView();

        loadImage();
    }

    @Override
    public void onIntent(Intent intent, Bundle extras) {
        image = (Image) extras.getSerializable(IntentKey.IMAGE);
    }

    private void loadImage() {
        AnimatedCircleLoadingView loadingView = view.getLoadingView();
        loadingView.startDeterminate();

        Ion.with(activity)
                .load(image.getImageUrl())
                .progressHandler((downloaded, total) -> {
                    int percent = (int) (100 * downloaded / total) % 100;

                    Timber.d("Progress: Total - %d - Downloaded - %d - Percent - %d", total, downloaded, percent);
                    loadingView.setPercent(percent);
                })
                .intoImageView(view.getImageView())
                .then((error, view) -> {
                    if (error == null) {
                        loadingView.stopOk();
                        this.view.showImageView();
                    } else {
                        loadingView.stopFailure();
                    }
                });
    }
}