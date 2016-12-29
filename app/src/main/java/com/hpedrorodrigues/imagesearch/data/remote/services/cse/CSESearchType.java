package com.hpedrorodrigues.imagesearch.data.remote.services.cse;

public enum CSESearchType {

    IMAGE("image");

    private final String value;

    CSESearchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}