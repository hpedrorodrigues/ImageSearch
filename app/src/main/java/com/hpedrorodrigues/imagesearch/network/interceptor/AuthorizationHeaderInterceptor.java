package com.hpedrorodrigues.imagesearch.network.interceptor;

import com.hpedrorodrigues.imagesearch.network.api.imgur.ImgurApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationHeaderInterceptor implements Interceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder();

        if (original.url().toString().startsWith(ImgurApi.ENDPOINT)) {
            builder.addHeader(AUTHORIZATION_HEADER, "Client-ID " + ImgurApi.API_CLIENT_ID);
        }

        Request request = builder.method(original.method(), original.body()).build();

        return chain.proceed(request);
    }
}