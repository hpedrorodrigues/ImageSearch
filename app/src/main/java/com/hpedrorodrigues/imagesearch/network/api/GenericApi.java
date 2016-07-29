package com.hpedrorodrigues.imagesearch.network.api;

import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;
import com.hpedrorodrigues.imagesearch.network.services.street_view.StreetViewApi;
import com.hpedrorodrigues.imagesearch.network.services.street_view.StreetViewImageDetail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

public class GenericApi {

    private static final List<BaseApi> APIS = Arrays.asList(
            new FlickrApi(),
            new CSEApi(),
            new ImgurApi(),
            new DuckDuckGoApi(),
            new BingApi()
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
}