package com.hpedrorodrigues.researcher.api.network.services.duckduckgo;

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