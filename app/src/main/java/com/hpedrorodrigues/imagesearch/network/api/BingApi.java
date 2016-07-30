package com.hpedrorodrigues.imagesearch.network.api;

import com.hpedrorodrigues.imagesearch.network.services.bing.BingSafeSearchType;

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
                .search(
                        text,
                        page,
                        perPage,
                        safeSearch ? BingSafeSearchType.STRICT.getValue() : BingSafeSearchType.OFF.getValue()
                );
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getBingService()
                .callSearch(
                        text,
                        page,
                        perPage,
                        safeSearch ? BingSafeSearchType.STRICT.getValue() : BingSafeSearchType.OFF.getValue()
                );
    }
}