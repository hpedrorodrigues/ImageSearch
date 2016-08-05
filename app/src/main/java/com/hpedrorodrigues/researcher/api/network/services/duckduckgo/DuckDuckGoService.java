package com.hpedrorodrigues.researcher.api.network.services.duckduckgo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DuckDuckGoService {

    String ENDPOINT = "https://api.duckduckgo.com/";

    int DEFAULT_IMAGES_COUNT = 35;

    @GET("/i.js")
    Observable<Map> search(@Query("q") String q,
                           @Query("s") Integer s,
                           @Query("next") Integer next,
                           @Query("kp") Integer safeSearch);

    @GET("/i.js")
    Call<Map> callSearch(@Query("q") String q,
                         @Query("s") Integer s,
                         @Query("next") Integer next,
                         @Query("kp") Integer safeSearch);
}