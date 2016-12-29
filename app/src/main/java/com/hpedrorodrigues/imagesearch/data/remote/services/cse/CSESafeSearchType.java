package com.hpedrorodrigues.imagesearch.data.remote.services.cse;

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