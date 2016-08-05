package com.hpedrorodrigues.researcher.api.network.services.pixabay;

public enum PixabayImageType {

    ALL("all"),
    PHOTO("photo"),
    ILLUSTRATION("illustration"),
    VECTOR("vector");

    private final String value;

    PixabayImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}