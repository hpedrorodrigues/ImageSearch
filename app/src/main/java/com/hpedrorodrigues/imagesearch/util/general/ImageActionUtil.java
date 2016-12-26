package com.hpedrorodrigues.imagesearch.util.general;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Feature;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.component.service.ConnectionService;
import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.util.StringUtil;

import javax.inject.Inject;

public class ImageActionUtil {

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

    @Inject
    public FeatureUtil featureUtil;

    @Inject
    public WallpaperUtil wallpaperUtil;

    @Inject
    public ConnectionService connection;

    @Inject
    public ImageActionUtil() {
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        featureUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void shareImageUrl(Image image, Activity activity) {
        shareUtil.shareText(activity, getImageUrlByConnection(image));
    }

    public void copyImageUrl(Image image) {
        clipboardUtil.copy(getImageUrlByConnection(image));
    }

    public void changeWallpaper(final Image image, final Activity activity) {
        Feature wallpaperFeature = new Feature();

        wallpaperFeature.setActivity(activity);
        wallpaperFeature.setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        wallpaperFeature.setRequestCode(9999);
        wallpaperFeature.setValue(image);

        featureUtil.requestFeature(wallpaperFeature, new FeatureUtil.onFeaturePermittedListener() {

            @Override
            public void onPermitted(Feature feature, boolean permissionGranted) {
                if (permissionGranted) {
                    internalChangeWallpaper(image, activity);
                } else {
                    toastUtil.showLong(activity.getString(R.string.permission_not_granted));
                }
            }
        });
    }

    public void shareImage(final Image image, final Activity activity) {
        Feature shareFeature = new Feature();

        shareFeature.setActivity(activity);
        shareFeature.setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        shareFeature.setRequestCode(9999);
        shareFeature.setValue(image);

        featureUtil.requestFeature(shareFeature, new FeatureUtil.onFeaturePermittedListener() {

            @Override
            public void onPermitted(Feature feature, boolean permissionGranted) {
                if (permissionGranted) {
                    internalShareImage(image, activity);
                } else {
                    toastUtil.showLong(activity.getString(R.string.permission_not_granted));
                }
            }
        });
    }

    public void downloadImage(final Image image, final Activity activity) {
        Feature downloadFeature = new Feature();

        downloadFeature.setActivity(activity);
        downloadFeature.setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        downloadFeature.setRequestCode(9998);
        downloadFeature.setValue(image);

        featureUtil.requestFeature(downloadFeature, new FeatureUtil.onFeaturePermittedListener() {

            @Override
            public void onPermitted(Feature feature, boolean permissionGranted) {
                if (permissionGranted) {
                    internalDownloadImage(image, activity);
                } else {
                    toastUtil.showLong(activity.getString(R.string.permission_not_granted));
                }
            }
        });
    }

    private String getImageUrlByConnection(Image image) {
        return connection.isConnectedFast() ? image.getImageUrl() : image.getThumbnailUrl();
    }

    private void internalChangeWallpaper(final Image image, final Activity activity) {
        final String imageUrl = getImageUrlByConnection(image);
        final long imageId = downloadUtil.enqueueDownload(imageUrl, ISConstant.DEFAULT_DIRECTORY);

        toastUtil.showLong(activity.getString(R.string.downloading, image.getImageUrl()));

        broadcastUtil.register(activity, DownloadManager.ACTION_DOWNLOAD_COMPLETE,
                new BroadcastUtil.OnReceive() {

                    @Override
                    public boolean receive(Context context, Intent intent) {
                        if (downloadUtil.isCompleted(imageId)) {

                            String path = downloadUtil.getPathById(imageId);
                            if (StringUtil.isEmpty(path)) {
                                toastUtil.showLong(activity.getString(R.string.error_downloading_image));
                            } else {
                                wallpaperUtil.change(path);
                            }

                            return true;
                        }

                        return false;
                    }
                });
    }

    private void internalShareImage(final Image image, final Activity activity) {
        final String imageUrl = getImageUrlByConnection(image);
        final long imageId = downloadUtil.enqueueDownload(imageUrl, ISConstant.DEFAULT_DIRECTORY);

        toastUtil.showLong(activity.getString(R.string.downloading, image.getImageUrl()));

        broadcastUtil.register(activity, DownloadManager.ACTION_DOWNLOAD_COMPLETE,
                new BroadcastUtil.OnReceive() {

                    @Override
                    public boolean receive(Context context, Intent intent) {
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
                    }
                });
    }

    private void internalDownloadImage(final Image image, final Activity activity) {
        final String imageUrl = getImageUrlByConnection(image);
        final long imageId = downloadUtil.enqueueDownload(imageUrl, ISConstant.DEFAULT_DIRECTORY);

        toastUtil.showLong(activity.getString(R.string.downloading, image.getImageUrl()));

        broadcastUtil.register(activity, DownloadManager.ACTION_DOWNLOAD_COMPLETE, new BroadcastUtil.OnReceive() {
            @Override
            public boolean receive(Context context, Intent intent) {
                if (downloadUtil.isCompleted(imageId)) {

                    String path = downloadUtil.getPathById(imageId);
                    String message = StringUtil.isEmpty(path)
                            ? activity.getString(R.string.error_downloading_image)
                            : activity.getString(R.string.image_downloaded_successful, path);

                    toastUtil.showLong(message);

                    return true;
                }

                return false;
            }
        });
    }
}