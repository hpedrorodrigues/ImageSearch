package com.hpedrorodrigues.researcher.util.general;

import android.app.WallpaperManager;

import com.hpedrorodrigues.researcher.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import timber.log.Timber;

public class WallpaperUtil {

    @Inject
    public WallpaperManager manager;

    @Inject
    public ToastUtil toastUtil;

    @Inject
    public WallpaperUtil() {
    }

    public void change(String path) {
        try {
            InputStream stream = getStreamFromPath(path);

            if (stream != null) {
                manager.setStream(stream);
                toastUtil.showLong(R.string.wallpaper_changed);
            }
        } catch (IOException e) {
            toastUtil.showLong(R.string.error_changing_wallpaper);
            Timber.e("Error changing wallpaper with path %s", path);
        }
    }

    private InputStream getStreamFromPath(String path) {
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            toastUtil.showLong(R.string.error_changing_wallpaper);
            Timber.e(e, "Error getting image file from path %s", path);
        }

        return null;
    }
}