package com.base.library.utils;

/**
 * @author reber
 * on 2020/10/10 09:51
 */
public class StringUtil {
    // SpannableString相关工具类→SpannableStringUtils

    /**
     * 判断String的内容是否为null或者为Empty
     */
    public static boolean isNullOrEmpty(String content) {
        return content == null || content.trim().isEmpty();
    }
}
