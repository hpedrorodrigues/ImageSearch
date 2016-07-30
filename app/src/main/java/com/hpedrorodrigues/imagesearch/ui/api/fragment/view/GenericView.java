package com.hpedrorodrigues.imagesearch.ui.api.fragment.view;

import android.view.View;

import com.etsy.android.grid.StaggeredGridView;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.ui.adapter.ImageAdapter;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;

import java.util.List;

import javax.inject.Inject;

import lombok.Data;

@Data
public class GenericView extends BaseView<GenericFragment> {

    private StaggeredGridView gridView;

    @Inject
    public ImageAdapter imagesAdapter;

    public GenericView(GenericFragment fragment) {
        super(fragment);
    }

    @Override
    public void onView(View view) {
        gridView = (StaggeredGridView) view.findViewById(R.id.grid_view);
    }

    public void setContentFromGridView(List<Image> images) {
        imagesAdapter.setContent(images);
        gridView.setAdapter(imagesAdapter);
    }
}