package com.bizzan.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.math.BigDecimal;
import java.util.List;

import com.bizzan.R;
import com.bizzan.base.Contract;
import com.bizzan.entity.WalletConstract;
import com.bizzan.ui.wallet.WalletActivity;

/**
 * Created by Administrator on 2018/2/5.
 */

public class WalletContractAdapter extends BaseQuickAdapter<WalletConstract, BaseViewHolder> {

    private Context context;

    public WalletContractAdapter(@LayoutRes int layoutResId, @Nullable List<WalletConstract> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, WalletConstract item) {
        //币种
        helper.setText(R.id.tvCoinUnit, item.getContractCoin().getSymbol() + " " + context.getResources().getText(R.string.tv_constract));
        //账户权益
        helper.setText(R.id.tvRights,  processPrice(new BigDecimal(item.getUsdtBalance() + item.getUsdtFrozenBalance() + item.getUsdtBuyPrincipalAmount()
                + item.getUsdtSellPrincipalAmount() + item.getUsdtTotalProfitAndLoss() )));
        // 如果是全仓并且收益小于0  可用保证金
        if(item.getUsdtTotalProfitAndLoss() < 0 && item.getUsdtPattern().equals("CROSSED")) {
            helper.setText(R.id.tvMayMoney, processPrice(new BigDecimal(item.getUsdtBalance()+item.getUsdtTotalProfitAndLoss())));
       }else{
            helper.setText(R.id.tvMayMoney, processPrice(new BigDecimal(item.getUsdtBalance())));
        }
        //已用保证金
        helper.setText(R.id.tvUseMoney, processPrice(new BigDecimal(item.getUsdtBuyPrincipalAmount()+item.getUsdtSellPrincipalAmount())));
        //冻结保证金
        helper.setText(R.id.tvFreezaMoey, processPrice(new BigDecimal(item.getUsdtFrozenBalance())));
    }

    private String processPrice(BigDecimal bPrice) {
        if (bPrice.compareTo(BigDecimal.valueOf(1000000)) >= 0) {
            return bPrice.setScale(0, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if (bPrice.compareTo(BigDecimal.valueOf(100000)) >= 0) {
            return bPrice.setScale(1, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if (bPrice.compareTo(BigDecimal.valueOf(10000)) >= 0) {
            return bPrice.setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if (bPrice.compareTo(BigDecimal.valueOf(1000)) >= 0) {
            return bPrice.setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if (bPrice.compareTo(BigDecimal.valueOf(100)) >= 0) {
            return bPrice.setScale(4, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if (bPrice.compareTo(BigDecimal.valueOf(10)) >= 0) {
            return bPrice.setScale(5, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if (bPrice.compareTo(BigDecimal.valueOf(1)) >= 0) {
            return bPrice.setScale(6, BigDecimal.ROUND_DOWN).toPlainString();
        }
        return bPrice.setScale(7, BigDecimal.ROUND_DOWN).toPlainString();
    }

}
