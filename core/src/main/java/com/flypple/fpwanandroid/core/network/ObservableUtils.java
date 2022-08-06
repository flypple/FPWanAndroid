package com.flypple.fpwanandroid.core.network;

import com.flypple.fpwanandroid.core.base.BaseData;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by qiqinglin on 2022/7/24
 */
public class ObservableUtils {

    public static <Data> Disposable execute(
            @NonNull Observable<BaseHttpResult<Data>> observable,
            @NonNull DefaultObserver.Callback<Data> callback) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DefaultObserver<Data>(callback));
    }
}
