package com.hpedrorodrigues.imagesearch.data.manager;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.component.service.ConnectionService;
import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.util.StringUtil;
import com.hpedrorodrigues.imagesearch.util.general.BroadcastUtil;
import com.hpedrorodrigues.imagesearch.util.general.ClipboardUtil;
import com.hpedrorodrigues.imagesearch.util.general.DownloadUtil;
import com.hpedrorodrigues.imagesearch.util.general.ShareUtil;
import com.hpedrorodrigues.imagesearch.util.general.ToastUtil;
import com.hpedrorodrigues.imagesearch.util.general.WallpaperUtil;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.EmptyPermissionListener;

import javax.inject.Inject;

public class ImageActionManager {

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
    public WallpaperUtil wallpaperUtil;

    @Inject
    public ConnectionService connection;

    @Inject
    public ImageActionManager() {
    }

    public void shareImageUrl(final Image image, final Activity activity) {
        shareUtil.shareText(activity, getImageUrlByConnection(image));
    }

    public void copyImageUrl(final Image image) {
        clipboardUtil.copy(getImageUrlByConnection(image));
    }

    public void changeWallpaper(final Image image, final Activity activity) {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new EmptyPermissionListener() {

                    @Override
                    public void onPermissionGranted(final PermissionGrantedResponse response) {
                        internalChangeWallpaper(image, activity);
                    }
                })
                .check();
    }

    public void shareImage(final Image image, final Activity activity) {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new EmptyPermissionListener() {

                    @Override
                    public void onPermissionGranted(final PermissionGrantedResponse response) {
                        internalShareImage(image, activity);
                    }
                })
                .check();
    }

    public void downloadImage(final Image image, final Activity activity) {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new EmptyPermissionListener() {

                    @Override
                    public void onPermissionGranted(final PermissionGrantedResponse response) {
                        internalDownloadImage(image, activity);
                    }
                })
                .check();
    }

    private String getImageUrlByConnection(final Image image) {
        return connection.isConnectedFast() ? image.getImageUrl() : image.getThumbnailUrl();
    }

    private void internalChangeWallpaper(final Image image, final Activity activity) {
        final String imageUrl = getImageUrlByConnection(image);
        final long imageId = downloadUtil.enqueueDownload(imageUrl, ISConstant.DEFAULT_DIRECTORY);

        toastUtil.showLong(activity.getString(R.string.downloading, image.getImageUrl()));

        broadcastUtil.register(activity, DownloadManager.ACTION_DOWNLOAD_COMPLETE,
                new BroadcastUtil.OnReceive() {

                    @Override
                    public boolean receive(final Context context, final Intent intent) {
                        if (downloadUtil.isCompleted(imageId)) {

                            final String path = downloadUtil.getPathById(imageId);
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
                    public boolean receive(final Context context, final Intent intent) {
                        if (downloadUtil.isCompleted(imageId)) {

                            final String path = downloadUtil.getPathById(imageId);
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
            public boolean receive(final Context context, final Intent intent) {
                if (downloadUtil.isCompleted(imageId)) {

                    final String path = downloadUtil.getPathById(imageId);
                    final String message = StringUtil.isEmpty(path)
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