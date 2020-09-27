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
import com.base.library.common.helper.AppViewHelper;

/**
 * @author reber
 */
public abstract class BaseAppFragment extends Fragment {

    private AppViewHelper mViewHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mViewHelper == null) {
            this.mViewHelper = new AppViewHelper(view);
            getLifecycle().addObserver(this.mViewHelper);
        } else {
            this.mViewHelper.setContentView(view);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getParamBundle();
        if (savedInstanceState != null) {
            onInitActivityCreated(mViewHelper, savedInstanceState);
        } else {
            onInitActivityCreated(mViewHelper, arguments);
        }
    }

    /**
     * 获取Fragment传递的参数bundle
     */
    private Bundle getParamBundle() {
        return AppNavigateUtil.getArgumentsParams(this);
    }

    @NonNull
    public AppViewHelper getViewHelper() {
        return mViewHelper;
    }

    /**
     * 获取Fragment的布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 在onCreate方法中，调用setContentView后的初始化，
     *
     * @param viewHelper 根据onCreateView返回值View，封装成一个helper辅助类
     */
    protected abstract void onInitActivityCreated(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState);
}
