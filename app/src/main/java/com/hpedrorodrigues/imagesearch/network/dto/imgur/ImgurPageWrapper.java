package com.hpedrorodrigues.imagesearch.network.dto.imgur;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ImgurPageWrapper {

    private List<ImgurImageDetail> data;

    private Integer status;

    private Boolean success;
}