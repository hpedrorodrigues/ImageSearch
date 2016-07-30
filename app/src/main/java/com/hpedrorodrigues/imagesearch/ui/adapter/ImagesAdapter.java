package com.hpedrorodrigues.imagesearch.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class ImagesAdapter extends BaseAsymmetricAdapter<Image> {

    @Inject
    public Context context;

    @Inject
    public LayoutInflater inflater;

    @Inject
    public ImagesAdapter() {
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.generic_item, viewGroup, false);
        }

        ImageView imageView = (ImageView) view;
        Image image = getItemTyped(i);

        Picasso.with(context).load(image.getThumbnailUrl()).into(imageView);

        return view;
    }
}