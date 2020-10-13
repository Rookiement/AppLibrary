package com.base.library.utils;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuggestionSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;

import com.base.library.utils.model.SpannableTextStyle;

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
     * StrikeThroughSpan : 删除线
     * SuggestionSpan : 相当于占位符
     * UnderlineSpan : 下划线
     * DynamicDrawableSpan : 设置图片，基于文本基线或底部对齐。
     * ImageSpan : 图片
     * RelativeSizeSpan : 相对大小（文本字体）
     * AbsoluteSizeSpan : 绝对大小（文本字体）
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

    /**
     * 根据内容匹配单个
     */
    public static CharSequence getTextWithSingleStyle(String content, SpannableTextStyle textStyle) {
        ParcelableSpan[] spans = textStyle.getSpans();
        if (!ArrayOrListUtil.isNullOrEmpty(spans)) {
            if (spans.length == 1) {
                return getSingleSpanContent(content, textStyle.getText(), new SpannableString(content), spans[0]);
            } else {
                return getMultiSpanContent(content, textStyle.getText(), new SpannableStringBuilder(content), spans);
            }
        }
        return content;
    }

    private static SpannableStringBuilder getTextWithSingleStyle(String content, SpannableTextStyle textStyle,
                                                                 SpannableStringBuilder stringBuilder) {
        ParcelableSpan[] spans = textStyle.getSpans();
        if (!ArrayOrListUtil.isNullOrEmpty(spans)) {
            return getMultiSpanContent(content, textStyle.getText(), stringBuilder, spans);
        }
        return stringBuilder;
    }

    /**
     * 根据内容匹配多个
     */
    public static CharSequence getTextWithMultiStyle(String content, SpannableTextStyle[] textStyles) {
        if (!ArrayOrListUtil.isNullOrEmpty(textStyles)) {
            if (textStyles.length == 1) {
                return getTextWithSingleStyle(content, textStyles[0]);
            } else {
                SpannableStringBuilder stringBuilder = new SpannableStringBuilder(content);
                for (SpannableTextStyle textStyle : textStyles) {
                    stringBuilder = getTextWithSingleStyle(content, textStyle, stringBuilder);
                }
                return stringBuilder;
            }
        }
        return content;
    }

    /**
     * 当spannable属性只有一个的时候使用SpannableString
     */
    private static SpannableString getSingleSpanContent(String content, String matcherString,
                                                        SpannableString spannableString, ParcelableSpan span) {
        Pattern p = Pattern.compile(matcherString);
        Matcher m = p.matcher(content);

        while (m.find()) {
            int startIndex = m.start();
            int endIndex = m.end();
            spannableString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return spannableString;
    }

    /**
     * 当spannable属性有多个的时候，使用SpannableStringBuilder
     */
    private static SpannableStringBuilder getMultiSpanContent(String content, String matcherString,
                                                              SpannableStringBuilder stringBuilder, ParcelableSpan[] spans) {
        Pattern p = Pattern.compile(matcherString);
        Matcher m = p.matcher(content);

        while (m.find()) {
            int startIndex = m.start();
            int endIndex = m.end();
            for (ParcelableSpan span : spans) {
                stringBuilder.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
        }
        return stringBuilder;
    }

    /**
     * 设置匹配文字的背景色
     */
    public static BackgroundColorSpan getBackgroundColorSpan(Context context, @ColorRes int colorResId) {
        return getBackgroundColorSpan(ContextCompat.getColor(context, colorResId));
    }

    public static BackgroundColorSpan getBackgroundColorSpan(@ColorInt int colorValue) {
        return new BackgroundColorSpan(colorValue);
    }

    /**
     * 设置匹配的文本颜色。
     */
    public static ForegroundColorSpan getForegroundColorSpan(Context context, @ColorRes int colorResId) {
        return getForegroundColorSpan(ContextCompat.getColor(context, colorResId));
    }

    public static ForegroundColorSpan getForegroundColorSpan(@ColorInt int colorValue) {
        return new ForegroundColorSpan(colorValue);
    }

    /**
     * 设置修饰效果，如：模糊
     *
     * @param radius 修饰的圆角半径
     * @param style  如：BlurMaskFilter.SOLID
     */
    public static MaskFilterSpan getMaskFilterSpan(float radius, BlurMaskFilter.Blur style) {
        return new MaskFilterSpan(new BlurMaskFilter(radius, style));
    }

    /**
     * 删除线
     */
    public static StrikethroughSpan getStrikeThroughSpan() {
        return new StrikethroughSpan();
    }

    /**
     * 相当于占位符
     */
    public static SuggestionSpan getSuggestionSpan(Context context, String[] suggestions, int flags) {
        return new SuggestionSpan(context, suggestions, flags);
    }

    /**
     * 下划线
     */
    public static UnderlineSpan getUnderlineSpan() {
        return new UnderlineSpan();
    }

    /**
     * 设置图片，基于文本基线或底部对齐,如： DynamicDrawableSpan.ALIGN_BOTTOM
     */
    public static DynamicDrawableSpan getDynamicDrawableSpan(int verticalAlignment, final Drawable drawable) {
        return new DynamicDrawableSpan(verticalAlignment) {
            @Override
            public Drawable getDrawable() {
                return drawable;
            }
        };
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

    /**
     * 设置相对文字大小，如：0.5f-缩小，1.5f放大
     */
    public static RelativeSizeSpan getRelativeSizeSpan(float textRatioForSize) {
        return new RelativeSizeSpan(textRatioForSize);
    }

    /**
     * 设置绝对文字大小
     */
    public static AbsoluteSizeSpan getAbsoluteSizeSpan(int textSizeValue) {
        return new AbsoluteSizeSpan(textSizeValue);
    }

    /**
     * 基于x轴缩放
     *
     * @param proportion Creates a {@link ScaleXSpan} based on a proportion. Values > 1.0 will stretch the text wider.
     *                   Values < 1.0 will stretch the text narrower.
     */
    public static ScaleXSpan getScaleXSpan(@FloatRange(from = 0) float proportion) {
        return new ScaleXSpan(proportion);
    }

    /**
     * 字体样式：粗体、斜体等
     */
    public static StyleSpan getStyleSpan(@StyleRes int styleRes) {
        return new StyleSpan(styleRes);
    }

    /**
     * 将文字设置成上标
     */
    public static SuperscriptSpan getSuperscriptSpan() {
        return new SuperscriptSpan();
    }

    /**
     * 将文字设置成下标
     */
    public static SubscriptSpan getSubscriptSpan() {
        return new SubscriptSpan();
    }

    /**
     * 文本外貌（包括字体、大小、样式和颜色）
     */
    public static TextAppearanceSpan getTextAppearanceSpan(Context context, @StyleRes int appearanceStyleRes) {
        return new TextAppearanceSpan(context, appearanceStyleRes);
    }

    /**
     * 文本字体
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static TypefaceSpan getTypefaceSpan(Typeface typeface) {
        return new TypefaceSpan(typeface);
    }

    /**
     * 文本超链接
     */
    public static URLSpan getURLSpan(String url) {
        return new URLSpan(url);
    }

    /**
     * 点击事件
     */
    public static ClickableSpan getClickableSpan(final View.OnClickListener listener) {
        return new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if (listener != null) {
                    listener.onClick(widget);
                }
            }
        };
    }
}
