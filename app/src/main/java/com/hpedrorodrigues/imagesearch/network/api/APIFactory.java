package com.hpedrorodrigues.imagesearch.network.api;

import com.hpedrorodrigues.imagesearch.network.interceptor.AuthorizationHeaderInterceptor;
import com.hpedrorodrigues.imagesearch.network.services.bing.BingApi;
import com.hpedrorodrigues.imagesearch.network.services.cse.CSEApi;
import com.hpedrorodrigues.imagesearch.network.services.duckduckgo.DuckDuckGoApi;
import com.hpedrorodrigues.imagesearch.network.services.flickr.FlickrApi;
import com.hpedrorodrigues.imagesearch.network.services.imgur.ImgurApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIFactory {

    @Inject
    public APIFactory() {
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(new AuthorizationHeaderInterceptor())
                .build();
    }

    private <T> T getApi(String endpoint, Class<T> clazz) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();

        return retrofit.create(clazz);
    }

    public FlickrApi getFlickrApi() {
        return getApi(FlickrApi.ENDPOINT, FlickrApi.class);
    }

    public CSEApi getCseApi() {
        return getApi(CSEApi.ENDPOINT, CSEApi.class);
    }

    public ImgurApi getImgurApi() {
        return getApi(ImgurApi.ENDPOINT, ImgurApi.class);
    }

    public DuckDuckGoApi getDuckDuckGoApi() {
        return getApi(DuckDuckGoApi.ENDPOINT, DuckDuckGoApi.class);
    }

    public BingApi getBingApi() {
        return getApi(BingApi.ENDPOINT, BingApi.class);
    }
}