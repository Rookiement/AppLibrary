package com.base.library.ui.flow;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.LayoutDirection;

import androidx.core.text.TextUtilsCompat;

import com.base.library.ui.R;

import java.util.Locale;

/**
 * @author reber
 * on 2020/9/28 18:15
 */
public class AppDirectionFlowLayout extends AppFlowLayout {
    private static final int LEFT = -1;
    private static final int CENTER = 0;
    private static final int RIGHT = 1;

    private int mFlowGravity;

    public AppDirectionFlowLayout(Context context) {
        this(context, null);
    }

    public AppDirectionFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppDirectionFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void loadFromAttributes(TypedArray array) {
        this.mFlowGravity = array.getInt(R.styleable.AppFlowLayout_flowGravity, LEFT);
        int layoutDirection = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault());
        if (layoutDirection == LayoutDirection.RTL) {
            if (mFlowGravity == LEFT) {
                mFlowGravity = RIGHT;
            } else {
                mFlowGravity = LEFT;
            }
        }
        super.loadFromAttributes(array);
    }

    public void setFlowGravity(int flowGravity) {
        this.mFlowGravity = flowGravity;
    }

    public int getFlowGravity() {
        return mFlowGravity;
    }
}
