package com.bizzan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.entity.DepthResult;
import com.bizzan.utils.WonderfulLogUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/29.
 */

public class DepthAdapter extends RecyclerView.Adapter<DepthAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<DepthResult.DepthListInfo> objBuyList;
    private ArrayList<DepthResult.DepthListInfo> objSellList;
    private double alBuyAccount;
    private double alSellccount;
    private double buyAccount;
    private double sellAccount;
    private int countWidth;

    public DepthAdapter(Context context) {
        this.context = context;
    }

    public void setObjBuyList(ArrayList<DepthResult.DepthListInfo> objBuyList) {
        this.objBuyList = objBuyList;
        if (objBuyList != null) {
            alBuyAccount = 0;
            for (int i = 0; i < objBuyList.size(); i++) {
                if (objBuyList.get(i).getAmount() != -1) {
                    alBuyAccount += objBuyList.get(i).getAmount();
                }
            }
        }
    }

    public void setObjSellList(ArrayList<DepthResult.DepthListInfo> objSellList) {
        this.objSellList = objSellList;
        if (objSellList != null) {
            alSellccount = 0;
            for (int i = 0; i < objSellList.size(); i++) {
                if (objSellList.get(i).getAmount() != -1) {
                    alSellccount += objSellList.get(i).getAmount();
                }
            }
        }
    }

    public void setWidth(int countWidth) {
        this.countWidth = countWidth;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_depth, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DepthResult.DepthListInfo buyInfo = objBuyList.get(position);
        DepthResult.DepthListInfo sellInfo = objSellList.get(position);
        holder.tvBuy.setText((position + 1) + "");
        String format3 = new DecimalFormat("#0.00000000").format( buyInfo.getAmount());
        BigDecimal bg2 = new BigDecimal(format3);
        String v2 =  bg2.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        holder.tvBuyNumber.setText(buyInfo.getAmount() == -1 ? "-- --" : v2 + "");
        String format = new DecimalFormat("#0.00000000").format( buyInfo.getPrice());
        BigDecimal bg = new BigDecimal(format);
        String v =  bg.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        holder.tvBuyPrice.setText(buyInfo.getPrice() == -1 ? "-- --" : v + "");
        holder.tvSell.setText((position + 1) + "");

        String format4 = new DecimalFormat("#0.00000000").format( sellInfo.getAmount());
        BigDecimal bg3 = new BigDecimal(format4);
        String v3 =  bg3.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        holder.tvSellNumber.setText(sellInfo.getAmount() == -1 ? "-- --" : v3 + "");
        String format1 = new DecimalFormat("#0.00000000").format(sellInfo.getPrice());
        BigDecimal bg1 = new BigDecimal(format1);
        String v1=  bg1.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();

        holder.tvSellPrice.setText(sellInfo.getPrice() == -1 ? "-- --" :v1 + "");

        if (buyInfo.getAmount() != -1) {
            buyAccount = 0;
            for (int i = 0; i <= position; i++) {
                buyAccount += objBuyList.get(i).getAmount();
            }
            double buyScale = (buyAccount / alBuyAccount);
            RelativeLayout.LayoutParams buyParams = (RelativeLayout.LayoutParams) holder.buyView.getLayoutParams();
            buyParams.width = (int) (countWidth * buyScale);
            //WonderfulLogUtils.logi("tag", " buyParams.width ==" + buyParams.width);
            holder.buyView.setLayoutParams(buyParams);
        }
        if (sellInfo.getAmount() != -1) {
            sellAccount = 0;
            for (int i = 0; i <= position; i++) {
                sellAccount += objSellList.get(i).getAmount();
            }
            double sellScale = (sellAccount / alSellccount);
            RelativeLayout.LayoutParams sellParams = (RelativeLayout.LayoutParams) holder.sellView.getLayoutParams();
            sellParams.width = (int) (countWidth * sellScale);
            holder.sellView.setLayoutParams(sellParams);
        }

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBuy;
        private TextView tvBuyNumber;
        private TextView tvBuyPrice;
        private TextView tvSell;
        private TextView tvSellNumber;
        private TextView tvSellPrice;
        private ImageView sellView;
        private ImageView buyView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvBuy = itemView.findViewById(R.id.tvBuy);
            tvBuyNumber = itemView.findViewById(R.id.tvBuyNumber);
            tvBuyPrice = itemView.findViewById(R.id.tvBuyPrice);
            tvSell = itemView.findViewById(R.id.tvSell);
            tvSellNumber = itemView.findViewById(R.id.tvSellNumber);
            tvSellPrice = itemView.findViewById(R.id.tvSellPrice);
            sellView = itemView.findViewById(R.id.sellView);
            buyView = itemView.findViewById(R.id.buyView);
        }


    }
}
