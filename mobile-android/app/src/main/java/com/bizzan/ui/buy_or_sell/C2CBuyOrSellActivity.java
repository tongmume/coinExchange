package com.bizzan.ui.buy_or_sell;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;

import com.bizzan.R;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.ui.my_order.MyOrderActivity;
import com.bizzan.adapter.TextWatcher;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.C2C;
import com.bizzan.entity.C2CExchangeInfo;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulMathUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class C2CBuyOrSellActivity extends BaseActivity implements C2CBuyOrSellContract.View, OrderConfirmDialogFragment.OperateCallback {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.ivHeader)
    ImageView ivHeader;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvLimit)
    TextView tvLimit;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvExchangeCount)
    TextView tvExchangeCount;
    @BindView(R.id.tvCommentPercent)
    TextView tvCommentPercent;
    @BindView(R.id.tvDoneHistory)
    TextView tvDoneHistory;
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.tvLocalCoinText)
    TextView tvLocalCoinText;
    @BindView(R.id.etLocalCoin)
    EditText etLocalCoin;
    @BindView(R.id.tvOtherCoinText)
    TextView tvOtherCoinText;
    @BindView(R.id.etOtherCoin)
    EditText etOtherCoin;
    @BindView(R.id.tvBuy)
    TextView tvBuy;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.tvMyOrder)
    TextView tvMyOrder;
    @BindView(R.id.ivZhifubao)
    ImageView ivZhifubao;
    @BindView(R.id.ivWeChat)
    ImageView ivWeChat;
    @BindView(R.id.ivUnionPay)
    ImageView ivUnionPay;
    @BindView(R.id.tvRemainAmount)
    TextView tvRemainAmount;
    @BindView(R.id.tvTradeAmount)
    TextView tvTradeAmount;
    @BindView(R.id.view_back)
    View view_back;
    /*@BindView(R.id.tvContact)
    TextView tvContact;*/
    private C2C.C2CBean c2cBean;
    private C2CExchangeInfo c2CExchangeInfo;
    private C2CBuyOrSellContract.Presenter presenter;
    private String mode = "0";

    private TextWatcher localChangeWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            localCoinChange();
        }
    };
    private TextWatcher otherChangeWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            otherCoinChange();
        }
    };

    public static void actionStart(Context context, C2C.C2CBean c2CBean) {
        Intent intent = new Intent(context, C2CBuyOrSellActivity.class);
        intent.putExtra("c2cBean", c2CBean);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_c2_cbuy;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new C2CBuyOrSellPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etLocalCoin.addTextChangedListener(localChangeWatcher);
        etOtherCoin.addTextChangedListener(otherChangeWatcher);
        tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog();
            }
        });
        tvMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrderActivity.actionStart(C2CBuyOrSellActivity.this, 0);
            }
        });
    }


    private void showConfirmDialog() {
        WonderfulLogUtils.logi("C2CBuyOrSellActivity", "   mode    =   " + mode);
        if (!MyApplication.getApp().isLogin()) {
            startActivityForResult(new Intent(this, LoginActivity.class), LoginActivity.RETURN_LOGIN);
            return;
        }
        String countStr = etOtherCoin.getText().toString();
        if (WonderfulStringUtils.isEmpty(countStr)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.tv_fill_number));
            return;
        }
        double count = Double.parseDouble(countStr);
        String totalStr = etLocalCoin.getText().toString();
        if (WonderfulStringUtils.isEmpty(totalStr)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.tv_fill_number));
            return;
        }
        double total = Double.parseDouble(totalStr);
        OrderConfirmDialogFragment orderConfirmDialogFragment = OrderConfirmDialogFragment.getInstance(c2cBean.getAdvertiseType(), c2CExchangeInfo.getUnit(), c2CExchangeInfo.getPrice(), count, total);
        orderConfirmDialogFragment.show(getSupportFragmentManager(), "orderConfirm");
    }

    @Override
    public void buyOrSell() {
        if (c2cBean == null || c2CExchangeInfo == null) return;
        String id = c2cBean.getAdvertiseId() + "";
        String coinId = c2CExchangeInfo.getOtcCoinId() + "";
        String price = c2CExchangeInfo.getPrice() + "";
        String money = etLocalCoin.getText().toString();
        String amount = etOtherCoin.getText().toString();
        String remark = "";
        if ("0".equals(c2cBean.getAdvertiseType()))
            presenter.c2cSell(getToken(), id, coinId, price, money, amount, remark, mode);
        else presenter.c2cBuy(getToken(), id, coinId, price, money, amount, remark, mode);
    }

    private void otherCoinChange() {
        mode = "1";
        etLocalCoin.removeTextChangedListener(localChangeWatcher);
        String otherStr = etOtherCoin.getText().toString();
        if (WonderfulStringUtils.isEmpty(otherStr) || c2cBean.getPrice() == 0)
            etLocalCoin.setText("0");
        else
            etLocalCoin.setText(WonderfulMathUtils.getRundNumber(Double.parseDouble(otherStr) * c2CExchangeInfo.getPrice(), 2, null));
        etLocalCoin.addTextChangedListener(localChangeWatcher);
    }

    private void localCoinChange() {
        if (c2CExchangeInfo == null) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.No_ads));
            presenter.c2cInfo(c2cBean.getAdvertiseId());
            return;
        }
        etOtherCoin.removeTextChangedListener(otherChangeWatcher);
        String localStr = etLocalCoin.getText().toString();
        mode = "0";
        if (WonderfulStringUtils.isEmpty(localStr) || c2cBean.getPrice() == 0)
            etOtherCoin.setText("0");
        else
            etOtherCoin.setText(WonderfulMathUtils.getRundNumber(Double.parseDouble(localStr) / c2CExchangeInfo.getPrice(), 8, null));
        etOtherCoin.addTextChangedListener(otherChangeWatcher);
    }

    @Override
    protected void obtainData() {
        c2cBean = (C2C.C2CBean) getIntent().getSerializableExtra("c2cBean");
    }

    @Override
    protected void fillWidget() {
        if ("0".equals(c2cBean.getAdvertiseType())) {
            tvTitle.setText(WonderfulToastUtils.getString(this,R.string.text_chu_sho) + c2cBean.getUnit());
            tvInfo.setText(WonderfulToastUtils.getString(this,R.string.text_much_sale));
        } else {
            tvTitle.setText(WonderfulToastUtils.getString(this,R.string.text_gou_mai) + c2cBean.getUnit());
            tvInfo.setText(WonderfulToastUtils.getString(this,R.string.text_much_buy));
        }
        setButtonText();
    }

    private void setButtonText() {
        if ("0".equals(c2cBean.getAdvertiseType())) {
            if (MyApplication.getApp().isLogin()) {
                //tvContact.setVisibility(View.VISIBLE);
                tvBuy.setText(WonderfulToastUtils.getString(this,R.string.text_chu_sho));
            } else {
                //tvContact.setVisibility(View.GONE);
                tvBuy.setText(WonderfulToastUtils.getString(this,R.string.text_to_login));
            }
        } else {
            if (MyApplication.getApp().isLogin()) {
                //tvContact.setVisibility(View.VISIBLE);
                tvBuy.setText(WonderfulToastUtils.getString(this,R.string.text_gou_mai));
            } else {
                //tvContact.setVisibility(View.GONE);
                tvBuy.setText(WonderfulToastUtils.getString(this,R.string.text_to_login));
            }
        }

    }

    @Override
    protected void loadData() {
        presenter.c2cInfo(c2cBean.getAdvertiseId());
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LoginActivity.RETURN_LOGIN:
                setButtonText();
                break;
            default:
        }
    }

    @Override
    public void setPresenter(C2CBuyOrSellContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void c2cInfoSuccess(C2CExchangeInfo obj) {
        if (obj == null) return;
        this.c2CExchangeInfo = obj;
        fillViews();
    }

    private void fillViews() {
        tvLimit.setText(WonderfulToastUtils.getString(this,R.string.text_quota) + "" + c2CExchangeInfo.getMinLimit() + "~" + c2CExchangeInfo.getMaxLimit() + "CNY");
        tvOtherCoinText.setText(c2CExchangeInfo.getUnit());
        if (!WonderfulStringUtils.isEmpty(c2cBean.getAvatar()))
            Glide.with(this).load(c2cBean.getAvatar()).into(ivHeader);
        else Glide.with(this).load(R.mipmap.icon_default_header).into(ivHeader);
        tvName.setText(c2CExchangeInfo.getUsername());
        tvTradeAmount.setText(c2CExchangeInfo.getTransactions() + "");
//        item.getTransactions()
        List<String> pays = Arrays.asList(c2CExchangeInfo.getPayMode().split(","));
        if (pays.contains("支付宝")) ivZhifubao.setVisibility(View.VISIBLE);
        else ivZhifubao.setVisibility(View.GONE);
        if (pays.contains("微信")) ivWeChat.setVisibility(View.VISIBLE);
        else ivWeChat.setVisibility(View.GONE);
        if (pays.contains("银联") || (pays.contains("银行卡"))) ivUnionPay.setVisibility(View.VISIBLE);
        else ivUnionPay.setVisibility(View.GONE);
        tvPrice.setText(c2CExchangeInfo.getPrice() + "CNY");
        tvMessage.setText(c2CExchangeInfo.getRemark());
        tvExchangeCount.setText(WonderfulToastUtils.getString(this,R.string.text_deal_num) + c2CExchangeInfo.getTransactions());
//        tvRemainAmount.setText(WonderfulToastUtils.getString(this,R.string.text_surplus_num)+new BigDecimal(c2CExchangeInfo.getMaxTradableAmount()));
        tvRemainAmount.setText(WonderfulToastUtils.getString(this,R.string.text_surplus_num) + "" + c2CExchangeInfo.getNumber());
    }

    @Override
    public void c2cInfoFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void c2cBuySuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        MyOrderActivity.actionStart(C2CBuyOrSellActivity.this, 0);
    }

    @Override
    public void c2cBuyFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void c2cSellSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        MyOrderActivity.actionStart(C2CBuyOrSellActivity.this, 0);
    }

    @Override
    public void c2cSellFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}