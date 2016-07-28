package com.hpedrorodrigues.imagesearch.network.api.bing;

import com.hpedrorodrigues.imagesearch.BuildConfig;
import com.hpedrorodrigues.imagesearch.network.dto.bing.BingPageWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface BingApi {

    String ENDPOINT = "https://api.cognitive.microsoft.com/";
    String API_KEY = BuildConfig.BING_API_KEY;

    @GET("/bing/v5.0/images/search")
    Observable<BingPageWrapper> search(@Query("q") String q,
                                       @Query("count") Integer count,
                                       @Query("offset") Integer offset,
                                       @Query("safeSearch") String safeSearch);
}