package com.hpedrorodrigues.imagesearch.data.remote.services.imgur;

public enum ImgurThumbnailSize {

    SMALL_SQUARE("s"),
    BIG_SQUARE("b"),
    SMALL_THUMBNAIL("t"),
    MEDIUM_THUMBNAIL("m"),
    LARGE_THUMBNAIL("l"),
    HUGE_THUMBNAIL("h");

    private final String value;

    ImgurThumbnailSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}