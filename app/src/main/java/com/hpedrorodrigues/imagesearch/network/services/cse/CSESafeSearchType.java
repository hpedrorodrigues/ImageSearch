package com.hpedrorodrigues.imagesearch.network.services.cse;

public enum CSESafeSearchType {

    ACTIVE("active"),
    MODERATE("moderate"),
    OFF("off");

    private final String value;

    CSESafeSearchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}