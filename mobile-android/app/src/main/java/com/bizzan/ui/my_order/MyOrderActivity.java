package com.bizzan.ui.my_order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.adapter.PagerAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ibDetail)
    TextView ibDetail;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vpPager)
    ViewPager vpPager;
    @BindView(R.id.llContainer)
    LinearLayout llContainer;
    @BindArray(R.array.order_status)
    String[] status;
    @BindView(R.id.view_back)
    View view_back;
    private List<BaseFragment> fragments = new ArrayList<>();
    private int type;

    public static void actionStart(Context context,int type) {
        Intent intent = new Intent(context, MyOrderActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            displayLoadingPopup();
            finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
//        if (MyApplication.getApp().isLogin()) {

//        } else {
//            showToLoginView();
//        }
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLoadingPopup();
                finish();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLoadingPopup();
                finish();
            }
        });
//        WonderfulLogUtils.logi("getToken",getToken());
        setFragments();
        type = getIntent().getIntExtra("type",0);
        vpPager.setCurrentItem(type);
        vpPager.setOffscreenPageLimit(1);
    }

    private void setFragments() {
        addFragments();
        vpPager.setOffscreenPageLimit(fragments.size() - 1);
        vpPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments, Arrays.asList(status)));
        tab.setupWithViewPager(vpPager);
    }

    @Override
    protected ViewGroup getEmptyView() {
        return llContainer;
    }

    private void addFragments() {
        fragments.add(OrderFragment.getInstance(OrderFragment.Status.UNPAID));
        fragments.add(OrderFragment.getInstance(OrderFragment.Status.PAID));
        fragments.add(OrderFragment.getInstance(OrderFragment.Status.DONE));
        fragments.add(OrderFragment.getInstance(OrderFragment.Status.CANC));
        fragments.add(OrderFragment.getInstance(OrderFragment.Status.COMPLAINING));
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

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LoginActivity.RETURN_LOGIN:
                if (resultCode == Activity.RESULT_OK) {
                    hideToLoginView();
                    setFragments();
                }
                break;
        }
    }
}
