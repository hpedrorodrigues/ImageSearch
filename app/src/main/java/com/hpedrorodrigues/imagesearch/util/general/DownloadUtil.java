package com.hpedrorodrigues.imagesearch.util.general;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.util.UUID;

import javax.inject.Inject;

public class DownloadUtil {

    @Inject
    public Context context;

    @Inject
    public DownloadManager downloadManager;

    @Inject
    public DownloadUtil() {
    }

    public long enqueueDownload(String downloadUrl, String directory) {
        File direct = new File(Environment.getExternalStorageDirectory(), directory);

        if (!direct.exists()) {
            direct.mkdirs();
        }

        Uri downloadUri = Uri.parse(downloadUrl);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);

        String fileName = UUID.randomUUID().toString();
        int types = DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE;

        request.setAllowedNetworkTypes(types)
                .setAllowedOverRoaming(false)
                .setVisibleInDownloadsUi(true)
                .setTitle(fileName)
                .setDescription(downloadUrl)
                .setDestinationInExternalPublicDir(getDirectoryName(directory), fileName);

        return downloadManager.enqueue(request);
    }

    public String getPathById(long id) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);

        Cursor cursor = downloadManager.query(query);

        if (cursor.moveToFirst()) {

            int pathIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
            return cursor.getString(pathIndex);
        }

        return null;
    }

    private String getDirectoryName(String directory) {
        return directory.startsWith(File.separator) ? directory : File.separator + directory;
    }
}