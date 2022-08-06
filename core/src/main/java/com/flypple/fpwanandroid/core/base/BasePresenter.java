package com.flypple.fpwanandroid.core.base;

/**
 * 用于BaseEmptyPresenter
 *
 * Created by flypple on 2018/8/17.
 */

public class BasePresenter<T extends BaseContract.View, E extends BaseContract.Model> {
    protected T view;
    protected E model;

    public void init(Object view, Object model) {
        this.view = (T) view;
        this.model = (E) model;
    }

    /**
     * 当onCreate或onCreateView方法执行完毕将会调用
     */
    public void onCreate() {
    }

    /**
     * 当onDestroy或onDestroyView方法执行完毕将会调用
     */
    public void onDestroy() {
        view = null;
        model = null;
    }

    /**
     * view是否还存在
     */
    public boolean viewIsExist() {
        return view != null;
    }
}
