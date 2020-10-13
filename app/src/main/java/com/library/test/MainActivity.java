package com.library.test;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.library.common.AppNavigateUtil;
import com.base.library.common.activity.BaseAppActivity;
import com.base.library.common.helper.AppViewHelper;
import com.base.library.ui.flow.AppFlowLayout;
import com.library.test.ui.AppUIActivity;
import com.library.test.util.AppUtilActivity;

/**
 * @author reber
 */
public class MainActivity extends BaseAppActivity implements View.OnClickListener {

    private static final int TYPE_UI = 1;
    private static final int TYPE_UTILS = 2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flow_layout;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {
        AppFlowLayout<Object> flowLayout = viewHelper.findViewById(R.id.flow_layout);
        flowLayout.addView(getItemView(TYPE_UI, "UI-Testing"));
        flowLayout.addView(getItemView(TYPE_UTILS, "Utils-Testing"));
    }

    @Override
    public void onClick(View v) {
        int type = (int) v.getTag();
        switch (type) {
            case TYPE_UI:
                AppNavigateUtil.startActivity(this, AppUIActivity.class);
                break;
            case TYPE_UTILS:
                AppNavigateUtil.startActivity(this, AppUtilActivity.class);
                break;
        }
    }

    private View getItemView(int type, String content) {
        View view = ViewTestUtil.generateItemView(this, type, content);
        view.setTag(type);
        view.setOnClickListener(this);
        return view;
    }
}