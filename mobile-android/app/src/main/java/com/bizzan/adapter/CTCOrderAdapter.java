package com.bizzan.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.entity.CTCOrderDetail;

public class CTCOrderAdapter extends BaseQuickAdapter<CTCOrderDetail,BaseViewHolder>{

    public CTCOrderAdapter( @Nullable List<CTCOrderDetail> data) {
        super(R.layout.adapter_ctcorder, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CTCOrderDetail item) {
        helper.setText(R.id.ctc_ordersn,item.getOrderSn());
        helper.setText(R.id.ctc_createtime, item.getCreateTime());
        helper.setText(R.id.ctc_direction, item.getDirection() == 0 ? mContext.getResources().getText(R.string.text_buy_coin) :mContext.getResources().getText(R.string.text_sale_coin))
                .setTextColor(R.id.ctc_direction, item.getDirection() == 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        helper.setText(R.id.ctc_amount, String.valueOf(item.getAmount()));
        helper.setText(R.id.ctc_price, String.valueOf(item.getPrice()));
        helper.setText(R.id.ctc_money, String.valueOf(item.getMoney())).setTextColor(R.id.ctc_money, item.getDirection() == 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        helper.setText(R.id.ctc_statusdetail, "");
        if(item.getStatus() == 0){
            helper.setText(R.id.ctc_status, mContext.getResources().getText(R.string.tv_wait_order)).setTextColor(R.id.ctc_status, ContextCompat.getColor(MyApplication.getApp(), R.color.colorPrimary));
        }
        if(item.getStatus() == 1){
            helper.setText(R.id.ctc_status, mContext.getResources().getText(R.string.tv_already_order)).setTextColor(R.id.ctc_status, ContextCompat.getColor(MyApplication.getApp(), R.color.colorPrimary));
            if(item.getDirection() == 0) {
                helper.setText(R.id.ctc_statusdetail, mContext.getResources().getText(R.string.tv_now_payment)).setTextColor(R.id.ctc_statusdetail, ContextCompat.getColor(MyApplication.getApp(), R.color.cc));
            }
        }
        if(item.getStatus() == 2){
            helper.setText(R.id.ctc_status, mContext.getResources().getText(R.string.tv_payment)).setTextColor(R.id.ctc_status, ContextCompat.getColor(MyApplication.getApp(), R.color.colorPrimary));
        }
        if(item.getStatus() == 3){
            helper.setText(R.id.ctc_status, mContext.getResources().getText(R.string.tv_succee)).setTextColor(R.id.ctc_status, ContextCompat.getColor(MyApplication.getApp(), R.color.colorPrimary));
        }
        if(item.getStatus() == 4){
            helper.setText(R.id.ctc_status,mContext.getResources().getText(R.string.tv_cancel)).setTextColor(R.id.ctc_status, ContextCompat.getColor(MyApplication.getApp(), R.color.colorTextNormal));
        }
    }
}
