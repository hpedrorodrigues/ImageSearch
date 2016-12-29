package com.hpedrorodrigues.imagesearch.data.remote.api;

import com.hpedrorodrigues.imagesearch.data.remote.services.cse.CSESafeSearchType;
import com.hpedrorodrigues.imagesearch.data.remote.services.cse.CSESearchType;
import com.hpedrorodrigues.imagesearch.data.remote.services.cse.CSEService;

import java.util.Map;

import retrofit2.Call;
import rx.Observable;

class CSEApi extends BaseApi {

    public CSEApi() {
    }

    @Override
    public Api getApi() {
        return Api.CSE;
    }

    @Override
    protected Observable<Map> search(final String text, final Integer page,
                                     final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getCseService()
                .search(
                        CSEService.API_KEY,
                        CSESearchType.IMAGE.getValue(),
                        CSEService.CX,
                        text,
                        getOffset(page, perPage),
                        perPage,
                        safeSearch ? CSESafeSearchType.ACTIVE.getValue() : CSESafeSearchType.OFF.getValue()
                );
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getCseService()
                .callSearch(
                        CSEService.API_KEY,
                        CSESearchType.IMAGE.getValue(),
                        CSEService.CX,
                        text,
                        getOffset(page, perPage),
                        perPage,
                        safeSearch ? CSESafeSearchType.ACTIVE.getValue() : CSESafeSearchType.OFF.getValue()
                );
    }
}