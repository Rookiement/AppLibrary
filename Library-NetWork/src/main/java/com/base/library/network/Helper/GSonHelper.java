package com.base.library.network.Helper;

import com.google.gson.Gson;

/**
 * @author reber
 * on 2020/9/30 14:28
 */
public class GSonHelper {

    private static GSonHelper mInstance;

    private final Gson mGSon;

    private GSonHelper() {
        this.mGSon = new Gson();
    }

    public static GSonHelper getInstance() {
        if (mInstance == null) {
            mInstance = new GSonHelper();
        }
        return mInstance;
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return this.mGSon.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> String toJson(T src) {
        return this.mGSon.toJson(src);
    }
}
