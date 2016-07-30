package com.hpedrorodrigues.imagesearch.api.network.services.cse;

import com.hpedrorodrigues.imagesearch.BuildConfig;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CSEService {

    String ENDPOINT = "https://www.googleapis.com/";
    String API_KEY = BuildConfig.CSE_API_KEY;

    String CX = "008358958714783432655:jgkoxl4lfti";

    @GET("/customsearch/v1element")
    Observable<Map> search(@Query("key") String key,
                           @Query("searchtype") String searchType,
                           @Query("cx") String cx,
                           @Query("q") String q,
                           @Query("start") Integer start,
                           @Query("num") Integer num,
                           @Query("safe") String safeSearch
    );

    @GET("/customsearch/v1element")
    Call<Map> callSearch(@Query("key") String key,
                         @Query("searchtype") String searchType,
                         @Query("cx") String cx,
                         @Query("q") String q,
                         @Query("start") Integer start,
                         @Query("num") Integer num,
                         @Query("safe") String safeSearch
    );
}