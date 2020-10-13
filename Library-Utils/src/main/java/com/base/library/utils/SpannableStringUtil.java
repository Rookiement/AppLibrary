package com.base.library.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.base.library.utils.model.StringFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author reber
 * on 2020/10/12 18:14
 */
public class SpannableStringUtil {
    /**
     * BackgroundColorSpan : 文本背景色
     * ForegroundColorSpan : 文本颜色
     * MaskFilterSpan : 修饰效果，如模糊(BlurMaskFilter)浮雕
     * RasterizerSpan : 光栅效果
     * StrikeThroughSpan : 删除线
     * SuggestionSpan : 相当于占位符
     * UnderlineSpan : 下划线
     * AbsoluteSizeSpan : 文本字体（绝对大小）
     * DynamicDrawableSpan : 设置图片，基于文本基线或底部对齐。
     * ImageSpan : 图片
     * RelativeSizeSpan : 相对大小（文本字体）
     * ScaleXSpan : 基于x轴缩放
     * StyleSpan : 字体样式：粗体、斜体等
     * SubscriptSpan : 下标（数学公式会用到）
     * SuperscriptSpan : 上标（数学公式会用到）
     * TextAppearanceSpan : 文本外貌（包括字体、大小、样式和颜色）
     * TypefaceSpan : 文本字体
     * URLSpan : 文本超链接
     * ClickableSpan : 点击事件
     */
    private SpannableStringUtil() {
        throw new Error(getClass().getSimpleName() + " do not need instantiate!");
    }

    public static void appendString(StringFilter[] stringFilter) {
        if (ArrayOrListUtil.isNullOrEmpty(stringFilter)) {
            return;
        }
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        for (StringFilter filter : stringFilter) {
            CharacterStyle[] characterStyles = filter.getCharacterStyles();
            if (!ArrayOrListUtil.isNullOrEmpty(characterStyles)) {
                int startIndex = stringBuilder.length();
                stringBuilder.append(filter.getText());
                int endIndex = stringBuilder.length();
                for (CharacterStyle characterStyle : characterStyles) {
                    stringBuilder.setSpan(characterStyle, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                }
            } else {
                stringBuilder.append(filter.getText());
            }
        }
    }

    /**
     * 在内容后面拼接
     */
    public static void appendSingleString(String content, StringFilter filter) {
        String filterContent = filter.getText();
        SpannableString spannableString = new SpannableString(filter.getText());
        CharacterStyle[] characterStyles = filter.getCharacterStyles();
        if (!ArrayOrListUtil.isNullOrEmpty(characterStyles)) {
            for (CharacterStyle characterStyle : characterStyles) {
                spannableString.setSpan(characterStyle, 0, content.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
        }
    }

    /**
     * 在原有的内容中更改文本
     */
    public static void changeSingleString(String content, StringFilter filter) {
        String filterContent = filter.getText();
        SpannableString spannableString = new SpannableString(filter.getText());
        CharacterStyle[] characterStyles = filter.getCharacterStyles();
        if (!ArrayOrListUtil.isNullOrEmpty(characterStyles)) {
            for (CharacterStyle characterStyle : characterStyles) {
                spannableString.setSpan(characterStyle, 0, content.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
        }
    }

    public static void changeMultiString(String content, String matcherContent) {
        Pattern p = Pattern.compile(matcherContent);
        Matcher m = p.matcher(content);
        while (m.find()) {
            int startIndex = m.start();
            int endIndex = m.end() - 1;
        }
    }

    /**
     * 文本设置颜色。
     */
    public static ForegroundColorSpan getForegroundColorSpan(Context context, @ColorRes int colorResId) {
        return getForegroundColorSpan(ContextCompat.getColor(context, colorResId));
    }

    public static ForegroundColorSpan getForegroundColorSpan(@ColorInt int colorValue) {
        return new ForegroundColorSpan(colorValue);
    }

    /**
     * 下划线
     */
    public static UnderlineSpan getUnderlineSpan() {
        return new UnderlineSpan();
    }

    /**
     * 删除线
     */
    public static StrikethroughSpan getStrikeThroughSpan() {
        return new StrikethroughSpan();
    }

    /**
     * 设置绝对文字大小
     */
    public static AbsoluteSizeSpan getAbsoluteSizeSpan(int textSize) {
        return new AbsoluteSizeSpan(textSize);
    }

    /**
     * 设置相对文字大小，如：0.5f-缩小，1.5f放大
     */
    public static RelativeSizeSpan getRelativeSizeSpan(float textRatioForSize) {
        return new RelativeSizeSpan(textRatioForSize);
    }

    /**
     * 将文字设置成下标
     */
    public static SuperscriptSpan getSuperscriptSpan() {
        return new SuperscriptSpan();
    }

    public static ImageSpan getImageSpan(@NonNull Context context, @DrawableRes int resourceId) {
        return new ImageSpan(context, resourceId, ImageSpan.ALIGN_BOTTOM);
    }

    /**
     * @param verticalAlignment 展示的位置，Bottom，Center，baseline等
     */
    public static ImageSpan getImageSpan(@NonNull Context context, @DrawableRes int resourceId,
                                         int verticalAlignment) {
        return new ImageSpan(context, resourceId, verticalAlignment);
    }
}
