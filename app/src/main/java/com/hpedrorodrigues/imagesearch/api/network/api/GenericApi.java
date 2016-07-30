package com.hpedrorodrigues.imagesearch.api.network.api;

import com.hpedrorodrigues.imagesearch.api.network.services.street_view.StreetViewImageDetail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import rx.Observable;

public class GenericApi {

    private static final List<BaseApi> APIS = Arrays.asList(
            new FlickrApi(),
            new CSEApi(),
            new ImgurApi(),
            new DuckDuckGoApi(),
            new BingApi(),
            new PixabayApi(),
            new GiphyApi()
    );

    @Inject
    public StreetViewApi streetViewApi;

    @Inject
    public GenericApi(ISComponent component) {
        for (BaseApi api : APIS) {
            component.inject(api);
        }
    }

    public String getImageUrl(StreetViewImageDetail imageDetail) {
        return streetViewApi.getImageUrl(imageDetail);
    }

    public Observable<Map> search(final Api api, final String text, final Integer page,
                                  final Integer perPage, final Boolean safeSearch) {

        for (BaseApi baseApi : APIS) {

            if (baseApi.getApi().equals(api)) {

                return baseApi.search(text, page, perPage, safeSearch);
            }
        }

        return null;
    }

    public Call<Map> callSearch(final Api api, final String text, final Integer page,
                                final Integer perPage, final Boolean safeSearch) {

        for (BaseApi baseApi : APIS) {

            if (baseApi.getApi().equals(api)) {

                return baseApi.callSearch(text, page, perPage, safeSearch);
            }
        }

        return null;
    }
}