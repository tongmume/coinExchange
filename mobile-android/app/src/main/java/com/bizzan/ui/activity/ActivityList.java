package com.bizzan.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;

/**
 * 创新实验室
 */
public class ActivityList extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.vpPager)
    ViewPager viewPager;
    @BindView(R.id.llTitle)
    View llTitle;
    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        findViewById(R.id.ibBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Fragment getItem(int position) {
                return ActivityFragment.newInstance(position - 1);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return getString(R.string.step_all);
                } else if (position == 1) {
                    return getString(R.string.step_prepare);
                } else if (position == 2) {
                    return getString(R.string.step_ongoing);
                } else if (position == 3) {
                    return getString(R.string.step_distributing);
                } else {
                    return getString(R.string.step_completed);
                }
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }
}