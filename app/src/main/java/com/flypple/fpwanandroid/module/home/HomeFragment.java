package com.flypple.fpwanandroid.module.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.flypple.fpwanandroid.R;
import com.flypple.fpwanandroid.core.base.BaseContract;
import com.flypple.fpwanandroid.core.base.BaseEmptyPresenter;
import com.flypple.fpwanandroid.core.base.adapter.HeaderRecyclerAdapter;
import com.flypple.fpwanandroid.core.base.fragment.BaseFragment;
import com.flypple.fpwanandroid.core.network.BaseHttpResult;
import com.flypple.fpwanandroid.core.network.DefaultObserver;
import com.flypple.fpwanandroid.core.network.ObservableUtils;
import com.flypple.fpwanandroid.core.network.exception.BaseHttpException;
import com.flypple.fpwanandroid.model.ArticleData;
import com.flypple.fpwanandroid.model.ArticleResultData;
import com.flypple.fpwanandroid.model.ArticleTag;
import com.flypple.fpwanandroid.model.BannerItemData;
import com.flypple.fpwanandroid.model.adapter.HomeAdapter;
import com.flypple.fpwanandroid.model.adapter.HomeBannerAdapter;
import com.flypple.fpwanandroid.module.home.model.HomeFragmentViewModel;
import com.flypple.fpwanandroid.network.Api;
import com.flypple.fpwanandroid.network.NetworkUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;

/**
 * Created by qiqinglin on 2022/7/23
 */
public class HomeFragment extends BaseFragment<BaseEmptyPresenter> implements BaseContract.View {

    private List<ArticleData> mData;
    private List<BannerItemData> mBannerDataList;

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private int currentPage = 0;
    private HeaderRecyclerAdapter<ArticleData> mAdapter;
    private Banner<BannerItemData, HomeBannerAdapter> mBanner;
    private HomeBannerAdapter mHomeBannerAdapter;
    private HomeFragmentViewModel mViewModel;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInit() {
        super.onInit();
        initView();
        initData();
    }

