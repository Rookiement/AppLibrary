package com.library.test.ui;

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
import com.library.test.ui.flow.AppFlowActivity;
import com.library.test.ui.recycler.AppRecyclerActivity;
import com.library.test.ui.refresh.AppRefreshActivity;
import com.library.test.ui.values.ValueColorActivity;
import com.library.test.ui.values.ValueFontActivity;

/**
 * @author reber
 */
public class AppUIActivity extends BaseAppActivity implements View.OnClickListener {

    private static final int TYPE_FLOW = 1;
    private static final int TYPE_VALUES_COLOR = 2;
    private static final int TYPE_VALUES_FONT = 3;

    private static final int TYPE_RECYCLER = 4;

    private static final int TYPE_REFRESH = 5;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flow_layout;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {
        AppFlowLayout<Object> flowLayout = viewHelper.findViewById(R.id.flow_layout);
        flowLayout.addView(getItemView(TYPE_FLOW, "FlowLayout"));
        flowLayout.addView(getItemView(TYPE_VALUES_COLOR, "ValuesColor"));
        flowLayout.addView(getItemView(TYPE_VALUES_FONT, "ValuesFont"));
        flowLayout.addView(getItemView(TYPE_RECYCLER, "RecyclerView"));
        flowLayout.addView(getItemView(TYPE_REFRESH, "RefreshLayout"));
    }

    @Override
    public void onClick(View v) {
        int type = (int) v.getTag();
        switch (type) {
            case TYPE_FLOW:
                AppNavigateUtil.startActivity(this, AppFlowActivity.class);
                break;
            case TYPE_VALUES_COLOR:
                AppNavigateUtil.startActivity(this, ValueColorActivity.class);
                break;
            case TYPE_VALUES_FONT:
                AppNavigateUtil.startActivity(this, ValueFontActivity.class);
                break;
            case TYPE_RECYCLER:
                AppNavigateUtil.startActivity(this, AppRecyclerActivity.class);
                break;
            case TYPE_REFRESH:
                AppNavigateUtil.startActivity(this, AppRefreshActivity.class);
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