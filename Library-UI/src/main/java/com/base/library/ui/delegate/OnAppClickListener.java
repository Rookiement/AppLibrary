package com.base.library.ui.delegate;

import android.view.View;

/**
 * @author reber
 * on 2020/9/28 16:02
 */
public class OnAppClickListener implements View.OnClickListener {

    private View.OnClickListener mListener;

    public OnAppClickListener(View.OnClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onClick(v);
        }
    }
}
