package com.hpedrorodrigues.imagesearch.api.network.interceptor;

import com.hpedrorodrigues.imagesearch.api.network.services.bing.BingService;
import com.hpedrorodrigues.imagesearch.api.network.services.imgur.ImgurService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder();

        if (original.url().toString().startsWith(ImgurService.ENDPOINT)) {

            builder.addHeader("Authorization", "Client-ID " + ImgurService.API_CLIENT_ID);
        } else if (original.url().toString().startsWith(BingService.ENDPOINT)) {

            builder.addHeader("Ocp-Apim-Subscription-Key", BingService.API_KEY);
        }

        Request request = builder.method(original.method(), original.body()).build();

        return chain.proceed(request);
    }
}