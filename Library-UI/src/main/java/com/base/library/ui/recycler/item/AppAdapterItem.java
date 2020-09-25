package com.base.library.ui.recycler.item;

import androidx.annotation.LayoutRes;

import com.base.library.ui.recycler.holder.AppViewHolder;

/**
 * @author reber
 */
public abstract class AppAdapterItem {

    private final int mItemViewType = getClass().getSimpleName().hashCode();

    public final int getItemViewType() {
        return mItemViewType;
    }

    @LayoutRes
    public abstract int getLayoutResId();

    public abstract <T> void onBindViewHolder(AppViewHolder<T> holder);
}
