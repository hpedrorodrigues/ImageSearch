package com.hpedrorodrigues.imagesearch.ui.feature.image;

import android.view.View;
import android.widget.ImageView;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.base.BaseView;

public class LoadImageView extends BaseView<ImageActivity> {

    private ImageView imageView;
    private AnimatedCircleLoadingView loadingView;

    public LoadImageView(ImageActivity activity) {
        super(activity);
    }

    @Override
    public void onView() {
        imageView = (ImageView) activity.findViewById(R.id.image);
        loadingView = (AnimatedCircleLoadingView) activity.findViewById(R.id.loading_view);
    }

    public void showImageView() {
        imageView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
    }

    public void hideImageView() {
        imageView.setVisibility(View.INVISIBLE);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public AnimatedCircleLoadingView getLoadingView() {
        return loadingView;
    }
}