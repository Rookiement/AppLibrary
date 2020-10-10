package com.base.library.network.model;

import androidx.annotation.StringRes;

/**
 * @author reber
 * on 2020/9/30 16:20
 */
public class AppException {

    private int errorCode;
    private String errorMessage;
    private int errorMessageResId;

    private AppException() {
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorMessageResId() {
        return errorMessageResId;
    }

    public static class Builder {

        private AppException mException;

        public Builder() {
            this.mException = new AppException();
        }

        public Builder setErrorCode(int errorCode) {
            this.mException.errorCode = errorCode;
            return this;
        }

        public Builder setErrorMessage(String errorMessage) {
            this.mException.errorMessage = errorMessage;
            return this;
        }

        public Builder setErrorMessageResId(@StringRes int errorMessageResId) {
            this.mException.errorMessageResId = errorMessageResId;
            return this;
        }

        public AppException build() {
            return mException;
        }
    }
}
