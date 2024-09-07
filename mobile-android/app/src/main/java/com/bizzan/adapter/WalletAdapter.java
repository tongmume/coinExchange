package com.bizzan.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.entity.Coin;
import com.bizzan.customview.MyHorizontalScrollView;
import com.bizzan.utils.WonderfulMathUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class WalletAdapter extends BaseQuickAdapter<Coin, BaseViewHolder> {

    List<MyHorizontalScrollView> scrollViews=new ArrayList<>();


    public WalletAdapter(@LayoutRes int layoutResId, @Nullable List<Coin> data) {
        super(layoutResId, data);

    }


    @Override
    protected void convert(final BaseViewHolder helper, Coin item) {

        //WonderfulMathUtils.getRundNumber(Double.valueOf(new BigDecimal(item.getToReleased()).toString()),8,null)
        helper.setText(R.id.tvCoinUnit, item.getCoin().getUnit()).setText(R.id.tvCanUse, processPrice(new BigDecimal(item.getBalance()))).setText(R.id.tv_suocang, processPrice(new BigDecimal(item.getToReleased()))).setText(R.id.tvFrozon, processPrice(new BigDecimal(item.getFrozenBalance()))).addOnClickListener(R.id.tvRecharge).addOnClickListener(R.id.tvExtract);

    }

    private String processPrice(BigDecimal bPrice) {
        if(bPrice.compareTo(BigDecimal.valueOf(1000000)) >= 0){
            return bPrice.setScale(0, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if(bPrice.compareTo(BigDecimal.valueOf(100000)) >= 0){
            return bPrice.setScale(1, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if(bPrice.compareTo(BigDecimal.valueOf(10000)) >= 0){
            return bPrice.setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if(bPrice.compareTo(BigDecimal.valueOf(1000)) >= 0){
            return bPrice.setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if(bPrice.compareTo(BigDecimal.valueOf(100)) >= 0){
            return bPrice.setScale(4, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if(bPrice.compareTo(BigDecimal.valueOf(10)) >= 0){
            return bPrice.setScale(5, BigDecimal.ROUND_DOWN).toPlainString();
        }
        if(bPrice.compareTo(BigDecimal.valueOf(1)) >= 0){
            return bPrice.setScale(6, BigDecimal.ROUND_DOWN).toPlainString();
        }
        return bPrice.setScale(7, BigDecimal.ROUND_DOWN).toPlainString();
    }

}
