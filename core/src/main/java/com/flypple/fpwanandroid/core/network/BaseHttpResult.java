package com.flypple.fpwanandroid.core.network;


import com.flypple.fpwanandroid.core.base.BaseData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by qiqinglin on 2022/7/17
 */
public class BaseHttpResult<T> extends BaseData {

    public static final int CODE_SUCCESS = 0;

    @SerializedName("data")
    private T data;

    @SerializedName("errorCode")
    private int errorCode;

    @SerializedName("errorMsg")
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
