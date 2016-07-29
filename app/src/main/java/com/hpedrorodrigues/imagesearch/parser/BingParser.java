package com.hpedrorodrigues.imagesearch.parser;

import com.hpedrorodrigues.imagesearch.entity.Image;
import com.hpedrorodrigues.imagesearch.network.api.Api;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

class BingParser extends BaseParser {

    @Inject
    public BingParser() {
    }

    @Override
    protected List<Map> getImages(Map data) {
        return (List<Map>) data.get("value");
    }

    @Override
    protected Image asImage(Map info) {
        Image image = new Image();

        image.setTitle(asString(info.get("name")));
        image.setDescription(asString(info.get("datePublished")));
        image.setWidth(asInteger(info.get("width")));
        image.setHeight(asInteger(info.get("height")));
        image.setWebSiteUrl(asString(info.get("hostPageUrl")));
        image.setThumbnailUrl(asString(info.get("thumbnailUrl")));
        image.setImageUrl(asString(info.get("contentUrl")));

        return image;
    }

    @Override
    protected boolean conditionToAdd(Map info) {
        return true;
    }

    @Override
    protected Api getApi() {
        return Api.BING;
    }
}