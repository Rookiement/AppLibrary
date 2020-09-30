package com.base.library.network.delegate;

import com.base.library.network.model.NetworkException;

/**
 * @author reber
 * on 2020/9/30 14:54
 */
public interface NetWorkCallBack<T> {

    void onNetWorkCallBack(@NetWorkState int networkState, T model, NetworkException exception);
}
