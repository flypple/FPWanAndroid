package com.flypple.fpwanandroid.module.profile;

import android.view.View;
import android.widget.TextView;

import com.flypple.fpwanandroid.R;
import com.flypple.fpwanandroid.core.base.BaseEmptyPresenter;
import com.flypple.fpwanandroid.core.base.activity.BaseActivity;
import com.flypple.fpwanandroid.model.UserInfo;
import com.flypple.fpwanandroid.model.event.LogoutEvent;
import com.flypple.fpwanandroid.module.account.AccountManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by qiqinglin on 2022/8/5
 */
public class ProfileActivity extends BaseActivity<BaseEmptyPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onInit() {
        super.onInit();
        TextView tvUserName = findViewById(R.id.tv_user_name);
        UserInfo user = AccountManager.getInstance().getUser();
        if (user != null) {
            tvUserName.setText(user.getNickname());
        }

        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountManager.getInstance().logout();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogout(LogoutEvent logoutEvent) {
        finish();
    }
}
