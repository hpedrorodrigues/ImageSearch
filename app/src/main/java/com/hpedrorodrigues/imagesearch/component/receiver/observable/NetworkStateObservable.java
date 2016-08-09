package com.hpedrorodrigues.imagesearch.component.receiver.observable;

public class NetworkStateObservable extends BaseObservable {

    public void stateConnectionChanged() {
        setChanged();
        notifyObservers();
    }
}