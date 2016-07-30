package com.hpedrorodrigues.imagesearch.ui.api.fragment.view;

import android.view.View;

import com.etsy.android.grid.StaggeredGridView;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;

import lombok.Data;

@Data
public class GenericView extends BaseView<GenericFragment> {

    private StaggeredGridView gridView;

    public GenericView(GenericFragment fragment) {
        super(fragment);
    }

    @Override
    public void onView(View view) {
        gridView = (StaggeredGridView) view.findViewById(R.id.grid_view);
    }
}