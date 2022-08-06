package com.flypple.fpwanandroid.core.network.exception;

/**
 * Created by qiqinglin on 2022/7/24
 */
public class ApiException extends BaseHttpException {
    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}
