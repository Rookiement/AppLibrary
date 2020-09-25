package com.base.library.ui.helper;

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

import com.base.library.ui.delegate.AppViewDelegate;

public class AppViewHelper implements AppViewDelegate<AppViewHelper> {

    private View mContentView;

    private SparseArray<View> mItemViews;

    public AppViewHelper(@NonNull View contentView) {
        this.mContentView = contentView;
        this.mItemViews = new SparseArray<>();
    }

    public Context getContext() {
        return mContentView.getContext();
    }

    @Override
    public AppViewHelper setText(@IdRes int viewId, String text) {
        TextView textView = findViewById(viewId);
        textView.setText(text);
        return this;
    }

    @Override
    public AppViewHelper setText(@IdRes int viewId, @StringRes int stringResId) {
        TextView textView = findViewById(viewId);
        textView.setText(getContext().getString(stringResId));
        return this;
    }

    @Override
    public AppViewHelper setText(@IdRes int viewId, @StringRes int stringResId, Object... formatArgs) {
        TextView textView = findViewById(viewId);
        textView.setText(getContext().getString(stringResId, formatArgs));
        return this;
    }

    @Override
    public AppViewHelper setTextColorWithRes(@IdRes int viewId, @ColorRes int colorResId) {
        TextView textView = findViewById(viewId);
        textView.setTextColor(ContextCompat.getColor(getContext(), colorResId));
        return this;
    }

    @Override
    public AppViewHelper setTextColorWithValue(@IdRes int viewId, @ColorInt int colorValue) {
        TextView textView = findViewById(viewId);
        textView.setTextColor(colorValue);
        return this;
    }

    @Override
    public AppViewHelper setTextSizeWithRes(@IdRes int viewId, @DimenRes int dimensResId) {
        TextView textView = findViewById(viewId);
        textView.setTextSize(getContext().getResources().getDimension(dimensResId));
        return this;
    }

    @Override
    public AppViewHelper setTextSizeWithValue(@IdRes int viewId, float textSizValue) {
        TextView textView = findViewById(viewId);
        textView.setTextSize(textSizValue);
        return this;
    }

    @Override
    public AppViewHelper setTextSizeBySp(@IdRes int viewId, float spValue) {
        TextView textView = findViewById(viewId);
        textView.setTextSize(spToPx(spValue));
        return this;
    }

    @Override
    public AppViewHelper setTextSizeByDp(@IdRes int viewId, float dpValue) {
        TextView textView = findViewById(viewId);
        textView.setTextSize(dpToPx(dpValue));
        return this;
    }

    @Override
    public AppViewHelper setBackgroundResource(@IdRes int viewId, @DrawableRes int drawableResId) {
        View view = findViewById(viewId);
        view.setBackground(ContextCompat.getDrawable(getContext(), drawableResId));
        return this;
    }

    @Override
    public AppViewHelper setBackgroundDrawable(@IdRes int viewId, Drawable drawable) {
        View view = findViewById(viewId);
        view.setBackground(drawable);
        return this;
    }

    @Override
    public AppViewHelper setBackgroundColor(@IdRes int viewId, @ColorRes int colorResId) {
        View view = findViewById(viewId);
        view.setBackgroundColor(ContextCompat.getColor(getContext(), colorResId));
        return this;
    }

    @Override
    public AppViewHelper setImageResource(@IdRes int viewId, @DrawableRes int drawableResId) {
        ImageView view = findViewById(viewId);
        view.setImageResource(drawableResId);
        return this;
    }

    @Override
    public AppViewHelper setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = findViewById(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public AppViewHelper setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = findViewById(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    @Override
    public AppViewHelper setEnable(int viewId, boolean enable) {
        View view = findViewById(viewId);
        view.setEnabled(enable);
        return this;
    }

    @Override
    public AppViewHelper setSelected(int viewId, boolean selected) {
        View view = findViewById(viewId);
        view.setSelected(selected);
        return this;
    }

    @Override
    public AppViewHelper setVisible(int viewId, boolean visible) {
        View view = findViewById(viewId);
        setVisible(view, visible);
        return this;
    }

    @Override
    public AppViewHelper setINVisible(int viewId, boolean visible) {
        View view = findViewById(viewId);
        setINVisible(view, visible);
        return this;
    }

    @Override
    public AppViewHelper setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        findViewById(viewId).setOnClickListener(listener);
        return this;
    }

    @Override
    public AppViewHelper setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener) {
        findViewById(viewId).setOnLongClickListener(listener);
        return this;
    }

    public final <V extends View> V findViewById(@IdRes int viewId) {
        View view = mItemViews.get(viewId);
        if (view == null) {
            view = mContentView.findViewById(viewId);
            mItemViews.put(viewId, view);
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

    private void setVisible(View view, boolean visible) {
        if (view != null) {
            if (visible) {
                if (view.getVisibility() != View.VISIBLE) {
                    view.setVisibility(View.VISIBLE);
                }
            } else {
                if (view.getVisibility() != View.GONE) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    private void setINVisible(View view, boolean visible) {
        if (view != null) {
            if (visible) {
                if (view.getVisibility() != View.VISIBLE) {
                    view.setVisibility(View.VISIBLE);
                }
            } else {
                if (view.getVisibility() != View.INVISIBLE) {
                    view.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    public void onDestroy() {
        this.mContentView = null;
        this.mItemViews.clear();
        this.mItemViews = null;
    }
}
