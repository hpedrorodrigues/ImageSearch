package com.hpedrorodrigues.imagesearch.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.ui.adapter.holder.ImageHolder;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class ImageAdapter extends ISBaseAdapter<Image> {

    @Inject
    public Context context;

    @Inject
    public LayoutInflater inflater;

    @Inject
    public ImageAdapter() {
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.generic_item, viewGroup, false);
        }

        ImageHolder holder = new ImageHolder(view);
        Image image = getItemTyped(i);

        Picasso.with(context).load(image.getThumbnailUrl()).into(holder.imageView);

        return view;
    }
}