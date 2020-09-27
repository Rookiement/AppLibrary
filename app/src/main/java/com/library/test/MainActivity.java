package com.library.test;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.library.common.AppNavigateUtil;
import com.base.library.common.activity.BaseAppActivity;
import com.base.library.common.helper.AppViewHelper;
import com.base.library.ui.flow.AppFlowLayout;
import com.library.test.ui.values.ValueColorActivity;

/**
 * @author reber
 */
public class MainActivity extends BaseAppActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flow_layout;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {
        AppFlowLayout flowLayout = viewHelper.findViewById(R.id.flow_layout);
        flowLayout.addView(getItemView(1, "ColorTest"));
        flowLayout.addView(getItemView(2, "FontTest"));
    }

    private View getItemView(int type, String content) {
        View view = ViewTestUtil.generateItemView(this, type, content);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        AppNavigateUtil.startActivity(this, ValueColorActivity.class);
    }
}