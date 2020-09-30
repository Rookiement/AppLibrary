package com.base.library.network.interceptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author reber
 * on 2020/9/30 11:52
 */
public class AppNetworkInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@Nullable Chain chain) throws IOException {
        return null;
    }
}
