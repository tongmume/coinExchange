package com.bizzan.ui.buy_or_sell;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseDialogFragment;
import com.bizzan.utils.WonderfulDpPxUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.io.Serializable;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/7.
 */

public class OrderConfirmDialogFragment extends BaseDialogFragment {

    @BindView(R.id.tvCancle)
    TextView tvCancle;
    @BindView(R.id.tvPriceText)
    TextView tvPriceText;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvCountText)
    TextView tvCountText;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.tvTotalText)
    TextView tvTotalText;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    private String type;
    private double price;
    private double count;
    private double total;
    private Serializable unit;

    public static OrderConfirmDialogFragment getInstance(String type,String unit, double price, double count, double total) {
        OrderConfirmDialogFragment fragment = new OrderConfirmDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putDouble("price", price);
        bundle.putString("unit",unit);
        bundle.putDouble("count", count);
        bundle.putDouble("total", total);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(getActivity() instanceof OperateCallback)) {
            throw new RuntimeException("The Activity which fragment is located must implement the OperateCallback interface!");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_fragment_order_confirm;
    }

    @Override
    protected void initLayout() {
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottomDialog);
        window.setLayout(MyApplication.getApp().getmWidth(), WonderfulDpPxUtils.dip2px(getActivity(), 340));
    }

    @Override
    protected void initView() {
        type = getArguments().getString("type");
        price = getArguments().getDouble("price");
        count = getArguments().getDouble("count");
        total = getArguments().getDouble("total");
        unit=getArguments().getSerializable("unit");
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OperateCallback) getActivity()).buyOrSell();
                dismiss();
            }
        });
    }

    @Override
    protected void fillWidget() {
        if ("0".equals(type)) {
            tvPriceText.setText(WonderfulToastUtils.getString(getActivity(),R.string.dialog_three_ones));
            tvCountText.setText(WonderfulToastUtils.getString(getActivity(),R.string.dialog_three_twos));
            tvTotalText.setText(WonderfulToastUtils.getString(getActivity(),R.string.dialog_three_threes));
        } else {
            tvPriceText.setText(WonderfulToastUtils.getString(getActivity(),R.string.dialog_three_one));
            tvCountText.setText(WonderfulToastUtils.getString(getActivity(),R.string.dialog_three_two));
            tvTotalText.setText(WonderfulToastUtils.getString(getActivity(),R.string.dialog_three_three));
        }
        tvPrice.setText( price + " CNY");
        tvCount.setText(  count+" "+ unit);
        tvTotal.setText( total + " CNY");
    }

    @Override
    protected void loadData() {

    }

    interface OperateCallback {
        void buyOrSell();
    }
}
