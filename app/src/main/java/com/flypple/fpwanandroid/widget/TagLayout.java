package com.flypple.fpwanandroid.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.flypple.fpwanandroid.R;
import com.flypple.fpwanandroid.model.ArticleTag;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * Created by qiqinglin on 2022/8/5
 */
public class TagLayout extends LinearLayout {
    public TagLayout(Context context) {
        this(context, null);
    }

    public TagLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTags(List<ArticleTag> tags) {
        Context context = getContext();
        if (context == null) {
            return;
        }

        removeAllViews();

        if (CollectionUtils.isEmpty(tags)) {
            return;
        }

        for (ArticleTag tag : tags) {
            String tagName = tag.getName();
            int type = tag.getType();
            TextView textView = new TextView(context);
            textView.setText(tagName);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            int paddingTop = ConvertUtils.dp2px(3);
            int paddingLeft = ConvertUtils.dp2px(4);
            textView.setPadding(paddingLeft, paddingTop, paddingLeft, paddingTop);
            Drawable drawable;
            int color;
            switch (type) {
                case ArticleTag.TAG_TYPE_TOP:
                case ArticleTag.TAG_TYPE_NEW:
                    drawable = ContextCompat.getDrawable(context, R.drawable.tag_background_red);
                    color = ContextCompat.getColor(context, R.color.tag_stroke_color_red);
                    break;
                default:
                    drawable = ContextCompat.getDrawable(context, R.drawable.tag_background_blue);
                    color = ContextCompat.getColor(context, R.color.tag_stroke_color_blue);
                    break;
            }
            textView.setBackground(drawable);
            textView.setTextColor(color);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (getChildCount() > 0) {
                layoutParams.leftMargin = ConvertUtils.dp2px(8);
            }
            textView.setLayoutParams(layoutParams);
            addView(textView);
        }
    }
}
