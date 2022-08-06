package com.flypple.fpwanandroid.network;

import com.flypple.fpwanandroid.core.network.BaseHttpResult;
import com.flypple.fpwanandroid.model.ArticleData;
import com.flypple.fpwanandroid.model.ArticleResultData;
import com.flypple.fpwanandroid.model.BannerItemData;
import com.flypple.fpwanandroid.model.UserInfo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by qiqinglin on 2022/7/17
 */
public interface Api {

    @POST("user/login")
    Observable<BaseHttpResult<UserInfo>> login(@Body RequestBody requestBody);

    @GET("article/top/json")
    Observable<BaseHttpResult<List<ArticleData>>> getHomeTopArticleList();

    @GET("article/list/{pageNum}/json")
    Observable<BaseHttpResult<ArticleResultData>> getHomeArticleList(@Path("pageNum") int pageNum);

    @GET("banner/json")
    Observable<BaseHttpResult<List<BannerItemData>>> getBanner();
}
