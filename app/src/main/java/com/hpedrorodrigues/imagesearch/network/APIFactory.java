package com.hpedrorodrigues.imagesearch.network;

import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrApi;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIFactory {

    @Inject
    public APIFactory() {
    }

    private <T> T getApi(String endpoint, Class<T> clazz) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }

    public FlickrApi getFlickrApi() {
        return getApi(FlickrApi.ENDPOINT, FlickrApi.class);
    }
}