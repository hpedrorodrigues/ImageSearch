package com.hpedrorodrigues.researcher.ui.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hpedrorodrigues.researcher.R;

public class ImageHolder extends BaseHolder {

    public ImageView imageView;
    public TextView titleView;
    public ImageView moreView;
    public RelativeLayout contentView;

    public ImageHolder(View view) {
        super(view);
        imageView = (ImageView) view.findViewById(R.id.image);
        titleView = (TextView) view.findViewById(R.id.title);
        moreView = (ImageView) view.findViewById(R.id.more);
        contentView = (RelativeLayout) view.findViewById(R.id.content);
    }
}