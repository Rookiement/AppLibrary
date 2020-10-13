package com.base.library.utils;

import android.view.View;

/**
 * @author reber
 * on 2020/10/10 09:53
 */
public class ViewUtil {
    /**
     * 设置View 显示Visible或者隐藏Gone
     */
    public static void setVisible(View view, boolean visible) {
        if (view == null) {
            return;
        }
        if (visible) {
            if (view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        } else {
            if (view.getVisibility() != View.GONE) {
                view.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置View 显示Visible或者隐藏INVISIBLE
     */
    public static void setINVisible(View view, boolean visible) {
        if (view == null) {
            return;
        }
        if (visible) {
            if (view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        } else {
            if (view.getVisibility() != View.INVISIBLE) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }
}
