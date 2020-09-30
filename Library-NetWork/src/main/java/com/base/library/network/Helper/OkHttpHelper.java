package com.base.library.network.Helper;


import android.util.SparseArray;

import androidx.annotation.NonNull;

import com.base.library.network.delegate.NetWorkCallBack;
import com.base.library.network.delegate.NetWorkState;
import com.base.library.network.interceptor.AppNetworkInterceptor;
import com.base.library.network.model.NetworkException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author reber
 * on 2020/9/30 11:28
 */
public class OkHttpHelper {

    private static OkHttpHelper mOkHttpHelper;
    private final OkHttpClient.Builder mOkHttpClientBuilder;
    private OkHttpClient mOkHttpClient;
    private Request mRequest;

    private final SparseArray<Call> mCallArray = new SparseArray<>();

    private OkHttpHelper() {
        this.mOkHttpClientBuilder = new OkHttpClient.Builder();
        initOkHttpClient();
    }

    public static OkHttpHelper getInstance() {
        if (mOkHttpHelper == null) {
            mOkHttpHelper = new OkHttpHelper();
        }
        return mOkHttpHelper;
    }

    private void initOkHttpClient() {
        this.mOkHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new AppNetworkInterceptor())
                .cache(getAppNetWorkCache())
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    private Cache getAppNetWorkCache() {
        return new Cache(new File(""), 1000);
    }

    private Request getHttpRequest() {
        mRequest = new Request.Builder()
                .url("https:www.baidu.com")
                .addHeader("", "")
                .build();
        return mRequest;
    }

    /**
     * get同步请求
     */
    public <T> void executeGetSyncRequest(int requestType, @NonNull final Class<T> clazz,
                                          @NonNull final NetWorkCallBack<T> callBack) {
        try {
            Call newCall = mOkHttpClient.newCall(mRequest);
            addCallToArray(requestType, newCall);

            Response response = newCall.execute();
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    String body = responseBody.toString();
                    T t = GSonHelper.getInstance().fromJson(body, clazz);
                    if (t != null) {
                        callBack.onNetWorkCallBack(NetWorkState.SUCCESS, t, null);
                        removeCallFromArray(requestType);
                        return;
                    }
                }
                callBack.onNetWorkCallBack(NetWorkState.EMPTY, null, new NetworkException.Builder()
                        .setErrorCode(400)
                        .setErrorMessage("empty")
                        .build());
            } else {
                callBack.onNetWorkCallBack(NetWorkState.FAILURE, null, new NetworkException.Builder()
                        .setErrorCode(400)
                        .setErrorMessage("failure")
                        .build());
            }
        } catch (IOException ignored) {
            callBack.onNetWorkCallBack(NetWorkState.FAILURE, null, new NetworkException.Builder()
                    .setErrorCode(400)
                    .setErrorMessage("failure")
                    .build());
        }
        removeCallFromArray(requestType);
    }

    /**
     * get异步请求
     */
    public <T> void executeGetAsyncRequest(final int requestType, @NonNull final Class<T> clazz,
                                           @NonNull final NetWorkCallBack<T> callBack) {
        Call newCall = mOkHttpClient.newCall(mRequest);
        addCallToArray(requestType, newCall);
        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                removeCallFromArray(requestType);
                callBack.onNetWorkCallBack(NetWorkState.FAILURE, null, new NetworkException.Builder()
                        .setErrorCode(400)
                        .setErrorMessage("failure")
                        .build());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                removeCallFromArray(requestType);
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        String body = responseBody.toString();
                        T t = GSonHelper.getInstance().fromJson(body, clazz);
                        if (t != null) {
                            callBack.onNetWorkCallBack(NetWorkState.SUCCESS, t, null);
                            return;
                        }
                    }
                }
                callBack.onNetWorkCallBack(NetWorkState.EMPTY, null, new NetworkException.Builder()
                        .setErrorCode(400)
                        .setErrorMessage("empty")
                        .build());
            }
        });
    }

    /**
     * 创建新请求之前，移除上一次的请求，防止多次请求多次回掉
     */
    protected void removeCallFromArray(int requestType) {
        Call oldCall = mCallArray.get(requestType);
        if (oldCall != null) {
            oldCall.cancel();
            mCallArray.put(requestType, null);
        }
    }

    /**
     * 保存当前请求的call对象，供removeCallFromArray回调使用
     */
    protected void addCallToArray(int requestType, @NonNull Call call) {
        removeCallFromArray(requestType);
        mCallArray.put(requestType, call);
    }
}
