package com.hpedrorodrigues.imagesearch.data.remote.parser;

import com.hpedrorodrigues.imagesearch.data.model.Image;
import com.hpedrorodrigues.imagesearch.data.remote.api.Api;
import com.hpedrorodrigues.imagesearch.data.remote.services.imgur.ImgurThumbnailSize;
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

        String imageUrl = asString(info.get("link"));

        image.setTitle(asString(info.get("title")));
        image.setDescription(asString(info.get("description")));
        image.setWidth(asInteger(info.get("width")));
        image.setHeight(asInteger(info.get("height")));
        image.setWebSiteUrl(null);
        image.setThumbnailUrl(getThumbnailUrl(imageUrl));
        image.setImageUrl(imageUrl);

        return image;
    }

    @Override
    protected boolean conditionToAdd(Map info) {
        String type = asString(info.get("type"));
        return !StringUtil.isEmpty(type) && type.equals("image/jpeg");
    }

    @Override
    protected Api getApi() {
        return Api.IMGUR;
    }

    private String getThumbnailUrl(String imageUrl) {
        if (imageUrl.contains(".")) {

            String format = imageUrl.substring(imageUrl.lastIndexOf("."), imageUrl.length());
            String thumbnailSize = ImgurThumbnailSize.SMALL_THUMBNAIL.getValue();
            return imageUrl.substring(0, imageUrl.lastIndexOf(".")) + thumbnailSize + format;
        }

        return imageUrl;
    }
}