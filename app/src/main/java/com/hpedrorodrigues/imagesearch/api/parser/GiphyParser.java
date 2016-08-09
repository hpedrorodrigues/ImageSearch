package com.hpedrorodrigues.imagesearch.api.parser;

import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;

import java.util.List;
import java.util.Map;

class GiphyParser extends BaseParser {

    public GiphyParser() {
    }

    @Override
    protected List<Map> getImages(Map data) {
        return (List<Map>) data.get("data");
    }

    @Override
    protected Image asImage(Map info) {
        Image image = new Image();

        Map images = (Map) info.get("images");
        Map largeImage = (Map) images.get("downsized_large");
        Map smallImage = (Map) images.get("fixed_height_small");

        image.setTitle(asString(info.get("slug")));
        image.setDescription(asString(info.get("id")));
        image.setWidth(asInteger(largeImage.get("width")));
        image.setHeight(asInteger(largeImage.get("height")));
        image.setWebSiteUrl(asString(info.get("url")));
        image.setThumbnailUrl(asString(smallImage.get("url")));
        image.setImageUrl(asString(largeImage.get("url")));

        return image;
    }

    @Override
    protected boolean conditionToAdd(Map info) {
        return true;
    }

    @Override
    protected Api getApi() {
        return Api.GIPHY;
    }
}