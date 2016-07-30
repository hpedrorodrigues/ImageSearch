package com.hpedrorodrigues.imagesearch.api.entity;

import com.hpedrorodrigues.imagesearch.api.network.api.Api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class Image {

    private String title;

    private String description;

    private String thumbnailUrl;

    private String imageUrl;

    private String webSiteUrl;

    private Integer width;

    private Integer height;

    private Api api;
}