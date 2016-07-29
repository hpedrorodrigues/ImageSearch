package com.hpedrorodrigues.imagesearch.entity;

import com.hpedrorodrigues.imagesearch.network.api.Api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
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