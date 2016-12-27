package com.hpedrorodrigues.imagesearch.ui.api.activity.view;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.constant.DrawerItem;
import com.hpedrorodrigues.imagesearch.ui.feature.list.MainActivity;
import com.hpedrorodrigues.imagesearch.util.general.VersionInfo;

import javax.inject.Inject;

public class MainView extends BaseView<MainActivity> {

    @Inject
    public VersionInfo versionInfo;

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
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        if (item.getItemId() != R.id.settings_item) {
                            item.setChecked(true);
                        }

                        closeDrawer();

                        if (drawerItemSelectedListener != null) {
                            drawerItemSelectedListener.onSelected(DrawerItem.find(item.getItemId()));
                        }

                        return true;
                    }
                });

        View headerView = navigationView.getHeaderView(0);
        TextView appVersionView = (TextView) headerView.findViewById(R.id.app_version);
        appVersionView.setText(versionInfo.getVersionName());

        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);

            String upperTitle = menuItem.getTitle().toString().toUpperCase();
            menuItem.setTitle(upperTitle);
        }
    }

    public boolean isDrawerOpen() {
        return drawer.isDrawerOpen(GravityCompat.START);
    }

    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    public DrawerLayout getDrawer() {
        return drawer;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    public ActionBarDrawerToggle getDrawerToggle() {
        return drawerToggle;
    }

    public void setDrawerItemSelectedListener(OnDrawerItemSelectedListener drawerItemSelectedListener) {
        this.drawerItemSelectedListener = drawerItemSelectedListener;
    }

    public interface OnDrawerItemSelectedListener {

        void onSelected(DrawerItem item);
    }
}