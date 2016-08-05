package com.hpedrorodrigues.researcher.ui.api.activity.presenter;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.goka.flickableview.FlickableImageView;
import com.hpedrorodrigues.researcher.R;
import com.hpedrorodrigues.researcher.api.entity.Image;
import com.hpedrorodrigues.researcher.constant.ISConstant;
import com.hpedrorodrigues.researcher.constant.IntentKey;
import com.hpedrorodrigues.researcher.constant.PreferenceKey;
import com.hpedrorodrigues.researcher.ui.activity.ImageActivity;
import com.hpedrorodrigues.researcher.ui.api.activity.view.LoadImageView;
import com.hpedrorodrigues.researcher.ui.api.navigation.Navigator;
import com.hpedrorodrigues.researcher.ui.component.ImageDetailDialog;
import com.hpedrorodrigues.researcher.util.general.AppUtil;
import com.hpedrorodrigues.researcher.util.general.ImageActionUtil;
import com.hpedrorodrigues.researcher.util.general.ToastUtil;
import com.koushikdutta.ion.Ion;

import javax.inject.Inject;

import timber.log.Timber;

public class ImagePresenter extends BasePresenter<ImageActivity> {

    @Inject
    public ImageActionUtil imageActionUtil;

    @Inject
    public ToastUtil toastUtil;

    @Inject
    public AppUtil appUtil;

    private LoadImageView view;
    private Image image;

    public ImagePresenter(ImageActivity activity, Navigator navigator) {
        super(activity, navigator);
        this.view = new LoadImageView(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.view.onView();

        loadImage();
        setUpScreen();

        view.getImageView().setOnFlickListener(new FlickableImageView.OnFlickableImageViewFlickListener() {
            @Override
            public void onStartFlick() {
            }

            @Override
            public void onFinishFlick() {
                view.hideImageView();
                navigator.toActivityParent();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        imageActionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        activity.getMenuInflater().inflate(R.menu.image, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem viewWebsiteItem = menu.findItem(R.id.action_view_website);

        if (viewWebsiteItem != null) {
            viewWebsiteItem.setVisible(image.getWebSiteUrl() != null);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigator.toActivityParent();
                break;
            case R.id.action_share:
                imageActionUtil.shareImage(image, activity);
                break;
            case R.id.action_share_link:
                imageActionUtil.shareImageUrl(image, activity);
                break;
            case R.id.action_copy_link:
                imageActionUtil.copyImageUrl(image);
                break;
            case R.id.action_download:
                imageActionUtil.downloadImage(image, activity);
                break;
            case R.id.action_set_as_wallpaper:
                imageActionUtil.changeWallpaper(image, activity);
                break;
            case R.id.action_image_detail:
                ImageDetailDialog dialog = ImageDetailDialog.create(image);
                activity.getComponent().inject(dialog);
                dialog.show(activity.getSupportFragmentManager(), "");
                break;
            case R.id.action_view_website:
                appUtil.openBrowser(activity, image.getWebSiteUrl());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onIntent(Intent intent, Bundle extras) {
        image = (Image) extras.getSerializable(IntentKey.IMAGE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unloadScreenOn();
    }

    private void loadImage() {
        AnimatedCircleLoadingView loadingView = view.getLoadingView();
        loadingView.startDeterminate();

        loadingView.setPercent(0);

        Ion.with(activity)
                .load(image.getImageUrl())
                .progressHandler((downloaded, total) -> {
                    int percent = (int) (100 * downloaded / total) % 100;

                    Timber.d("Progress: Total - %d - Downloaded - %d - Percent - %d", total, downloaded, percent);
                    loadingView.setPercent(percent);
                })
                .intoImageView(view.getImageView())
                .then((error, view) -> {
                    if (error == null) {
                        loadingView.stopOk();
                        this.view.showImageView();
                    } else {
                        loadingView.stopFailure();
                    }
                });
    }

    private void setUpScreen() {
        activity.setTitle(image.getTitle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int color = ContextCompat.getColor(activity, R.color.dark_gray);

            activity.getWindow().setStatusBarColor(color);
            activity.getWindow().setNavigationBarColor(color);
        }

        loadScreenOn();
    }

    private void loadScreenOn() {
        if (preferences.getBoolean(PreferenceKey.KEEP_SCREEN_ON, ISConstant.DEFAULT_KEEP_SCREEN_ON)) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    private void unloadScreenOn() {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}