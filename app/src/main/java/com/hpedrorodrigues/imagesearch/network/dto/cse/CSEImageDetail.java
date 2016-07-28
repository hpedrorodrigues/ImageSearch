package com.hpedrorodrigues.imagesearch.network.dto.cse;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CSEImageDetail {

    @SerializedName("GsearchResultClass")
    private String gSearchResultClass;

    private String content;

    private String contentNoFormatting;

    private String fileFormat;

    private String title;

    private String titleNoFormatting;

    private String unescapedUrl;

    private String url;

    private String visibleUrl;

    private String originalContextUrl;

    private String height;

    private String width;

    private String tbUrl;

    private String tbHeight;

    private String tbWidth;

    private String imageId;
}