package com.base.library.ui.recycler.adapter;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.base.library.ui.recycler.holder.AppViewHolder;
import com.base.library.ui.recycler.item.AppAdapterItem;

public class AppMultiTypeAdapter extends AppAdapter<AppAdapterItem> {

    private final SparseIntArray mLayoutIdArray;

    public AppMultiTypeAdapter() {
        this.mLayoutIdArray = new SparseIntArray();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemWithPosition(position).getItemViewType();
    }

    @NonNull
    @Override
    public AppViewHolder<AppAdapterItem> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppViewHolder<>(LayoutInflater.from(parent.getContext())
                .inflate(mLayoutIdArray.get(viewType), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder<AppAdapterItem> holder, int position) {
        AppAdapterItem adapterItem = getItemWithPosition(position);
        adapterItem.onBindViewHolder(holder);
    }

    @Override
    protected void insertItemWhenNotEmpty(@NonNull AppAdapterItem item, int position) {
        // 根据不同的ViewType添加layoutId
        int itemViewType = item.getItemViewType();
        if (mLayoutIdArray.get(item.getItemViewType()) == 0) {
            mLayoutIdArray.put(itemViewType, item.getLayoutResId());
        }
        super.insertItemWhenNotEmpty(item, position);
    }
}
