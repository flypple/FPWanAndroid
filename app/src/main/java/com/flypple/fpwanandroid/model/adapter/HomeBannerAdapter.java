package com.flypple.fpwanandroid.model.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flypple.fpwanandroid.model.BannerItemData;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by qiqinglin on 2022/8/6
 *
 * 首页Banner的Adapter
 */
public class HomeBannerAdapter extends BannerAdapter<BannerItemData, HomeBannerAdapter.ViewHolder> {

    public HomeBannerAdapter(List<BannerItemData> data) {
        super(data);
    }

    @Override
    public ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindView(ViewHolder holder, BannerItemData data, int position, int size) {
        String url = data.getImagePath();
        if (holder.itemView instanceof ImageView) {
            ImageView imageView = (ImageView) holder.itemView;
            if (!TextUtils.isEmpty(url)) {
                Glide.with(imageView.getContext())
                        .load(url)
                        .apply(new RequestOptions().centerCrop())
                        .into(imageView);
            }
        }
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
