package com.bizzan.base;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import com.bizzan.R;

/**
 * Created by Administrator on 2018/1/9.
 */

public abstract class BaseTransFragmentActivity extends BaseActivity {
    protected List<BaseTransFragment> fragments = new ArrayList<>();
    protected BaseFragment currentFragment;

    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) initFragments();
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
    }

    protected abstract void recoverFragment();

    protected abstract void initFragments();

    public abstract int getContainerId();

    public void addFragment(BaseTransFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(getContainerId(), fragment, fragment.getmTag()).commit();
    }

    public void removeFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        fragments.remove(fragment);
    }

    protected void showFragment(BaseTransFragment fragment) {
        if (currentFragment == fragment) return;
        currentFragment = fragment;
        hideFragments();
        if (!fragment.isAdded()) addFragment(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.setTransition(FragmentTransaction.TRANSIT_UNSET);
        transaction.show(fragment).commit();
    }

    protected void hideFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (!fragments.get(i).isHidden() && currentFragment != fragments.get(i)) {
//                transaction.setTransition(FragmentTransaction.TRANSIT_UNSET);
                transaction.hide(fragments.get(i));
            }
        }
        transaction.commit();
    }

    protected void hideFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

//    notice: 下面两个方法用于平板开发框架搭建 一个按钮对应多个界面 且有记录顺序  详见速分期开发
//    public abstract void switchFragment(int layoutId, String tag);
//    public abstract void switchButton(Button btn);
//
}
