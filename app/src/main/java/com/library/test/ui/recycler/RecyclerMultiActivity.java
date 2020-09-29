package com.library.test.ui.recycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.library.common.activity.BaseAppActivity;
import com.base.library.common.helper.AppViewHelper;
import com.library.test.R;

/**
 * @author reber
 * on 2020/9/29 16:13
 */
public class RecyclerMultiActivity extends BaseAppActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_layout;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {

    }
}
