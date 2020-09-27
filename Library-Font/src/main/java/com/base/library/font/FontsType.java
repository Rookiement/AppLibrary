package com.base.library.font;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author reber
 */
@IntDef({
        FontsType.ROBOTO_REGULAR,
        FontsType.ROBOTO_ITALIC,
        FontsType.ROBOTO_BOLD
})
//Only judge in parameters, it will be discarded after compilation
@Retention(RetentionPolicy.SOURCE)
public @interface FontsType {

    int ROBOTO_REGULAR = 0;
    int ROBOTO_ITALIC = 1;
    int ROBOTO_BOLD = 2;
}
