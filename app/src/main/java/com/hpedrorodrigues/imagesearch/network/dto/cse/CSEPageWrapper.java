package com.hpedrorodrigues.imagesearch.network.dto.cse;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CSEPageWrapper {

    private CSECursor cursor;

    private CSEContext context;

    private List<CSEImageDetail> results;
}