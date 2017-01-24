package com.hpedrorodrigues.imagesearch.ui.feature.list;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.data.constant.DrawerItem;
import com.hpedrorodrigues.imagesearch.ui.base.BaseActivity;
import com.hpedrorodrigues.imagesearch.ui.common.navigation.AndroidNavigator;
import com.hpedrorodrigues.imagesearch.ui.common.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.util.general.VersionInfo;

import javax.inject.Inject;

public class ListActivity extends BaseActivity implements ListContract.View {

    @Inject
    public VersionInfo versionInfo;

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private OnDrawerItemSelectedListener listener;

    private ListContract.Presenter presenter;
    private Navigator navigator;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onView();
        onConfig();
        presenter.start();
    }

    @Override
    public void onView() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    @Override
    public void onConfig() {
        setUpDrawer();
        setUpNavigationView();
    }

    @Override
    public MenuItem getFirstMenuItem() {
        return navigationView.getMenu().getItem(0);
    }

    @Override
    public void onPresenter() {
        navigator = new AndroidNavigator(R.id.container, this);
        presenter = new ListPresenter(this, navigator);
        getComponent().inject(presenter);
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected String getScreenName() {
        return "Main";
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) && super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setDrawerItemSelectedListener(final OnDrawerItemSelectedListener listener) {
        this.listener = listener;
    }

    private void setUpDrawer() {
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawer,
                getToolbar(),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawer.addDrawerListener(drawerToggle);

        drawerToggle.syncState();
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(final MenuItem item) {
                        if (item.getItemId() != R.id.settings_item) {
                            item.setChecked(true);
                        }

                        closeDrawer();

                        if (listener != null) {
                            listener.onSelected(DrawerItem.find(item.getItemId()));
                        }

                        return true;
                    }
                });

        final View headerView = navigationView.getHeaderView(0);
        final TextView appVersionView = (TextView) headerView.findViewById(R.id.app_version);
        final Menu menu = navigationView.getMenu();

        appVersionView.setText(versionInfo.getVersionName());

        for (int i = 0; i < menu.size(); i++) {
            final MenuItem menuItem = menu.getItem(i);
            final String upperTitle = menuItem.getTitle().toString().toUpperCase();

            menuItem.setTitle(upperTitle);
        }
    }

    private boolean isDrawerOpen() {
        return drawer.isDrawerOpen(GravityCompat.START);
    }

    private void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    public Navigator getNavigator() {
        return navigator;
    }
}