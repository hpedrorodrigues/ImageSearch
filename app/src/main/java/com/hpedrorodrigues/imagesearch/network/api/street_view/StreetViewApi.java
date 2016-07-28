package com.hpedrorodrigues.imagesearch.network.api.street_view;

import com.hpedrorodrigues.imagesearch.BuildConfig;

import javax.inject.Inject;

public class StreetViewApi {

    private String ENDPOINT = "https://maps.googleapis.com/maps/api/streetview";
    private String API_KEY = BuildConfig.STREET_VIEW_API_KEY;

    @Inject
    public StreetViewApi() {
    }

    public String createImageUrl(Integer width, Integer height,
                                 Double latitude, Double longitude,
                                 Double heading, Double pitch,
                                 Integer scale) {

        return new StringBuilder()
                .append(ENDPOINT)
                .append("?")
                .append("size=")
                .append(width)
                .append("x")
                .append(height)
                .append("&")
                .append("location=")
                .append(latitude)
                .append(",")
                .append(longitude)
                .append("&")
                .append("heading=")
                .append(heading)
                .append("&")
                .append("pitch=")
                .append(pitch)
                .append("&")
                .append("scale=")
                .append(scale)
                .append("&")
                .append("key=")
                .append(API_KEY)
                .toString();
    }
}