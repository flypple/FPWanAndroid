package com.flypple.fpwanandroid.core.network.exception;

/**
 * Created by qiqinglin on 2022/7/24
 */
public class DataException extends BaseHttpException {
    public DataException() {
    }

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataException(Throwable cause) {
        super(cause);
    }
}
