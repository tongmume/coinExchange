package com.bizzan.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.ui.home.MainActivity;
import com.bizzan.app.MyApplication;
import com.bizzan.entity.Currency;
import com.bizzan.utils.WonderfulMathUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * author: wuzongjie
 * time  : 2018/4/16 0016 18:18
 * desc  :
 */

public class Homes2Adapter extends BaseQuickAdapter<Currency, BaseViewHolder> {
    private int type;
    private boolean isLoad;

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }

    public Homes2Adapter(@Nullable List<Currency> data, int type) {
        super(R.layout.adapter_layout_two2, data);
        this.type = type;
    }


    @Override
    protected void convert(BaseViewHolder helper, Currency item) {
        if (isLoad) {
            helper.setText(R.id.item_home_money, "≈" + WonderfulMathUtils.getRundNumber(item.getBaseUsdRate() * item.getClose() * MainActivity.rate, 2, null) + " CNY");
        } else {
            helper.setText(R.id.item_home_money, "≈" + WonderfulMathUtils.getRundNumber(item.getBaseUsdRate() * item.getClose() * MainActivity.rate, 2, null) + " CNY");
//            helper.setText(R.id.item_home_money, "$" + String.valueOf(item.getUsdRate()));
        }
        helper.setText(R.id.item_home_chg, (item.getChg() >= 0 ? "+" : "") + WonderfulMathUtils.getRundNumber(item.getChg() * 100, 2, "########0.") + "%");
//        helper.setTextColor(R.id.item_home_chg, item.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(),
//                R.color.white) : ContextCompat.getColor(MyApplication.getApp(), R.color.kred));
        helper.getView(R.id.item_home_chg).setEnabled(item.getChg() >= 0);
        if (type == 2) {
            helper.setText(R.id.item_home_symbol, item.getSymbol().split("/")[0]);
            helper.setText(R.id.item_home_symbolTwo, "/" + item.getSymbol().split("/")[1]);
        } else {
            helper.setText(R.id.item_home_symbol, item.getOtherCoin());
            helper.setText(R.id.item_home_symbolTwo, item.getSymbol().substring(item.getSymbol().indexOf("/"), item.getSymbol().length()));
        }
//        helper.setText(R.id.item_home_close, String.valueOf(item.getClose()));
        try {
            String format = new DecimalFormat("#0.00000000").format(item.getClose());
            BigDecimal bg = new BigDecimal(format);
            String v = bg.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            helper.setText(R.id.item_home_close, v);
        } catch (Exception e) {
            e.printStackTrace();
        }
        helper.setTextColor(R.id.item_home_close, item.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(),
                R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        helper.setText(R.id.item_home_change, mContext.getResources().getText(R.string.text_24_change) + processAmount(item.getVolume()));
    }

    private String processAmount(BigDecimal bAmount) {
        String v1 = "0.00";
        if (bAmount.compareTo(BigDecimal.ZERO) >= 0) {
            v1 = bAmount.setScale(7, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }

        if (bAmount.compareTo(new BigDecimal(10)) >= 0) {
            v1 = bAmount.setScale(6, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        if (bAmount.compareTo(new BigDecimal(100)) >= 0) {
            v1 = bAmount.setScale(5, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        // 1100 将以 1.1k显示（千计数）
        if (bAmount.compareTo(new BigDecimal(1000)) >= 0) {
            v1 = bAmount.setScale(4, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        if (bAmount.compareTo(new BigDecimal(10000)) >= 0) {
            v1 = bAmount.setScale(3, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        if (bAmount.compareTo(new BigDecimal(100000)) >= 0) {
            v1 = bAmount.setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        // 1100000 将以 1.1m显示（百万计数）
        if (bAmount.compareTo(new BigDecimal(1000000)) >= 0) {
            v1 = bAmount.divide(new BigDecimal(1000000)).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "m";
        }
        // 1100000000 将以 1.1b显示（十亿计数）
        if (bAmount.compareTo(new BigDecimal(1000000000)) >= 0) {
            v1 = bAmount.divide(new BigDecimal(1000000000)).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "b";
        }
        return v1;
    }

}