package com.base.library.ui.recycler.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class AppViewHolder<T> extends RecyclerView.ViewHolder {

    private final SparseArray<View> mViews;

    public AppViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
    }

    public AppViewHolder<T> setText(@IdRes int viewId, String text) {
        TextView textView = findViewById(viewId);
        textView.setText(text);
        return this;
    }

    public AppViewHolder<T> setText(@IdRes int viewId, @StringRes int stringResId) {
        TextView textView = findViewById(viewId);
        textView.setText(getContext().getString(stringResId));
        return this;
    }

    public AppViewHolder<T> setText(@IdRes int viewId, @StringRes int stringResId, Object... formatArgs) {
        TextView textView = findViewById(viewId);
        textView.setText(getContext().getString(stringResId, formatArgs));
        return this;
    }

    public AppViewHolder<T> setTextColorWithRes(@IdRes int viewId, @ColorRes int colorResId) {
        TextView textView = findViewById(viewId);
        textView.setTextColor(ContextCompat.getColor(getContext(), colorResId));
        return this;
    }

    public AppViewHolder<T> setTextColorWithValue(@IdRes int viewId, @ColorInt int colorValue) {
        TextView textView = findViewById(viewId);
        textView.setTextColor(colorValue);
        return this;
    }

    public AppViewHolder<T> setTextSizeWithRes(@IdRes int viewId, @DimenRes int dimensResId) {
        TextView textView = findViewById(viewId);
        textView.setTextSize(getContext().getResources().getDimension(dimensResId));
        return this;
    }

    public AppViewHolder<T> setTextSizeWithValue(@IdRes int viewId, float textSizValue) {
        TextView textView = findViewById(viewId);
        textView.setTextSize(textSizValue);
        return this;
    }

    public AppViewHolder<T> setTextSizeBySp(@IdRes int viewId, float spValue) {
        TextView textView = findViewById(viewId);
        textView.setTextSize(spToPx(spValue));
        return this;
    }

    public AppViewHolder<T> setTextSizeByDp(@IdRes int viewId, float dpValue) {
        TextView textView = findViewById(viewId);
        textView.setTextSize(dpToPx(dpValue));
        return this;
    }

    public AppViewHolder<T> setBackgroundResource(@IdRes int viewId, @DrawableRes int drawableResId) {
        View view = findViewById(viewId);
        view.setBackground(ContextCompat.getDrawable(getContext(), drawableResId));
        return this;
    }

    public AppViewHolder<T> setBackgroundDrawable(@IdRes int viewId, Drawable drawable) {
        View view = findViewById(viewId);
        view.setBackground(drawable);
        return this;
    }

    public AppViewHolder<T> setBackgroundColor(@IdRes int viewId, @ColorRes int colorResId) {
        View view = findViewById(viewId);
        view.setBackgroundColor(ContextCompat.getColor(getContext(), colorResId));
        return this;
    }

    public AppViewHolder<T> setImageResource(@IdRes int viewId, @DrawableRes int drawableResId) {
        ImageView view = findViewById(viewId);
        view.setImageResource(drawableResId);
        return this;
    }

    public AppViewHolder<T> setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = findViewById(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public AppViewHolder<T> setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = findViewById(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public void setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        findViewById(viewId).setOnClickListener(listener);
    }

    public void setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener) {
        findViewById(viewId).setOnLongClickListener(listener);
    }

    public final <V extends View> V findViewById(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    private float dpToPx(float dipValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private float spToPx(float sp) {
        Resources resources = getContext().getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.getDisplayMetrics());
    }

    public Context getContext() {
        return itemView.getContext();
    }
}