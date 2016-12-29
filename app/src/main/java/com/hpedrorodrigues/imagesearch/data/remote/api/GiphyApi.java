package com.hpedrorodrigues.imagesearch.data.remote.api;

import com.hpedrorodrigues.imagesearch.data.remote.services.giphy.GiphyRating;
import com.hpedrorodrigues.imagesearch.data.remote.services.giphy.GiphyService;

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
                        getOffset(page, perPage),
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
                        getOffset(page, perPage),
                        perPage,
                        safeSearch ? GiphyRating.RESTRICTED.getValue() : GiphyRating.GENERAL_AUDIENCES.getValue()
                );
    }
}