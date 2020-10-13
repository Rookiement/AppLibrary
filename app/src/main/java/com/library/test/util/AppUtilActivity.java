package com.library.test.util;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.library.common.AppNavigateUtil;
import com.base.library.common.activity.BaseAppActivity;
import com.base.library.common.helper.AppViewHelper;
import com.base.library.ui.flow.AppFlowLayout;
import com.library.test.R;
import com.library.test.ViewTestUtil;

/**
 * @author reber
 * on 2020/10/13 11:45
 */
public class AppUtilActivity extends BaseAppActivity implements View.OnClickListener {

    private static final int TYPE_SPANNABLE_STRING = 1;
    private static final int TYPE_TOAST = 2;
    private static final int TYPE_Log = 3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flow_layout;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {
        AppFlowLayout<Object> flowLayout = viewHelper.findViewById(R.id.flow_layout);
        flowLayout.addView(getItemView(TYPE_SPANNABLE_STRING, "SpannableString"));
        flowLayout.addView(getItemView(TYPE_TOAST, "Toast"));
        flowLayout.addView(getItemView(TYPE_Log, "Log"));
    }

    @Override
    public void onClick(View v) {
        int type = (int) v.getTag();
        switch (type) {
            case TYPE_SPANNABLE_STRING:
                AppNavigateUtil.startActivity(this, UtilSpannableStringActivity.class);
                break;
            case TYPE_TOAST:
                AppNavigateUtil.startActivity(this, UtilToastActivity.class);
                break;
            case TYPE_Log:
                AppNavigateUtil.startActivity(this, UtilLogActivity.class);
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
