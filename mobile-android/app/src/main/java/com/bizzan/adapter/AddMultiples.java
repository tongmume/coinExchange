package com.bizzan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import com.bizzan.R;
import com.bizzan.entity.Address;

public class AddMultiples  extends BaseQuickAdapter<String, BaseViewHolder> {

    private ItemSelectedCallBack mCallBack;

    public AddMultiples(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_add,item+"x");
        if (mCallBack != null) {
            mCallBack.convert(helper, helper.getLayoutPosition());
        }
    }

    public void setItemSelectedCallBack(ItemSelectedCallBack CallBack) {
        mCallBack = CallBack;
    }
    public interface ItemSelectedCallBack {
        void convert(BaseViewHolder holder, int position);
    }

}
