package com.flypple.fpwanandroid.model;

import com.flypple.fpwanandroid.core.base.BaseData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by qiqinglin on 2022/8/5
 */
public class ArticleTag extends BaseData {

    public static final int TAG_TYPE_DEFAULT = 0;
    public static final int TAG_TYPE_TOP = 1;
    public static final int TAG_TYPE_NEW = 2;

    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
