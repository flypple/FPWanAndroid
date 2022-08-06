package com.flypple.fpwanandroid.model;

import com.flypple.fpwanandroid.core.base.BaseData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by qiqinglin on 2022/7/24
 */
public class BannerItemData extends BaseCardInfo {
    @SerializedName("desc")
    private String desc;
    @SerializedName("id")
    private Integer id;
    @SerializedName("imagePath")
    private String imagePath;
    @SerializedName("isVisible")
    private Integer isVisible;
    @SerializedName("order")
    private Integer order;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private Integer type;
    @SerializedName("url")
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
