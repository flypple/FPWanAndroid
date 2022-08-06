package com.flypple.fpwanandroid.common;

import android.app.Application;
import android.content.Context;

import com.flypple.fpwanandroid.core.utils.Utils;

/**
 * Created by qiqinglin on 2022/7/18
 */
public class MyApplication extends Application {

    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        Utils.init(this);
        com.blankj.utilcode.util.Utils.init(this);
    }

    public static Context getContext() {
        return mApplication;
    }
}
