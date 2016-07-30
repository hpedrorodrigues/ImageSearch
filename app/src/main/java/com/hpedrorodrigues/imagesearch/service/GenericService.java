package com.hpedrorodrigues.imagesearch.service;

import com.hpedrorodrigues.imagesearch.entity.Image;
import com.hpedrorodrigues.imagesearch.network.api.Api;
import com.hpedrorodrigues.imagesearch.network.api.GenericApi;
import com.hpedrorodrigues.imagesearch.network.services.street_view.StreetViewImageDetail;
import com.hpedrorodrigues.imagesearch.parser.GenericParser;
import com.hpedrorodrigues.imagesearch.util.EnumUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import lombok.Data;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

@Data
public class GenericService {

    @Inject
    public GenericApi genericApi;

    @Inject
    public GenericParser genericParser;

    @Inject
    public GenericService() {
    }

    public Observable<Map> search(final Api api, final String search,
                                  final int page, final int perPage,
                                  final boolean safeSearch) {

        return genericApi.search(api, search, page, perPage, safeSearch);
    }

    public Call<Map> callSearch(final Api api, final String search,
                                final int page, final int perPage,
                                final boolean safeSearch) {

        return genericApi.callSearch(api, search, page, perPage, safeSearch);
    }

    public Observable<List<Image>> searchAll(final String search, final int page, final int perPage,
                                             final boolean safeSearch) {

        return Observable
                .create((Subscriber<? super List<Image>> subscriber) -> {

                    try {
                        List<Image> images = new ArrayList<>();
                        List<Api> apis = EnumUtil.valuesAsList(Api.class);

                        for (Api api : apis) {
                            images.addAll(callSearchAnParse(api, search, page, perPage, safeSearch));
                        }

                        subscriber.onNext(images);
                    } catch (Throwable t) {

                        subscriber.onError(t);
                    } finally {

                        subscriber.onCompleted();
                    }
                });
    }

    public List<Image> parse(final Api api, final Map data) {
        return genericParser.parse(api, data);
    }

    public String getImageUrl(StreetViewImageDetail imageDetail) {
        return genericApi.getImageUrl(imageDetail);
    }

    private List<Image> callSearchAnParse(final Api api, final String search,
                                          final int page, final int perPage,
                                          final boolean safeSearch) throws IOException {

        Call<Map> call = genericApi.callSearch(api, search, page, perPage, safeSearch);

        Response<Map> response = call.execute();

        if (response.isSuccessful()) {

            return genericParser.parse(api, response.body());
        }

        return Collections.emptyList();
    }
}