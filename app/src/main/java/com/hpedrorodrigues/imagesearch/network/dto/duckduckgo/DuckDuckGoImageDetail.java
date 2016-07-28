package com.hpedrorodrigues.imagesearch.network.dto.duckduckgo;

import lombok.Data;

@Data
public class DuckDuckGoImageDetail {

    private String title;

    private String thumbnail;

    private String source;

    private Integer width;

    private Integer height;

    private String image;

    private String url;
}