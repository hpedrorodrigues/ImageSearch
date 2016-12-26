package com.hpedrorodrigues.imagesearch.util.general;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import javax.inject.Inject;

public class BroadcastUtil {

    @Inject
    public BroadcastUtil() {
    }

    public void register(final Activity activity, final String action, final OnReceive onReceive) {
        IntentFilter filter = new IntentFilter(action);

        activity.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (onReceive.receive(context, intent)) {
                    activity.unregisterReceiver(this);
                }
            }
        }, filter);
    }

    public interface OnReceive {

        boolean receive(Context context, Intent intent);
    }
}