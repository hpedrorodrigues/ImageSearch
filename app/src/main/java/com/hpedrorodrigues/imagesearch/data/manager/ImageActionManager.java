package com.hpedrorodrigues.imagesearch.data.manager;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.component.service.ConnectionService;
import com.hpedrorodrigues.imagesearch.util.StringUtil;
import com.hpedrorodrigues.imagesearch.util.general.DownloadUtil;
import com.hpedrorodrigues.imagesearch.util.general.ShareUtil;
import com.hpedrorodrigues.imagesearch.util.general.ToastUtil;
import com.hpedrorodrigues.imagesearch.util.general.WallpaperUtil;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.EmptyPermissionListener;

import javax.inject.Inject;

import static com.hpedrorodrigues.imagesearch.util.general.DownloadUtil.OnDownloadListener;

public class ImageActionManager {

    @Inject
    public Context context;

    @Inject
    public ClipboardManager clipboardManager;

    @Inject
    public ShareUtil shareUtil;

    @Inject
    public DownloadUtil downloadManager;

    @Inject
    public ToastUtil toastUtil;

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
        final String text = getImageUrlByConnection(image);
        final ClipData clip = ClipData.newPlainText(text, text);

        clipboardManager.setPrimaryClip(clip);
        toastUtil.showLong(context.getString(R.string.text_copied_to_clipboard, text));
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

        downloadManager.enqueueDownload(imageUrl, activity, new OnDownloadListener() {

            @Override
            public void onStart() {
                toastUtil.showLong(activity.getString(R.string.downloading, image.getImageUrl()));
            }

            @Override
            public void onCompleted(final String path) {
                if (StringUtil.isEmpty(path)) {
                    toastUtil.showLong(activity.getString(R.string.error_downloading_image));
                } else {
                    wallpaperUtil.change(path);
                }
            }
        });
    }

    private void internalShareImage(final Image image, final Activity activity) {
        final String imageUrl = getImageUrlByConnection(image);

        downloadManager.enqueueDownload(imageUrl, activity, new OnDownloadListener() {

            @Override
            public void onStart() {
                toastUtil.showLong(activity.getString(R.string.downloading, image.getImageUrl()));
            }

            @Override
            public void onCompleted(final String path) {
                if (StringUtil.isEmpty(path)) {
                    toastUtil.showLong(activity.getString(R.string.error_downloading_image));
                } else {
                    shareUtil.shareImage(activity, path);
                }
            }
        });
    }

    private void internalDownloadImage(final Image image, final Activity activity) {
        final String imageUrl = getImageUrlByConnection(image);

        downloadManager.enqueueDownload(imageUrl, activity, new OnDownloadListener() {

            @Override
            public void onStart() {
                toastUtil.showLong(activity.getString(R.string.downloading, image.getImageUrl()));
            }

            @Override
            public void onCompleted(final String path) {
                final String message = StringUtil.isEmpty(path)
                        ? activity.getString(R.string.error_downloading_image)
                        : activity.getString(R.string.image_downloaded_successful, path);

                toastUtil.showLong(message);
            }
        });
    }
}