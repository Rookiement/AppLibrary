package com.base.library.ui.recycler.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.library.ui.recycler.holder.AppViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 * @author reber
 */
public abstract class AppAdapter<T> extends RecyclerView.Adapter<AppViewHolder<T>> {

    private final List<T> mAdapterList;

    public AppAdapter() {
        this(false);
    }

    public AppAdapter(boolean hasStabledIds) {
        this.mAdapterList = new ArrayList<>();
        setHasStableIds(hasStabledIds);
    }

    public AppAdapter(@NonNull List<T> adapterList, boolean hasStabledIds) {
        this.mAdapterList = adapterList;
        setHasStableIds(hasStabledIds);
    }

    public final List<T> getAdapterList() {
        return mAdapterList;
    }

    public final T getItemWithPosition(int position) {
        return mAdapterList.get(position);
    }

    @Override
    public final int getItemCount() {
        return mAdapterList.size();
    }

    /*=============================单个插入Item======================*/
    public final void insertItem(T item) {
        if (item != null) {
            int position = getItemCount();
            insertItemWhenNotEmpty(item, position);
        }
    }

    public final void insertItemNotify(T item) {
        if (item != null) {
            int position = getItemCount();
            insertItemWhenNotEmpty(item, position);
            notifyItemChanged(position);
        }
    }

    public final void insertItem(T item, int position) {
        if (item != null) {
            insertItemWhenNotEmpty(item, position);
        }
    }

    public final void insertItemNotify(T item, int position) {
        if (item != null) {
            insertItemWhenNotEmpty(item, position);
            notifyItemChanged(position);
        }
    }

    /*==============================插入列表=========================*/
    public final void insertItemList(T[] itemList) {
        if (itemList != null && itemList.length > 0) {
            insertItemListWhenNotEmpty(itemList);
        }
    }

    public final void insertItemListNotify(T[] itemList) {
        if (itemList != null && itemList.length > 0) {
            int startPosition = getItemCount();
            insertItemListWhenNotEmpty(itemList);
            notifyItemRangeInserted(startPosition, itemList.length);
        }
    }

    public final void insertItemList(List<T> itemList) {
        if (itemList != null && !itemList.isEmpty()) {
            insertItemListWhenNotEmpty(itemList);
        }
    }

    public final void insertItemListNotify(List<T> itemList) {
        if (itemList != null && !itemList.isEmpty()) {
            int startPosition = getItemCount();
            insertItemListWhenNotEmpty(itemList);
            notifyItemRangeInserted(startPosition, itemList.size());
        }
    }

    public final void addNewItemListNotify(T[] itemList) {
        mAdapterList.clear();
        if (itemList != null && itemList.length > 0) {
            insertItemListWhenNotEmpty(itemList);
        }
        notifyDataSetChanged();
    }

    public final void addNewItemListNotify(List<T> itemList) {
        mAdapterList.clear();
        if (itemList != null && !itemList.isEmpty()) {
            insertItemListWhenNotEmpty(itemList);
        }
        notifyDataSetChanged();
    }

    protected void insertItemWhenNotEmpty(@NonNull T item, int position) {
        mAdapterList.add(position, item);
    }

    protected final void insertItemListWhenNotEmpty(@NonNull T[] itemList) {
        int startPosition = getItemCount();
        for (int i = 0; i < itemList.length; i++) {
            insertItemWhenNotEmpty(itemList[i], startPosition + i);
        }
    }

    protected final void insertItemListWhenNotEmpty(@NonNull List<T> itemList) {
        int startPosition = getItemCount();
        for (int i = 0; i < itemList.size(); i++) {
            insertItemWhenNotEmpty(itemList.get(i), startPosition + i);
        }
    }
}
