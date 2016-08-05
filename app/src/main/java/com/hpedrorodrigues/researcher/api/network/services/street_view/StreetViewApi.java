package com.hpedrorodrigues.researcher.api.network.services.street_view;

import com.hpedrorodrigues.researcher.BuildConfig;

import javax.inject.Inject;

public class StreetViewApi {

    private String ENDPOINT = "https://maps.googleapis.com/maps/api/streetview";
    private String API_KEY = BuildConfig.STREET_VIEW_API_KEY;

    @Inject
    public StreetViewApi() {
    }

    public String getImageUrl(StreetViewImageDetail imageDetail) {
        return new StringBuilder()
                .append(ENDPOINT)
                .append("?")
                .append("size=")
                .append(imageDetail.getWidth())
                .append("x")
                .append(imageDetail.getHeight())
                .append("&")
                .append("location=")
                .append(imageDetail.getLatitude())
                .append(",")
                .append(imageDetail.getLongitude())
                .append("&")
                .append("heading=")
                .append(imageDetail.getHeading())
                .append("&")
                .append("pitch=")
                .append(imageDetail.getPitch())
                .append("&")
                .append("scale=")
                .append(imageDetail.getScale())
                .append("&")
                .append("key=")
                .append(API_KEY)
                .toString();
    }
}