package com.flypple.fpwanandroid.module.home.model;

import android.util.Log;

import com.flypple.fpwanandroid.model.ArticleData;
import com.flypple.fpwanandroid.model.BannerItemData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by qiqinglin on 2022/8/6
 */
public class HomeFragmentViewModel extends ViewModel {

    private static final String TAG = "HomeFragmentViewModel";

    private boolean mFirstLoad = true;
    private MutableLiveData<List<ArticleData>> articleListLiveData;
    private MutableLiveData<List<BannerItemData>> bannerListLiveData;

    public LiveData<List<ArticleData>> getArticleListLiveData() {
        if (articleListLiveData == null) {
            articleListLiveData = new MutableLiveData<>();
        }
        return articleListLiveData;
    }

    public boolean isFirstLoad() {
        return mFirstLoad;
    }

    public void setFirstLoad(boolean firstLoad) {
        mFirstLoad = firstLoad;
    }

    public LiveData<List<BannerItemData>> getBannerListLiveData() {
        if (bannerListLiveData == null) {
            bannerListLiveData = new MutableLiveData<>();
        }
        return bannerListLiveData;
    }

    public void setBannerListLiveData(List<BannerItemData> banners) {
        bannerListLiveData.setValue(banners);
    }

    public void setArticleListLiveData(List<ArticleData> articles) {
        articleListLiveData.setValue(articles);
    }

    public void addArticleListLiveData(List<ArticleData> articles) {
        List<ArticleData> currentList = articleListLiveData.getValue();
        if (currentList != null) {
            currentList.addAll(articles);
        } else {
            currentList = articles;
        }
        articleListLiveData.setValue(currentList);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
    }
}
