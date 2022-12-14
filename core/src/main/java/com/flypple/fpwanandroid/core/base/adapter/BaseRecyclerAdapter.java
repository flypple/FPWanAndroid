package com.flypple.fpwanandroid.core.base.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 封装普通单条目RecyclerView的Adapter基类，只需要继承后实现创建ViewHodler和绑定数据的操作即可
 * 泛型T：数据类型，泛型VH：ViewHodler类型
 * Created by flypple on 2018/8/21.
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected final Context mContext;
    protected List<T> mData;

    public BaseRecyclerAdapter(Context context, List<T> data) {
        mContext = context;
        mData = data == null ? new ArrayList<T>() : data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        bindData(position, holder, mData.get(position));
    }

    public abstract VH createHolder(ViewGroup parent, int viewType);

    public abstract void bindData(int position, VH holder, T t);

    /**
     * 更新数据
     */
    public void update(List<T> data) {
        mData.clear();
        addData(data);
    }

    /**
     * 添加数据
     */
    public void addData(List<T> data) {
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 获取数据
     */
    public List<T>getData(){
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public T getItem(int position) {
        if (position > mData.size()) {
            return null;
        }
        return mData.get(position);
    }
}
