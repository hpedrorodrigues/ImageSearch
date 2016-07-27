package com.hpedrorodrigues.imagesearch.network.api.cse;

import com.hpedrorodrigues.imagesearch.network.dto.cse.CSEPageWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CSEApi {

    String ENDPOINT = "https://www.googleapis.com/";
    String API_KEY = "AIzaSyCVAXiUzRYsML1Pv6RwSG1gunmMikTzQqY";

    @GET("/customsearch/v1element")
    Observable<CSEPageWrapper> retrieve(@Query("method") String method,
                                        @Query("api_key") String apiKey,
                                        @Query("format") String format,
                                        @Query("per_page") Integer perPage,
                                        @Query("page") Integer page,
                                        @Query("nojsoncallback") Integer noJsonCallback);
}