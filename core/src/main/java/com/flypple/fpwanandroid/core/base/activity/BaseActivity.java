/*
 * Copyright 2016 XuJiaji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flypple.fpwanandroid.core.base.activity;

import android.os.Bundle;

import com.flypple.fpwanandroid.core.base.BaseContract;
import com.flypple.fpwanandroid.core.base.BasePresenter;
import com.flypple.fpwanandroid.core.utils.GenericUtils;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 项目中Activity的基类
 * Created by flypple on 2018/8/18.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseContract.View {
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(getLayoutId());
        try{
            /*
             * 通过反射获取presenter对象，同时实现一起实例化了model对象，
             * 并初始化了presenter，实现了对view和model的持有
             */
            presenter = GenericUtils.newPresenter(this);
            if (presenter != null) {
                presenter.onCreate();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        EventBus.getDefault().register(this);
        onInit();
    }

    /**
     * 需要在SetContentView之前做的操作
     */
    protected void beforeSetContentView() {
    }

    /**
     * 在这里面进行初始化
     */
    protected void onInit() {}

    /**
     * 获取布局的id
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
        try {
            EventBus.getDefault().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
