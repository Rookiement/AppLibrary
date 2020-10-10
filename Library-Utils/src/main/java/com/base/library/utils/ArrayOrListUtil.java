package com.base.library.utils;

import java.util.List;

/**
 * @author reber
 * on 2020/10/10 10:37
 */
public class ArrayOrListUtil {

    /**
     * 判断数组Array是否为null或者为Empty
     */
    public static <T> boolean isNullOrEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断集合List是否为null或者为Empty
     */
    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }
}
