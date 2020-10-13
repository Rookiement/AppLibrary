package com.base.library.ui.recycler.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

import com.base.library.ui.recycler.holder.AppViewHolder;

import java.util.List;

/**
 * @param <T>
 * @author reber
 */
public abstract class AppSingleTypeAdapter<T> extends AppAdapter<T> {

    private final int mItemLayoutResId;
    private final Lifecycle mlifecycle;

    public AppSingleTypeAdapter(@LayoutRes int itemLayoutResId) {
        this(null, itemLayoutResId, false);
    }

    public AppSingleTypeAdapter(Lifecycle lifecycle, @LayoutRes int itemLayoutResId) {
        this(lifecycle, itemLayoutResId, false);
    }

    public AppSingleTypeAdapter(Lifecycle lifecycle, @LayoutRes int itemLayoutResId, @NonNull List<T> adapterList) {
        this(lifecycle, itemLayoutResId, adapterList, false);
    }

    public AppSingleTypeAdapter(Lifecycle lifecycle, @LayoutRes int itemLayoutResId, boolean hasStabledIds) {
        super(hasStabledIds);
        this.mItemLayoutResId = itemLayoutResId;
        this.mlifecycle = lifecycle;
    }

    public AppSingleTypeAdapter(Lifecycle lifecycle, @LayoutRes int itemLayoutResId, @NonNull List<T> adapterList, boolean hasStabledIds) {
        super(adapterList, hasStabledIds);
        this.mItemLayoutResId = itemLayoutResId;
        this.mlifecycle = lifecycle;
    }

    @NonNull
    @Override
    public AppViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppViewHolder<>(LayoutInflater.from(parent.getContext())
                .inflate(mItemLayoutResId, parent, false), mlifecycle);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder<T> holder, int position) {
        onUpdateViewHolderWhenBind(holder, getItemWithPosition(position), position);
    }

    public abstract void onUpdateViewHolderWhenBind(@NonNull AppViewHolder<T> holder, T item, int position);
}