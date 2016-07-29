package com.hpedrorodrigues.imagesearch.network.api;

import com.hpedrorodrigues.imagesearch.network.services.cse.CSEParameter;
import com.hpedrorodrigues.imagesearch.network.services.cse.CSEService;

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
                .getCseApi()
                .search(
                        CSEService.API_KEY,
                        CSEParameter.RSZ,
                        CSEParameter.SOURCE,
                        CSEParameter.GSS,
                        CSEParameter.SIG,
                        CSEParameter.SEARCH_TYPE,
                        CSEParameter.CX,
                        CSEParameter.GOOGLE_HOST,
                        text,
                        page,
                        perPage
                );
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getCseApi()
                .callSearch(
                        CSEService.API_KEY,
                        CSEParameter.RSZ,
                        CSEParameter.SOURCE,
                        CSEParameter.GSS,
                        CSEParameter.SIG,
                        CSEParameter.SEARCH_TYPE,
                        CSEParameter.CX,
                        CSEParameter.GOOGLE_HOST,
                        text,
                        page,
                        perPage
                );
    }
}