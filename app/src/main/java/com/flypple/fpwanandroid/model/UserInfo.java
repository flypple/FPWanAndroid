package com.flypple.fpwanandroid.model;

import com.flypple.fpwanandroid.core.base.BaseData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by qiqinglin on 2022/7/17
 */
public class UserInfo extends BaseData {

    /**
     * 是否管理员
     */
    @SerializedName("admin")
    private Boolean admin;

    /**
     * 硬币数？
     */
    @SerializedName("coinCount")
    private Integer coinCount;

    /**
     * 邮箱
     */
    @SerializedName("email")
    private String email;

    /**
     * 头像
     */
    @SerializedName("icon")
    private String icon;

    /**
     * 用户ID
     */
    @SerializedName("id")
    private Integer id;

    /**
     * 昵称
     */
    @SerializedName("nickname")
    private String nickname;

    /**
     * 密码
     */
    @SerializedName("password")
    private String password;

    /**
     * 对外名称？
     */
    @SerializedName("publicName")
    private String publicName;

    /**
     * token
     */
    @SerializedName("token")
    private String token;

    /**
     * 不清楚有什么用的类型
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 用户名
     */
    @SerializedName("username")
    private String username;

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
