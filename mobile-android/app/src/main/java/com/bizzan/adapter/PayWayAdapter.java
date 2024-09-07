package com.bizzan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.entity.PayWay;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */
public class PayWayAdapter extends BaseQuickAdapter<PayWay, BaseViewHolder> {
    public PayWayAdapter(int layoutResId, @Nullable List<PayWay> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayWay item) {
        helper.setText(R.id.tvCoinName, item.getName());
        helper.setVisible(R.id.ivSellect, item.isSelect());

    }
}
