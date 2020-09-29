package com.library.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author reber
 */
public class ViewTestUtil {

    private static final ViewGroup.MarginLayoutParams mLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

    public static View generateItemView(Context context, int type, String content) {
        AppCompatTextView textView = new AppCompatTextView(context);
        textView.setPadding(20, 20, 20, 20);
        textView.setLayoutParams(mLayoutParams);
        textView.setTextAppearance(context, R.style.RobotoBold_Red_18);
        textView.setText(content);
        return textView;
    }
}
