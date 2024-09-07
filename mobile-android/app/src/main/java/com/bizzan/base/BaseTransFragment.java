package com.bizzan.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public abstract class BaseTransFragment extends BaseFragment {
    private boolean isFirst = true;
    
    protected List<BaseFragment> fragments = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (savedInstanceState == null) {
//            super.onViewCreated(view, savedInstanceState);
//            addFragments();
//        } else {
//            super.onViewCreated(view, savedInstanceState);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && !isFirst && isNeedLoad) loadData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            if (immersionBar != null) initImmersionBar();
            if (!isNeedLoad) return;
            rootView.post(new Runnable() {
                @Override
                public void run() {
                    loadData();
                    isFirst = false;
                }
            });
        }
    }

    protected abstract String getmTag();

}
