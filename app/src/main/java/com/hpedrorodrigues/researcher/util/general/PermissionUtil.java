package com.hpedrorodrigues.researcher.util.general;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

public class PermissionUtil {

    @Inject
    public ToastUtil toastUtil;

    @Inject
    public Context context;

    @Inject
    public PermissionUtil() {
    }

    public void requestPermission(Activity activity, int requestCode, String... permissions) {
        if (!permissionGranted(permissions)) {

            if (!shouldShowRequestPermissionRationale(activity, permissions)) {

                ActivityCompat.requestPermissions(activity, permissions, requestCode);
            }
        }
    }

    public boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        for (String permission : permissions) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {

                return false;
            }
        }

        return true;
    }

    public boolean permissionGranted(String... permissions) {
        for (String permission : permissions) {

            if (!permissionGranted(permission)) {

                return false;
            }
        }

        return true;
    }

    public boolean permissionGranted(String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(
                context,
                permission
        );

        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    public boolean permissionGranted(int[] grantResults) {
        return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }
}