    private void initView() {
        View rootView = getRootView();
        if (rootView == null) {
            return;
        }
        mData = new ArrayList<>();
        mBannerDataList = new ArrayList<>();
        HomeAdapter homeAdapter = new HomeAdapter(getContext(), mData);
        mAdapter = new HeaderRecyclerAdapter<>(homeAdapter);
        mRefreshLayout = rootView.findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
            }
        });

        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        // 添加分割线
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_line, null));
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setAdapter(mAdapter);

        // 头部Banner
        mBanner = new Banner<>(getContext());
        mHomeBannerAdapter = new HomeBannerAdapter(mBannerDataList);
        mBanner.setAdapter(mHomeBannerAdapter);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(200));
        mBanner.setLayoutParams(layoutParams);
        mBanner.setIndicator(new CircleIndicator(getContext()))
                .getIndicatorConfig()
                .setNormalColor(ResourcesCompat.getColor(getResources(), R.color.color_black_a13, null))
                .setSelectedColor(ResourcesCompat.getColor(getResources(), R.color.color_black_a40, null))
                .setNormalWidth(ConvertUtils.dp2px(6))
                .setSelectedWidth(ConvertUtils.dp2px(6));
        mAdapter.addHeaderView(mBanner);
    }

    private void initData() {
        mViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        mViewModel.getBannerListLiveData().observe(this, new Observer<List<BannerItemData>>() {
            @Override
            public void onChanged(List<BannerItemData> bannerItemData) {
                if (CollectionUtils.isEmpty(bannerItemData)) {
                    return;
                }
                // 更新Banner UI
                mBanner.setDatas(bannerItemData);
            }
        });
        mViewModel.getArticleListLiveData().observe(this, new Observer<List<ArticleData>>() {
            @Override
            public void onChanged(List<ArticleData> articleData) {
                if (CollectionUtils.isEmpty(articleData)) {
                    return;
                }
                mData.clear();
                mData.addAll(articleData);
                mAdapter.notifyDataSetChanged();
            }
        });
        if (mViewModel.isFirstLoad()) {
            mViewModel.setFirstLoad(false);
            refreshData();
        }
    }

    private void refreshData() {
        // 刷新数据时，将页码归零
        currentPage = 0;

        Api service = NetworkUtils.getService();
        // 请求banner数据
        ObservableUtils.execute(service.getBanner(), new DefaultObserver.Callback<List<BannerItemData>>() {
            @Override
            public void onSuccess(List<BannerItemData> bannerItemData) {
                // 更新Banner数据
                mViewModel.setBannerListLiveData(bannerItemData);
            }

            @Override
            public void onFailed(BaseHttpException e) {
                LogUtils.d(e.getMessage());
            }
        });

        // 请求文章列表数据
        Observable<BaseHttpResult<ArticleResultData>> zip = Observable.zip(service.getHomeTopArticleList(), service.getHomeArticleList(currentPage), new BiFunction<BaseHttpResult<List<ArticleData>>, BaseHttpResult<ArticleResultData>, BaseHttpResult<ArticleResultData>>() {
            @Override
            public BaseHttpResult<ArticleResultData> apply(BaseHttpResult<List<ArticleData>> topArticleResult, BaseHttpResult<ArticleResultData> articleResult) throws Throwable {
                List<ArticleData> topArticle = topArticleResult.getData();
                ArticleResultData articleResultData = articleResult.getData();
                if (articleResultData != null) {
                    List<ArticleData> articleList = articleResultData.getDatas();
                    if (articleList == null) {
                        // 如果文章列表为空，则赋值一个空列表
                        articleList = new ArrayList<>();
                        articleResultData.setDatas(articleList);
                    }
                    // 如果置顶文章不为空，则处理置顶文章
                    if (topArticle != null && !CollectionUtils.isEmpty(topArticle)) {
                        for (ArticleData articleData : topArticle) {
                            // 设置置顶标识
                            articleData.setTop("1");
                            // 添加置顶标签
                            List<ArticleTag> tags = articleData.getTags();
                            if (tags == null) {
                                tags = new ArrayList<>();
                                articleData.setTags(tags);
                            }
                            ArticleTag articleTag = new ArticleTag();
                            articleTag.setName("置顶");
                            articleTag.setType(ArticleTag.TAG_TYPE_TOP);
                            tags.add(0, articleTag);
                        }
                        List<ArticleData> datas = articleList;
                        datas.addAll(0, topArticle);
                    }
                }
                return articleResult;
            }
        });
        ObservableUtils.execute(zip, new DefaultObserver.Callback<ArticleResultData>() {
            @Override
            public void onSuccess(ArticleResultData articleResultData) {
                List<ArticleData> articleList = articleResultData.getDatas();
                if (!CollectionUtils.isEmpty(articleList)) {
                    // 刷新数据后记得清空之前的数据
//                    mData.clear();

                    // 给24小时内的文章加标签
                    handleNewArticle(articleList);
                    // 记录页码
                    currentPage = articleResultData.getCurPage();

                    mViewModel.setArticleListLiveData(articleList);
                }
                mRefreshLayout.finishRefresh(true);
            }

            @Override
            public void onFailed(BaseHttpException e) {
                mRefreshLayout.finishRefresh(false);
            }
        });
    }

    private void loadMore() {
        ObservableUtils.execute(NetworkUtils.getService().getHomeArticleList(currentPage), new DefaultObserver.Callback<ArticleResultData>() {
            @Override
            public void onSuccess(ArticleResultData articleResultData) {
                List<ArticleData> articleList = articleResultData.getDatas();
                if (!CollectionUtils.isEmpty(articleList)) {
                    // 给24小时内的文章加标签
                    handleNewArticle(articleList);
                    // 记录页码
                    currentPage = articleResultData.getCurPage();

                    mViewModel.addArticleListLiveData(articleList);
                }
                mRefreshLayout.finishLoadMore(true);
            }

            @Override
            public void onFailed(BaseHttpException e) {
                mRefreshLayout.finishLoadMore(false);
            }
        });
    }

    private void handleNewArticle(List<ArticleData> articleList) {
        long currentTimeMillis = System.currentTimeMillis();
        long daysTimeMillis = 24 * 60 * 60 * 1000;
        // 给24小时内的文章加标签 "新"
        for (ArticleData articleData : articleList) {
            Long publishTime = articleData.getPublishTime();
            if (currentTimeMillis - publishTime < daysTimeMillis) {
                List<ArticleTag> tags = articleData.getTags();
                if (tags == null) {
                    tags = new ArrayList<>();
                    articleData.setTags(tags);
                }
                ArticleTag articleTag = new ArticleTag();
                articleTag.setName("新");
                articleTag.setType(ArticleTag.TAG_TYPE_NEW);
                tags.add(0, articleTag);
            }
        }
    }
}
