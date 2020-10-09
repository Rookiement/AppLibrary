package com.base.library.network.delegate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author reber
 * on 2020/10/9 15:42
 */
@Retention(RetentionPolicy.SOURCE)
public @interface RequestType {

    int GET = 0;

    int POST = 1;

    int DELETE = 2;

    int PUT = 3;
}
