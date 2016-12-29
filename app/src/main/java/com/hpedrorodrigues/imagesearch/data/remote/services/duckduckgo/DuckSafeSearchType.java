package com.hpedrorodrigues.imagesearch.data.remote.services.duckduckgo;

public enum DuckSafeSearchType {

    ON(1),
    OFF(-1);

    private final int value;

    DuckSafeSearchType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}