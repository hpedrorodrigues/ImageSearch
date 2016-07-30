package com.hpedrorodrigues.imagesearch.api.parser;

import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;

import java.util.List;
import java.util.Map;

class PixabayParser extends BaseParser {

    public PixabayParser() {
    }

    @Override
    protected List<Map> getImages(Map data) {
        return (List<Map>) data.get("hits");
    }

    @Override
    protected Image asImage(Map info) {
        Image image = new Image();

        image.setTitle(asString(info.get("previewURL")));
        image.setDescription(asString(info.get("user")));
        image.setWidth(asInteger(info.get("webformatWidth")));
        image.setHeight(asInteger(info.get("webformatHeight")));
        image.setWebSiteUrl(asString(info.get("pageURL")));
        image.setThumbnailUrl(asString(info.get("previewURL")));
        image.setImageUrl(asString(info.get("webformatURL")));

        return image;
    }

    @Override
    protected boolean conditionToAdd(Map info) {
        return true;
    }

    @Override
    protected Api getApi() {
        return Api.PIXABAY;
    }
}