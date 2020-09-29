package com.library.test.ui.recycler;

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
 * on 2020/9/29 16:11
 */
public class AppRecyclerActivity extends BaseAppActivity implements View.OnClickListener {

    private static final int TYPE_DECORATION_LINEAR = 1;
    private static final int TYPE_DECORATION_GRID = 2;
    private static final int TYPE_ADAPTER_MULTI = 3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flow_layout;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {
        AppFlowLayout<Object> flowLayout = viewHelper.findViewById(R.id.flow_layout);
        flowLayout.addView(getItemView(TYPE_DECORATION_LINEAR, "线性Decoration"));
        flowLayout.addView(getItemView(TYPE_DECORATION_GRID, "网格Decoration"));
        flowLayout.addView(getItemView(TYPE_ADAPTER_MULTI, "多类型的Adapter"));
    }

    @Override
    public void onClick(View v) {
        int type = (int) v.getTag();
        switch (type) {
            case TYPE_DECORATION_LINEAR:
                AppNavigateUtil.startActivity(this, RecyclerLinearDecorationActivity.class);
                break;
            case TYPE_DECORATION_GRID:
                AppNavigateUtil.startActivity(this, RecyclerGridDecorationActivity.class);
                break;
            case TYPE_ADAPTER_MULTI:
                AppNavigateUtil.startActivity(this, RecyclerMultiActivity.class);
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
