package com.hpedrorodrigues.imagesearch.ui.api.activity.presenter;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.goka.flickableview.FlickableImageView;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Feature;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.constant.IntentKey;
import com.hpedrorodrigues.imagesearch.ui.activity.ImageActivity;
import com.hpedrorodrigues.imagesearch.ui.api.activity.view.LoadImageView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.util.StringUtil;
import com.hpedrorodrigues.imagesearch.util.general.BroadcastUtil;
import com.hpedrorodrigues.imagesearch.util.general.ClipboardUtil;
import com.hpedrorodrigues.imagesearch.util.general.DownloadUtil;
import com.hpedrorodrigues.imagesearch.util.general.FeatureUtil;
import com.hpedrorodrigues.imagesearch.util.general.ShareUtil;
import com.hpedrorodrigues.imagesearch.util.general.ToastUtil;
import com.koushikdutta.ion.Ion;

import javax.inject.Inject;

import timber.log.Timber;

public class ImagePresenter extends BasePresenter<ImageActivity> {

    private LoadImageView view;

    private Image image;

    @Inject
    public FeatureUtil featureUtil;

    @Inject
    public ClipboardUtil clipboardUtil;

    @Inject
    public ShareUtil shareUtil;

    @Inject
    public DownloadUtil downloadUtil;

    @Inject
    public ToastUtil toastUtil;

    @Inject
    public BroadcastUtil broadcastUtil;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        activity.getMenuInflater().inflate(R.menu.image, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        featureUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigator.toActivityParent();
                break;
            case R.id.action_share:
                Feature shareFeature = createImageFeature(image);
                featureUtil.requestFeature(shareFeature, (feature, permissionGranted) -> {
                    if (permissionGranted) {
                        shareImage(image);
                    } else {
                        toastUtil.showLong(activity.getString(R.string.permission_not_granted));
                    }
                });
                break;
            case R.id.action_share_link:
                shareUtil.shareText(activity, image.getImageUrl());
                break;
            case R.id.action_copy_link:
                clipboardUtil.copy(image.getImageUrl());
                break;
            case R.id.action_download:
                Feature downloadFeature = createImageFeature(image);
                featureUtil.requestFeature(downloadFeature, (feature, permissionGranted) -> {
                    if (permissionGranted) {
                        downloadImage(image);
                    } else {
                        toastUtil.showLong(activity.getString(R.string.permission_not_granted));
                    }
                });
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onIntent(Intent intent, Bundle extras) {
        image = (Image) extras.getSerializable(IntentKey.IMAGE);
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

            int color = activity.getResources().getColor(R.color.black, activity.getTheme());
            activity.getWindow().setNavigationBarColor(color);
        }
    }

    private Feature createImageFeature(Image image) {
        Feature feature = new Feature();

        feature.setActivity(activity);
        feature.setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        feature.setRequestCode(8888);
        feature.setValue(image);

        return feature;
    }

    private void downloadImage(Image image) {
        long imageId = downloadUtil.enqueueDownload(image.getImageUrl(), ISConstant.DEFAULT_DIRECTORY);

        toastUtil.showLong(activity.getString(R.string.downloading, image.getImageUrl()));

        broadcastUtil.register(activity, DownloadManager.ACTION_DOWNLOAD_COMPLETE, (context1, intent) -> {
            if (downloadUtil.isCompleted(imageId)) {

                String path = downloadUtil.getPathById(imageId);
                String message = StringUtil.isEmpty(path)
                        ? activity.getString(R.string.error_downloading_image)
                        : activity.getString(R.string.image_downloaded_successful, path);

                toastUtil.showLong(message);

                return true;
            }

            return false;
        });
    }

    private void shareImage(Image image) {
        long imageId = downloadUtil.enqueueDownload(image.getImageUrl(), ISConstant.DEFAULT_DIRECTORY);

        toastUtil.showLong(activity.getString(R.string.downloading, image.getImageUrl()));

        broadcastUtil.register(activity, DownloadManager.ACTION_DOWNLOAD_COMPLETE, (context1, intent) -> {
            if (downloadUtil.isCompleted(imageId)) {

                String path = downloadUtil.getPathById(imageId);
                if (StringUtil.isEmpty(path)) {
                    toastUtil.showLong(activity.getString(R.string.error_downloading_image));
                } else {
                    shareUtil.shareImage(activity, path);
                }

                return true;
            }

            return false;
        });
    }
}