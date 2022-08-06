package com.flypple.fpwanandroid.model.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.CollectionUtils;
import com.flypple.fpwanandroid.R;
import com.flypple.fpwanandroid.core.base.adapter.BaseRecyclerAdapter;
import com.flypple.fpwanandroid.model.ArticleData;
import com.flypple.fpwanandroid.model.ArticleTag;
import com.flypple.fpwanandroid.widget.TagLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by qiqinglin on 2022/8/5
 *
 * 首页文章流的Adapter
 */
public class HomeAdapter extends BaseRecyclerAdapter<ArticleData, HomeAdapter.Holder> {
    public HomeAdapter(Context context, List<ArticleData> data) {
        super(context, data);
    }

    @Override
    public Holder createHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_article, parent, false);
        return new Holder(view);
    }

    @Override
    public void bindData(int position, Holder holder, ArticleData data) {
        ArticleData article = getItem(position);
        if (article == null) {
            return;
        }
        List<ArticleTag> tags = article.getTags();
        if (CollectionUtils.isEmpty(tags)) {
            holder.mTagLayout.setVisibility(View.GONE);
        } else {
            holder.mTagLayout.setVisibility(View.VISIBLE);
            holder.mTagLayout.setTags(tags);
        }
        String authorName = article.getAuthor();
        if (TextUtils.isEmpty(authorName)) {
            authorName = article.getShareUser();
        }
        holder.mTvAuthorName.setText(authorName);
        holder.mTvPublishTime.setText(article.getNiceDate());
        holder.mTvTitle.setText(article.getTitle());
        StringBuilder chapterText = new StringBuilder();
        if (!TextUtils.isEmpty(article.getChapterName())) {
            chapterText.append(article.getChapterName());
        }
        if (!TextUtils.isEmpty(article.getSuperChapterName())) {
            if (!TextUtils.isEmpty(chapterText)) {
                chapterText.append(" / ");
            }
            chapterText.append(article.getSuperChapterName());
        }
        holder.mTvChapter.setText(chapterText.toString());
    }

    public static class Holder extends RecyclerView.ViewHolder {

        private final TagLayout mTagLayout;
        private final TextView mTvAuthorName;
        private final TextView mTvPublishTime;
        private final TextView mTvTitle;
        private final TextView mTvChapter;
        private final ImageView mIvCollect;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mTagLayout = itemView.findViewById(R.id.tag_layout);
            mTvAuthorName = itemView.findViewById(R.id.tv_author_name);
            mTvPublishTime = itemView.findViewById(R.id.tv_publish_time);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mTvChapter = itemView.findViewById(R.id.tv_chapter);
            mIvCollect = itemView.findViewById(R.id.iv_collect);
        }
    }
}
