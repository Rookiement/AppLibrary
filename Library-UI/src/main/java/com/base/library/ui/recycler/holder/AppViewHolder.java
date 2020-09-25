package com.base.library.ui.recycler.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;

import com.base.library.ui.delegate.AppViewDelegate;
import com.base.library.ui.helper.AppViewHelper;

/**
 * @param <T>
 * @author reber
 */
public class AppViewHolder<T> extends RecyclerView.ViewHolder implements LifecycleObserver,
        AppViewDelegate<AppViewHolder<T>> {

    private AppViewHelper mViewHelper;

    public AppViewHolder(@NonNull View itemView) {
        this(itemView, null);
    }

    public AppViewHolder(@NonNull View itemView, LifecycleRegistry lifecycleRegistry) {
        super(itemView);
        this.mViewHelper = new AppViewHelper(itemView);
        if (lifecycleRegistry != null) {
            lifecycleRegistry.addObserver(this);
        }
    }

    public Context getContext() {
        return mViewHelper.getContext();
    }

    @Override
    public AppViewHolder<T> setText(@IdRes int viewId, String text) {
        mViewHelper.setText(viewId, text);
        return this;
    }

    @Override
    public AppViewHolder<T> setText(@IdRes int viewId, @StringRes int stringResId) {
        mViewHelper.setText(viewId, stringResId);
        return this;
    }

    @Override
    public AppViewHolder<T> setText(@IdRes int viewId, @StringRes int stringResId, Object... formatArgs) {
        mViewHelper.setText(viewId, stringResId, formatArgs);
        return this;
    }

    @Override
    public AppViewHolder<T> setTextColorWithRes(@IdRes int viewId, @ColorRes int colorResId) {
        mViewHelper.setTextColorWithRes(viewId, colorResId);
        return this;
    }

    @Override
    public AppViewHolder<T> setTextColorWithValue(@IdRes int viewId, @ColorInt int colorValue) {
        mViewHelper.setTextColorWithValue(viewId, colorValue);
        return this;
    }

    @Override
    public AppViewHolder<T> setTextSizeWithRes(@IdRes int viewId, @DimenRes int dimensResId) {
        mViewHelper.setTextSizeWithRes(viewId, dimensResId);
        return this;
    }

    @Override
    public AppViewHolder<T> setTextSizeWithValue(@IdRes int viewId, float textSizValue) {
        mViewHelper.setTextSizeWithValue(viewId, textSizValue);
        return this;
    }

    @Override
    public AppViewHolder<T> setTextSizeBySp(@IdRes int viewId, float spValue) {
        mViewHelper.setTextSizeBySp(viewId, spValue);
        return this;
    }

    @Override
    public AppViewHolder<T> setTextSizeByDp(@IdRes int viewId, float dpValue) {
        mViewHelper.setTextSizeByDp(viewId, dpValue);
        return this;
    }

    @Override
    public AppViewHolder<T> setBackgroundResource(@IdRes int viewId, @DrawableRes int drawableResId) {
        mViewHelper.setBackgroundResource(viewId, drawableResId);
        return this;
    }

    @Override
    public AppViewHolder<T> setBackgroundDrawable(@IdRes int viewId, Drawable drawable) {
        mViewHelper.setBackgroundDrawable(viewId, drawable);
        return this;
    }

    @Override
    public AppViewHolder<T> setBackgroundColor(@IdRes int viewId, @ColorRes int colorResId) {
        mViewHelper.setBackgroundColor(viewId, colorResId);
        return this;
    }

    @Override
    public AppViewHolder<T> setImageResource(@IdRes int viewId, @DrawableRes int drawableResId) {
        mViewHelper.setImageResource(viewId, drawableResId);
        return this;
    }

    @Override
    public AppViewHolder<T> setImageDrawable(@IdRes int viewId, Drawable drawable) {
        mViewHelper.setImageDrawable(viewId, drawable);
        return this;
    }

    @Override
    public AppViewHolder<T> setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        mViewHelper.setImageBitmap(viewId, bitmap);
        return this;
    }

    @Override
    public AppViewHolder<T> setEnable(int viewId, boolean enable) {
        mViewHelper.setEnable(viewId, enable);
        return this;
    }

    @Override
    public AppViewHolder<T> setSelected(int viewId, boolean selected) {
        mViewHelper.setSelected(viewId, selected);
        return this;
    }

    @Override
    public AppViewHolder<T> setVisible(int viewId, boolean visible) {
        mViewHelper.setVisible(viewId, visible);
        return this;
    }

    @Override
    public AppViewHolder<T> setINVisible(int viewId, boolean visible) {
        mViewHelper.setINVisible(viewId, visible);
        return this;
    }

    @Override
    public AppViewHolder<T> setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        mViewHelper.setOnClickListener(viewId, listener);
        return this;
    }

    @Override
    public AppViewHolder<T> setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener) {
        mViewHelper.setOnLongClickListener(viewId, listener);
        return this;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        this.mViewHelper.onDestroy();
        this.mViewHelper = null;
    }
}