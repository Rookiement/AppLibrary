package com.base.library.network.Helper;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.base.library.network.delegate.NetRequestCallBack;
import com.base.library.network.delegate.NetRequestState;
import com.base.library.network.delegate.NetRequestType;
import com.base.library.network.model.AppException;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * @author reber
 * on 2020/10/9 09:56
 */
public class NetRequestHelper {

    private String mBaseUrl = "";
    private static NetRequestHelper mInstance;

    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private HashMap<String, Call> mCallArray;
    private Gson mGSon;
    private Handler mHandler;

    private NetRequestHelper() {
    }

    private NetRequestHelper(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    public static void init(String baseUrl) {
        if (mInstance == null) {
            mInstance = new NetRequestHelper(baseUrl);
        }
    }

    public static NetRequestHelper getInstance() {
        if (mInstance == null) {
            throw new Error(" please init base url before getInstance()!");
        }
        return mInstance;
    }

    public void setBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    private HashMap<String, Call> getOkHttpCallArray() {
        if (this.mCallArray == null) {
            this.mCallArray = new HashMap<>();
        }
        return this.mCallArray;
    }

    public Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    public Gson getGSon() {
        if (mGSon == null) {
            mGSon = new Gson();
        }
        return mGSon;
    }

    public OkHttpClient getOkHttpClient() {
        if (this.mOkHttpClient == null) {
            this.mOkHttpClient = NetRequestUtil.getNewOkHttpClient();
        }
        return this.mOkHttpClient;
    }

    public Retrofit getRetrofit() {
        if (this.mRetrofit == null) {
            this.mRetrofit = NetRequestUtil.getSingleRetrofitBuilder(mBaseUrl, getOkHttpClient()).build();
        }
        return this.mRetrofit;
    }

    /**
     * OkHttp的get同步请求
     */
    public <T> void executeSyncFromOkHttp(@NetRequestType int requestType, String requestUrl,
                                          @NonNull final Class<T> clazz,
                                          @NonNull final NetRequestCallBack<T> callBack) {
        try {
            Call newCall = getOkHttpClient().newCall(NetRequestUtil.getOkHttpRequest(requestType,
                    String.format("%s%s", mBaseUrl, requestUrl)));
            addCallToArray(requestUrl, newCall);

            Response response = newCall.execute();
            handleResponseAfterRequest(response, requestUrl, clazz, callBack);
        } catch (IOException ignored) {
            handleResponseAfterRequest(null, requestUrl, clazz, callBack);
        } finally {
            removeCallFromArray(requestUrl);
        }
    }

    /**
     * OkHttp的get异步请求
     */
    public <T> void executeAsyncFromOkHttp(@NetRequestType int requestType, final String requestUrl,
                                           @NonNull final Class<T> clazz,
                                           @NonNull final NetRequestCallBack<T> callBack) {

        Call newCall = getOkHttpClient().newCall(NetRequestUtil.getOkHttpRequest(requestType,
                String.format("%s%s", mBaseUrl, requestUrl)));
        addCallToArray(requestUrl, newCall);

        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        handleResponseAfterRequest(null, requestUrl, clazz, callBack);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, final @NonNull Response response) throws IOException {
                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        handleResponseAfterRequest(response, requestUrl, clazz, callBack);
                    }
                });
            }
        });
    }

    private <T> void handleResponseAfterRequest(Response response, String requestUrl,
                                                @NonNull final Class<T> clazz,
                                                @NonNull final NetRequestCallBack<T> callBack) {
        if (response != null && response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String body = responseBody.toString();
                T t = fromJson(body, clazz);
                if (t != null) {
                    callBack.onNetWorkCallBack(NetRequestState.SUCCESS, t, null);
                    removeCallFromArray(requestUrl);
                    return;
                }
            }
            callBack.onNetWorkCallBack(NetRequestState.EMPTY, null, new AppException.Builder()
                    .setErrorCode(NetRequestState.EMPTY)
                    .build());
        } else {
            callBack.onNetWorkCallBack(NetRequestState.FAILURE, null, new AppException.Builder()
                    .setErrorCode(NetRequestState.FAILURE)
                    .build());
        }
    }

    /**
     * 创建新请求之前，移除上一次的请求，防止多次请求多次回掉
     */
    private void removeCallFromArray(String requestUrl) {
        Call oldCall = getOkHttpCallArray().get(requestUrl);
        if (oldCall != null) {
            if (oldCall.isExecuted() && !oldCall.isCanceled()) {
                oldCall.cancel();
            }
            getOkHttpCallArray().put(requestUrl, null);
        }
    }

    /**
     * 保存当前请求的call对象，供removeCallFromArray回调使用
     */
    private void addCallToArray(String requestUrl, @NonNull Call call) {
        removeCallFromArray(requestUrl);
        getOkHttpCallArray().put(requestUrl, call);
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return getGSon().fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> String toJson(T src) {
        return getGSon().toJson(src);
    }
}
