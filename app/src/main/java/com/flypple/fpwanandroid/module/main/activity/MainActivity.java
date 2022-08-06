package com.flypple.fpwanandroid.module.main.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import com.flypple.fpwanandroid.R;
import com.flypple.fpwanandroid.model.UserInfo;
import com.flypple.fpwanandroid.model.event.LogoutEvent;
import com.flypple.fpwanandroid.module.account.AccountManager;
import com.flypple.fpwanandroid.module.account.activity.LoginActivity;
import com.flypple.fpwanandroid.core.base.BaseEmptyPresenter;
import com.flypple.fpwanandroid.core.base.activity.BaseActivity;
import com.flypple.fpwanandroid.module.home.HomeFragment;
import com.flypple.fpwanandroid.module.knowledge.KnowledgeSystemFragment;
import com.flypple.fpwanandroid.module.mine.MineFragment;
import com.flypple.fpwanandroid.module.profile.ProfileActivity;
import com.flypple.fpwanandroid.module.square.SquareFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.ArraySet;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by qiqinglin on 2022/7/16
 */
public class MainActivity extends BaseActivity<BaseEmptyPresenter> {

    private Toolbar mToolBar;
    private View mToLoginLayout;
    private View mButtonToLogin;
    private View mTabHome;
    private ActivityResultLauncher<Intent> mForLoginResult;
    private List<Integer> mFragmentIds = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private int currentIndex = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInit() {
        super.onInit();
        initView();

        mForLoginResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    updateForLoginUser();
                }
            }
        });
    }

    private void initView() {
        mToolBar = findViewById(R.id.action_bar);
        mToolBar.inflateMenu(R.menu.menu_main);
        mToolBar.setTitle("首页");
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        UserInfo user = AccountManager.getInstance().getUser();
                        if (user == null) {
                            toLogin();
                        } else {
                            toProfile();
                        }
                        break;
                }
                return true;
            }
        });

        mToLoginLayout = findViewById(R.id.to_login_layout);
        mButtonToLogin = findViewById(R.id.btn_to_login);
        mButtonToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });

        mTabHome = findViewById(R.id.tab_home);

        initNavigation();

        updateForLoginUser();
    }

    private void initNavigation() {
        // 创建fragments
        mFragmentIds.add(R.id.bottom_navigation_home);
        mFragmentIds.add(R.id.bottom_navigation_square);
        mFragmentIds.add(R.id.bottom_navigation_knowledge_system);
        mFragmentIds.add(R.id.bottom_navigation_mine);

        mFragments.add(HomeFragment.newInstance());
        mFragments.add(SquareFragment.newInstance());
        mFragments.add(KnowledgeSystemFragment.newInstance());
        mFragments.add(MineFragment.newInstance());

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                int index = mFragmentIds.indexOf(itemId);
                MainActivity.this.onNavigationItemSelected(index);
                return true;
            }
        });
        onNavigationItemSelected(0);
    }

    private void onNavigationItemSelected(int index) {
        if (index < 0 || index > mFragments.size() - 1) {
            return;
        }
        if (currentIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = mFragments.get(index);

        if (fragment == null) {
            return;
        }
        if (!hasFragment(fragmentManager, fragment)) {
            transaction.add(R.id.main_tab_fragment_container, fragment, fragment.getClass().getSimpleName());
        }
        transaction.show(fragment);

        if (currentIndex >= 0) {
            Fragment currentFragment = mFragments.get(currentIndex);
            if (hasFragment(fragmentManager, currentFragment)) {
                transaction.hide(currentFragment);
            }
        }

        currentIndex = index;
        transaction.commit();
    }

    private boolean hasFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName());
        return fragmentByTag != null;
    }

    private void updateForLoginUser() {
        UserInfo user = AccountManager.getInstance().getUser();
        if (user != null) {
            mTabHome.setVisibility(VISIBLE);
            mToLoginLayout.setVisibility(GONE);
        } else {
            mTabHome.setVisibility(GONE);
            mToLoginLayout.setVisibility(VISIBLE);
        }
    }

    private void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        mForLoginResult.launch(intent);
    }



    private void toProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogout(LogoutEvent logoutEvent) {
        updateForLoginUser();
    }
}
