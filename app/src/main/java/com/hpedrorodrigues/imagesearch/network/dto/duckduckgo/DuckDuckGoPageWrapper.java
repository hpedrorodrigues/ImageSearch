package com.hpedrorodrigues.imagesearch.network.dto.duckduckgo;

import java.util.List;

import lombok.Data;

@Data
public class DuckDuckGoPageWrapper {

    private String query;

    private String next;

    private String ads;

    private List<DuckDuckGoImageDetail> results;
}