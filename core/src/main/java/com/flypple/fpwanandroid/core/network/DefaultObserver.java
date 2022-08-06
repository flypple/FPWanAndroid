package com.flypple.fpwanandroid.core.network;

import com.flypple.fpwanandroid.core.network.exception.ApiException;
import com.flypple.fpwanandroid.core.network.exception.BaseHttpException;
import com.flypple.fpwanandroid.core.network.exception.DataException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

/**
 * Created by qiqinglin on 2022/7/24
 */
public class DefaultObserver<Data> extends DisposableObserver<BaseHttpResult<Data>> {

    private Callback<Data> mCallBack;

    public DefaultObserver(@NonNull DefaultObserver.Callback<Data> callBack) {
        mCallBack = callBack;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCallBack.onStart();
    }

    @Override
    public void onNext(@NonNull BaseHttpResult<Data> httpResult) {
        int code = httpResult.getErrorCode();
        String errorMsg = httpResult.getErrorMsg();
        Data data = httpResult.getData();

        if (code == BaseHttpResult.CODE_SUCCESS) {
            if (data == null) {
                DataException dataException = new DataException("errorCode = " + code + ", message = " + errorMsg);
                mCallBack.onFailed(dataException);
            } else {
                mCallBack.onSuccess(data);
            }
        } else {
            ApiException apiException = new ApiException("errorCode = " + code + ", message = " + errorMsg);
            mCallBack.onFailed(apiException);
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        BaseHttpException exception = new BaseHttpException(e);
        mCallBack.onFailed(exception);
    }

    @Override
    public void onComplete() {
        mCallBack.onFinish();
    }

    public abstract static class Callback<Data> {
        public void onStart(){ };
        public abstract void onSuccess(@NonNull Data data);
        public abstract void onFailed(BaseHttpException e);
        public void onFinish(){ };
    }
}
