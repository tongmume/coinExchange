package com.bizzan.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import com.bizzan.R;
import com.bizzan.entity.OptionIconBean;

/**
 * data: 2020/10/12.
 */
public class OptionIconAdapter extends BaseQuickAdapter<OptionIconBean.DataBean.ContentBean, BaseViewHolder> {
    public OptionIconAdapter(int layoutResId, @Nullable List<OptionIconBean.DataBean.ContentBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, OptionIconBean.DataBean.ContentBean item) {
        if (item.getResult().equals("WIN")) {//涨
            helper.setBackgroundRes(R.id.ll_backgrount, R.drawable.shape_option_icon_up)
                    .setImageResource(R.id.iv_change, R.drawable.icon_up);
        } else if (item.getResult().equals("LOSE")) {//跌
            helper.setBackgroundRes(R.id.ll_backgrount, R.drawable.shape_option_icon_down)
                    .setImageResource(R.id.iv_change, R.drawable.icon_down);
        }
        helper.addOnClickListener(R.id.ll_backgrount);
    }

    @Override
    public void addData(int position, @NonNull OptionIconBean.DataBean.ContentBean data) {
        super.addData(position, data);
    }
}
