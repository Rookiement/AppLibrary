package com.base.library.utils.model;

import android.content.Context;
import android.text.ParcelableSpan;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.core.content.ContextCompat;

/**
 * @author reber
 * on 2020/10/10 17:17
 */
public class SpannableTextStyle {

    private String text;
    private int textColor;
    private int textSize;
    private ParcelableSpan[] spans;

    private SpannableTextStyle() {
    }

    public String getText() {
        return text;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public ParcelableSpan[] getSpans() {
        return spans;
    }

    public static class Builder {

        private String content;
        private int colorResId;
        private int textSizeResId;
        private ParcelableSpan[] spans;

        private final SpannableTextStyle mSpannableTextStyle;

        public Builder() {
            this.mSpannableTextStyle = new SpannableTextStyle();
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setColorResId(@ColorRes int colorResId) {
            this.colorResId = colorResId;
            return this;
        }

        public Builder setTextSizeResId(@DimenRes int textSizeResId) {
            this.textSizeResId = textSizeResId;
            return this;
        }

        public void setSpans(ParcelableSpan[] spans) {
            this.spans = spans;
        }

        public SpannableTextStyle build(Context context) {
            this.mSpannableTextStyle.text = content;
            if (colorResId != 0) {
                this.mSpannableTextStyle.textColor = ContextCompat.getColor(context, colorResId);
            }
            if (textSizeResId != 0) {
                this.mSpannableTextStyle.textSize = context.getResources().getDimensionPixelSize(textSizeResId);
            }
            this.mSpannableTextStyle.spans = spans;
            return this.mSpannableTextStyle;
        }
    }
}
