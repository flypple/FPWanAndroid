package com.flypple.fpwanandroid.model;

import com.flypple.fpwanandroid.core.base.BaseData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by qiqinglin on 2022/7/17
 */
public class ProfileResultData extends BaseData {
    /**
     * 配置信息
     */
    @SerializedName("configInfo")
    private ConfigInfo configInfo;

    /**
     * 配置信息
     */
    @SerializedName("userInfo")
    private UserInfo userInfo;

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
