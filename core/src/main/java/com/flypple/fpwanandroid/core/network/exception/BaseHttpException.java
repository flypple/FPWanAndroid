package com.flypple.fpwanandroid.core.network.exception;

/**
 * Created by qiqinglin on 2022/7/24
 */
public class BaseHttpException extends Exception {
    public BaseHttpException() {
    }

    public BaseHttpException(String message) {
        super(message);
    }

    public BaseHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseHttpException(Throwable cause) {
        super(cause);
    }
}
