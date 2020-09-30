package com.library.test.ui.refresh;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.library.common.activity.BaseAppActivity;
import com.base.library.common.helper.AppViewHelper;
import com.base.library.ui.refresh.AppRefreshLayout;
import com.library.test.R;

/**
 * @author reber
 * on 2020/9/29 18:02
 */
public class AppRefreshActivity extends BaseAppActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh_app;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {
        final AppRefreshLayout refreshLayout = viewHelper.findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(new AppRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }
}
