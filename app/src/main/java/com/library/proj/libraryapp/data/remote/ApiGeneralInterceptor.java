package com.library.proj.libraryapp.data.remote;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class ApiGeneralInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .build();
        return chain.proceed(request);
    }
}
