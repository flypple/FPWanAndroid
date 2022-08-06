package com.flypple.fpwanandroid.module.knowledge;

import android.os.Bundle;

import com.flypple.fpwanandroid.R;
import com.flypple.fpwanandroid.core.base.BaseEmptyPresenter;
import com.flypple.fpwanandroid.core.base.fragment.BaseFragment;

/**
 * Created by qiqinglin on 2022/7/23
 */
public class KnowledgeSystemFragment extends BaseFragment<BaseEmptyPresenter> {

    public static KnowledgeSystemFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeSystemFragment fragment = new KnowledgeSystemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_system;
    }
}
