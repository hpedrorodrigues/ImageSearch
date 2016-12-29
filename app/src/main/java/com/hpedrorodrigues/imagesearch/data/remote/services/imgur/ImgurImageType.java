package com.hpedrorodrigues.imagesearch.data.remote.services.imgur;

public enum ImgurImageType {
    JPG("jpg"),
    PNG("png"),
    GIF("gif"),
    ANIGIF("anigif"),
    ALBUM("album");

    private final String value;

    ImgurImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}