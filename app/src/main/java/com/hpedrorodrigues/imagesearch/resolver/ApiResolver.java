package com.hpedrorodrigues.imagesearch.resolver;

import com.hpedrorodrigues.imagesearch.entity.ImageEntity;
import com.hpedrorodrigues.imagesearch.network.dto.cse.CSEImageDetail;
import com.hpedrorodrigues.imagesearch.network.dto.cse.CSEPageWrapper;
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
}