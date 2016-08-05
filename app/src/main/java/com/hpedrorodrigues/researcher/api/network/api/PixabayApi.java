package com.hpedrorodrigues.researcher.api.network.api;

import com.hpedrorodrigues.researcher.api.network.services.pixabay.PixabayImageType;
import com.hpedrorodrigues.researcher.api.network.services.pixabay.PixabayService;

import java.util.Map;

import retrofit2.Call;
import rx.Observable;

class PixabayApi extends BaseApi {

    public PixabayApi() {
    }

    @Override
    public Api getApi() {
        return Api.PIXABAY;
    }

    @Override
    protected Observable<Map> search(final String text, final Integer page,
                                     final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getPixabayService()
                .search(
                        PixabayService.API_KEY,
                        PixabayImageType.PHOTO.getValue(),
                        text,
                        page,
                        perPage,
                        safeSearch
                );
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getPixabayService()
                .callSearch(
                        PixabayService.API_KEY,
                        PixabayImageType.PHOTO.getValue(),
                        text,
                        page,
                        perPage,
                        safeSearch
                );
    }
}