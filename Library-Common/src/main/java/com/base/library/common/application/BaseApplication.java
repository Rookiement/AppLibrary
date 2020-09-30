package com.base.library.common.application;

import android.app.Application;

/**
 * @author reber
 * on 2020/9/30 15:13
 */
public abstract class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        onInitApplicationAfterCreate();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    protected abstract void onInitApplicationAfterCreate();
}
