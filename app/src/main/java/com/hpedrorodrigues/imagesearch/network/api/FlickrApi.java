package com.hpedrorodrigues.imagesearch.network.api;

import com.hpedrorodrigues.imagesearch.network.dto.FlickrPageWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FlickrApi {

    String ENDPOINT = "https://api.flickr.com/";
    String API_KEY = "83b6754bd787a2b0e97fd296dcda6c91";

    @GET("/services/rest/")
    Observable<FlickrPageWrapper> retrieve(@Query("method") String method,
                                           @Query("api_key") String apiKey,
                                           @Query("format") String format,
                                           @Query("per_page") Integer perPage,
                                           @Query("page") Integer page,
                                           @Query("nojsoncallback") Integer noJsonCallback);

    @GET("/services/rest/")
    Observable<FlickrPageWrapper> search(@Query("method") String method,
                                         @Query("api_key") String apiKey,
                                         @Query("format") String format,
                                         @Query("text") String text,
                                         @Query("per_page") Integer perPage,
                                         @Query("page") Integer page,
                                         @Query("nojsoncallback") Integer noJsonCallback);
}