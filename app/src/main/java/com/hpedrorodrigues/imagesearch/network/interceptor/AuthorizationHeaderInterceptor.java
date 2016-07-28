package com.hpedrorodrigues.imagesearch.network.interceptor;

import com.hpedrorodrigues.imagesearch.network.api.bing.BingApi;
import com.hpedrorodrigues.imagesearch.network.api.imgur.ImgurApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder();

        if (original.url().toString().startsWith(ImgurApi.ENDPOINT)) {

            builder.addHeader("Authorization", "Client-ID " + ImgurApi.API_CLIENT_ID);
        } else if (original.url().toString().startsWith(BingApi.ENDPOINT)) {

            builder.addHeader("Ocp-Apim-Subscription-Key", BingApi.API_KEY);
        }

        Request request = builder.method(original.method(), original.body()).build();

        return chain.proceed(request);
    }
}