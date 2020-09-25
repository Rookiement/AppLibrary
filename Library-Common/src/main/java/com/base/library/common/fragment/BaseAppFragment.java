package com.base.library.common.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.base.library.common.AppNavigateUtil;

/**
 * @author reber
 */
public abstract class BaseAppFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onInitViewCreated(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getParamBundle();
        if (savedInstanceState != null) {
            onInitActivityCreated(savedInstanceState);
        } else {
            onInitActivityCreated(arguments);
        }
    }

    /**
     * 获取Fragment传递的参数bundle
     */
    private Bundle getParamBundle() {
        return AppNavigateUtil.getArgumentsParams(this);
    }

    /**
     * 获取Fragment的布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 在View创建成功后，onViewCreated之后调用
     *
     * @param contentView onCreateView返回值
     */
    protected abstract void onInitViewCreated(@NonNull View contentView);

    /**
     * 在onCreate方法中，调用setContentView后的初始化，
     */
    protected abstract void onInitActivityCreated(@Nullable Bundle savedInstanceState);
}
