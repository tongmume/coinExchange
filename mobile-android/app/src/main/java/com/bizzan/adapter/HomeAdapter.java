package com.bizzan.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

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
 * time  : 2018/4/16 0016 15:23
 * desc  : 首页涨幅榜适配器
 */

public class HomeAdapter extends BaseQuickAdapter<Currency, BaseViewHolder> {

    private boolean isLoad;
    private int colorA = Color.parseColor("#1F2833");
    private int colorC = Color.parseColor("#1A212A");

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }

    public HomeAdapter(@Nullable List<Currency> data) {
        super(R.layout.adapter_home_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Currency item) {
        if (isLoad) {
            helper.setText(R.id.item_home_money, "≈" + WonderfulMathUtils.getRundNumber(item.getClose() * item.getBaseUsdRate() * MainActivity.rate, 2, null) + " CNY");
        } else {
            helper.setText(R.id.item_home_money, "$" + WonderfulMathUtils.getRundNumber(item.getUsdRate(), 2, null));
        }
        TextView view = helper.getView(R.id.item_home_position);

        // 动态背景变化控件获取
        FrameLayout itemLayout = helper.getView(R.id.layout_homeadapter_item);
        TextView closeView = helper.getView(R.id.item_home_close);
        String oldText = closeView.getText().toString();

        if ((helper.getAdapterPosition() + 1) <= 5) {
            switch (helper.getAdapterPosition() + 1) {
                case 1:
                    view.setBackgroundResource(R.color.color_green1);
                    break;
                case 2:
                    view.setBackgroundResource(R.color.color_green2);
                    break;
                case 3:
                    view.setBackgroundResource(R.color.color_green2);
                    break;
                case 4:
                    view.setBackgroundResource(R.color.color_green4);
                    break;
                case 5:
                    view.setBackgroundResource(R.color.color_green5);
                    break;
            }
        } else {
            view.setBackgroundResource(R.color.color_green3);
        }
        helper.setText(R.id.item_home_position, "" + (helper.getAdapterPosition() + 1));
        helper.setText(R.id.item_home_chg, (item.getChg() >= 0 ? "+" : "") + WonderfulMathUtils.getRundNumber(item.getChg() * 100, 2, "########0.") + "%");
        helper.setTextColor(R.id.item_home_close, item.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(),
                R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        helper.getView(R.id.item_home_chg).setEnabled(item.getChg() >= 0);
        helper.setText(R.id.item_home_symbol, item.getSymbol());
        helper.setText(R.id.item_home_change, mContext.getResources().getText(R.string.text_24_change) + processAmount(item.getVolume()));

//        helper.setText(R.id.item_home_close, WonderfulMathUtils.getRundNumber(item.getClose(),2,null));
        String format = new DecimalFormat("#0.00000000").format(item.getClose());
        try {
            BigDecimal bg = new BigDecimal(format);
            String v = bg.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();

//        if(!v.equals(oldText)){
//            itemLayout.animate().setDuration(300).start();
//            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(itemLayout,"backgroundColor",colorA, colorC);
//            objectAnimator.setDuration(300);
//            objectAnimator.setEvaluator(new ArgbEvaluator());
//            objectAnimator.start();
//        }

            helper.setText(R.id.item_home_close, v);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
