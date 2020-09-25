package com.base.library.ui.recycler.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.base.library.ui.recycler.holder.AppViewHolder;

/**
 * @param <T>
 * @author reber
 */
public abstract class AppSingleTypeAdapter<T> extends AppAdapter<T> {

    private final int mItemLayoutResId;

    public AppSingleTypeAdapter(@LayoutRes int itemLayoutResId) {
        this(itemLayoutResId, false);
    }

    public AppSingleTypeAdapter(@LayoutRes int itemLayoutResId, boolean hasStabledIds) {
        super(hasStabledIds);
        this.mItemLayoutResId = itemLayoutResId;
    }

    @NonNull
    @Override
    public AppViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppViewHolder<>(LayoutInflater.from(parent.getContext())
                .inflate(mItemLayoutResId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder<T> holder, int position) {
        onUpdateViewHolderWhenBind(holder, getItemWithPosition(position), position);
    }

    public abstract void onUpdateViewHolderWhenBind(@NonNull AppViewHolder<T> holder, T item, int position);
}