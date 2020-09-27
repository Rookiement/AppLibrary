package com.base.library.common.helper;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

/**
 * @param <T>
 * @author reber
 */
public interface AppViewDelegate<T> {

    T setText(@IdRes int viewId, String text);

    T setText(@IdRes int viewId, @StringRes int stringResId);

    T setText(@IdRes int viewId, @StringRes int stringResId, Object... formatArgs);

    T setTextColorWithRes(@IdRes int viewId, @ColorRes int colorResId);

    T setTextColorWithValue(@IdRes int viewId, @ColorInt int colorValue);

    T setTextSizeWithRes(@IdRes int viewId, @DimenRes int dimensResId);

    T setTextSizeByPx(@IdRes int viewId, float textSizValue);

    T setTextSizeBySp(@IdRes int viewId, float spValue);

    T setTextSizeByDp(@IdRes int viewId, float dpValue);

    T setBackgroundResource(@IdRes int viewId, @DrawableRes int drawableResId);

    T setBackgroundDrawable(@IdRes int viewId, Drawable drawable);

    T setBackgroundColor(@IdRes int viewId, @ColorRes int colorResId);

    T setImageResource(@IdRes int viewId, @DrawableRes int drawableResId);

    T setImageDrawable(@IdRes int viewId, Drawable drawable);

    T setImageBitmap(@IdRes int viewId, Bitmap bitmap);

    T setEnable(@IdRes int viewId, boolean enable);

    T setSelected(@IdRes int viewId, boolean selected);

    T setVisible(@IdRes int viewId, boolean visible);

    T setINVisible(@IdRes int viewId, boolean visible);

    T setOnClickListener(@IdRes int viewId, View.OnClickListener listener);

    T setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener);
}
