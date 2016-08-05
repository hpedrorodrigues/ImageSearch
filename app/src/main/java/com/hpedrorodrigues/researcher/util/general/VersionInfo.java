package com.hpedrorodrigues.researcher.util.general;

import android.content.Context;
import android.content.pm.PackageManager;

import javax.inject.Inject;

import timber.log.Timber;

public class VersionInfo {

    @Inject
    public Context context;

    @Inject
    public VersionInfo() {
    }

    public String getVersionName() {
        String version = "-";

        try {

            version = context
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionName;
        } catch (PackageManager.NameNotFoundException e) {

            Timber.e(e, "Error getting version name");
        }

        return version;
    }

    public int getVersionCode() {
        int version = 0;

        try {

            version = context
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionCode;
        } catch (PackageManager.NameNotFoundException e) {

            Timber.e(e, "Error getting version name");
        }

        return version;
    }
}