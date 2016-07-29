package com.hpedrorodrigues.imagesearch.network.api.flickr;

import com.hpedrorodrigues.imagesearch.BuildConfig;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FlickrApi {

    String ENDPOINT = "https://api.flickr.com/";
    String API_KEY = BuildConfig.FLICKR_API_KEY;

    @GET("/services/rest/")
    Observable<Map> search(@Query("method") String method,
                           @Query("api_key") String apiKey,
                           @Query("format") String format,
                           @Query("text") String text,
                           @Query("per_page") Integer perPage,
                           @Query("page") Integer page,
                           @Query("nojsoncallback") Integer noJsonCallback);
}