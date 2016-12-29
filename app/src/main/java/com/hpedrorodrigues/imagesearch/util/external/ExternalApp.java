package com.hpedrorodrigues.imagesearch.util.external;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.hpedrorodrigues.imagesearch.util.general.IntentUtil;

import javax.inject.Inject;

public class ExternalApp {

    @Inject
    public IntentUtil intentUtil;

    @Inject
    public ExternalApp() {
    }

    public void openBrowser(final Activity activity, final String url) {
        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public void openPlayStore(final Activity activity) {
        final String packageName = activity.getPackageName();
        final Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("market://details?id=" + packageName));

        if (!intentUtil.isAvailable(activity, intent)) {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
        }

        activity.startActivity(intent);
    }
}