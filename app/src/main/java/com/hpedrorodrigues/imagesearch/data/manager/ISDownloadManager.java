package com.hpedrorodrigues.imagesearch.data.manager;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.util.UUID;

import javax.inject.Inject;

import static android.app.DownloadManager.COLUMN_LOCAL_FILENAME;
import static android.app.DownloadManager.COLUMN_STATUS;
import static android.app.DownloadManager.Query;
import static android.app.DownloadManager.Request;
import static android.app.DownloadManager.STATUS_FAILED;
import static android.app.DownloadManager.STATUS_SUCCESSFUL;

public class ISDownloadManager {

    @Inject
    public Context context;

    @Inject
    public DownloadManager downloadManager;

    @Inject
    public ISDownloadManager() {
    }

    public void enqueueDownload(final String url, final Activity activity,
                                final OnDownloadListener listener) {
        final IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        final long id = enqueueDownload(url);

        listener.onStart();

        activity.registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(final Context context, final Intent intent) {
                if (isCompleted(id)) {
                    listener.onCompleted(getPathById(id));
                    activity.unregisterReceiver(this);
                }
            }
        }, filter);
    }

    private long enqueueDownload(final String downloadUrl) {
        final String directory = Environment.DIRECTORY_DOWNLOADS;
        final File direct = new File(Environment.getExternalStorageDirectory(), directory);

        if (!direct.exists()) {
            direct.mkdirs();
        }

        final Uri downloadUri = Uri.parse(downloadUrl);
        final Request request = new Request(downloadUri);

        final String fileName = UUID.randomUUID().toString() + getFileFormat(downloadUrl);
        int types = Request.NETWORK_WIFI | Request.NETWORK_MOBILE;

        request.setAllowedNetworkTypes(types)
                .setAllowedOverRoaming(false)
                .setVisibleInDownloadsUi(true)
                .setTitle(fileName)
                .setDescription(downloadUrl)
                .setDestinationInExternalPublicDir(getDirectoryName(directory), fileName);

        return downloadManager.enqueue(request);
    }

    private boolean isCompleted(final long id) {
        final Integer status = getStatusById(id);
        return status != null && (status.equals(STATUS_FAILED) || status.equals(STATUS_SUCCESSFUL));
    }

    private Integer getStatusById(final long id) {
        final Query query = new Query();
        query.setFilterById(id);

        final Cursor cursor = downloadManager.query(query);
        if (cursor.moveToFirst()) {
            final int status = cursor.getInt(cursor.getColumnIndex(COLUMN_STATUS));
            cursor.close();
            return status;
        }
        cursor.close();

        return null;
    }

    private String getPathById(final long id) {
        final Query query = new Query();
        query.setFilterById(id);

        final Cursor cursor = downloadManager.query(query);
        if (cursor.moveToFirst()) {
            if (getStatusById(id) == STATUS_SUCCESSFUL) {
                final String path = cursor.getString(cursor.getColumnIndex(COLUMN_LOCAL_FILENAME));
                cursor.close();
                return path;
            }
        }
        cursor.close();

        return null;
    }

    private String getDirectoryName(final String directory) {
        return directory.startsWith(File.separator) ? directory : File.separator + directory;
    }

    private String getFileFormat(final String downloadUrl) {
        if (downloadUrl.contains(".")) {
            final int indexOfDot = downloadUrl.lastIndexOf(".") + 1;
            final String format = downloadUrl.substring(indexOfDot, downloadUrl.length());
            return "." + format;
        }

        return "";
    }

    public interface OnDownloadListener {

        void onStart();

        void onCompleted(final String path);
    }
}