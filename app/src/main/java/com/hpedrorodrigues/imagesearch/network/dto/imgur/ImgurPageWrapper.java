package com.hpedrorodrigues.imagesearch.network.dto.imgur;

import java.util.List;

import lombok.Data;

@Data
public class ImgurPageWrapper {

    private List<ImgurImageDetail> data;

    private Integer status;

    private Boolean success;
}