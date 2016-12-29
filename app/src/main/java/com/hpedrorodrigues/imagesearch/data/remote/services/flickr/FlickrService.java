package com.hpedrorodrigues.imagesearch.data.remote.services.flickr;

import com.hpedrorodrigues.imagesearch.BuildConfig;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FlickrService {

    String ENDPOINT = "https://api.flickr.com/";
    String API_KEY = BuildConfig.FLICKR_API_KEY;

    @GET("/services/rest/")
    Observable<Map> search(@Query("method") String method,
                           @Query("api_key") String apiKey,
                           @Query("format") String format,
                           @Query("nojsoncallback") Integer noJsonCallback,
                           @Query("text") String text,
                           @Query("page") Integer page,
                           @Query("per_page") Integer perPage,
                           @Query("safe_search") Integer safeSearch
    );

    @GET("/services/rest/")
    Call<Map> callSearch(@Query("method") String method,
                         @Query("api_key") String apiKey,
                         @Query("format") String format,
                         @Query("nojsoncallback") Integer noJsonCallback,
                         @Query("text") String text,
                         @Query("page") Integer page,
                         @Query("per_page") Integer perPage,
                         @Query("safe_search") Integer safeSearch
    );
}