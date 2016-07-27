package com.hpedrorodrigues.imagesearch.network.dto.cse;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CSECursor {

    private Integer currentPageIndex;

    private String estimatedResultCount;

    private String moreResultsUrl;

    private String resultCount;

    private String searchResultTime;

    private List<CSEPageInfo> pages;
}