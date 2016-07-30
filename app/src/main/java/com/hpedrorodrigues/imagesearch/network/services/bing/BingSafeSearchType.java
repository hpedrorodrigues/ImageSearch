package com.hpedrorodrigues.imagesearch.network.services.bing;

public enum BingSafeSearchType {

    OFF("Off"),
    MODERATE("Moderate"),
    STRICT("Strict");

    private final String value;

    BingSafeSearchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}