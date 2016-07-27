package com.hpedrorodrigues.imagesearch.network.dto.cse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CSEContext {

    private String title;

    @SerializedName("total_results")
    private String totalResults;

    private List<CSEFacet> facets;
}