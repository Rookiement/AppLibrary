package com.base.library.network.delegate;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author reber
 * on 2020/9/30 14:55
 */
@IntDef({
        NetWorkState.EMPTY,
        NetWorkState.SUCCESS,
        NetWorkState.FAILURE
})
@Retention(RetentionPolicy.SOURCE)
public @interface NetWorkState {

    int EMPTY = 0;
    int SUCCESS = 1;
    int FAILURE = 2;
}
