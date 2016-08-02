package com.hpedrorodrigues.imagesearch.ui.api.activity.view;

import android.view.View;
import android.widget.ImageView;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.activity.ImageActivity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
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
}