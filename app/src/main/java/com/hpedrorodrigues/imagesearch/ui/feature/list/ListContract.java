package com.hpedrorodrigues.imagesearch.ui.feature.list;

import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.data.constant.DrawerItem;
import com.hpedrorodrigues.imagesearch.ui.base.BaseNewPresenter;
import com.hpedrorodrigues.imagesearch.ui.base.BaseNewView;

public interface ListContract {

    interface View extends BaseNewView<Presenter> {

        void onView();

        void onConfig();

        void setTitle(int resourceId);

        MenuItem getFirstMenuItem();

        void setDrawerItemSelectedListener(OnDrawerItemSelectedListener listener);

        interface OnDrawerItemSelectedListener {

            void onSelected(DrawerItem item);
        }
    }

    interface Presenter extends BaseNewPresenter {
    }
}