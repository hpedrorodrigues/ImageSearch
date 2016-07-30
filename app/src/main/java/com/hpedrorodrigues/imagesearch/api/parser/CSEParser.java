package com.hpedrorodrigues.imagesearch.api.parser;

import com.hpedrorodrigues.imagesearch.api.network.api.Api;
import com.hpedrorodrigues.imagesearch.entity.Image;

import java.util.List;
import java.util.Map;

class CSEParser extends BaseParser {

    public CSEParser() {
    }

    @Override
    protected List<Map> getImages(Map data) {
        return (List<Map>) data.get("results");
    }

    @Override
    protected Image asImage(Map info) {
        Image image = new Image();

        image.setTitle(asString(info.get("titleNoFormatting")));
        image.setDescription(asString(info.get("contentNoFormatting")));
        image.setWidth(asInteger(info.get("width")));
        image.setHeight(asInteger(info.get("height")));
        image.setWebSiteUrl(asString(info.get("originalContextUrl")));
        image.setThumbnailUrl(asString(info.get("tbUrl")));
        image.setImageUrl(asString(info.get("unescapedUrl")));

        return image;
    }

    @Override
    protected boolean conditionToAdd(Map info) {
        return true;
    }

    @Override
    protected Api getApi() {
        return Api.CSE;
    }
}