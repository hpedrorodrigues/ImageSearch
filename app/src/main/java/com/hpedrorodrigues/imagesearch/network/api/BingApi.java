package com.hpedrorodrigues.imagesearch.network.api;

import com.hpedrorodrigues.imagesearch.network.services.bing.BingParameter;

import java.util.Map;

import retrofit2.Call;
import rx.Observable;

class BingApi extends BaseApi {

    public BingApi() {
    }

    @Override
    public Api getApi() {
        return Api.BING;
    }

    @Override
    protected Observable<Map> search(final String text, final Integer page,
                                     final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getBingService()
                .search(text, page, perPage, BingParameter.SAFE_SEARCH);
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getBingService()
                .callSearch(text, page, perPage, BingParameter.SAFE_SEARCH);
    }
}