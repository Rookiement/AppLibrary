package com.base.library.common.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.base.library.common.AppNavigateUtil;
import com.base.library.common.helper.AppViewHelper;


/**
 * @author reber
 */
public abstract class BaseAppActivity extends AppCompatActivity {

    private AppViewHelper mViewHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (this.mViewHelper == null) {
            this.mViewHelper = new AppViewHelper(getWindow().getDecorView());
            getLifecycle().addObserver(this.mViewHelper);
        } else {
            this.mViewHelper.setContentView(getWindow().getDecorView());
        }

        if (savedInstanceState != null) {
            onInitCreateWithState(this.mViewHelper, savedInstanceState);
        } else {
            onInitCreateWithState(this.mViewHelper, getParamBundle());
        }
    }

    /**
     * 获取activity传递的参数bundle
     */
    private Bundle getParamBundle() {
        return AppNavigateUtil.getBundleParams(this);
    }

    @NonNull
    public AppViewHelper getViewHelper() {
        return mViewHelper;
    }

    /**
     * 获取activity的布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 在onCreate方法中，调用setContentView后的初始化，
     *
     * @param viewHelper 根据onCreateView返回值View，封装成一个helper辅助类
     */
    protected abstract void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState);
}
