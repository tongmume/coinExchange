package com.bizzan.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.utils.WonderfulDateUtils;
import com.bizzan.utils.WonderfulMathUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * author: wuzongjie
 * time  : 2018/4/17 0017 10:56
 * desc  :历史记录的适配器
 */

public class TrustHistoryAdapter extends BaseQuickAdapter<EntrustHistory, BaseViewHolder> {

    public TrustHistoryAdapter(@Nullable List<EntrustHistory> data) {
        super(R.layout.adapter_trust_history_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EntrustHistory item) {
        if ("BUY".equals(item.getDirection())) {
            helper.setText(R.id.history_type, mContext.getResources().getText(R.string.text_buy)).setTextColor(R.id.history_type,
                    ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen));
        } else {
            helper.setText(R.id.history_type, mContext.getResources().getText(R.string.text_sale)).setTextColor(R.id.history_type,
                    ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        }
        if ("COMPLETED".equals(item.getStatus())) { // 已成交
            helper.setVisible(R.id.history_cancel, false);
            helper.setVisible(R.id.history_success, true);
        } else {
            helper.setVisible(R.id.history_cancel, true);
            helper.setVisible(R.id.history_success, false);
        }

        helper.setText(R.id.history_num_s, mContext.getResources().getText(R.string.text_volume) + "(" + item.getCoinSymbol() + ")");
        String[] times = WonderfulDateUtils.getFormatTime(null, new Date(item.getTime())).split(" ");
        helper.setText(R.id.history_one, times[0].substring(5, times[0].length()) + " " + times[1].substring(0, 5));
        if ("LIMIT_PRICE".equals(item.getType())) { // 限价

            helper.setText(R.id.history_num, mContext.getResources().getText(R.string.text_entrust_num) + "(" + item.getCoinSymbol() + ")");

            String format3 = new DecimalFormat("#0.00000000").format(item.getPrice());
            BigDecimal bg3 = new BigDecimal(format3);
            String v3 =  bg3.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            helper.setText(R.id.history_two, v3);
        } else { // 市价
            if ("BUY".equals(item.getDirection())) {
                helper.setText(R.id.history_two, String.valueOf(mContext.getResources().getText(R.string.text_best_prices)));
                helper.setText(R.id.history_num, mContext.getResources().getText(R.string.text_total_entrust) + "(" + item.getBaseSymbol() + ")");
            } else {
                helper.setText(R.id.history_num, mContext.getResources().getText(R.string.text_entrust_num) + "(" + item.getCoinSymbol() + ")");
                helper.setText(R.id.history_two, String.valueOf(mContext.getResources().getText(R.string.text_best_prices)));
            }
        }
        helper.setText(R.id.history_wei, mContext.getResources().getText(R.string.text_wei_price) + "(" + item.getBaseSymbol() + ")");
        // 委托量
        String format = new DecimalFormat("#0.00000000").format(item.getAmount());
        BigDecimal bg = new BigDecimal(format);
        String v =  bg.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();

        helper.setText(R.id.history_three,v);
        // 成交总额
        String format2 = new DecimalFormat("#0.00000000").format(item.getTurnover());
        BigDecimal bg2 = new BigDecimal(format2);
        String v2 =  bg2.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        helper.setText(R.id.history_four,v2);

        helper.setText(R.id.history_total, mContext.getResources().getText(R.string.text_total_deal) + "(" + item.getBaseSymbol() + ")");
        // 成交均价
        if (item.getTradedAmount() == 0.0 || item.getTurnover() == 0.0) {
            helper.setText(R.id.history_five, String.valueOf(0.0));
        } else {
            helper.setText(R.id.history_five, String.valueOf(WonderfulMathUtils.getRundNumber(item.getTurnover() / item.getTradedAmount(),
                    2, null)));
        }
        helper.setText(R.id.history_average, mContext.getResources().getText(R.string.text_average_price) + "(" + item.getBaseSymbol() + ")");
        // 成交量
        //helper.setText(R.id.history_six,String.valueOf(item.getTradedAmount()));
        String format1 = new DecimalFormat("#0.00000000").format(item.getTradedAmount());
        BigDecimal bg1 = new BigDecimal(format1);
        String v1 =  bg1.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        helper.setText(R.id.history_six,v1);
    }
}