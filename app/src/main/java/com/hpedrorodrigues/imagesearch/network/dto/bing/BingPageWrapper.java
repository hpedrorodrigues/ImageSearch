package com.hpedrorodrigues.imagesearch.network.dto.bing;

import java.util.List;

import lombok.Data;

@Data
public class BingPageWrapper {

    private String displayRecipeSourcesBadges;

    private String webSearchUrl;

    private String _type;

    private String totalEstimatedMatches;

    private String displayShoppingSourcesBadges;

    private List<BingImageDetail> value;

    private List<BingQueryExpansion> queryExpansions;

    private String nextOffsetAddCount;

    private BingInstrumentation instrumentation;

    private String readLink;

    private List<BingPivotSuggestion> pivotSuggestions;
}