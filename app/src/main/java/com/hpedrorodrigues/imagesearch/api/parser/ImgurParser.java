package com.hpedrorodrigues.imagesearch.api.parser;

import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.util.StringUtil;

import java.util.List;
import java.util.Map;

class ImgurParser extends BaseParser {

    public ImgurParser() {
    }

    @Override
    protected List<Map> getImages(Map data) {
        return (List<Map>) data.get("data");
    }

    @Override
    protected Image asImage(Map info) {
        Image image = new Image();

        image.setTitle(asString(info.get("title")));
        image.setDescription(asString(info.get("description")));
        image.setWidth(asInteger(info.get("width")));
        image.setHeight(asInteger(info.get("height")));
        image.setWebSiteUrl(null);
        image.setThumbnailUrl(asString(info.get("link")));
        image.setImageUrl(asString(info.get("link")));

        return image;
    }

    @Override
    protected boolean conditionToAdd(Map info) {
        return !StringUtil.isEmpty(asString(info.get("type")));
    }

    @Override
    protected Api getApi() {
        return Api.IMGUR;
    }
}