package com.library.test.ui.flow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.library.common.activity.BaseAppActivity;
import com.base.library.common.helper.AppViewHelper;
import com.base.library.ui.flow.AppFlowLayout;
import com.library.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author reber
 * on 2020/9/29 10:44
 */
public class AppFlowActivity extends BaseAppActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_flow_app;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {
        AppFlowLayout<String> flowLayout = viewHelper.findViewById(R.id.flow_layout);
        flowLayout.setFlowHelper(new DefaultFlowHelper());
        flowLayout.addFlowItems(getFlowItemList());

        AppFlowLayout<String> flowDirectionLayout = viewHelper.findViewById(R.id.flow_direction_layout);
        flowDirectionLayout.setFlowHelper(new DefaultFlowHelper());
        flowDirectionLayout.addFlowItems(getFlowItemList());
    }

    private List<String> getFlowItemList() {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemList.add("Test-" + i);
        }
        return itemList;
    }
}
