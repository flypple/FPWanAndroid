package com.flypple.fpwanandroid.core.base.adapter;

import android.view.View;
import android.view.ViewGroup;


import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.annotations.NonNull;

/**
 * Created by qiqinglin on 2022/8/5
 *
 * 一个可以给RecyclerView扩展添加头部功能的Adapter（参考鸿洋大神方案）
 */
public class HeaderRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 100000;

    /**
     * 头部View的缓存列表
     */
    private final SparseArrayCompat<View> headers = new SparseArrayCompat<>();
    /**
     * 真正代理数据渲染的adapter
     */
    private RecyclerView.Adapter mInnerAdapter;

    public HeaderRecyclerAdapter(@NonNull RecyclerView.Adapter adapter) {
        super();
        mInnerAdapter = adapter;
    }

    @androidx.annotation.NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View headerView = headers.get(viewType);
        if (headerView != null) {
            return new ViewHolder(headerView);
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position)) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, getRealPosition(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return headers.keyAt(position);
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + headers.size();
    }

    /**
     * 获取真正的元素数量（数据item的数量）
     */
    public int getRealDataCount() {
        return mInnerAdapter.getItemCount();
    }

    /**
     * 该position是否位于头部
     */
    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    public void addHeaderView(View view) {
        headers.put(headers.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    /**
     * 清空HeaderView
     */
    public void removeAllHeader() {
        headers.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取HeaderView的数量
     * @return
     */
    public int getHeadersCount() {
        return headers.size();
    }

    /**
     * 根据在Adapter中的position获取数据列表中的position
     */
    private int getRealPosition(int position) {
        return position - getHeadersCount();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
