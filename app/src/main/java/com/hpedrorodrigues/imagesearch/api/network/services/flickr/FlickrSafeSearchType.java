package com.hpedrorodrigues.imagesearch.api.network.services.flickr;

public enum FlickrSafeSearchType {

    SAFE(1),
    MODERATE(2),
    RESTRICTED(3);

    private final int value;

    FlickrSafeSearchType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}