package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.view.View;

import com.hpedrorodrigues.imagesearch.ui.api.fragment.view.GenericView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;

public class GenericPresenter extends BasePresenter<GenericFragment> {

    private final GenericView view;

    public GenericPresenter(GenericFragment fragment, Navigator navigator) {
        super(fragment, navigator);
        this.view = new GenericView(fragment);
    }

    @Override
    public void onViewCreated(View view) {
        this.view.onView(view);
    }
}