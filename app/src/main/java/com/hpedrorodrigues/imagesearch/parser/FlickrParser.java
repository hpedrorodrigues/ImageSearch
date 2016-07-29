package com.hpedrorodrigues.imagesearch.parser;

import com.hpedrorodrigues.imagesearch.entity.Image;
import com.hpedrorodrigues.imagesearch.network.api.Api;

import java.util.List;
import java.util.Map;

class FlickrParser extends BaseParser {

    public FlickrParser() {
    }

    @Override
    protected List<Map> getImages(Map data) {
        Map<String, Object> photos = (Map) data.get("photos");
        return (List<Map>) photos.get("photo");
    }

    @Override
    protected Image asImage(Map info) {
        Image image = new Image();

        image.setTitle(asString(info.get("title")));
        image.setDescription(asString(info.get("id")));
        image.setWidth(null);
        image.setHeight(null);
        image.setWebSiteUrl(null);
        image.setThumbnailUrl(createThumbnailUrl(info));
        image.setImageUrl(createImageUrl(info));

        return image;
    }

    @Override
    protected boolean conditionToAdd(Map info) {
        return true;
    }

    @Override
    protected Api getApi() {
        return Api.FLICKR;
    }

    private String createThumbnailUrl(Map imageDetail) {
        return new StringBuilder()
                .append("https://farm")
                .append(asInteger(imageDetail.get("farm")))
                .append(".staticflickr.com/")
                .append(imageDetail.get("server"))
                .append("/")
                .append(imageDetail.get("id"))
                .append("_")
                .append(imageDetail.get("secret"))
                .append(".jpg")
                .toString();
    }

    private String createImageUrl(Map imageDetail) {
        return new StringBuilder()
                .append("https://farm")
                .append(asInteger(imageDetail.get("farm")))
                .append(".staticflickr.com/")
                .append(imageDetail.get("server"))
                .append("/")
                .append(imageDetail.get("id"))
                .append("_")
                .append(imageDetail.get("secret"))
                .append("_b")
                .append(".jpg")
                .toString();
    }
}