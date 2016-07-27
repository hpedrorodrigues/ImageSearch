package com.hpedrorodrigues.imagesearch.network;

import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrApi;
import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrMethod;
import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrOutputFormat;
import com.hpedrorodrigues.imagesearch.network.dto.flickr.FlickrPageWrapper;

import javax.inject.Inject;

import rx.Observable;

public class ImageApi {

    @Inject
    public APIFactory apiFactory;

    @Inject
    public ImageApi() {
    }

    public Observable<FlickrPageWrapper> flickrSearch(String text, Integer perPage, Integer page) {
        return apiFactory.getFlickrApi()
                .search(
                        FlickrMethod.SEARCH.getId(),
                        FlickrApi.API_KEY,
                        FlickrOutputFormat.JSON.getValue(),
                        text,
                        perPage,
                        page,
                        1
                );
    }
}