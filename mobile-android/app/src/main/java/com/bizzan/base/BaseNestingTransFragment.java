package com.bizzan.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Administrator on 2018/3/30.
 */

public abstract class BaseNestingTransFragment extends BaseTransFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) addFragments();
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void addFragments();

    protected abstract void recoveryFragments();

}
