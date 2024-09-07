package com.bizzan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.ui.entrust.TrustDetailActivity;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.utils.WonderfulMathUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class EntrustHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /*private List<String> group;
    private List<List<EntrustHistory>> child;*/
    //private Map<String,List<EntrustHistory>> map = new HashMap<>();
    private List<EntrustHistory> historyList;
    //private List<String> nameList;
    private Context context;

    // 普通布局
    private final int TYPE_ITEM = 1;
    // 脚布局
    private final int TYPE_FOOTER = 2;
    // 当前加载状态，默认为加载完成
    private int loadState = 2;
    // 正在加载
    public final int LOADING = 1;
    // 加载完成
    public final int LOADING_COMPLETE = 2;
    // 加载到底
    public final int LOADING_END = 3;


    public EntrustHistoryAdapter(Context context,List<EntrustHistory> historyList){
        this.context = context;
        this.historyList = historyList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_entrust_child, null, false);
            return new RecyclerViewHolder(view);

        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_refresh_footer, null, false);
            return new FootViewHolder(view);
        }
        return null;
        /*View view = LayoutInflater.from(context).inflate(
                R.layout.adapter_entrust_child, null, false);
        return new ViewHolder(view);*/
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为FooterView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int i) {
        if (holder instanceof RecyclerViewHolder) {
            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;

            if (historyList.get(i).getDirection().equals("BUY")) {
                recyclerViewHolder.tvChildType.setText(mContext.getResources().getText(R.string.text_buy));
                recyclerViewHolder.tvChildType.setTextColor(context.getResources().getColor(R.color.typeGreen));
            }else {
                recyclerViewHolder.tvChildType.setText(mContext.getResources().getText(R.string.text_sale));
                recyclerViewHolder.tvChildType.setTextColor(context.getResources().getColor(R.color.typeRed));
            }

            if (historyList.get(i).getStatus().equals("COMPLETED")) {
                recyclerViewHolder.tvChildState.setText(mContext.getResources().getText(R.string.traded_trade));
                recyclerViewHolder.ivChildGo.setVisibility(View.VISIBLE);
                recyclerViewHolder.tvChildState.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TrustDetailActivity.show(context,historyList.get(i).getSymbol(),historyList.get(i));
                    }
                });
            }else if (historyList.get(i).getStatus().equals("CANCELED")){
                recyclerViewHolder.tvChildState.setText(mContext.getResources().getText(R.string.undone));
                recyclerViewHolder.ivChildGo.setVisibility(View.INVISIBLE);
            }else {
                recyclerViewHolder.tvChildState.setText(mContext.getResources().getText(R.string.trading));
                recyclerViewHolder.ivChildGo.setVisibility(View.INVISIBLE);
            }

            recyclerViewHolder.tvChildBase.setText(historyList.get(i).getBaseSymbol());

            recyclerViewHolder.tvChildCoin.setText(historyList.get(i).getCoinSymbol());

            String data = String.valueOf(android.text.format.DateFormat.format("HH:mm MM/dd",historyList.get(i).getTime()));
            recyclerViewHolder.tvChildTime.setText(data);

            if (historyList.get(i).getType().equals("MARKET_PRICE")) {
                recyclerViewHolder.tvChildPrice.setText(mContext.getResources().getText(R.string.marketPrice));
            }else recyclerViewHolder.tvChildPrice.setText(new BigDecimal(String.valueOf(historyList.get(i).getPrice())).toPlainString());



            recyclerViewHolder.tvChildAmount.setText(new BigDecimal(String.valueOf(historyList.get(i).getAmount())).toPlainString());

            recyclerViewHolder.tvChildTurnoverText.setText(historyList.get(i).getBaseSymbol());

            recyclerViewHolder.tvChildAverageText.setText(historyList.get(i).getBaseSymbol());

            recyclerViewHolder.tvChildTradedAmountText.setText(historyList.get(i).getCoinSymbol());

            recyclerViewHolder.tvChildTurnover.setText(String.valueOf(historyList.get(i).getTurnover()));


            recyclerViewHolder.tvChildAverage.setText(WonderfulMathUtils.getRundNumber(
                    historyList.get(i).getTurnover()/historyList.get(i).getTradedAmount(),2,null));

            recyclerViewHolder.tvChildTradeAmount.setText(new BigDecimal(String.valueOf(historyList.get(i).getTradedAmount())).toPlainString());
        }else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            switch (loadState) {
                case LOADING: // 正在加载
                    footViewHolder.pbLoading.setVisibility(View.VISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                case LOADING_COMPLETE: // 加载完成
                    footViewHolder.pbLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                case LOADING_END: // 加载到底
                    footViewHolder.pbLoading.setVisibility(View.GONE);
                    footViewHolder.tvLoading.setVisibility(View.GONE);
                    footViewHolder.llEnd.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size()+1;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView tvChildType;
        TextView tvChildState;
        ImageView ivChildGo;
        TextView tvChildBase;
        TextView tvChildCoin;
        TextView tvChildTime;
        TextView tvChildPrice;
        TextView tvChildAmount;
        TextView tvChildTurnoverText;
        TextView tvChildAverageText;
        TextView tvChildTradedAmountText;
        TextView tvChildTurnover;
        TextView tvChildAverage;
        TextView tvChildTradeAmount;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
             tvChildType =  itemView.findViewById(R.id.tvChildType);
             tvChildState =  itemView.findViewById(R.id.tvChildState);
             ivChildGo =  itemView.findViewById(R.id.ivChildGo);
             tvChildBase =  itemView.findViewById(R.id.tvChildBase);
             tvChildCoin =  itemView.findViewById(R.id.tvChildCoin);
             tvChildTime =  itemView.findViewById(R.id.tvChildTime);
             tvChildPrice =  itemView.findViewById(R.id.tvChildPrice);
             tvChildAmount =  itemView.findViewById(R.id.tvChildAmount);
             tvChildTurnoverText =  itemView.findViewById(R.id.tvChildTurnoverText);
             tvChildAverageText =  itemView.findViewById(R.id.tvChildAverageText);
             tvChildTradedAmountText =  itemView.findViewById(R.id.tvChildTradedAmountText);
             tvChildTurnover =  itemView.findViewById(R.id.tvChildTurnover);
             tvChildAverage =  itemView.findViewById(R.id.tvChildAverage);
             tvChildTradeAmount =  itemView.findViewById(R.id.tvChildTradeAmount);
        }
    }

    private class FootViewHolder extends RecyclerView.ViewHolder {

        ProgressBar pbLoading;
        TextView tvLoading;
        LinearLayout llEnd;

        FootViewHolder(View itemView) {
            super(itemView);
            pbLoading = (ProgressBar) itemView.findViewById(R.id.pb_loading);
            tvLoading = (TextView) itemView.findViewById(R.id.tv_loading);
            llEnd = (LinearLayout) itemView.findViewById(R.id.ll_end);
        }
    }

    /**
     * 设置上拉加载状态
     *
     * @param loadState 0.正在加载 1.加载完成 2.加载到底
     */
    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }
}
