package com.flypple.fpwanandroid.model;

import com.flypple.fpwanandroid.core.base.BaseData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by qiqinglin on 2022/7/17
 */
public class ConfigInfo extends BaseData {

    @SerializedName("coinCount")
    private Integer coinCount;
    @SerializedName("level")
    private Integer level;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("rank")
    private String rank;
    @SerializedName("userId")
    private Integer userId;
    @SerializedName("username")
    private String username;
}
