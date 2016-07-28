package com.hpedrorodrigues.imagesearch.resolver;

import com.hpedrorodrigues.imagesearch.entity.ImageEntity;
import com.hpedrorodrigues.imagesearch.network.dto.bing.BingImageDetail;
import com.hpedrorodrigues.imagesearch.network.dto.bing.BingPageWrapper;
import com.hpedrorodrigues.imagesearch.network.dto.cse.CSEImageDetail;
import com.hpedrorodrigues.imagesearch.network.dto.cse.CSEPageWrapper;
import com.hpedrorodrigues.imagesearch.network.dto.duckduckgo.DuckDuckGoImageDetail;
import com.hpedrorodrigues.imagesearch.network.dto.duckduckgo.DuckDuckGoPageWrapper;
import com.hpedrorodrigues.imagesearch.network.dto.flickr.FlickrPageWrapper;
import com.hpedrorodrigues.imagesearch.network.dto.flickr.FlickrPhoto;
import com.hpedrorodrigues.imagesearch.network.dto.imgur.ImgurImageDetail;
import com.hpedrorodrigues.imagesearch.network.dto.imgur.ImgurPageWrapper;
import com.hpedrorodrigues.imagesearch.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ApiResolver {

    @Inject
    public ApiResolver() {
    }

    public List<ImageEntity> resolve(Api api, Object object) {
        switch (api) {

            case FLICKR:
                return resolveFlickr((FlickrPageWrapper) object);

            case CSE:
                return resolverCSE((CSEPageWrapper) object);

            case IMGUR:
                return resolveImgur((ImgurPageWrapper) object);

            case DUCK_DUCK_GO:
                return resolveDuckDuckGo((DuckDuckGoPageWrapper) object);

            case BING:
                return resolveBing((BingPageWrapper) object);

            default:
                throw new IllegalArgumentException("Unsupported api " + api);
        }
    }

    private List<ImageEntity> resolveFlickr(FlickrPageWrapper wrapper) {
        List<ImageEntity> images = new ArrayList<>();

        for (FlickrPhoto photo : wrapper.getPage().getPhotos()) {
            images.add(new ImageEntity(
                    photo.getTitle(),
                    null,
                    photo.getPhotoUrl(),
                    photo.getLargePhotoUrl(),
                    Api.FLICKR
            ));
        }

        return images;
    }

    private List<ImageEntity> resolverCSE(CSEPageWrapper wrapper) {
        List<ImageEntity> images = new ArrayList<>();

        for (CSEImageDetail imageDetail : wrapper.getResults()) {
            images.add(new ImageEntity(
                    imageDetail.getTitleNoFormatting(),
                    imageDetail.getContent(),
                    imageDetail.getTbUrl(),
                    imageDetail.getUnescapedUrl(),
                    Api.CSE
            ));
        }

        return images;
    }

    private List<ImageEntity> resolveImgur(ImgurPageWrapper wrapper) {
        List<ImageEntity> images = new ArrayList<>();

        for (ImgurImageDetail imageDetail : wrapper.getData()) {

            if (!StringUtil.isEmpty(imageDetail.getType())) {
                images.add(new ImageEntity(
                        imageDetail.getTitle(),
                        imageDetail.getDescription(),
                        imageDetail.getLink(),
                        imageDetail.getLink(),
                        Api.IMGUR
                ));
            }
        }

        return images;
    }

    private List<ImageEntity> resolveDuckDuckGo(DuckDuckGoPageWrapper wrapper) {
        List<ImageEntity> images = new ArrayList<>();

        for (DuckDuckGoImageDetail imageDetail : wrapper.getResults()) {

            images.add(new ImageEntity(
                    imageDetail.getTitle(),
                    imageDetail.getSource(),
                    imageDetail.getThumbnail(),
                    imageDetail.getImage(),
                    Api.DUCK_DUCK_GO
            ));
        }

        return images;
    }

    private List<ImageEntity> resolveBing(BingPageWrapper wrapper) {
        List<ImageEntity> images = new ArrayList<>();

        for (BingImageDetail imageDetail : wrapper.getValue()) {
            images.add(new ImageEntity(
                    imageDetail.getName(),
                    imageDetail.getDatePublished(),
                    imageDetail.getThumbnailUrl(),
                    imageDetail.getContentUrl(),
                    Api.BING
            ));
        }

        return images;
    }
}