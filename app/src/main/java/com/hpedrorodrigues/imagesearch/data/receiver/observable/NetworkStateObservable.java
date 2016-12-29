package com.hpedrorodrigues.imagesearch.data.receiver.observable;

public class NetworkStateObservable extends BaseObservable {

    public void stateConnectionChanged() {
        setChanged();
        notifyObservers();
    }
}