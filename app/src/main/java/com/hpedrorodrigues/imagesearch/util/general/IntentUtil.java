package com.hpedrorodrigues.imagesearch.util.general;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.hpedrorodrigues.imagesearch.util.CollectionUtil;

import java.util.List;

import javax.inject.Inject;

public class IntentUtil {

    @Inject
    public IntentUtil() {
    }

    public boolean isAvailable(final Activity activity, final Intent intent) {
        final PackageManager manager = activity.getPackageManager();
        final List<ResolveInfo> list = manager
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return !CollectionUtil.isEmpty(list);
    }
}