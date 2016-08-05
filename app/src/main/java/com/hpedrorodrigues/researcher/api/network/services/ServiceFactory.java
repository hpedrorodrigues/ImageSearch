package com.hpedrorodrigues.researcher.api.network.services;

import com.hpedrorodrigues.researcher.api.network.interceptor.AuthorizationHeaderInterceptor;
import com.hpedrorodrigues.researcher.api.network.services.bing.BingService;
import com.hpedrorodrigues.researcher.api.network.services.cse.CSEService;
import com.hpedrorodrigues.researcher.api.network.services.duckduckgo.DuckDuckGoService;
import com.hpedrorodrigues.researcher.api.network.services.flickr.FlickrService;
import com.hpedrorodrigues.researcher.api.network.services.giphy.GiphyService;
import com.hpedrorodrigues.researcher.api.network.services.imgur.ImgurService;
import com.hpedrorodrigues.researcher.api.network.services.pixabay.PixabayService;

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

    private <T> T getService(String endpoint, Class<T> clazz) {
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

    public FlickrService getFlickrService() {
        return getService(FlickrService.ENDPOINT, FlickrService.class);
    }

    public CSEService getCseService() {
        return getService(CSEService.ENDPOINT, CSEService.class);
    }

    public ImgurService getImgurService() {
        return getService(ImgurService.ENDPOINT, ImgurService.class);
    }

    public DuckDuckGoService getDuckDuckGoService() {
        return getService(DuckDuckGoService.ENDPOINT, DuckDuckGoService.class);
    }

    public BingService getBingService() {
        return getService(BingService.ENDPOINT, BingService.class);
    }

    public PixabayService getPixabayService() {
        return getService(PixabayService.ENDPOINT, PixabayService.class);
    }

    public GiphyService getGiphyService() {
        return getService(GiphyService.ENDPOINT, GiphyService.class);
    }
}