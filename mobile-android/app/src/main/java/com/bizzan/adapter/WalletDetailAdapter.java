package com.bizzan.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.bizzan.R;
import com.bizzan.entity.WalletDetailNew;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class WalletDetailAdapter extends BaseQuickAdapter<WalletDetailNew.ContentBean, BaseViewHolder> {

    private String discountFee;
    private String amount;
    private String fee;
    private String realFee;

    public WalletDetailAdapter(@LayoutRes int layoutResId, @Nullable List<WalletDetailNew.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletDetailNew.ContentBean contentBean) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(contentBean.getCreateTime());
            WonderfulLogUtils.logi("WalletDetailAdapter", "date  " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String time = String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd HH:mm", date));
        amount = WonderfulStringUtils.getLongFloatString(contentBean.getAmount(), 8);
        fee = WonderfulStringUtils.getLongFloatString(contentBean.getFee(), 8);
        if (contentBean.getDiscountFee() == null) {
            discountFee = "0";
        } else {
            discountFee = WonderfulStringUtils.getLongFloatString(Float.parseFloat(contentBean.getDiscountFee()), 8);
        }
        if (contentBean.getRealFee() == null) {
            realFee = "0";
        } else {
            realFee = WonderfulStringUtils.getLongFloatString(Float.parseFloat(contentBean.getRealFee()), 8);
        }
        String type = "";
        switch (contentBean.getType()) {
            case 0:
                type = mContext.getResources().getText(R.string.top_up)+"";
                break;
            case 1:
                type = mContext.getResources().getText(R.string.withdrawal)+"";
                break;
            case 2:
                type = mContext.getResources().getText(R.string.transfer)+"";
                break;
            case 3:
                type = mContext.getResources().getText(R.string.coinCurrencyTrading)+"";
                break;
            case 4:
                type = mContext.getResources().getText(R.string.FiatMoneyBuy)+"";
                break;
            case 5:
                type = mContext.getResources().getText(R.string.FiatMoneySell)+"";
                break;
            case 6:
                type = mContext.getResources().getText(R.string.activitiesReward)+"";
                break;
            case 7:
                type = mContext.getResources().getText(R.string.promotionRewards)+"";
                break;
            case 8:
                type = mContext.getResources().getText(R.string.shareOutBonus)+"";
                break;
            case 9:
                type = mContext.getResources().getText(R.string.vote)+"";
                break;
            case 10:
                type = mContext.getResources().getText(R.string.ArtificialTop_up)+"";
                break;
            case 11:
                type = mContext.getResources().getText(R.string.matchMoney)+"";
                break;
            case 12:
                type = mContext.getResources().getText(R.string.activityexchange)+"";
                break;
            case 13:
                type = mContext.getResources().getText(R.string.ctcbuy)+"";
                break;
            case 14:
                type = mContext.getResources().getText(R.string.ctcsell)+"";
                break;

            case 15:
                type = mContext.getResources().getText(R.string.red_out)+"";
                break;
            case 16:
                type = mContext.getResources().getText(R.string.red_in)+"";
                break;
            case 17:
                type = mContext.getResources().getText(R.string.withdrawcode_out)+"";
                break;
            case 18:
                type = mContext.getResources().getText(R.string.withdrawcode_in)+"";
                break;
            case 19:
                type = mContext.getResources().getText(R.string.contract_fee)+"";
                break;
            case 20:
                type = mContext.getResources().getText(R.string.contract_proit)+"";
                break;
            case 21:
                type = mContext.getResources().getText(R.string.contract_loss)+"";
                break;
            case 22:
                type = mContext.getResources().getText(R.string.option_fail)+"";
                break;
            case 23:
                type = mContext.getResources().getText(R.string.option_fee)+"";
                break;
            case 24:
                type = mContext.getResources().getText(R.string.option_reward)+"";
                break;
            default:
        }
        helper.setText(R.id.trade_time_value, time).setText(R.id.trade_amount_value, amount).setText(R.id.trust_symbol, contentBean.getSymbol()
        ).setText(R.id.fee_value, fee).setText(R.id.discount_fee_value, discountFee).setText(R.id.real_fee_value, realFee).setText(R.id.trade_type_value, type);
    }


}
