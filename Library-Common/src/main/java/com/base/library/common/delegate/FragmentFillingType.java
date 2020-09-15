package com.base.library.common.delegate;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        FragmentFillingType.ADD,
        FragmentFillingType.REPLACE
})
@Retention(RetentionPolicy.SOURCE)
public @interface FragmentFillingType {
    int ADD = 0;
    int REPLACE = 1;
}
