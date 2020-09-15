package com.base.library.common.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author reber
 */
public abstract class AppBaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInitActivityCreated(savedInstanceState);
    }

    /**
     * 获取Fragment的布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 在onCreate方法中，调用setContentView后的初始化，
     */
    protected abstract void onInitActivityCreated(@Nullable Bundle savedInstanceState);
}
