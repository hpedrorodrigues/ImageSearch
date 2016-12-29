package com.hpedrorodrigues.imagesearch.data.remote.services.pixabay;

import com.hpedrorodrigues.imagesearch.BuildConfig;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface PixabayService {

    String ENDPOINT = "https://pixabay.com/";
    String API_KEY = BuildConfig.PIXABAY_API_KEY;

    @GET("/api/")
    Observable<Map> search(@Query("key") String key,
                           @Query("image_type") String imageType,
                           @Query("q") String q,
                           @Query("page") Integer page,
                           @Query("per_page") Integer perPage,
                           @Query("safesearch") Boolean safeSearch);

    @GET("/api/")
    Call<Map> callSearch(@Query("key") String key,
                         @Query("image_type") String imageType,
                         @Query("q") String q,
                         @Query("page") Integer page,
                         @Query("per_page") Integer perPage,
                         @Query("safesearch") Boolean safeSearch);
}