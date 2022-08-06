package com.flypple.fpwanandroid.core.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by qiqinglin on 2022/7/22
 */
public class Utils {
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;

    public static void init(Application application) {
        context = application;
    }

    public static Context getAppContext() {
        if (context == null) {
            throw new RuntimeException("Utils尚未在Application中初始化");
        }
        return context;
    }
}
