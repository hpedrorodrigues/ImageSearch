package com.hpedrorodrigues.imagesearch.api.network.services.giphy;

public enum GiphyRating {

    GENERAL_AUDIENCES("g"), // All ages admitted
    PARENTAL_GUIDANCE_SUGGESTED("pg"), // Some material may not be suitable for children
    PARENTS_STRONGLY_CAUTIONED("pg-13"), //Some material may be inappropriate for children under 13
    RESTRICTED("r"), // Under 17 requires accompanying parent or adult guardian
    Y("y");

    private final String value;

    GiphyRating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}