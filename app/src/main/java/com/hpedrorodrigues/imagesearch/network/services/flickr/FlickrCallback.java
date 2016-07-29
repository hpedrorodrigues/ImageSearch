package com.hpedrorodrigues.imagesearch.network.services.flickr;

public enum FlickrCallback {

    ON(0),
    OFF(1);

    private final int value;

    FlickrCallback(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}