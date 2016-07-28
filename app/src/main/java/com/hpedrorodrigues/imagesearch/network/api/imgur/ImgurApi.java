package com.hpedrorodrigues.imagesearch.network.api.imgur;

import com.hpedrorodrigues.imagesearch.BuildConfig;
import com.hpedrorodrigues.imagesearch.network.dto.imgur.ImgurPageWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ImgurApi {

    String ENDPOINT = "https://api.imgur.com/";
    String API_CLIENT_ID = BuildConfig.IMGUR_API_CLIENT_ID;

    @GET("/3/gallery/search")
    Observable<ImgurPageWrapper> search(@Query("q_type") String qType,
                                        @Query("q") String q,
                                        @Query("page") Integer page,
                                        @Query("per_page") Integer perPage);
}