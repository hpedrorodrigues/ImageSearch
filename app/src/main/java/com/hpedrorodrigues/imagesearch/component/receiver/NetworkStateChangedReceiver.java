package com.hpedrorodrigues.imagesearch.component.receiver;

import android.content.Context;
import android.content.Intent;

import com.hpedrorodrigues.imagesearch.component.receiver.observable.NetworkStateObservable;
import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;

import javax.inject.Inject;

public class NetworkStateChangedReceiver extends BaseReceiver {

    @Inject
    public NetworkStateObservable observable;

    @Override
    protected void onHandleReceive(ISComponent component, Context context, Intent intent) {
        component.inject(this);

        observable.stateConnectionChanged();
    }
}