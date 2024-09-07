package com.bizzan.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.ui.home.MainActivity;
import com.bizzan.app.MyApplication;
import com.bizzan.entity.Currency;
import com.bizzan.utils.WonderfulMathUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 */

public class HomeOneAdapter extends BaseQuickAdapter<Currency, BaseViewHolder> {
    public HomeOneAdapter(@LayoutRes int layoutResId, @Nullable List<Currency> data) {
        super(layoutResId, data);
    }
private int width;
    private  int height;
    @Override
    protected void convert(BaseViewHolder helper, Currency item) {

        LinearLayout view = helper.getView(R.id.line);
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) view.getLayoutParams();
        params.width= (int) (width*0.38);
        view.setLayoutParams(params);
        helper.setImageResource(R.id.ivCollect, item.isCollect() ? R.drawable.icon_collect_yes : R.drawable.icon_collect_no);
        helper.addOnClickListener(R.id.ivCollect);
        helper.setText(R.id.tvCurrencyName, item.getSymbol()).setText(R.id.tvClose, WonderfulMathUtils.getRundNumber(item.getClose(), 2, null))
                .setText(R.id.tvAddPercent, (item.getChg() >= 0 ? "+" : "") + WonderfulMathUtils.getRundNumber(item.getChg() * 100, 2, "########0.") + "%")
                .setText(R.id.tvVol,"≈" + WonderfulMathUtils.getRundNumber(item.getClose()*item.getBaseUsdRate() * MainActivity.rate ,2,null)+"CNY")
                .setTextColor(R.id.tvAddPercent, item.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed))
                //.setTextColor(R.id.tvVol, item.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.kgreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.kred))
                .setTextColor(R.id.tvClose, item.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
    }

//    public void notifyCollect(int position) {
//        Currency currency = getData().get(position);
//        currency.setCollect(!currency.isCollect());
//        notifyDataSetChanged();
//    }
}
