package com.hpedrorodrigues.imagesearch.util.general;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.hpedrorodrigues.imagesearch.R;

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

    public void enqueueDownload(String downloadUrl, String directory) {
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
                .setTitle(fileName)
                .setDescription(downloadUrl)
                .setDestinationInExternalPublicDir(getDirectoryName(directory), fileName);

        downloadManager.enqueue(request);

        Toast.makeText(
                context,
                context.getString(R.string.downloading, downloadUri),
                Toast.LENGTH_LONG
        ).show();
    }

    private String getDirectoryName(String directory) {
        return directory.startsWith(File.separator) ? directory : File.separator + directory;
    }
}