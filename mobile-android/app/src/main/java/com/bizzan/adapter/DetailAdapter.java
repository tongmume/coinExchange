package com.bizzan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.utils.WonderfulDateUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * author: wuzongjie
 * time  : 2018/4/25 0025 16:47
 * desc  :
 */

public class DetailAdapter extends BaseQuickAdapter<EntrustHistory.DetailBean,BaseViewHolder>{

    public DetailAdapter( @Nullable List<EntrustHistory.DetailBean> data) {
        super(R.layout.layout_detail_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EntrustHistory.DetailBean item) {
        String[] times = WonderfulDateUtils.getFormatTime(null, new Date(item.getTime())).split(" ");
        helper.setText(R.id.item_time_one,times[0].substring(5,times[0].length()) + " " + times[1].substring(0,5));
        helper.setText(R.id.item_time_two,new BigDecimal(String.valueOf(item.getPrice())).stripTrailingZeros().toPlainString());
        helper.setText(R.id.item_time_three,new BigDecimal(String.valueOf(item.getAmount())).stripTrailingZeros().toPlainString());
        helper.setText(R.id.item_time_four,new BigDecimal(String.valueOf(item.getFee())).stripTrailingZeros().toPlainString());
    }
}
