package com.hpedrorodrigues.imagesearch.ui.api.activity.view;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.constant.DrawerItem;
import com.hpedrorodrigues.imagesearch.ui.activity.MainActivity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MainView extends BaseView<MainActivity> {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private OnDrawerItemSelectedListener drawerItemSelectedListener;

    public MainView(MainActivity activity) {
        super(activity);
    }

    @Override
    protected void onView() {
        drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
    }

    public void setUp() {
        setUpDrawer();
        setUpNavigationView();
    }

    private void setUpDrawer() {
        drawerToggle = new ActionBarDrawerToggle(
                activity,
                drawer,
                activity.getToolbar(),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawer.addDrawerListener(drawerToggle);

        drawerToggle.syncState();
    }

    public void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);

            closeDrawer();

            if (drawerItemSelectedListener != null) {
                drawerItemSelectedListener.onSelected(DrawerItem.find(item.getItemId()));
            }

            return true;
        });
    }

    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    public interface OnDrawerItemSelectedListener {

        void onSelected(DrawerItem item);
    }
}