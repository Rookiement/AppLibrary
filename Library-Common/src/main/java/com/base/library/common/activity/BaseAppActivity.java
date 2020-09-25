package com.base.library.common.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.base.library.common.AppNavigateUtil;


/**
 * @author reber
 */
public abstract class BaseAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (savedInstanceState != null) {
            onInitCreateWithState(savedInstanceState);
        } else {
            onInitCreateWithState(getParamBundle());
        }
    }

    /**
     * 获取activity传递的参数bundle
     */
    private Bundle getParamBundle() {
        return AppNavigateUtil.getBundleParams(this);
    }

    /**
     * 获取activity的布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 在onCreate方法中，调用setContentView后的初始化，
     */
    protected abstract void onInitCreateWithState(@Nullable Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
