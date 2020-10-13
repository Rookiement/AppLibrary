package com.base.library.utils.model;

import android.content.Context;
import android.text.style.CharacterStyle;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.core.content.ContextCompat;

/**
 * @author reber
 * on 2020/10/10 17:17
 */
public class StringFilter {

    private String text;
    private int textColor;
    private int textSize;
    private CharacterStyle[] characterStyles;

    private StringFilter() {
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

    public CharacterStyle[] getCharacterStyles() {
        return characterStyles;
    }

    public static class Builder {

        private String content;
        private int colorResId;
        private int textSizeResId;
        private CharacterStyle[] spanStyles;

        private final StringFilter mStringFilter;

        public Builder() {
            this.mStringFilter = new StringFilter();
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

        public void setSpanStyles(CharacterStyle[] spanStyles) {
            this.spanStyles = spanStyles;
        }

        public StringFilter build(Context context) {
            this.mStringFilter.text = content;
            if (colorResId != 0) {
                this.mStringFilter.textColor = ContextCompat.getColor(context, colorResId);
            }
            if (textSizeResId != 0) {
                this.mStringFilter.textSize = context.getResources().getDimensionPixelSize(textSizeResId);
            }
            this.mStringFilter.characterStyles = spanStyles;
            return this.mStringFilter;
        }
    }
}
