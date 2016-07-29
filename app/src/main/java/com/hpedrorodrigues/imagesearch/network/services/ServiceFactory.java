package com.hpedrorodrigues.imagesearch.network.services;

import com.hpedrorodrigues.imagesearch.network.interceptor.AuthorizationHeaderInterceptor;
import com.hpedrorodrigues.imagesearch.network.services.bing.BingService;
import com.hpedrorodrigues.imagesearch.network.services.cse.CSEService;
import com.hpedrorodrigues.imagesearch.network.services.duckduckgo.DuckDuckGoService;
import com.hpedrorodrigues.imagesearch.network.services.flickr.FlickrService;
import com.hpedrorodrigues.imagesearch.network.services.imgur.ImgurService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    private Map<String, Object> cache;

    @Inject
    public ServiceFactory() {
        this.cache = new HashMap<>();
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
        if (cache.containsKey(endpoint)) {
            return (T) cache.get(endpoint);
        }

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();

        T service = retrofit.create(clazz);

        cache.put(endpoint, service);

        return service;
    }

    public FlickrService getFlickrApi() {
        return getApi(FlickrService.ENDPOINT, FlickrService.class);
    }

    public CSEService getCseApi() {
        return getApi(CSEService.ENDPOINT, CSEService.class);
    }

    public ImgurService getImgurApi() {
        return getApi(ImgurService.ENDPOINT, ImgurService.class);
    }

    public DuckDuckGoService getDuckDuckGoApi() {
        return getApi(DuckDuckGoService.ENDPOINT, DuckDuckGoService.class);
    }

    public BingService getBingApi() {
        return getApi(BingService.ENDPOINT, BingService.class);
    }
}