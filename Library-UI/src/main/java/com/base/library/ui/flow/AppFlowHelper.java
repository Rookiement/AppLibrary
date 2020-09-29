package com.base.library.ui.flow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

/**
 * @author reber
 * on 2020/9/28 13:53
 */
public abstract class AppFlowHelper<T> {

    private ViewGroup.MarginLayoutParams mItemLayoutParams;

    @NonNull
    public View getItemView(Context context, ViewGroup parent, T item) {
        View itemView = LayoutInflater.from(context).inflate(getFlowItemLayoutId(), parent, false);
        onInitItemView(itemView, item);
        return itemView;
    }

    @LayoutRes
    protected abstract int getFlowItemLayoutId();

    public abstract void onInitItemView(View itemView, T item);

    public ViewGroup.MarginLayoutParams getDefaultLayoutParam() {
        if (this.mItemLayoutParams == null) {
            this.mItemLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        return mItemLayoutParams;
    }
}
