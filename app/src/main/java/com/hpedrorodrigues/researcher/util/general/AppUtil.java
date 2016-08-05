package com.hpedrorodrigues.researcher.util.general;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import javax.inject.Inject;

public class AppUtil {

    @Inject
    public IntentUtil intentUtil;

    @Inject
    public AppUtil() {
    }

    public void viewOnPlayStore(Activity activity) {
        String packageName = activity.getPackageName();
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("market://details?id=" + packageName));

        if (!intentUtil.isAvailable(activity, intent)) {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
        }

        activity.startActivity(intent);
    }
}