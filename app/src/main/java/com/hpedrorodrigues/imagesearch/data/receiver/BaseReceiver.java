package com.hpedrorodrigues.imagesearch.data.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hpedrorodrigues.imagesearch.injection.application.ISApplication;
import com.hpedrorodrigues.imagesearch.injection.component.ISComponent;

abstract class BaseReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        onHandleReceive(getComponent(context), context, intent);
    }

    private ISComponent getComponent(Context context) {
        return ((ISApplication) context.getApplicationContext()).getComponent();
    }

    protected abstract void onHandleReceive(ISComponent component, Context context, Intent intent);
}