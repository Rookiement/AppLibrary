package com.base.library.ui.flow;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.base.library.ui.R;

/**
 * @author reber
 * on 2020/9/29 14:00
 */
public class AppDefaultFlowHelper<T> extends AppFlowHelper<T> {

    @Deprecated
    @Override
    protected int getFlowItemLayoutId() {
        return 0;
    }

    @NonNull
    @Override
    public View getItemView(Context context, ViewGroup parent, T item) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(getDefaultLayoutParam());
        onInitItemView(textView, item);
        return textView;
    }

    @Override
    public void onInitItemView(View itemView, T item) {
        TextView textView = (TextView) itemView;
        initTextStyle(itemView.getContext(), textView, false);
        textView.setGravity(Gravity.CENTER);
        textView.setText(item.toString());
    }

    @Override
    public void onItemClickIndexChanged(View itemView, boolean selected) {
        if (itemView instanceof TextView) {
            TextView textView = (TextView) itemView;
            initTextStyle(itemView.getContext(), textView, selected);
        }
    }

    private void initTextStyle(Context context, TextView textView, boolean selected) {
        if (selected) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(R.style.RobotoBold_Red_16);
            } else {
                textView.setTextAppearance(context, R.style.RobotoBold_Red_16);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(R.style.RobotoBold_Black_14);
            } else {
                textView.setTextAppearance(context, R.style.RobotoBold_Black_14);
            }
        }
    }
}
