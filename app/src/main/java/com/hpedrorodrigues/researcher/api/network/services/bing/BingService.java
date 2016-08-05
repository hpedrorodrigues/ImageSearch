package com.hpedrorodrigues.researcher.api.network.services.bing;

import com.hpedrorodrigues.researcher.BuildConfig;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface BingService {

    String ENDPOINT = "https://api.cognitive.microsoft.com/";
    String API_KEY = BuildConfig.BING_API_KEY;

    @GET("/bing/v5.0/images/search")
    Observable<Map> search(@Query("q") String q,
                           @Query("offset") Integer offset,
                           @Query("count") Integer count,
                           @Query("safeSearch") String safeSearch
    );

    @GET("/bing/v5.0/images/search")
    Call<Map> callSearch(@Query("q") String q,
                         @Query("offset") Integer offset,
                         @Query("count") Integer count,
                         @Query("safeSearch") String safeSearch
    );
}