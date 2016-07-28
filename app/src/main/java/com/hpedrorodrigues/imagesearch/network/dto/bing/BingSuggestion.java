package com.hpedrorodrigues.imagesearch.network.dto.bing;

import lombok.Data;

@Data
public class BingSuggestion {

    private String searchLink;

    private String webSearchUrl;

    private String text;

    private BingThumbnail thumbnail;

    private String displayText;
}