package com.hpedrorodrigues.imagesearch.api.network.api;

import java.util.Map;

import retrofit2.Call;
import rx.Observable;

class DuckDuckGoApi extends BaseApi {

    public DuckDuckGoApi() {
    }

    @Override
    public Api getApi() {
        return Api.DUCK_DUCK_GO;
    }

    @Override
    protected Observable<Map> search(final String text, final Integer page,
                                     final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getDuckDuckGoService()
                .search(text, page, perPage, safeSearch);
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getDuckDuckGoService()
                .callSearch(text, page, perPage, safeSearch);
    }
}