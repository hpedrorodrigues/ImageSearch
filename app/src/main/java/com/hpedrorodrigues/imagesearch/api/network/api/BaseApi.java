package com.hpedrorodrigues.imagesearch.api.network.api;

import com.hpedrorodrigues.imagesearch.api.network.services.ServiceFactory;

import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import rx.Observable;

public abstract class BaseApi {

    @Inject
    public ServiceFactory serviceFactory;

    public abstract Api getApi();

    protected abstract Observable<Map> search(final String text,
                                              final Integer page,
                                              final Integer perPage,
                                              final Boolean safeSearch);

    protected abstract Call<Map> callSearch(final String text,
                                            final Integer page,
                                            final Integer perPage,
                                            final Boolean safeSearch);

    protected int getOffset(int page, int perPage) {
        return (page - 1) * perPage;
    }
}