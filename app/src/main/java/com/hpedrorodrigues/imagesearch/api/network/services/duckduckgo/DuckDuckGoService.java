package com.hpedrorodrigues.imagesearch.api.network.services.duckduckgo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DuckDuckGoService {

    String ENDPOINT = "https://api.duckduckgo.com/";

    @GET("/i.js")
    Observable<Map> search(@Query("q") String q,
                           @Query("next") Integer next,
                           @Query("s") Integer s,
                           @Query("safesearch") Boolean safeSearch);

    @GET("/i.js")
    Call<Map> callSearch(@Query("q") String q,
                         @Query("next") Integer next,
                         @Query("s") Integer s,
                         @Query("safesearch") Boolean safeSearch);
}