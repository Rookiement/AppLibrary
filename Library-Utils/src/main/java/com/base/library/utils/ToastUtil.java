package com.base.library.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.StringRes;

/**
 * @author reber
 * on 2020/10/10 09:50
 */
public class ToastUtil {

    private static Toast mToast;

    private ToastUtil() {
        throw new Error(getClass().getSimpleName() + " do not need instantiate!");
    }

    public static Toast getToast(Context context, String message) {
        return getToast(context, message, Toast.LENGTH_SHORT);
    }

    public static Toast getToast(Context context, String message, int duration) {
        return Toast.makeText(context, message, duration);
    }

    public static Toast getToast(Context context, @StringRes int stringResId) {
        return getToast(context, stringResId, Toast.LENGTH_SHORT);
    }

    public static Toast getToast(Context context, @StringRes int stringResId, int duration) {
        return Toast.makeText(context, stringResId, duration);
    }

    public static void showSingle(Context context, String message) {
        showWithDuration(context, message, Toast.LENGTH_SHORT);
    }

    public static void showSingle(Context context, @StringRes int stringResId) {
        showWithDuration(context, stringResId, Toast.LENGTH_SHORT);
    }

    public static void showCenter(Context context, String message) {
        showWithGravity(context, message, Gravity.CENTER);
    }

    public static void showCenter(Context context, @StringRes int stringResId) {
        showWithGravity(context, stringResId, Gravity.CENTER);
    }

    public static void showWithGravity(Context context, String message, int gravity) {
        if (mToast == null) {
            mToast = getToast(context, message);
        } else {
            mToast.setText(message);
        }
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }

    public static void showWithGravity(Context context, @StringRes int stringResId, int gravity) {
        if (mToast == null) {
            mToast = getToast(context, stringResId);
        } else {
            mToast.setText(stringResId);
        }
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }

    public static void showWithDuration(Context context, String message, int duration) {
        if (mToast == null) {
            mToast = getToast(context, message, duration);
        } else {
            mToast.setText(message);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void showWithDuration(Context context, @StringRes int stringResId, int duration) {
        if (mToast == null) {
            mToast = getToast(context, stringResId, duration);
        } else {
            mToast.setText(stringResId);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void showWithAll(Context context, String message, int gravity, int duration) {
        if (mToast == null) {
            mToast = getToast(context, message, duration);
        } else {
            mToast.setText(message);
            mToast.setDuration(duration);
        }
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }

    public static void showWithAll(Context context, @StringRes int stringResId, int gravity, int duration) {
        if (mToast == null) {
            mToast = getToast(context, stringResId, duration);
        } else {
            mToast.setText(stringResId);
            mToast.setDuration(duration);
        }
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
