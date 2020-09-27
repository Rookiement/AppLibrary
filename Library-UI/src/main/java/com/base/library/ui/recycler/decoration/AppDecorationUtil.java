package com.base.library.ui.recycler.decoration;

/**
 * @author reber
 * <p>
 * RecyclerView的分割线的工具类
 */
class AppDecorationUtil {

    /**
     * @param unitValue     分割线单份的值（纵向分割线的宽/spanCount）
     * @param childPosition item的位置
     * @param spanCount     item的横向展示的数量
     * @return 当recyclerview是纵向时，返回分割线左边值
     */
    static int getLeftWhenVertical(float unitValue, int childPosition, int spanCount) {
        int remaining = childPosition % spanCount;
        return (int) (unitValue * remaining);
    }

    /**
     * @param childPosition item的位置
     * @param spanCount     item的横向展示的数量
     * @param topValue      recyclerView顶部边界值
     * @return 当recyclerview是纵向时，返回分割线顶部值
     */
    static int getTopWhenVertical(int childPosition, int spanCount, int topValue) {
        if (childPosition < spanCount) {
            return topValue;
        } else {
            return 0;
        }
    }

    /**
     * @param unitValue     纵向分割线单份的值（纵向分割线的宽/spanCount）
     * @param childPosition item的位置
     * @param spanCount     item的横向展示的数量
     * @return 当recyclerview是纵向时，返回分割线右边的值
     */
    static int getRightWhenVertical(float unitValue, int childPosition, int spanCount) {
        int remaining = childPosition % spanCount;
        return (int) ((spanCount - 1 - remaining) * unitValue);
    }

    /**
     * @param bottomValue recyclerView底部边界值
     * @param divideValue item的纵向分割线的宽度值
     * @return 当recyclerview是纵向时，返回分割线底部的值
     */
    static int getBottomWhenVertical(int childPosition, int spanCount, int itemCount, int bottomValue, int divideValue) {
        int multiplier = (itemCount - 1) / spanCount;
        if (childPosition >= multiplier * spanCount) {
            return bottomValue;
        }
        return divideValue;
    }

    /**
     * @param leftValue recyclerView左边边界值
     * @return 当recyclerview是横向时，返回分割线左边值
     */
    static int getLeftWhenHorizontal(int childPosition, int spanCount, int leftValue) {
        // 是否是第一列数据
        if (childPosition < spanCount) {
            return leftValue;
        } else {
            return 0;
        }
    }

    /**
     * @param unitValue 横向分割线单份的值（横向分割线的高/spanCount）
     * @return 当recyclerview是横向时，返回分割线顶部值
     */
    static int getTopWhenHorizontal(float unitValue, int childPosition, int spanCount) {
        int remaining = childPosition % spanCount;
        return (int) (unitValue * remaining);
    }

    /**
     * @param rightValue  recyclerView右边边界值
     * @param divideValue item的纵向分割线的宽度值
     * @return 当recyclerview是横向时，返回分割线右边的值
     */
    static int getRightWhenHorizontal(int childPosition, int spanCount, int itemCount, int rightValue, int divideValue) {
        int multiplier = (itemCount - 1) / spanCount;
        if (childPosition >= multiplier * spanCount) {
            return rightValue;
        }
        return divideValue;
    }

    /**
     * @param unitValue 横向分割线单份的值（横向分割线的高/spanCount）
     * @return 当recyclerview是纵向时，返回分割线左边值
     */
    static int getBottomWhenHorizontal(float unitValue, int childPosition, int spanCount) {
        int remaining = childPosition % spanCount;
        return (int) ((spanCount - 1 - remaining) * unitValue);
    }
}
