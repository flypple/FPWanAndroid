package com.flypple.fpwanandroid.module.account.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flypple.fpwanandroid.R;
import com.flypple.fpwanandroid.model.UserInfo;
import com.flypple.fpwanandroid.module.account.AccountManager;
import com.flypple.fpwanandroid.network.params.LoginBody;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Created by qiqinglin on 2022/7/17
 */
public class LoginActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private EditText mEtUserName;
    private EditText mEtPassword;
    private Button mBtnLogin;

    private Disposable loginDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mToolBar = findViewById(R.id.action_bar);
        mToolBar.setTitle("登录");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        mEtUserName = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
        mBtnLogin = findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });
    }

    private void toLogin() {
        Editable usernameText = mEtUserName.getText();
        Editable passwordText = mEtPassword.getText();
        if (TextUtils.isEmpty(usernameText) || TextUtils.isEmpty(passwordText)) {
            alert("用户名和密码不能为空！");
            return;
        }


        if (loginDisposable == null) {
            LoginBody loginBody = new LoginBody(usernameText.toString(), passwordText.toString());
            loginDisposable = AccountManager.getInstance().login(loginBody, new AccountManager.LoginCallBack() {
                @Override
                public void onSuccess(UserInfo userInfo) {
                    setResult(RESULT_OK);
                    finish();
                }

                @Override
                public void onFailed(String errorMsg) {
                    alert(errorMsg);
                }

                @Override
                public void onFinish() {
                    loginDisposable = null;
                }
            });
        }
    }

    private void alert(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginDisposable != null) {
            if (!loginDisposable.isDisposed()) {
                loginDisposable.dispose();
            }
            loginDisposable = null;
        }
    }
}
