package com.bizzan.ui.wallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import com.bizzan.R;
import com.bizzan.adapter.WalletListAdapter;
import com.bizzan.app.UrlFactory;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.Coin;
import com.bizzan.ui.extract.ExtractActivity;
import com.bizzan.ui.recharge.RechargeActivity;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;

import okhttp3.Request;

public class WalletListActivity extends BaseActivity {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.view_back)
    View viewBack;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.rv_currency)
    RecyclerView rvCurrency;
    private List<Coin> list;
    private WalletListAdapter adapter;
    private int type; // 1 充币  2 提币

    public static void actionStart(Context context, List<Coin> coins, int type) {
        Intent intent = new Intent(context, WalletListActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putInt("type", type);
        mBundle.putSerializable("coins", (Serializable) coins);
        intent.putExtras(mBundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_wallet_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        list = (List<Coin>) intent.getSerializableExtra("coins");
        type = intent.getIntExtra("type", 0);
        if (type == 2) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCoin().getCanWithdraw() == 0) {
                    list.remove(i);
                }
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCoin().getCanRecharge() == 0) {
                    list.remove(i);
                }
            }
        }
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCurrency.setLayoutManager(manager);
        adapter = new WalletListAdapter(R.layout.adapter_wallet_list, list);
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        adapter.isFirstOnly(true);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (type == 1) {//充币
                    if (list.get(position).getCoin().getCanRecharge() == 0) {
                        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(WalletListActivity.this, R.string.notChargeMoneyTip));
                    } else {
                        if (WonderfulStringUtils.isEmpty(list.get(position).getAddress())) {
                            huoQu(list.get(position));
                        } else {
                            RechargeActivity.actionStart(WalletListActivity.this, list.get(position));
                        }
                    }
                } else if (type == 2) {//提币
                    if (list.get(position).getCoin().getCanWithdraw() == 0) {
                        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(WalletListActivity.this, R.string.notMentionMoneyTip));
                    } else {
                        ExtractActivity.actionStart(WalletListActivity.this, list.get(position));
                    }
                }
            }
        });
        rvCurrency.setAdapter(adapter);
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
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }

    public void huoQu(final Coin coin) {
        if (coin.getCoin().getAccountType() != 1) {
            WonderfulOkhttpUtils.post().url(UrlFactory.getChongbi())
                    .addHeader("x-auth-token", SharedPreferenceInstance.getInstance().getTOKEN())
                    .addParams("unit", coin.getCoin().getUnit())
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    super.onError(request, e);
                }

                @Override
                public void onResponse(String response) {
                    // WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.noAddAddressTip));
                    WonderfulLogUtils.logi("miao", response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        int code = jsonObject.optInt("code");
                        if (code == 0) {
                            RechargeActivity.actionStart(WalletListActivity.this, coin);
                        } else {
                            WonderfulToastUtils.showToast("" + jsonObject.optString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            RechargeActivity.actionStart(WalletListActivity.this, coin);
        }
    }

    @OnClick(R.id.ibBack)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.ibBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                break;
        }
    }
}