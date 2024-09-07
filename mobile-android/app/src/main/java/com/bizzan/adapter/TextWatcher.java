package com.bizzan.adapter;

import android.text.Editable;

/**
 * Created by wuzongjie on 2018/1/16.
 * TextWatcher
 */

public abstract class TextWatcher implements android.text.TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
