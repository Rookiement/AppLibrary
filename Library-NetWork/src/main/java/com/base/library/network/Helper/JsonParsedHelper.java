package com.base.library.network.Helper;

import com.google.gson.Gson;

/**
 * @author reber
 * on 2020/9/30 14:28
 */
public class JsonParsedHelper {

    private static JsonParsedHelper mJsonParsedHelper;

    private final Gson mGson;

    private JsonParsedHelper() {
        this.mGson = new Gson();
    }

    public static JsonParsedHelper getInstance() {
        if (mJsonParsedHelper == null) {
            mJsonParsedHelper = new JsonParsedHelper();
        }
        return mJsonParsedHelper;
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return this.mGson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> String toJson(T src) {
        return this.mGson.toJson(src);
    }
}
