package com.hpedrorodrigues.imagesearch.entity;

import com.hpedrorodrigues.imagesearch.resolver.Api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class ImageEntity {

    private String title;

    private String description;

    private String thumbnailUrl;

    private String imageUrl;

    private Api api;
}