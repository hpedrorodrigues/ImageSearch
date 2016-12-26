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
import com.koushikdutta.ion.Ion;

import javax.inject.Inject;

public class ImageAdapter extends ISBaseAdapter<Image> {

    @Inject
    public Context context;

    @Inject
    public LayoutInflater inflater;

    private OnPopupItemClickListener listener;

    private OnImageClickListener onImageClickListener;

    private boolean showImagesDescription = true;

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

        holder.contentView.setVisibility(showImagesDescription ? View.VISIBLE : View.GONE);

        holder.titleView.setText(image.getTitle());

        holder.moreView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showPopup(v, image);
            }
        });

        Ion.with(context).load(image.getThumbnailUrl()).intoImageView(holder.imageView);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onImageClickListener != null) {
                    onImageClickListener.onClick(image);
                }
            }
        });

        return view;
    }

    public void showPopup(final View view, final Image image) {
        Context wrapper = new ContextThemeWrapper(context, R.style.PopupMenu);
        PopupMenu popupMenu = new PopupMenu(wrapper, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_item, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                listener.onClick(item, image);
                return true;
            }
        });
    }

    public void setListener(OnPopupItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public void setShowImagesDescription(boolean showImagesDescription) {
        this.showImagesDescription = showImagesDescription;

        notifyDataSetChanged();
    }

    public interface OnPopupItemClickListener {

        void onClick(MenuItem item, Image image);
    }

    public interface OnImageClickListener {

        void onClick(Image image);
    }
}