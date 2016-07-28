package com.hpedrorodrigues.imagesearch.network.dto.cse;

import java.util.List;

import lombok.Data;

@Data
public class CSEPageWrapper {

    private CSECursor cursor;

    private CSEContext context;

    private List<CSEImageDetail> results;
}