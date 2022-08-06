package com.flypple.fpwanandroid.network.params;

/**
 * Created by qiqinglin on 2022/7/17
 */
public class LoginBody extends BaseBody {
    public LoginBody(String username, String password) {
        super();
        putParams("username", username);
        putParams("password", password);
    }
}
