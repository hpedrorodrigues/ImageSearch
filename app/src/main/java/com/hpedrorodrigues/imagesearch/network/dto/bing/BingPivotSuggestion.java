package com.hpedrorodrigues.imagesearch.network.dto.bing;

import java.util.List;

import lombok.Data;

@Data
public class BingPivotSuggestion {

    private String pivot;

    private List<BingSuggestion> suggestions;
}