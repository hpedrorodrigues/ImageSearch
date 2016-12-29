package com.hpedrorodrigues.imagesearch.util.general;

import android.os.Build;

import javax.inject.Inject;

public class DeviceInfoUtil {

    @Inject
    public DeviceInfoUtil() {
    }

    public String getDetails() {
        return new StringBuilder()
                .append("Device details:")
                .append("\n\n")
                .append("Device: ")
                .append(Build.DEVICE)
                .append("\n")
                .append("CPU: ")
                .append(Build.CPU_ABI)
                .append("\n")
                .append("Manufacturer: ")
                .append(Build.MANUFACTURER)
                .append("\n")
                .append("Model: ")
                .append(Build.MODEL)
                .append("\n")
                .append("Hardware: ")
                .append(Build.HARDWARE)
                .append("\n")
                .append("Brand: ")
                .append(Build.BRAND)
                .append("\n")
                .append("Tags: ")
                .append(Build.TAGS)
                .append("\n")
                .append("Android version: ")
                .append(Build.VERSION.RELEASE)
                .append(" (API ")
                .append(Build.VERSION.SDK_INT)
                .append(")")
                .append("\n\n")
                .append("---------------------------------------------")
                .append("\n\n\n\n")
                .toString();
    }
}