package com.bizzan.ui.dialog;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;

import java.math.BigDecimal;

import butterknife.BindView;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseDialogFragment;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.utils.WonderfulCommonUtils;
import com.bizzan.utils.WonderfulDpPxUtils;
import com.bizzan.utils.WonderfulMathUtils;
import com.bizzan.utils.WonderfulToastUtils;

/**
 * Created by Administrator on 2018/1/31.
 */

public class EntrustOperateDialogFragment extends BaseDialogFragment {

    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.tvCancle)
    TextView tvCancle;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.tvAmount)
    TextView tvAmount;
    @BindView(R.id.tvCancleOrder)
    TextView tvCancleOrder;

    private EntrustHistory entrust;

    public static EntrustOperateDialogFragment getInstance(EntrustHistory entrust) {
        EntrustOperateDialogFragment bottomCustomFragment = new EntrustOperateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("entrust", entrust);
        bottomCustomFragment.setArguments(bundle);
        return bottomCustomFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_fragment_entrust;
    }

    @Override
    protected void initLayout() {
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.windowAnimations = R.style.bottomDialog;
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    protected void initView() {
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvCancleOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((OperateCallback) getTargetFragment()).cancleOrder(entrust.getOrderId());
                } catch (Exception e) {
                    if (callback != null) callback.cancleOrder(entrust.getOrderId());
                }

                dismiss();
            }
        });
    }

    private OperateCallback callback;

    public void setCallback(OperateCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void fillWidget() {
        Bundle bundle = getArguments();
        entrust = (EntrustHistory) bundle.getSerializable("entrust");
        if (entrust == null) {
            dismiss();
            return;
        }
        if ("BUY".equals(entrust.getDirection())) {
            tvType.setText(WonderfulToastUtils.getString(getActivity(),R.string.text_buy));
            tvType.setTextColor(ContextCompat.getColor(getActivity(), R.color.typeGreen));
        } else {
            tvType.setText(WonderfulToastUtils.getString(getActivity(),R.string.text_sale));
            tvType.setTextColor(ContextCompat.getColor(getActivity(), R.color.typeRed));
        }
        if ("LIMIT_PRICE".equals(entrust.getType())) { // 限价
            tvPrice.setText(new BigDecimal(entrust.getPrice()).setScale(8, BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString() + entrust.getBaseSymbol());
            tvAmount.setText(String.valueOf( new BigDecimal(entrust.getAmount()).setScale(8, BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString() + entrust.getCoinSymbol()));
            tvTotal.setText( formatMoney(new BigDecimal(String.valueOf(WonderfulMathUtils.getRundNumber(entrust.getPrice() * entrust.getAmount(), 8, null)
                    ))) + entrust.getBaseSymbol());
        } else { // 市价
            tvPrice.setText(String.valueOf(WonderfulToastUtils.getString(getActivity(),R.string.text_best_prices)));
            if ("BUY".equals(entrust.getDirection())) {
                tvAmount.setText(String.valueOf("- -" + entrust.getCoinSymbol()));
                tvTotal.setText(String.valueOf(new BigDecimal(entrust.getAmount()).setScale(8, BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString() + entrust.getCoinSymbol()));
            } else {
                tvAmount.setText(String.valueOf(new BigDecimal(entrust.getAmount()).setScale(8, BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString() + entrust.getCoinSymbol()));
                tvTotal.setText(formatMoney(new BigDecimal(String.valueOf(WonderfulMathUtils.getRundNumber(entrust.getPrice() * entrust.getAmount(), 8, null)
                       ))) + entrust.getBaseSymbol());
            }
        }
    }
    private String formatMoney(BigDecimal bYuE) {
        String tem = bYuE.toPlainString();
        if(tem.length() > 8) {
            if(bYuE.compareTo(BigDecimal.valueOf(1000000)) >= 0){
                return bYuE.setScale(0, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if(bYuE.compareTo(BigDecimal.valueOf(100000)) >= 0){
                return bYuE.setScale(1, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if(bYuE.compareTo(BigDecimal.valueOf(10000)) >= 0){
                return bYuE.setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if(bYuE.compareTo(BigDecimal.valueOf(1000)) >= 0){
                return bYuE.setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if(bYuE.compareTo(BigDecimal.valueOf(100)) >= 0){
                return bYuE.setScale(4, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if(bYuE.compareTo(BigDecimal.valueOf(10)) >= 0){
                return bYuE.setScale(5, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if(bYuE.compareTo(BigDecimal.valueOf(1)) >= 0){
                return bYuE.setScale(6, BigDecimal.ROUND_DOWN).toPlainString();
            }
            return bYuE.setScale(7, BigDecimal.ROUND_DOWN).toPlainString();
        }else{
            return tem;
        }
    }
    @Override
    protected void loadData() {

    }

    public interface OperateCallback {
        void cancleOrder(String orderId);
    }

}
