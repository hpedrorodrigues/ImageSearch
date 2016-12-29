package com.hpedrorodrigues.imagesearch.data.service;

import com.hpedrorodrigues.imagesearch.data.model.Image;
import com.hpedrorodrigues.imagesearch.data.remote.api.Api;
import com.hpedrorodrigues.imagesearch.data.remote.api.GenericApi;
import com.hpedrorodrigues.imagesearch.data.remote.parser.GenericParser;
import com.hpedrorodrigues.imagesearch.data.remote.services.street_view.StreetViewImageDetail;
import com.hpedrorodrigues.imagesearch.util.EnumUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

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
                .create(new Observable.OnSubscribe<List<Image>>() {

                    @Override
                    public void call(Subscriber<? super List<Image>> subscriber) {
                        try {
                            List<Api> apis = EnumUtil.valuesAsList(Api.class);

                            for (Api api : apis) {
                                subscriber.onNext(callSearchAnParse(api, search, page, perPage, safeSearch));
                            }
                        } catch (Throwable t) {

                            subscriber.onError(t);
                        } finally {

                            subscriber.onCompleted();
                        }
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
        } else {

            Timber.e("Response of api %s is not successful: %s", api, response.errorBody().string());
        }

        return Collections.emptyList();
    }
}