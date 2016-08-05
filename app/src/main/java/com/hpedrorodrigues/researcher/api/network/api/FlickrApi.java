package com.hpedrorodrigues.researcher.api.network.api;

import com.hpedrorodrigues.researcher.api.network.services.flickr.FlickrCallback;
import com.hpedrorodrigues.researcher.api.network.services.flickr.FlickrMethod;
import com.hpedrorodrigues.researcher.api.network.services.flickr.FlickrOutputFormat;
import com.hpedrorodrigues.researcher.api.network.services.flickr.FlickrSafeSearchType;
import com.hpedrorodrigues.researcher.api.network.services.flickr.FlickrService;

import java.util.Map;

import retrofit2.Call;
import rx.Observable;

class FlickrApi extends BaseApi {

    public FlickrApi() {
    }

    @Override
    public Api getApi() {
        return Api.FLICKR;
    }

    @Override
    protected Observable<Map> search(final String text, final Integer page,
                                     final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getFlickrService()
                .search(
                        FlickrMethod.SEARCH.getId(),
                        FlickrService.API_KEY,
                        FlickrOutputFormat.JSON.getValue(),
                        FlickrCallback.OFF.getValue(),
                        text,
                        page,
                        perPage,
                        safeSearch ? FlickrSafeSearchType.SAFE.getValue() : FlickrSafeSearchType.MODERATE.getValue()
                );
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getFlickrService()
                .callSearch(
                        FlickrMethod.SEARCH.getId(),
                        FlickrService.API_KEY,
                        FlickrOutputFormat.JSON.getValue(),
                        FlickrCallback.OFF.getValue(),
                        text,
                        page,
                        perPage,
                        safeSearch ? FlickrSafeSearchType.SAFE.getValue() : FlickrSafeSearchType.MODERATE.getValue()
                );
    }
}