package com.hpedrorodrigues.imagesearch.network.dto.flickr;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class FlickrPhoto {

    private String id;

    private String owner;

    private String secret;

    private String server;

    private Integer farm;

    private String title;

    @SerializedName("ispublic")
    private Integer isPublic;

    @SerializedName("isfriend")
    private Integer isFriend;

    @SerializedName("isfamily")
    private Integer isFamily;

    public String getPhotoUrl() {
        return new StringBuilder()
                .append("https://farm")
                .append(getFarm())
                .append(".staticflickr.com/")
                .append(getServer())
                .append("/")
                .append(getId())
                .append("_")
                .append(getSecret())
                .append(".jpg")
                .toString();
    }

    public String getLargePhotoUrl() {
        return new StringBuilder()
                .append("https://farm")
                .append(getFarm())
                .append(".staticflickr.com/")
                .append(getServer())
                .append("/")
                .append(getId())
                .append("_")
                .append(getSecret())
                .append("_b")
                .append(".jpg")
                .toString();
    }
}