package com.bizzan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bizzan.base.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private List<String> tabls;

    public PagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> tabls) {
        super(fm);
        this.fragments = fragments;
        this.tabls = tabls;
    }

    public PagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (tabls == null || tabls.size() == 0) return super.getPageTitle(position);
        return tabls.get(position);
    }
}
