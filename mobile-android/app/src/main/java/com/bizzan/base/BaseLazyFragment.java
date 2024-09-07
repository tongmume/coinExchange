package com.bizzan.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * setUserVisibleHint  总是先于 onAttach等执行的
 */
public abstract class BaseLazyFragment extends BaseFragment {
    /**
     * 是否正在加载数据
     */
    protected boolean isLoad = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tryToLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        tryToLoadData();
    }

    private void tryToLoadData() {
        if (!isInit) return;
        if (!isNeedLoad) return;
        if (getUserVisibleHint()) {
            rootView.post(new Runnable() {
                @Override
                public void run() {
                    loadData();
                }
            });
            isLoad = true;
            if (isImmersionBarEnabled()) initImmersionBar();
        } else {
            if (isLoad) stopLoad();
        }
    }

    @Override
    public void onDestroyView() {
        isInit = false;
        isLoad = false;
        super.onDestroyView();
    }

    /**
     * 隐藏时停止 加载数据
     */
    protected void stopLoad() {
    }

}
