package com.bizzan.ui.entrust;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.adapter.DetailAdapter;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.utils.WonderfulToastUtils;

import java.math.BigDecimal;

import butterknife.BindView;

/**
 * 交易明细
 */
public class TrustDetailActivity extends BaseActivity {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.bar_title)
    TextView barTitle; // 标题
    @BindView(R.id.mDetailType)
    TextView mDetailType; // 买入还是卖出
    @BindView(R.id.mDetailName)
    TextView mDetailName; // BTC/USDT
    @BindView(R.id.mDetailOne)
    TextView mDetailOne; // 成交总额
    @BindView(R.id.mDetailTwo)
    TextView mDetailTwo; // 成交均价
    @BindView(R.id.mDetailThree)
    TextView mDetailThree; // 成交量
    //    @BindView(R.id.mDetailTime)
//    TextView mDetailTime; // 时间
//    @BindView(R.id.mDetailPrice)
//    TextView mDetailPrice; // 成交价
//    @BindView(R.id.mDetailNum)
//    TextView mDetailNum; // 成交量
//    @BindView(R.id.mDetailChg)
//    TextView mDetailChg; // 手续费
    @BindView(R.id.detailLayout)
    LinearLayout mDetailLayout;
    @BindView(R.id.mDetailFour)
    TextView mDetailFour;
    @BindView(R.id.mDetailFive)
    TextView mDetailFive;
    @BindView(R.id.mDetailTotal)
    TextView mDetailTotal;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private EntrustHistory historyOrder;
    private String symbol;
    @BindView(R.id.view_back)
    View view_back;

    public static void show(Context activity, String symbol, EntrustHistory order) {
        Intent intent = new Intent(activity, TrustDetailActivity.class);
        intent.putExtra("symbol", symbol);
        intent.putExtra("order", order);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_trust_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        } else {
            symbol = intent.getStringExtra("symbol");
            barTitle.setText(symbol);
            historyOrder = (EntrustHistory) intent.getSerializableExtra("order");
        }
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (historyOrder.getDetail() == null || historyOrder.getDetail().size() == 0) {
            mDetailLayout.setVisibility(View.GONE);
        }
        mRecyclerView.setAdapter(new DetailAdapter(historyOrder.getDetail()));
    }

    @Override
    protected void obtainData() {
        if ("BUY".equals(historyOrder.getDirection())) {
            mDetailType.setText(WonderfulToastUtils.getString(this,R.string.text_buy));
            mDetailType.setTextColor(ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen));
        } else {
            mDetailType.setText(WonderfulToastUtils.getString(this,R.string.text_sale));
            mDetailType.setTextColor(ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        }
        mDetailName.setText(symbol);
        mDetailTotal.setText(WonderfulToastUtils.getString(this,R.string.text_total_deal) + "(" + historyOrder.getBaseSymbol() + ")");
        ;
        mDetailFour.setText(WonderfulToastUtils.getString(this,R.string.text_average_price) + "(" + historyOrder.getBaseSymbol() + ")");
        mDetailFive.setText(WonderfulToastUtils.getString(this,R.string.text_volume) + "(" + historyOrder.getCoinSymbol() + ")");
        // 成交总额
        mDetailOne.setText(new BigDecimal(String.valueOf(historyOrder.getTurnover())).setScale(8, BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString());
        // 成交均价
        if (historyOrder.getTradedAmount() == 0.0 || historyOrder.getTurnover() == 0.0) {
            mDetailTwo.setText(String.valueOf(0.0));
        } else {
            mDetailTwo.setText(new BigDecimal(String.valueOf(historyOrder.getTurnover() / historyOrder.getTradedAmount())).setScale(8, BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString());
        }
        // 成交量
        mDetailThree.setText(new BigDecimal(String.valueOf(historyOrder.getTradedAmount())).toPlainString());
//        // 成交时间
//        String[] times = WonderfulDateUtils.getFormatTime(null, new Date(historyOrder.getTime())).split(" ");
//        mDetailTime.setText(times[0].substring(5,times[0].length()) + " " + times[1].substring(0,5));
//        // 成交价
//        mDetailPrice.setText(String.valueOf(historyOrder.getPrice()) + historyOrder.getBaseSymbol());
//        // 成交量
//        mDetailNum.setText(String.valueOf(historyOrder.getAmount()) + historyOrder.getCoinSymbol());
//        // 手续费
//        mDetailChg.setText(String.valueOf(historyOrder.getTurnover() + historyOrder.getBaseSymbol()));
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }
}
