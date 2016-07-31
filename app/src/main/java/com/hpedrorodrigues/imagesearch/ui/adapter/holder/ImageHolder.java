package com.hpedrorodrigues.imagesearch.ui.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hpedrorodrigues.imagesearch.R;

public class ImageHolder extends BaseHolder {

    public ImageView imageView;
    public TextView titleView;
    public ImageView moreView;

    public ImageHolder(View view) {
        super(view);
        imageView = (ImageView) view.findViewById(R.id.image);
        titleView = (TextView) view.findViewById(R.id.title);
        moreView = (ImageView) view.findViewById(R.id.more);
    }
}