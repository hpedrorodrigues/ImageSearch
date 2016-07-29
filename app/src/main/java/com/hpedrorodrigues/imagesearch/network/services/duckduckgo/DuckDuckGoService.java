package com.hpedrorodrigues.imagesearch.network.services.duckduckgo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DuckDuckGoService {

    String ENDPOINT = "https://api.duckduckgo.com/";

    @GET("/i.js")
    Observable<Map> search(@Query("next") Integer next,
                           @Query("s") Integer s,
                           @Query("q") String q);

    @GET("/i.js")
    Call<Map> callSearch(@Query("next") Integer next,
                         @Query("s") Integer s,
                         @Query("q") String q);
}