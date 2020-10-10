package com.base.library.network.Helper;

import androidx.annotation.NonNull;

import com.base.library.network.delegate.NetRequestType;
import com.base.library.network.interceptor.AppNetRequestInterceptor;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author reber
 * on 2020/10/9 10:06
 */
public class NetRequestUtil {

    public static OkHttpClient getNewOkHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new AppNetRequestInterceptor())
                .cache(getOkHttpCache())
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    private static Cache getOkHttpCache() {
        return new Cache(new File(""), 1000);
    }

    public static Retrofit.Builder getNewRetrofitBuilder(@NonNull String baseUrl) {
        return getSingleRetrofitBuilder(baseUrl, getNewOkHttpClient());
    }

    public static Retrofit.Builder getSingleRetrofitBuilder(@NonNull String baseUrl,
                                                            @NonNull OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);
    }

    public static Request getOkHttpRequest(@NonNull String requestUrl) {
        return getOkHttpRequest(NetRequestType.GET, requestUrl, null);
    }

    public static Request getOkHttpRequest(@NetRequestType int requestType, @NonNull String requestUrl) {
        return getOkHttpRequest(requestType, requestUrl, null);
    }

    public static Request getOkHttpRequest(@NetRequestType int requestType, @NonNull String requestUrl,
                                           Map<String, String> params) {
        return getOkHttpRequest(requestType, requestUrl, params, null);
    }

    /**
     * @param requestType 请求方式：Get，Post，Delete，Put等
     * @param requestUrl  请求的Url，如果是get请求，url是全路径但不包含参数
     * @param params      请求的参数列表
     * @param headers     请求头列表
     */
    public static Request getOkHttpRequest(@NetRequestType int requestType, @NonNull String requestUrl,
                                           Map<String, String> params, Map<String, String> headers) {
        switch (requestType) {
            default:
            case NetRequestType.GET:
                return buildGetRequest(requestUrl, params, headers);
            case NetRequestType.POST:
                return buildPostRequest(requestUrl, params, headers);
            case NetRequestType.DELETE:
                return buildDeleteRequest(requestUrl, params, headers);
            case NetRequestType.PUT:
                return buildPutRequest(requestUrl, params, headers);
        }
    }

    /**
     * OkHttp的get请求
     *
     * @param params  post请求需要的参数列表
     * @param headers request请求需要的header列表，可为null，默认在request上不设置header
     */
    public static Request buildGetRequest(@NonNull String getUrl, Map<String, String> params,
                                          Map<String, String> headers) {
        Request.Builder requestBuilder = getOkHttpRequestBuilder(headers);
        if (params != null && params.size() > 0) {
            HttpUrl httpUrl = HttpUrl.parse(getUrl);
            if (httpUrl != null) {
                HttpUrl.Builder builder = httpUrl.newBuilder();
                for (String key : params.keySet()) {
                    String value = params.get(key);
                    if (value != null) {
                        builder.addQueryParameter(key, value);
                    }
                }
                return requestBuilder.url(httpUrl).get().build();
            }
        }
        return requestBuilder.url(getUrl).build();
    }

    /**
     * OkHttp的Post请求
     */
    public static Request buildPostRequest(@NonNull String postUrl, Map<String, String> params, Map<String, String> headers) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                String value = params.get(key);
                if (value == null) value = "";
                formBuilder.add(key, value);
            }
        }
        return getOkHttpRequestBuilder(headers)
                .url(postUrl)
                .post(formBuilder.build())
                .build();
    }

    /**
     * OkHttp的Post请求
     */
    public static Request buildDeleteRequest(@NonNull String postUrl, Map<String, String> params, Map<String, String> headers) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                String value = params.get(key);
                if (value == null) value = "";
                formBuilder.add(key, value);
            }
        }
        return getOkHttpRequestBuilder(headers)
                .url(postUrl)
                .delete(formBuilder.build())
                .build();
    }

    /**
     * OkHttp的Post请求
     */
    public static Request buildPutRequest(@NonNull String postUrl, Map<String, String> params, Map<String, String> headers) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                String value = params.get(key);
                if (value == null) value = "";
                formBuilder.add(key, value);
            }
        }
        return getOkHttpRequestBuilder(headers)
                .url(postUrl)
                .put(formBuilder.build())
                .build();
    }

    /**
     * @param headers request的请求头列表
     * @return 返回带请求头的Request.Builder
     */
    private static Request.Builder getOkHttpRequestBuilder(Map<String, String> headers) {
        Request.Builder builder = getDefaultOkHttpRequestBuilder();
        if (headers != null && headers.size() > 0) {
            for (String key : headers.keySet()) {
                String value = headers.get(key);
                if (value == null) value = "";
                builder.addHeader(key, value);
            }
        }
        return builder;
    }

    /**
     * @return 返回默认的Request.Builder
     */
    private static Request.Builder getDefaultOkHttpRequestBuilder() {
        return new Request.Builder();
    }
}
