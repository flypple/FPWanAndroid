package com.flypple.fpwanandroid.module.square;

import android.os.Bundle;

import com.flypple.fpwanandroid.R;
import com.flypple.fpwanandroid.core.base.BaseEmptyPresenter;
import com.flypple.fpwanandroid.core.base.fragment.BaseFragment;

/**
 * Created by qiqinglin on 2022/7/23
 */
public class SquareFragment extends BaseFragment<BaseEmptyPresenter> {

    public static SquareFragment newInstance() {
        Bundle args = new Bundle();
        SquareFragment fragment = new SquareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_square;
    }
}
