package com.hpedrorodrigues.imagesearch.util.general;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hpedrorodrigues.imagesearch.R;

import javax.inject.Inject;

public class ShareUtil {

    @Inject
    public Context context;

    @Inject
    public ShareUtil() {
    }

    public void shareText(Activity activity, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");

        activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.choose_app)));
    }
}