package com.base.library.network.delegate;

import com.base.library.network.model.AppException;

/**
 * @author reber
 * on 2020/9/30 14:54
 */
public interface NetRequestCallBack<T> {

    void onNetWorkCallBack(@NetRequestState int networkState, T model, AppException exception);
}
