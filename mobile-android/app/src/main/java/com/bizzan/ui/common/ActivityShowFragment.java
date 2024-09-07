package com.bizzan.ui.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.bizzan.R;
import com.bizzan.base.BaseActivity;

/**
 * 单独打开fragment
 */
public class ActivityShowFragment extends BaseActivity {

    public static final int FRAGMENT_ADD = 1;
    public static final int FRAGMENT_REPLACE = 2;
    private static CallBack mcallback;

    public static void showFragment(Context context, CallBack callback) {
        mcallback = callback;
        Intent intent = new Intent(context, ActivityShowFragment.class);
        context.startActivity(intent);
    }


    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_show_fragment;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mcallback != null) {
            Fragment fragment = mcallback.getFragment();
            ft.add(R.id.container, fragment, fragment.getClass().getSimpleName());
            ft.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mcallback = null;
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


    public interface CallBack {
        Fragment getFragment();
    }
}
