package com.base.library.ui.recycler;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

public class AppRecyclerView extends RecyclerView {

    public AppRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public AppRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 默认不展示边界阴影
        setSupportBorderShadow(false);
    }

    /**
     * 移除默认Item刷新动画
     */
    public void removeDefaultItemAnimator() {
        if (getItemAnimator() != null) {
            SimpleItemAnimator itemAnimator = (SimpleItemAnimator) getItemAnimator();
            itemAnimator.setAddDuration(0);
            itemAnimator.setChangeDuration(0);
            itemAnimator.setMoveDuration(0);
            itemAnimator.setRemoveDuration(0);
            itemAnimator.setSupportsChangeAnimations(false);
        }
    }

    /**
     * 移除recyclerView的焦点
     */
    public void removeDefaultFocusable(boolean focusable) {
        setFocusable(focusable);
        setFocusableInTouchMode(focusable);
    }

    /**
     * 是否支持边界滑动阴影
     */
    public void setSupportBorderShadow(boolean supportBorderShadow) {
        if (supportBorderShadow) {
            setOverScrollMode(OVER_SCROLL_IF_CONTENT_SCROLLS);
        } else {
            setOverScrollMode(OVER_SCROLL_NEVER);
        }
    }
}
