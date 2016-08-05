package com.hpedrorodrigues.researcher.api.network.api;

import com.hpedrorodrigues.researcher.api.network.services.imgur.ImgurImageType;

import java.util.Map;

import retrofit2.Call;
import rx.Observable;

class ImgurApi extends BaseApi {

    public ImgurApi() {
    }

    @Override
    public Api getApi() {
        return Api.IMGUR;
    }

    @Override
    protected Observable<Map> search(final String text, final Integer page,
                                     final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getImgurService()
                .search(
                        ImgurImageType.JPG.getValue(),
                        text,
                        page,
                        perPage
                );
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getImgurService()
                .callSearch(
                        ImgurImageType.JPG.getValue(),
                        text,
                        page,
                        perPage
                );
    }
}