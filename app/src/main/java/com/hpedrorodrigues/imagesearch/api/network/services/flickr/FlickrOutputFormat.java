package com.hpedrorodrigues.imagesearch.api.network.services.flickr;

public enum FlickrOutputFormat {

    JSON("json"),
    XML("rest"),
    PHP_SERIAL("php_serial");

    private final String value;

    FlickrOutputFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}