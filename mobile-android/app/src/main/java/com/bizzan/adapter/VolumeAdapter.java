package com.bizzan.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.entity.VolumeInfo;
import com.bizzan.utils.WonderfulDateUtils;
import com.bizzan.utils.WonderfulMathUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 成交adapter
 * Created by daiyy on 2018/1/29.
 */

public class VolumeAdapter extends RecyclerView.Adapter<VolumeAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<VolumeInfo> objList;

    public VolumeAdapter(Context context, ArrayList<VolumeInfo> objList) {
        this.context = context;
        this.objList = objList;
    }

    public void setObjList(ArrayList<VolumeInfo> objList) {
        this.objList = objList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_volume, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        VolumeInfo volumeInfo = objList.get(position);
        if (volumeInfo.getTime() == -1) {
            holder.tvTime.setText("-- --");
        } else {
            holder.tvTime.setText(WonderfulDateUtils.getFormatTime("HH:mm:ss", new Date(volumeInfo.getTime())));

        }
        String direct = volumeInfo.getDirection();
        if (direct == null) {
            holder.tvDirect.setText("-- --");
        } else {
            holder.tvDirect.setText(volumeInfo.getDirection().equals("SELL") ? context.getResources().getText(R.string.text_sale_coin) : context.getResources().getText(R.string.text_buy_coin));
            holder.tvDirect.setTextColor(volumeInfo.getDirection().equals("SELL") ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen));
        }
        String format = new DecimalFormat("#0.00000000").format(volumeInfo.getPrice());
        BigDecimal bg = new BigDecimal(format);
        String v =  bg.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        holder.tvPrice.setText(volumeInfo.getPrice() == -1 ? "-- --" :v + "");

        holder.tvNumber.setText(volumeInfo.getAmount() == -1 ? "-- --" : WonderfulMathUtils.getRundNumber(volumeInfo.getAmount(),
                4, null) + "");

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private TextView tvDirect;
        private TextView tvPrice;
        private TextView tvNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDirect = itemView.findViewById(R.id.tvDirect);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvNumber = itemView.findViewById(R.id.tvNumber);
        }


    }
}
