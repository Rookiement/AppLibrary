package com.base.library.network.Helper;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author reber
 * on 2020/9/30 17:02
 */
public class RetrofitHelper {

    private static RetrofitHelper mInstance;

    private Retrofit mRetrofit;
    private final Retrofit.Builder mRetrofitBuilder;

    private RetrofitHelper() {
        this.mRetrofitBuilder = getRetrofitBuilder();
        this.mRetrofit = getRetrofit(mRetrofitBuilder);
    }

    public static RetrofitHelper getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitHelper();
        }
        return mInstance;
    }

    private Retrofit.Builder getRetrofitBuilder() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpHelper.getInstance().getOkHttpClient());
    }

    /**
     * 当接口环境切换的时候，需要更新Retrofit的baseUrl
     */
    public void initRetrofitBaseUrl(@NonNull String url) {
        mRetrofitBuilder.baseUrl(url);
    }

    private Retrofit getRetrofit(@NonNull Retrofit.Builder builder) {
        return builder.build();
    }

    private <T> void executeGetAsyncRequest(Class<T> serviceApiClazz) {
        T serviceApi = mRetrofit.create(serviceApiClazz);
    }
}
