package com.hpedrorodrigues.imagesearch.ui.api.fragment.view;

import android.view.View;

import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;

import lombok.Data;

@Data
public class GenericView extends BaseView<GenericFragment> {

    private AsymmetricGridView asymmetricGridView;

    public GenericView(GenericFragment fragment) {
        super(fragment);
    }

    @Override
    public void onView(View view) {
        asymmetricGridView = (AsymmetricGridView) view.findViewById(R.id.asymmetricgridview);
    }
}