package com.bizzan.ui.wallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bizzan.R;
import com.bizzan.adapter.SelectCoinsAdapter;
import com.bizzan.adapter.WalletListAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.WalletConstract;
import com.bizzan.ui.extract.ExtractActivity;
import com.bizzan.ui.recharge.RechargeActivity;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

public class SelectCoinActivity extends BaseActivity {

    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.rv_coins)
    RecyclerView rvCoins;
    private List<WalletConstract> constracts;
    private SelectCoinsAdapter adapter;


    public static void actionStart(Context context, List<WalletConstract> constracts) {
        Intent intent = new Intent(context, SelectCoinActivity.class);
        intent.putExtra("constract", (Serializable) constracts);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_select_coin;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        constracts = (List<WalletConstract>) getIntent().getSerializableExtra("constract");

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCoins.setLayoutManager(manager);
        adapter = new SelectCoinsAdapter(R.layout.adapter_select_coins, constracts);
        adapter.isFirstOnly(true);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().post(new WalletMessage(constracts.get(position).getContractCoin().getCoinSymbol()));
                OverturnActivity.actionStart(SelectCoinActivity.this);
            }
        });
        rvCoins.setAdapter(adapter);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }

    @OnClick({R.id.ibBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void WalletMessage(String coin) {

    }
}