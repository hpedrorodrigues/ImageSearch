package com.hpedrorodrigues.imagesearch.ui.adapter.holder;

import android.view.View;
import android.widget.ImageView;

import com.hpedrorodrigues.imagesearch.R;

public class ImageHolder extends BaseHolder {

    public ImageView imageView;

    public ImageHolder(View view) {
        super(view);
        imageView = (ImageView) view.findViewById(R.id.image);
    }
}