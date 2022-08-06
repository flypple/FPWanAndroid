package com.flypple.fpwanandroid.model;

import com.flypple.fpwanandroid.core.base.BaseData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by qiqinglin on 2022/7/17
 */
public class ArticleResultData extends BaseData {

    @SerializedName("curPage")
    private Integer curPage;
    @SerializedName("datas")
    private List<ArticleData> datas;
    @SerializedName("offset")
    private Integer offset;
    @SerializedName("over")
    private Boolean over;
    @SerializedName("pageCount")
    private Integer pageCount;
    @SerializedName("size")
    private Integer size;
    @SerializedName("total")
    private Integer total;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public List<ArticleData> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleData> datas) {
        this.datas = datas;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean getOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
