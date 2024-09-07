package com.bizzan.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.entity.CurrentEntrust;
import com.bizzan.entity.OptionOrderHistoryBean;
import com.bizzan.utils.WonderfulDateUtils;

/**
 * desc  :当前委托的适配器
 */

public class OptionHistoryAdapter extends BaseQuickAdapter<OptionOrderHistoryBean.DataBean.ContentBean, BaseViewHolder> {

    public OptionHistoryAdapter(@Nullable List<OptionOrderHistoryBean.DataBean.ContentBean> data) {
        super(R.layout.adapter_option_history_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OptionOrderHistoryBean.DataBean.ContentBean item) {
        helper.setText(R.id.installments, mContext.getResources().getText(R.string.nos) + "" + item.getOptionNo() + "" + mContext.getResources().getText(R.string.expect))
                .setText(R.id.tv_time, WonderfulDateUtils.getFormatTime("yyyy-MM-dd HH:mm:ss", new Date(item.getCreateTime())))
                .setText(R.id.now_one, (int) item.getBetAmount() + "")
                .setText(R.id.now_four, getpoint(item.getRewardAmount()) + "")
                .setText(R.id.now_five, getpoint(item.getFee()) + "")
                .setText(R.id.now_six, getpoint(item.getWinFee()) + "");

        if (item.getDirection().equals("BUY")) {//涨
            helper.setText(R.id.now_two, mContext.getResources().getText(R.string.text_look_up))
                    .setTextColor(R.id.now_two, mContext.getResources().getColor(R.color.typeGreen));
        } else if (item.getDirection().equals("SELL")) {//跌
            helper.setText(R.id.now_two, mContext.getResources().getText(R.string.text_look_down))
                    .setTextColor(R.id.now_two, mContext.getResources().getColor(R.color.typeRed));
        }
        if (item.getResult().equals("WIN")) {//涨
            helper.setText(R.id.now_three, mContext.getResources().getText(R.string.success))
                    .setTextColor(R.id.now_three, mContext.getResources().getColor(R.color.typeGreen));
        } else if (item.getResult().equals("LOSE")) {//跌
            helper.setText(R.id.now_three, mContext.getResources().getText(R.string.fail))
                    .setTextColor(R.id.now_three, mContext.getResources().getColor(R.color.typeRed));
        }

    }


    public String getpoint(double data) {
        String format = new DecimalFormat("#").format(data);
        BigDecimal bg = new BigDecimal(format);
        String s = bg.setScale(0, BigDecimal.ROUND_DOWN).toPlainString();
        return s;
    }
}
