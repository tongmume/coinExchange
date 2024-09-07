package com.bizzan.adapter;

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
import com.bizzan.utils.WonderfulDateUtils;
import com.bizzan.utils.WonderfulToastUtils;

/**
 * desc  :当前委托的适配器
 */

public class CurrentEntrustAdapter extends BaseQuickAdapter<CurrentEntrust.ContentBean, BaseViewHolder> {

    public CurrentEntrustAdapter(@Nullable List<CurrentEntrust.ContentBean> data) {
        super(R.layout.adapter_current_entrust_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CurrentEntrust.ContentBean item) {
        //方向
        if ("OPEN".equals(item.getEntrustType())) {
            if ("BUY".equals(item.getDirection())) {
                helper.setText(R.id.now_type, mContext.getResources().getText(R.string.buyOpenmore)).setTextColor(R.id.now_type,
                        ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen));
            } else {
                helper.setText(R.id.now_type, mContext.getResources().getText(R.string.sellOpennull)).setTextColor(R.id.now_type,
                        ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
            }
        } else {
            if ("BUY".equals(item.getDirection())) {
                helper.setText(R.id.now_type, mContext.getResources().getText(R.string.buyflatnull)).setTextColor(R.id.now_type,
                        ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen));
            } else {
                helper.setText(R.id.now_type, mContext.getResources().getText(R.string.sellflatmore)).setTextColor(R.id.now_type,
                        ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
            }
        }
        //撤单
        helper.addOnClickListener(R.id.trust_back);
        //时间
        String[] times = WonderfulDateUtils.getFormatTime(null, new Date(item.getCreateTime())).split(" ");
        helper.setText(R.id.now_time, times[0].substring(5, times[0].length()) + " " + times[1].substring(0, 5));


        //委托类型
        if (item.getType().equals("LIMIT_PRICE")) {
            helper.setText(R.id.now_one, mContext.getResources().getText(R.string.text_limit_entrust));
        } else if (item.getType().equals("MARKET_PRICE")) {
            helper.setText(R.id.now_one, mContext.getResources().getText(R.string.text_Market_entrust));
        } else {
            helper.setText(R.id.now_one, mContext.getResources().getText(R.string.text_plan_entrust));
        }
        //触发价
        if (item.getType().equals("SPOT_LIMIT")) {
            helper.setText(R.id.now_two, getpoint(item.getTriggerPrice()));
        } else {
            helper.setText(R.id.now_two, "--");
        }
        //委托价
        helper.setText(R.id.now_three, getpoint(item.getEntrustPrice()));
        //成交价
        helper.setText(R.id.now_four, getpoint(item.getTradedPrice()));
        //保证金
        helper.setText(R.id.now_five, getpoint(item.getPrincipalAmount()) + "USDT");
        //委托数量
        helper.setText(R.id.now_six,((int)item.getVolume())+""+ mContext.getResources().getText(R.string.zhang));


    }



    public String getpoint(double data){
        String format = new DecimalFormat("#.00").format(data);
        BigDecimal bg = new BigDecimal(format);
        String s = bg.setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
        return s;
    }
}
