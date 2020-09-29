package com.base.library.ui.flow;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;

import com.base.library.ui.R;
import com.base.library.ui.delegate.OnAppClickListener;

import java.util.List;

/**
 * @author reber
 * on 2020/9/28 17:30
 */
public class AppFlowLayout<T> extends ViewGroup implements View.OnClickListener {

    private int mHorizontalSpacing;  // 横向间隔
    private int mVerticalSpacing;    // 纵向间隔
    private boolean mFlowSingleLine; // 是否只显示一行
    private int mMaxSelectedCount;   // -1 不限制

    private AppFlowHelper<T> mFlowHelper;
    private OnAppClickListener mClickListener;

    public AppFlowLayout(Context context) {
        this(context, null);
    }

    public AppFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mFlowSingleLine = false;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AppFlowLayout);
        this.loadFromAttributes(array);
    }

    protected void loadFromAttributes(TypedArray array) {
        this.mMaxSelectedCount = array.getInt(R.styleable.AppFlowLayout_flowMaxSelected, -1);
        this.mVerticalSpacing = array.getDimensionPixelSize(R.styleable.AppFlowLayout_verticalSpacing, 0);
        this.mHorizontalSpacing = array.getDimensionPixelSize(R.styleable.AppFlowLayout_horizontalSpacing, 0);
        this.mFlowSingleLine = array.getBoolean(R.styleable.AppFlowLayout_flowSingleLine, false);
        array.recycle();
    }

    @Override
    public void onClick(View v) {

    }

    protected int getHorizontalSpacing() {
        return this.mHorizontalSpacing;
    }

    protected void setHorizontalSpacing(int lineSpacing) {
        this.mHorizontalSpacing = lineSpacing;
    }

    protected int getVerticalSpacing() {
        return this.mVerticalSpacing;
    }

    protected void setVerticalSpacing(int itemSpacing) {
        this.mVerticalSpacing = itemSpacing;
    }

    protected boolean isFlowSingleLine() {
        return this.mFlowSingleLine;
    }

    public void setSingleLine(boolean singleLine) {
        this.mFlowSingleLine = singleLine;
    }

    public void setMaxSelectedCount(int maxSelectedCount) {
        this.mMaxSelectedCount = maxSelectedCount;
    }

    public int getMaxSelectedCount() {
        return mMaxSelectedCount;
    }

    public OnAppClickListener getClickListener() {
        if (mClickListener == null) {
            mClickListener = new OnAppClickListener(this);
        }
        return mClickListener;
    }

    public void setFlowHelper(AppFlowHelper<T> mFlowHelper) {
        this.mFlowHelper = mFlowHelper;
    }

    public AppFlowHelper<T> getFlowHelper() {
        return mFlowHelper;
    }

    public void addFlowItem(int index, T t) {
        View itemView = getFlowHelper().getItemView(getContext(), this, t);
        itemView.setTag(index);
        itemView.setOnClickListener(getClickListener());
        addView(itemView);
    }

    public void addFlowItems(T[] items) {
        for (int i = 0; i < items.length; i++) {
            addFlowItem(i, items[i]);
        }
    }

    public void addFlowItems(List<T> items) {
        for (int i = 0; i < items.size(); i++) {
            addFlowItem(i, items.get(i));
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int maxWidth = widthMode != MeasureSpec.AT_MOST && widthMode != MeasureSpec.EXACTLY ? Integer.MAX_VALUE : width;

        int childLeft = this.getPaddingLeft();
        int childTop = this.getPaddingTop();
        int childBottom = childTop;
        int maxChildRight = 0;
        int maxRight = maxWidth - this.getPaddingRight();

        int finalWidth;
        for (finalWidth = 0; finalWidth < this.getChildCount(); ++finalWidth) {
            View child = this.getChildAt(finalWidth);
            if (child.getVisibility() != View.GONE) {
                this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
                LayoutParams lp = child.getLayoutParams();
                int leftMargin = 0;
                int rightMargin = 0;
                if (lp instanceof MarginLayoutParams) {
                    MarginLayoutParams marginLp = (MarginLayoutParams) lp;
                    leftMargin += marginLp.leftMargin;
                    rightMargin += marginLp.rightMargin;
                }

                int childRight = childLeft + leftMargin + child.getMeasuredWidth();
                if (childRight > maxRight && !this.mFlowSingleLine) {
                    childLeft = this.getPaddingLeft();
                    childTop = childBottom + this.mHorizontalSpacing;
                }

                childRight = childLeft + leftMargin + child.getMeasuredWidth();
                childBottom = childTop + child.getMeasuredHeight();
                if (childRight > maxChildRight) {
                    maxChildRight = childRight;
                }

                childLeft += leftMargin + rightMargin + child.getMeasuredWidth() + this.mVerticalSpacing;
            }
        }

        finalWidth = getMeasuredDimension(width, widthMode, maxChildRight);
        int finalHeight = getMeasuredDimension(height, heightMode, childBottom);
        this.setMeasuredDimension(finalWidth, finalHeight);
    }

    private static int getMeasuredDimension(int size, int mode, int childrenEdge) {
        switch (mode) {
            case MeasureSpec.AT_MOST:
                return Math.min(childrenEdge, size);
            case MeasureSpec.EXACTLY:
                return size;
            default:
                return childrenEdge;
        }
    }

    protected void onLayout(boolean sizeChanged, int left, int top, int right, int bottom) {
        if (this.getChildCount() != 0) {
            boolean isRtl = ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL;
            int paddingStart = isRtl ? this.getPaddingRight() : this.getPaddingLeft();
            int paddingEnd = isRtl ? this.getPaddingLeft() : this.getPaddingRight();
            int childStart = paddingStart;
            int childTop = this.getPaddingTop();
            int childBottom = childTop;
            int maxChildEnd = right - left - paddingEnd;

            for (int i = 0; i < this.getChildCount(); ++i) {
                View child = this.getChildAt(i);
                if (child.getVisibility() != View.GONE) {
                    LayoutParams lp = child.getLayoutParams();
                    int startMargin = 0;
                    int endMargin = 0;
                    if (lp instanceof MarginLayoutParams) {
                        MarginLayoutParams marginLp = (MarginLayoutParams) lp;
                        startMargin = MarginLayoutParamsCompat.getMarginStart(marginLp);
                        endMargin = MarginLayoutParamsCompat.getMarginEnd(marginLp);
                    }

                    int childEnd = childStart + startMargin + child.getMeasuredWidth();
                    if (!this.mFlowSingleLine && childEnd > maxChildEnd) {
                        childStart = paddingStart;
                        childTop = childBottom + this.mHorizontalSpacing;
                    }

                    childEnd = childStart + startMargin + child.getMeasuredWidth();
                    childBottom = childTop + child.getMeasuredHeight();
                    if (isRtl) {
                        child.layout(maxChildEnd - childEnd, childTop, maxChildEnd - childStart - startMargin, childBottom);
                    } else {
                        child.layout(childStart + startMargin, childTop, childEnd, childBottom);
                    }

                    childStart += startMargin + endMargin + child.getMeasuredWidth() + this.mVerticalSpacing;
                }
            }
        }
    }
}
