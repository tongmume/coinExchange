package com.bizzan.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.entity.Exchange;
import com.bizzan.utils.WonderfulMathUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * author: wuzongjie
 * time  : 2018/4/17 0017 17:42
 * desc  : 这个是交易界面盘口的适配器
 */

public class SellAdapter extends BaseQuickAdapter<Exchange, BaseViewHolder> {

    private int type = 0; // 为0就是绿色否则为红色
    private int price = 2;
    private int amount = 2;
    private myText text;
    float totalAmount = 0;

    public myText getText() {
        return text;
    }

    List<Exchange> data;

    public void setText(myText text) {
        this.text = text;
    }

    public SellAdapter(@Nullable List<Exchange> data, int type) {
        super(R.layout.adapter_sell_layout, data);
        this.type = type;
        this.data = data;
        getTotalCount(data);
    }

    public void setList(List<Exchange> data) {
        this.data = data;
        getTotalCount(data);
    }

    private void getTotalCount(List<Exchange> data) {
        totalAmount = 0;
        for (Exchange exchange : data) {
            if (!"--".equals(exchange.getAmount())) {
                totalAmount += Float.parseFloat(exchange.getAmount());
            }
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, Exchange item) {
        // 对不同的币种做不同的限制
        if (text != null) {
            price = text.two();
            amount = text.one();
        }
        if ("--".equals(item.getPrice()) || "--".equals(item.getAmount())) {
            helper.setText(R.id.item_sell_two, String.valueOf(item.getPrice()));
            helper.setText(R.id.item_sell_three, String.valueOf(item.getAmount()));
        } else {
            BigDecimal bg = new BigDecimal(WonderfulMathUtils.getRundNumber(Double.valueOf(item.getPrice()), price, null));
            String v = processPrice(bg);//bg.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            BigDecimal bg1 = new BigDecimal(WonderfulMathUtils.getRundNumber(Double.valueOf(item.getAmount()), amount, null));
            String v1 = processAmount(bg1);
            helper.setText(R.id.item_sell_two, "" + v);
            helper.setText(R.id.item_sell_three, "" + v1);
        }
        int position = helper.getAdapterPosition();
        float currentTotalAmount = 0;
        if (type == 1) {
            for (int i = 0; i <= position; i++) {
                if (!"--".equals(data.get(i).getAmount())) {
                    currentTotalAmount += Float.parseFloat(data.get(i).getAmount());
                }
            }
        } else {
            for (int i = data.size() - 1; i >= position; i--) {
                if (!"--".equals(data.get(i).getAmount())) {
                    currentTotalAmount += Float.parseFloat(data.get(i).getAmount());
                }
            }
        }
        float scale = 0;
        if (totalAmount != 0) {
            scale = currentTotalAmount / totalAmount;
        }
        //Log.i("getAdapterPosition",position+"--"+currentTotalAmount+"--"+totalAmount+"--"+scale);
        scale = scale > 1 ? 1 : scale;
        int parentWidth=helper.getView(R.id.ceshi).getMeasuredWidth();
        int backWidth = (int) (parentWidth* scale);
        ViewGroup.LayoutParams lp = helper.getView(R.id.tv_back_depth).getLayoutParams();
        int lastPosition = type == 1 ? data.size() - 1 : 0;
        if (!"--".equals(data.get(position).getAmount())) {
            if (position == lastPosition) {
                lp.width = parentWidth-1;
                helper.getView(R.id.tv_back_depth).setLayoutParams(lp);
            } else {
                lp.width = backWidth>=1?backWidth-1:backWidth;
                helper.getView(R.id.tv_back_depth).setLayoutParams(lp);
            }
        }else {
            lp.width = 0;
            helper.getView(R.id.tv_back_depth).setLayoutParams(lp);
        }

        if (type == 1) {
            helper.setText(R.id.item_sell_one, mContext.getResources().getText(R.string.item_buy)+"" + (item.getPosition() + 1));
            helper.setTextColor(R.id.item_sell_two, ContextCompat.getColor(MyApplication.getApp(),
                    R.color.typeGreen));
            helper.setTextColor(R.id.item_sell_one, ContextCompat.getColor(MyApplication.getApp(),
                    R.color.typeGreen));
            helper.setBackgroundColor(R.id.tv_back_depth, ContextCompat.getColor(MyApplication.getApp(),
                    R.color.green_back));
        } else {
            helper.setText(R.id.item_sell_one, mContext.getResources().getText(R.string.item_sell) +""+ (item.getPosition()));
        }
    }
    private String processAmount(BigDecimal bAmount){
        String v1 = "";
        if(bAmount.compareTo(BigDecimal.ZERO) > 0){
            v1 = bAmount.setScale(7, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }

        if(bAmount.compareTo(new BigDecimal(10)) >= 0){
            v1 = bAmount.setScale(6, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        if(bAmount.compareTo(new BigDecimal(100)) >= 0){
            v1 = bAmount.setScale(5, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        // 1100 将以 1.1k显示（千计数）
        if(bAmount.compareTo(new BigDecimal(1000)) >= 0){
            v1 = bAmount.divide(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "k";
        }
        // 1100000 将以 1.1m显示（百万计数）
        if(bAmount.compareTo(new BigDecimal(1000000)) >= 0){
            v1 = bAmount.divide(new BigDecimal(1000000)).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "m";
        }
        // 1100000000 将以 1.1b显示（十亿计数）
        if(bAmount.compareTo(new BigDecimal(1000000000)) >= 0){
            v1 = bAmount.divide(new BigDecimal(1000000000)).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "b";
        }
        return v1;
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
    public interface myText {
        int one();

        int two();
    }
}
