package com.library.test.ui.flow;

import android.view.View;
import android.widget.TextView;

import com.base.library.ui.flow.AppFlowHelper;
import com.library.test.R;

/**
 * @author reber
 * on 2020/9/29 10:58
 */
public class TestFlowHelper extends AppFlowHelper<String> {
    @Override
    protected int getFlowItemLayoutId() {
        return R.layout.item_flow_app;
    }

    @Override
    public void onInitItemView(View itemView, String item) {
        TextView textView = itemView.findViewById(R.id.text_item_flow);
        textView.setText(item);
    }

    @Override
    public void onItemClickIndexChanged(View itemView, boolean selected) {

    }
}
