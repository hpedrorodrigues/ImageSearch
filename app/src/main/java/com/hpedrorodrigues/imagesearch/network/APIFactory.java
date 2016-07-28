package com.hpedrorodrigues.imagesearch.network;

import com.hpedrorodrigues.imagesearch.network.api.cse.CSEApi;
import com.hpedrorodrigues.imagesearch.network.api.duckduckgo.DuckDuckGoApi;
import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrApi;
import com.hpedrorodrigues.imagesearch.network.api.imgur.ImgurApi;
import com.hpedrorodrigues.imagesearch.network.interceptor.AuthorizationHeaderInterceptor;

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
}