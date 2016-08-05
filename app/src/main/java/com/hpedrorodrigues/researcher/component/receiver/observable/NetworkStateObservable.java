package com.hpedrorodrigues.researcher.component.receiver.observable;

public class NetworkStateObservable extends BaseObservable {

    public void stateConnectionChanged() {
        setChanged();
        notifyObservers();
    }
}