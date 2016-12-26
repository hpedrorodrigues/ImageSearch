package com.hpedrorodrigues.imagesearch.ui.api.activity.view;

import android.view.View;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.goka.flickableview.FlickableImageView;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.activity.ImageActivity;

public class LoadImageView extends BaseView<ImageActivity> {

    private FlickableImageView imageView;
    private AnimatedCircleLoadingView loadingView;

    public LoadImageView(ImageActivity activity) {
        super(activity);
    }

    @Override
    public void onView() {
        imageView = (FlickableImageView) activity.findViewById(R.id.image);
        loadingView = (AnimatedCircleLoadingView) activity.findViewById(R.id.loading_view);
    }

    public void showImageView() {
        imageView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
    }

    public void hideImageView() {
        imageView.setVisibility(View.INVISIBLE);
    }

    public FlickableImageView getImageView() {
        return imageView;
    }

    public AnimatedCircleLoadingView getLoadingView() {
        return loadingView;
    }
}