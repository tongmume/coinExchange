package com.bizzan.ui.dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bizzan.R;

import butterknife.BindView;
import com.bizzan.ui.releaseAd.ReleaseAdsActivity;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseDialogFragment;
import com.bizzan.utils.WonderfulDpPxUtils;

/**
 * Created by Administrator on 2018/1/31.
 */

public class BuyOrSellDialogFragment extends BaseDialogFragment {

    @BindView(R.id.ivBuy)
    ImageView ivBuy;
    @BindView(R.id.tvBuy)
    TextView tvBuy;
    @BindView(R.id.ivSell)
    ImageView ivSell;
    @BindView(R.id.tvSell)
    TextView tvSell;
    @BindView(R.id.tvCancle)
    TextView tvCancle;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bottom_show_buy_sell;
    }

    @Override
    protected void initLayout() {
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottomDialog);
        window.setLayout(MyApplication.getApp().getmWidth(), WonderfulDpPxUtils.dip2px(getActivity(), 200));
    }

    @Override
    protected void initView() {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ivBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBuy();
            }
        });
        tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBuy();
            }
        });
        ivSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSell();
            }
        });
        tvSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSell();
            }
        });
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void toSell() {
        dismiss();
        ReleaseAdsActivity.actionStart(getActivity(), "SELL", null);
    }

    private void toBuy() {
        dismiss();
        ReleaseAdsActivity.actionStart(getActivity(), "BUY", null);
    }


    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

}
