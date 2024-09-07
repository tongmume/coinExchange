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
import com.bizzan.entity.EntrustHistory;
import com.bizzan.entity.EntrustHistory_constract;
import com.bizzan.utils.WonderfulDateUtils;
import com.bizzan.utils.WonderfulToastUtils;

public class TrustAdapterHistory_constract extends BaseQuickAdapter<EntrustHistory_constract.ContentBean, BaseViewHolder> {
    boolean isCurrent;

    public TrustAdapterHistory_constract(@Nullable List<EntrustHistory_constract.ContentBean> data, boolean isCurrent) {
        super(R.layout.adapter_trust_constract, data);
        this.isCurrent = isCurrent;
    }

    @Override
    protected void convert(final BaseViewHolder helper, EntrustHistory_constract.ContentBean item) {
        //方向
        if ("OPEN".equals(item.getEntrustType())) {
            if ("BUY".equals(item.getDirection())) {
                helper.setText(R.id.trust_direction, mContext.getResources().getText(R.string.buyOpenmore)).setTextColor(R.id.trust_direction,
                        ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen));
            } else {
                helper.setText(R.id.trust_direction, mContext.getResources().getText(R.string.sellOpennull)).setTextColor(R.id.trust_direction,
                        ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
            }
        } else {
            if ("BUY".equals(item.getDirection())) {
                helper.setText(R.id.trust_direction, mContext.getResources().getText(R.string.buyflatnull)).setTextColor(R.id.trust_direction,
                        ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen));
            } else {
                helper.setText(R.id.trust_direction, mContext.getResources().getText(R.string.sellflatmore)).setTextColor(R.id.trust_direction,
                        ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
            }
        }
        //时间
        String[] times = WonderfulDateUtils.getFormatTime(null, new Date(item.getCreateTime())).split(" ");
        helper.setText(R.id.trust_time, times[0].substring(5, times[0].length()) + " " + times[1].substring(0, 5));
        //委托数量
        helper.setText(R.id.trust_one,((int) item.getVolume())+""+ mContext.getResources().getText(R.string.zhang));
        //委托价
        helper.setText(R.id.trust_two, getpoint(item.getEntrustPrice()));
        //成交价
        helper.setText(R.id.trust_three, getpoint(item.getTradedPrice()));
        //结算盈亏
        if (item.getProfitAndLoss() == 0){
            helper.setText(R.id.trust_four, "--")
                    .setTextColor(R.id.trust_four, ContextCompat.getColor(MyApplication.getApp(), R.color.colorTextNormal));
        }else {
            if (item.getProfitAndLoss() > 0){
                helper.setText(R.id.trust_four, getpoint(item.getProfitAndLoss()))
                        .setTextColor(R.id.trust_four, ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen));
            }else {
                helper.setText(R.id.trust_four, getpoint(item.getProfitAndLoss()))
                        .setTextColor(R.id.trust_four, ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
            }
        }
        helper.addOnClickListener(R.id.ll_item);
        helper.addOnClickListener(R.id.trust_direction);
    }

    public String getpoint(double data) {
        String format = new DecimalFormat("#.00").format(data);
        BigDecimal bg = new BigDecimal(format);
        String s = bg.setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
        return s;
    }
}

