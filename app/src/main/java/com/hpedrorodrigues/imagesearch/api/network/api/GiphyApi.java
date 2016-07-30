package com.hpedrorodrigues.imagesearch.api.network.api;

import com.hpedrorodrigues.imagesearch.api.network.services.giphy.GiphyRating;
import com.hpedrorodrigues.imagesearch.api.network.services.giphy.GiphyService;

import java.util.Map;

import retrofit2.Call;
import rx.Observable;

class GiphyApi extends BaseApi {

    public GiphyApi() {
    }

    @Override
    public Api getApi() {
        return Api.GIPHY;
    }

    @Override
    protected Observable<Map> search(final String text, final Integer page,
                                     final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getGiphyService()
                .search(
                        GiphyService.API_KEY,
                        text,
                        page,
                        perPage,
                        safeSearch ? GiphyRating.RESTRICTED.getValue() : GiphyRating.GENERAL_AUDIENCES.getValue()
                );
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getGiphyService()
                .callSearch(
                        GiphyService.API_KEY,
                        text,
                        page,
                        perPage,
                        safeSearch ? GiphyRating.RESTRICTED.getValue() : GiphyRating.GENERAL_AUDIENCES.getValue()
                );
    }
}