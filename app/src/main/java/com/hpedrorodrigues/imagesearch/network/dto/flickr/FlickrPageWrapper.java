package com.hpedrorodrigues.imagesearch.network.dto.flickr;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class FlickrPageWrapper {

    @SerializedName("photos")
    private FlickrPage page;

    @SerializedName("stat")
    private String status;
}