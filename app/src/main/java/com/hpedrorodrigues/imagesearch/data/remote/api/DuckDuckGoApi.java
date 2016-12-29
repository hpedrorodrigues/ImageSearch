package com.hpedrorodrigues.imagesearch.data.remote.api;

import com.hpedrorodrigues.imagesearch.data.remote.services.duckduckgo.DuckDuckGoService;
import com.hpedrorodrigues.imagesearch.data.remote.services.duckduckgo.DuckSafeSearchType;

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
        final int defaultPerPage = DuckDuckGoService.DEFAULT_IMAGES_COUNT;

        return serviceFactory
                .getDuckDuckGoService()
                .search(
                        text,
                        getOffset(page, defaultPerPage),
                        defaultPerPage,
                        safeSearch ? DuckSafeSearchType.ON.getValue() : DuckSafeSearchType.OFF.getValue()
                );
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        final int defaultPerPage = DuckDuckGoService.DEFAULT_IMAGES_COUNT;

        return serviceFactory
                .getDuckDuckGoService()
                .callSearch(
                        text,
                        getOffset(page, defaultPerPage),
                        defaultPerPage,
                        safeSearch ? DuckSafeSearchType.ON.getValue() : DuckSafeSearchType.OFF.getValue()
                );
    }
}