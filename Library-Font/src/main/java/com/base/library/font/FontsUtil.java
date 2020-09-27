package com.base.library.font;

import android.content.Context;
import android.graphics.Typeface;

import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

/**
 * @author reber
 * <p>
 * Provide the desired typeface for the TextView to use according to the name of the font,
 * maybe you need use typeface when you custom your textview or you need through cavas draw text
 */
public class FontsUtil {

    /**
     * @param context  application or activity
     * @param fontType such as FontType.ROBOTO_BOLD that you can
     * @see FontsType it contained all fonts type
     */
    public static Typeface getTypeFace(@NonNull Context context, @FontsType int fontType) {
        return ResourcesCompat.getFont(context, getFontResourceId(fontType));
    }

    /**
     * @param fontType /
     * @return the font resource id is from the path of res/font,before use the font,
     * you need to add font resource to this path
     * @see #getTypeFace(Context, int)
     */
    private static @FontRes
    int getFontResourceId(@FontsType int fontType) {
        switch (fontType) {
            case FontsType.ROBOTO_REGULAR:
                return R.font.roboto_regular;
            case FontsType.ROBOTO_ITALIC:
                return R.font.roboto_italic;
            case FontsType.ROBOTO_BOLD:
                return R.font.roboto_bold;
            default:
                return R.font.roboto_regular;
        }
    }
}
