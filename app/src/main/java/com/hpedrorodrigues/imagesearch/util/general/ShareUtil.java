package com.hpedrorodrigues.imagesearch.util.general;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.hpedrorodrigues.imagesearch.R;

import java.io.File;

import javax.inject.Inject;

public class ShareUtil {

    @Inject
    public Context context;

    @Inject
    public ShareUtil() {
    }

    public void shareApp(final Activity activity) {
        final String message = activity.getString(R.string.share_app_message,
                "https://play.google.com/store/apps/details?id=" + activity.getPackageName());

        shareText(activity, message);
    }

    public void shareText(final Activity activity, final String text) {
        final Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");

        activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.choose_app)));
    }

    public void shareImage(final Activity activity, final String path) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(path)));

        activity.startActivity(Intent.createChooser(shareIntent, activity.getString(R.string.choose_app)));
    }
}