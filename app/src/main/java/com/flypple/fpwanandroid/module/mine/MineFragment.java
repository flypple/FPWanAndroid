package com.flypple.fpwanandroid.module.mine;

import android.os.Bundle;

import com.flypple.fpwanandroid.R;
import com.flypple.fpwanandroid.core.base.BaseEmptyPresenter;
import com.flypple.fpwanandroid.core.base.fragment.BaseFragment;

/**
 * Created by qiqinglin on 2022/7/23
 */
public class MineFragment extends BaseFragment<BaseEmptyPresenter> {

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }
}
