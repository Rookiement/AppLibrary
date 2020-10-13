package com.library.test.util;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.library.common.activity.BaseAppActivity;
import com.base.library.common.helper.AppViewHelper;
import com.library.test.R;

/**
 * @author reber
 * on 2020/10/13 11:49
 */
public class UtilLogActivity extends BaseAppActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_util_spannable_string;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {

    }
}
