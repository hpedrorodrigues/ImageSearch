package com.hpedrorodrigues.imagesearch.ui.adapter;

import android.content.Context;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.ui.adapter.holder.ImageHolder;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class ImageAdapter extends ISBaseAdapter<Image> {

    private OnPopupItemClickListener listener;

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

        final ImageHolder holder = new ImageHolder(view);
        final Image image = getItemTyped(i);

        holder.titleView.setText(image.getTitle());

        holder.moreView.setOnClickListener(v -> showPopup(v, image));

        Picasso.with(context).load(image.getThumbnailUrl()).into(holder.imageView);

        return view;
    }

    public void showPopup(View view, Image image) {
        Context wrapper = new ContextThemeWrapper(context, R.style.PopupMenu);
        PopupMenu popupMenu = new PopupMenu(wrapper, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_item, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(item -> {
            listener.onClick(item, image);
            return true;
        });
    }

    public void setListener(OnPopupItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnPopupItemClickListener {

        void onClick(MenuItem item, Image image);
    }
}