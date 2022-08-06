package com.flypple.fpwanandroid.module.account;

import android.text.TextUtils;

import com.flypple.fpwanandroid.core.network.BaseHttpResult;
import com.flypple.fpwanandroid.core.network.DefaultObserver;
import com.flypple.fpwanandroid.core.network.ObservableUtils;
import com.flypple.fpwanandroid.core.network.exception.BaseHttpException;
import com.flypple.fpwanandroid.model.UserInfo;
import com.flypple.fpwanandroid.model.event.LogoutEvent;
import com.flypple.fpwanandroid.network.Api;
import com.flypple.fpwanandroid.network.NetworkUtils;
import com.flypple.fpwanandroid.network.params.LoginBody;
import com.flypple.fpwanandroid.core.utils.SPUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Created by qiqinglin on 2022/7/17
 */
public class AccountManager {

    private static final String ACCOUNT_SP_NAME = "account_sp";
    private static final String KEY_LOGIN_USER = "login_user";
    private static volatile AccountManager instance = null;
    private final SPUtils mSpUtils;

    private AccountManager() {
        mSpUtils = SPUtils.newInstance(ACCOUNT_SP_NAME);
        String userInfoStr = mSpUtils.get(KEY_LOGIN_USER, "");
        if (!TextUtils.isEmpty(userInfoStr)) {
            mLoginUser = new Gson().fromJson(userInfoStr, UserInfo.class);
        }
    }

    public static AccountManager getInstance() {
        if (instance == null) {
            synchronized (AccountManager.class) {
                if (instance == null) {
                    instance = new AccountManager();
                }
            }
        }

        return instance;
    }

    private UserInfo mLoginUser;

    public UserInfo getUser() {
        return mLoginUser;
    }

    public Disposable login(LoginBody loginBody, @NonNull LoginCallBack callBack) {
        Api service = NetworkUtils.getService();
        Observable<BaseHttpResult<UserInfo>> loginObservable = service.login(loginBody.create());
        Disposable disposable = ObservableUtils.execute(loginObservable, new DefaultObserver.Callback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo data) {
                if (data != null) {
                    mLoginUser = data;
                    mSpUtils.save(KEY_LOGIN_USER, new Gson().toJson(mLoginUser));
                    callBack.onSuccess(mLoginUser);
                } else {
                    callBack.onFailed("data is null!");
                }
            }

            @Override
            public void onFailed(BaseHttpException e) {
                callBack.onFailed(e.getMessage());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                callBack.onFinish();
            }
        });
        return disposable;
    }

    public void logout() {
        mSpUtils.remove(KEY_LOGIN_USER);
        mLoginUser = null;
        EventBus.getDefault().post(new LogoutEvent());
    }

    public interface LoginCallBack {
        void onSuccess(UserInfo userInfo);

        void onFailed(String errorMsg);

        void onFinish();
    }
}
