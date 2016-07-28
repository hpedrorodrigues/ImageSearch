package com.hpedrorodrigues.imagesearch.network.dto.bing;

import lombok.Data;

@Data
public class BingThumbnail {

    private Integer height;

    private Integer width;

    private String thumbnailUrl;
}