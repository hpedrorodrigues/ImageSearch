package com.hpedrorodrigues.imagesearch.network.dto.flickr;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class FlickrPage {

    private Integer page;

    private Integer pages;

    @SerializedName("perpage")
    private Integer perPage;

    private String total;

    @SerializedName("photo")
    private List<FlickrPhoto> photos;
}