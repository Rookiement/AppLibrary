package com.base.library.ui.recycler.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author reber
 * <p>
 * RecyclerView的通用网格分割线
 */
public class AppGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int DEFAULT_DIVIDER_VALUE = 1;
    private static final int DEFAULT_SPAN_COUNT = 2;

    /**
     * 网格布局的宽度/高度展示数量
     */
    private int mSpanCount;

    @RecyclerView.Orientation
    private int mOrientation = RecyclerView.VERTICAL;

    private int mVDividerValue;

    private int mHDividerValue;

    /**
     * RecyclerView的VERTICAL(top,bottom),HORIZONTAL(left,right)的边界值设置
     */
    private int mRVLeftTopValue;
    private int mRVRightBottomValue;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (state.isPreLayout()) {
            return;
        }
        int childPosition = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();
        if (mOrientation == RecyclerView.VERTICAL) {
            /*
             * 每个Item左边和右边的占用空间份数
             * 1-（0，0）
             * 2-（0，1）、（1，0）
             * 3-（0，2）、（1，1）、（2，0）
             * 4-（0，3）、（1，2）、（2，1）、（3，0）
             * 5-（0，4）、（1，3）、（2，2）、（3，1）、（4，0）
             * 6-（0，5）、（1，4）、（2，3）、（3，2）、（4，1）、（5，0）
             * ......
             */
            float unitValue = mVDividerValue * 1f / mSpanCount;
            outRect.set(
                    AppDecorationUtil.getLeftWhenVertical(unitValue, childPosition, mSpanCount),
                    AppDecorationUtil.getTopWhenVertical(childPosition, mSpanCount, mRVLeftTopValue),
                    AppDecorationUtil.getRightWhenVertical(unitValue, childPosition, mSpanCount),
                    AppDecorationUtil.getBottomWhenVertical(childPosition, mSpanCount, itemCount, mRVRightBottomValue, mHDividerValue)
            );
        } else {
            float unitValue = mHDividerValue * 1f / mSpanCount;
            outRect.set(
                    AppDecorationUtil.getLeftWhenHorizontal(childPosition, mSpanCount, mRVLeftTopValue),
                    AppDecorationUtil.getTopWhenHorizontal(unitValue, childPosition, mSpanCount),
                    AppDecorationUtil.getRightWhenHorizontal(childPosition, mSpanCount, itemCount, mRVRightBottomValue, mVDividerValue),
                    AppDecorationUtil.getBottomWhenHorizontal(unitValue, childPosition, mSpanCount)
            );
        }
    }

    public static class Builder {

        private final AppGridItemDecoration mDecoration;

        /**
         * 判断是否初始化了分割线宽/高
         */
        private boolean isInitDividerValue;

        /**
         * 是否初始化了RecyclerView的top、bottom边界值
         */
        private boolean isInitRVBoundValue;

        @DimenRes
        private int mVDividerValueResId;

        @DimenRes
        private int mHDividerValueResId;

        /**
         * RecyclerView的top、bottom的边界值设置
         */
        @DimenRes
        private int mRVLeftTopValueResId;
        @DimenRes
        private int mRVRightBottomValueResId;

        public Builder() {
            this.mDecoration = new AppGridItemDecoration();
        }

        /**
         * 设置分割线的方向，取的是RecyclerView的Orientation
         */
        public Builder setOrientation(@RecyclerView.Orientation int orientation) {
            this.mDecoration.mOrientation = orientation;
            return this;
        }

        /**
         * 设置网格布局的限制宽高位置的展示数量
         */
        public Builder setSpanCount(@IntRange(from = 2, to = Integer.MAX_VALUE) int spanCount) {
            this.mDecoration.mSpanCount = spanCount;
            return this;
        }

        /**
         * 设置RecyclerView顶部和底部的边界值，左右边界无法根据分割线设置，会影响Item的宽高不均分问题
         */
        public Builder setBoundaryValues(@DimenRes int valueResId) {
            return setBoundaryValues(valueResId, valueResId);
        }

        public Builder setBoundaryValues(@DimenRes int leftTopValueResId, @DimenRes int rightBottomValueResId) {
            this.mRVLeftTopValueResId = leftTopValueResId;
            this.mRVRightBottomValueResId = rightBottomValueResId;
            isInitRVBoundValue = true;
            return this;
        }

        /**
         * 根据方向设置分割线的横向/纵向的宽度
         */
        public Builder setDivideValuesResId(@DimenRes int dimensResId) {
            return setDivideValuesResId(dimensResId, dimensResId);
        }

        public Builder setDivideValuesResId(@DimenRes int vDimensResId, @DimenRes int hDimensResId) {
            this.mVDividerValueResId = vDimensResId;
            this.mHDividerValueResId = hDimensResId;
            this.isInitDividerValue = true;
            return this;
        }

        public AppGridItemDecoration build(Context context) {
            int verticalValue = 0;
            int horizontalValue = 0;
            try {
                if (isInitDividerValue) {
                    verticalValue = (int) context.getResources().getDimension(mVDividerValueResId);
                    horizontalValue = (int) context.getResources().getDimension(mHDividerValueResId);
                }
                if (isInitRVBoundValue) {
                    mDecoration.mRVLeftTopValue = (int) context.getResources().getDimension(mRVLeftTopValueResId);
                    mDecoration.mRVRightBottomValue = (int) context.getResources().getDimension(mRVRightBottomValueResId);
                }
            } catch (Resources.NotFoundException ignore) {
            }
            // 设置纵向分割线的值，如果最后分割线的宽高值 <= 0时，设置默认值:1px
            if (verticalValue > 0) {
                mDecoration.mVDividerValue = verticalValue;
            } else {
                mDecoration.mVDividerValue = DEFAULT_DIVIDER_VALUE;
            }
            // 设置横向分割线的值
            if (horizontalValue > 0) {
                mDecoration.mHDividerValue = horizontalValue;
            } else {
                mDecoration.mHDividerValue = DEFAULT_DIVIDER_VALUE;
            }
            mDecoration.mSpanCount = Math.max(mDecoration.mSpanCount, DEFAULT_SPAN_COUNT);
            return mDecoration;
        }
    }
}
