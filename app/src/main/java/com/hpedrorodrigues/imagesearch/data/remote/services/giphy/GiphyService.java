package com.hpedrorodrigues.imagesearch.data.remote.services.giphy;

import com.hpedrorodrigues.imagesearch.BuildConfig;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GiphyService {

    String ENDPOINT = "http://api.giphy.com/";
    String API_KEY = BuildConfig.GIPHY_API_KEY;

    @GET("/v1/gifs/search")
    Observable<Map> search(@Query("api_key") String apiKey,
                           @Query("q") String q,
                           @Query("offset") Integer offset,
                           @Query("limit") Integer limit,
                           @Query("safesearch") String safeSearch);

    @GET("/v1/gifs/search")
    Call<Map> callSearch(@Query("api_key") String apiKey,
                         @Query("q") String q,
                         @Query("offset") Integer offset,
                         @Query("limit") Integer limit,
                         @Query("safesearch") String safeSearch);
}