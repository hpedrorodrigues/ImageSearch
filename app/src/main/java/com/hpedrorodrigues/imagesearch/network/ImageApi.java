package com.hpedrorodrigues.imagesearch.network;

import com.hpedrorodrigues.imagesearch.network.api.cse.CSEApi;
import com.hpedrorodrigues.imagesearch.network.api.cse.CSEParameter;
import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrApi;
import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrMethod;
import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrOutputFormat;
import com.hpedrorodrigues.imagesearch.network.api.imgur.ImgurImageType;
import com.hpedrorodrigues.imagesearch.network.api.street_view.StreetViewApi;
import com.hpedrorodrigues.imagesearch.network.dto.cse.CSEPageWrapper;
import com.hpedrorodrigues.imagesearch.network.dto.flickr.FlickrPageWrapper;
import com.hpedrorodrigues.imagesearch.network.dto.imgur.ImgurPageWrapper;

import javax.inject.Inject;

import rx.Observable;

public class ImageApi {

    @Inject
    public APIFactory apiFactory;

    @Inject
    public StreetViewApi streetViewApi;

    @Inject
    public ImageApi() {
    }

    public String createImageUrl(Integer width, Integer height,
                                 Double latitude, Double longitude,
                                 Double heading, Double pitch,
                                 Integer scale) {
        return streetViewApi.createImageUrl(width, height, latitude, longitude, heading, pitch, scale);
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

    public Observable<CSEPageWrapper> cseSearch(String text, Integer start, Integer num) {
        return apiFactory.getCseApi()
                .search(
                        CSEApi.API_KEY,
                        CSEParameter.RSZ,
                        start,
                        num,
                        CSEParameter.SOURCE,
                        CSEParameter.GSS,
                        CSEParameter.SIG,
                        CSEParameter.SEARCH_TYPE,
                        CSEParameter.CX,
                        CSEParameter.GOOGLE_HOST,
                        text
                );
    }

    public Observable<ImgurPageWrapper> imgurSearch(String text, Integer perPage, Integer page) {
        return apiFactory.getImgurApi()
                .search(
                        ImgurImageType.JPG.getValue(),
                        text,
                        page,
                        perPage
                );
    }
}