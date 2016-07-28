package com.hpedrorodrigues.imagesearch.network.dto.bing;

import lombok.Data;

@Data
public class BingImageDetail {

    private String contentSize;

    private String contentUrl;

    private String datePublished;

    private Integer width;

    private Integer height;

    private String encodingFormat;

    private String accentColor;

    private String hostPageUrl;

    private String hostPageDisplayUrl;

    private String imageInsightsToken;

    private String webSearchUrl;

    private String imageId;

    private BingThumbnail thumbnail;

    private BingInsightsSourcesSummary insightsSourcesSummary;

    private String name;

    private String thumbnailUrl;
}