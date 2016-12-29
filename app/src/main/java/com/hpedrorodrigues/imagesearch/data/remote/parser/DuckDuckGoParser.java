package com.hpedrorodrigues.imagesearch.data.remote.parser;

import com.hpedrorodrigues.imagesearch.data.model.Image;
import com.hpedrorodrigues.imagesearch.data.remote.api.Api;

import java.util.List;
import java.util.Map;

class DuckDuckGoParser extends BaseParser {

    public DuckDuckGoParser() {
    }

    @Override
    protected List<Map> getImages(Map data) {
        return (List<Map>) data.get("results");
    }

    @Override
    protected Image asImage(Map info) {
        Image image = new Image();

        image.setTitle(asString(info.get("title")));
        image.setDescription(asString(info.get("source")));
        image.setWidth(asInteger(info.get("width")));
        image.setHeight(asInteger(info.get("height")));
        image.setWebSiteUrl(asString(info.get("url")));
        image.setThumbnailUrl(asString(info.get("thumbnail")));
        image.setImageUrl(asString(info.get("image")));

        return image;
    }

    @Override
    protected boolean conditionToAdd(Map info) {
        return true;
    }

    @Override
    protected Api getApi() {
        return Api.DUCK_DUCK_GO;
    }
}