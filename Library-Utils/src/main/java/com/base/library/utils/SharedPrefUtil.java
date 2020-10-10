package com.base.library.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author reber
 * on 2020/10/10 09:51
 */
public class SharedPrefUtil {

    private final SharedPreferences mSharedPreferences;
    private final SharedPreferences.Editor mEditor;

    /**
     * 如果对SP操作比较频繁，可以创建该对象
     */
    @SuppressLint("CommitPrefEdits")
    public SharedPrefUtil(Context context, String name) {
        this.mSharedPreferences = getSharedPreferences(context, name);
        this.mEditor = this.mSharedPreferences.edit();
    }

    public void putValue(String key, Object value) {
        if (value instanceof String) {
            mEditor.putString(key, String.valueOf(value));
        } else if (value instanceof Boolean) {
            mEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            mEditor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            mEditor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            mEditor.putLong(key, (Long) value);
        }
        mEditor.apply();
    }

    public String getString(String key, String defaultValue) {
        return getSingleString(key, defaultValue, this.mSharedPreferences);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getSingleBoolean(key, defaultValue, this.mSharedPreferences);
    }

    public int getInteger(String key, int defaultValue) {
        return getSingleInteger(key, defaultValue, this.mSharedPreferences);
    }

    public float getFloat(String key, float defaultValue) {
        return getSingleFloat(key, defaultValue, this.mSharedPreferences);
    }

    public long getLong(String key, long defaultValue) {
        return getSingleLong(key, defaultValue, this.mSharedPreferences);
    }

    public void remove(String key) {
        removeSingle(key, this.mEditor);
    }

    public void clear() {
        clearSingle(this.mEditor);
    }

    public static String getString(Context context, String spName, String key, String defaultValue) {
        return getSingleString(key, defaultValue, getSharedPreferences(context, spName));
    }

    public static boolean getBoolean(Context context, String spName, String key, boolean defaultValue) {
        return getSingleBoolean(key, defaultValue, getSharedPreferences(context, spName));
    }

    public static int getInteger(Context context, String spName, String key, int defaultValue) {
        return getSingleInteger(key, defaultValue, getSharedPreferences(context, spName));
    }

    public static float getFloat(Context context, String spName, String key, float defaultValue) {
        return getSingleFloat(key, defaultValue, getSharedPreferences(context, spName));
    }

    public static long getLong(Context context, String spName, String key, long defaultValue) {
        return getSingleLong(key, defaultValue, getSharedPreferences(context, spName));
    }

    @SuppressLint("CommitPrefEdits")
    public static void remove(Context context, String spName, String key) {
        removeSingle(key, getSharedPreferences(context, spName).edit());
    }

    @SuppressLint("CommitPrefEdits")
    public static void clear(Context context, String spName) {
        clearSingle(getSharedPreferences(context, spName).edit());
    }

    private static String getSingleString(String key, String defaultValue, SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(key, defaultValue);
    }

    private static boolean getSingleBoolean(String key, boolean defaultValue, SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    private static int getSingleInteger(String key, int defaultValue, SharedPreferences sharedPreferences) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    private static float getSingleFloat(String key, float defaultValue, SharedPreferences sharedPreferences) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    private static long getSingleLong(String key, long defaultValue, SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    private static void removeSingle(String key, SharedPreferences.Editor editor) {
        editor.remove(key);
        editor.apply();
    }

    private static void clearSingle(SharedPreferences.Editor editor) {
        editor.clear();
        editor.apply();
    }

    private static SharedPreferences getSharedPreferences(Context context, String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
}
