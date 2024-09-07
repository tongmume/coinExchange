package com.bizzan.ui.ctc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.bizzan.R;
import com.bizzan.adapter.CTCOrderAdapter;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.Injection;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.CTCOrder;
import com.bizzan.entity.CTCOrderDetail;
import com.bizzan.entity.CTCPayType;
import com.bizzan.entity.CTCPrice;
import com.bizzan.entity.SafeSetting;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.ui.message_detail.MessageHelpActivity;
import com.bizzan.utils.IMyTextChange;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

public class CTCActivity  extends BaseActivity implements CTCContract.View{
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.view_back)
    View view_back;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.llBuy)
    LinearLayout llBuy;
    @BindView(R.id.llSell)
    LinearLayout llSell;

    @BindView(R.id.tabBuy)
    LinearLayout tabBuy;
    @BindView(R.id.tabSell)
    LinearLayout tabSell;

    @BindView(R.id.buyPrice)
    TextView buyPrice;
    @BindView(R.id.buyAmount)
    EditText buyAmount;
    @BindView(R.id.llPayTypeBuy)
    LinearLayout llPayTypeBuy;
    @BindView(R.id.tvPayTypeBuy)
    TextView tvPayTypeBuy;
    @BindView(R.id.tvBuyTotal)
    TextView tvBuyTotal;
    @BindView(R.id.btnBuy)
    Button btnBuy;
    @BindView(R.id.tv_buy_tab)
    TextView tv_buy_tab;
    @BindView(R.id.buy_tab_underline)
    View buy_tab_underline;

    @BindView(R.id.sellPrice)
    TextView sellPrice;
    @BindView(R.id.sellAmount)
    EditText sellAmount;
    @BindView(R.id.llPayTypeSell)
    LinearLayout llPayTypeSell;
    @BindView(R.id.tvPayTypeSell)
    TextView tvPayTypeSell;
    @BindView(R.id.tvSellTotal)
    TextView tvSellTotal;
    @BindView(R.id.btnSell)
    Button btnSell;
    @BindView(R.id.tv_sell_tab)
    TextView tv_sell_tab;
    @BindView(R.id.sell_tab_underline)
    View sell_tab_underline;

    @BindView(R.id.rvDetail)
    RecyclerView rvDetail;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.tvTradeKnow)
    TextView tvTradeKnow;

    TextView tvSendCode = null;
    EditText etPhoneCode = null;
    EditText etAssetPwd = null;

    private List<CTCOrderDetail> orderDetailList = new ArrayList<>();
    private CTCOrderAdapter adapter;

    private CountDownTimer timer;

    private String[] payTypeArr;
    private List<String> payTypeList = new ArrayList<>();
    private int pageNo = 1;
    private int pageSize = 20;
    private String buyPayType = "bank";
    private String sellPayType = "bank";
    private int direction = 0;// 0:买入  1：卖出
    private String fundpwd = "";
    private String phoneCode = "";
    private int amount = 0;

    private String[] payTypeBankArr;
    private List<String> payTypeBankList = new ArrayList<>();

    private CTCContract.Presenter presenter;

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, CTCActivity.class);
        activity.startActivity(intent);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CTCActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_ctc;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new CTCPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
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

        llBuy.setVisibility(View.VISIBLE);
        llSell.setVisibility(View.GONE);

        payTypeArr = getResources().getStringArray(R.array.ctc_payType);
        for (int i = 0; i < payTypeArr.length; i++) {
            CTCPayType name = new CTCPayType();
            name.setPayName(payTypeArr[i]);
            payTypeList.add(name.getPayName());
        }
        llPayTypeBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPayTypeBuy();
            }
        });

        payTypeBankArr = getResources().getStringArray(R.array.ctc_payTypeBank);
        for (int i = 0; i < payTypeBankArr.length; i++) {
            CTCPayType name = new CTCPayType();
            name.setPayName(payTypeBankArr[i]);
            payTypeBankList.add(name.getPayName());
        }
        llPayTypeSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPayTypeSell();
            }
        });

        // 初始化付款/收款方式
        tvPayTypeBuy.setText(payTypeList.get(0));
        tvPayTypeSell.setText(payTypeBankList.get(0));

        // Tab 切换
        tabBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchBuySell(0);
            }
        });
        tabSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchBuySell(1);
            }
        });

        // 设置Hint文字字体
        SpannableString ss = new SpannableString(WonderfulToastUtils.getString(this,R.string.ctc_amount_tips));//定义hint的值
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(13,true);//设置字体大小 true表示单位是sp
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        buyAmount.setHint(new SpannedString(ss));
        sellAmount.setHint(new SpannedString(ss));

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBuyClick();
            }
        });

        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSellClick();
            }
        });

        buyAmount.addTextChangedListener(new IMyTextChange() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if(WonderfulStringUtils.isEmpty(buyAmount.getText().toString())) {
                    tvBuyTotal.setText("0.00");
                }else{
                    BigDecimal price = new BigDecimal(buyPrice.getText().toString());
                    BigDecimal cAmount = new BigDecimal(buyAmount.getText().toString());
                    tvBuyTotal.setText(price.multiply(cAmount).setScale(2).toString());
                }
            }
        });

        sellAmount.addTextChangedListener(new IMyTextChange() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if(WonderfulStringUtils.isEmpty(sellAmount.getText().toString())) {
                    tvSellTotal.setText("0.00");
                }else{
                    BigDecimal price = new BigDecimal(sellPrice.getText().toString());
                    BigDecimal cAmount = new BigDecimal(sellAmount.getText().toString());

                    tvSellTotal.setText(price.multiply(cAmount).setScale(2).toString());
                }
            }
        });

        // 交易须知
        tvTradeKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageHelpActivity.actionStart(CTCActivity.this, GlobalConstant.CTC_TRADEARTICLE_ID);
            }
        });
    }
    private void refresh() {
        pageNo = 1;
        presenter.ctcOrderList(getToken(), pageNo, pageSize);
        adapter.setEnableLoadMore(false);
        adapter.loadMoreEnd(false);
    }
    /**
     * 买入按钮点击事件
     */
    private void btnBuyClick(){
        if (!MyApplication.getApp().isLogin()) {
            startActivityForResult(new Intent(this, LoginActivity.class), LoginActivity.RETURN_LOGIN);
            return;
        }
        if(WonderfulStringUtils.isEmpty(buyAmount.getText().toString())){
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.ctc_buy_amount_tips));
            return;
        }
        BigDecimal cAmount = new BigDecimal(buyAmount.getText().toString());
        if(cAmount.compareTo(BigDecimal.valueOf(50000)) > 0){
            buyAmount.setText("50000");
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.ctc_amount_tips));
            return;
        }
        if(cAmount.compareTo(BigDecimal.valueOf(50)) < 0){
            buyAmount.setText("50");
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.ctc_amount_tips));
            return;
        }

        amount = Integer.parseInt(buyAmount.getText().toString());
        showConfirmDialog();
    }

    /**
     * 卖出按钮点击事件
     */
    private void btnSellClick(){
        if (!MyApplication.getApp().isLogin()) {
            startActivityForResult(new Intent(this, LoginActivity.class), LoginActivity.RETURN_LOGIN);
            return;
        }
        if(WonderfulStringUtils.isEmpty(sellAmount.getText().toString())){
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.ctc_sell_amount_tips));
            return;
        }
        BigDecimal cAmount = new BigDecimal(sellAmount.getText().toString());
        if(cAmount.compareTo(BigDecimal.valueOf(50000)) > 0){
            sellAmount.setText("50000");
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.ctc_amount_tips));
            return;
        }
        if(cAmount.compareTo(BigDecimal.valueOf(50)) < 0){
            sellAmount.setText("50");
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.ctc_amount_tips));
            return;
        }
        amount = Integer.parseInt(sellAmount.getText().toString());

        showConfirmDialog();
    }

    /**
     * 短信验证码与资金密码输入确认弹出框
     */
    private void showConfirmDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_assetpwd, null);
        TextView tvDialogYes = view.findViewById(R.id.tv_dialog_yes);
        TextView tvDialogNo = view.findViewById(R.id.tv_dialog_no);
        etPhoneCode = view.findViewById(R.id.etPhoneCode);
        etAssetPwd = view.findViewById(R.id.etAssetsPwd);
        tvSendCode = view.findViewById(R.id.tvSendCode);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        tvSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode();
            }
        });
        tvDialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fundpwd = etAssetPwd.getText().toString();
                phoneCode = etPhoneCode.getText().toString();
                if (WonderfulStringUtils.isEmpty(fundpwd)) {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(CTCActivity.this,R.string.paymentTip6));
                    return;
                }
                if (WonderfulStringUtils.isEmpty(phoneCode)) {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(CTCActivity.this,R.string.code_shuru));
                    return;
                }
                dialog.hide();
                newCtcOrder();
            }
        });
        tvDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
    }
    @Override
    protected void obtainData() {

    }
    private void sendCode() {
        tvSendCode.setEnabled(false);
        presenter.ctcSendNewOrderPhoneCode(getToken());
    }
    @Override
    protected void fillWidget() {
        initRvDetail();
    }

    private void initRvDetail() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDetail.setLayoutManager(manager);
        adapter = new CTCOrderAdapter(orderDetailList);
        View emptyView = getLayoutInflater().inflate(R.layout.empty_no_order, null);
        ((TextView) emptyView.findViewById(R.id.tvMessage)).setText(WonderfulToastUtils.getString(this,R.string.detailTableViewTip));
        adapter.bindToRecyclerView(rvDetail);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, rvDetail);
        adapter.setEmptyView(emptyView);
    }
    private void loadMore() {
        if(!MyApplication.getApp().isLogin()){
            return;
        }
        refreshLayout.setEnabled(false);
        pageNo++;
        presenter.ctcOrderList(SharedPreferenceInstance.getInstance().getTOKEN(), pageNo, pageSize);
    }
    @Override
    protected void loadData() {
        if (MyApplication.getApp().isLogin()) {
            loginingViewText();
        } else {
            notLoginViewText();
        }
        // 获取买卖价
        presenter.ctcPrice();
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
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LoginActivity.RETURN_LOGIN:
                if (resultCode == Activity.RESULT_OK && MyApplication.getApp().isLogin()) {
                    loginingViewText();
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    notLoginViewText();
                }
                break;
            default:
        }
    }
    private void notLoginViewText() {
        btnBuy.setText(WonderfulToastUtils.getString(this,R.string.text_xian_login));
        btnSell.setText(WonderfulToastUtils.getString(this,R.string.text_xian_login));

        adapter.setEnableLoadMore(false);
        refreshLayout.setEnabled(false);
    }
    private void loginingViewText() {
        btnBuy.setText(WonderfulToastUtils.getString(this,R.string.text_buy_coin)+"USDT");
        btnSell.setText(WonderfulToastUtils.getString(this,R.string.text_sale_coin)+"USDT");

        // 加载数据
        presenter.safeSetting(getToken()); // 用户安全设定
        //  加载用户订单数据
        presenter.ctcOrderList(getToken(), pageNo, pageSize);

        // 设置下拉刷新事件
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void newCtcOrder(){
        BigDecimal nPrice = direction == 0 ? new BigDecimal(buyPrice.getText().toString()) : new BigDecimal(sellPrice.getText().toString());
        BigDecimal nAmount = direction == 0 ? new BigDecimal(buyAmount.getText().toString()) : new BigDecimal(sellAmount.getText().toString());
        String nPayType = direction == 0 ? buyPayType : sellPayType;
        presenter.ctcNewOrder(getToken(), nPrice, nAmount, nPayType, direction, "USDT", fundpwd, phoneCode);
    }

    private void switchBuySell(int side){
        if(side == 0) {
            direction = 0;
            llBuy.setVisibility(View.VISIBLE);
            llSell.setVisibility(View.GONE);
            tv_buy_tab.setSelected(true);
            buy_tab_underline.setVisibility(View.VISIBLE);

            tv_sell_tab.setSelected(false);
            sell_tab_underline.setVisibility(View.GONE);
        }else{
            direction = 1;
            llBuy.setVisibility(View.GONE);
            llSell.setVisibility(View.VISIBLE);
            tv_buy_tab.setSelected(false);
            buy_tab_underline.setVisibility(View.GONE);

            tv_sell_tab.setSelected(true);
            sell_tab_underline.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 选择买入时的付款方式
     */
    private void showDialogPayTypeBuy() {
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(CTCActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tvPayTypeBuy.setText(payTypeList.get(options1));
                if(options1 == 0) {
                    buyPayType = "bank";
                }
                if(options1 == 1) {
                    buyPayType = "alipay";
                }
                if(options1 == 2) {
                    buyPayType = "wechatpay";
                }
            }
        }).setSubmitColor(Color.parseColor("#f0a70a"))//确定按钮文字颜色
                .setTitleText(WonderfulToastUtils.getString(this,R.string.text_ad_fu_kuan)).setTitleColor(Color.WHITE)
                .setCancelColor(Color.parseColor("#666666"))
                .setTextColorCenter(Color.parseColor("#f0a70a"))
                .setTitleBgColor(getResources().getColor(R.color.primaryBackNormal))
                .setBgColor(getResources().getColor(R.color.primaryBackNormal))
                .build();
        pvOptions.setPicker(payTypeList);//取消按钮文字颜色;
        pvOptions.show();
    }

    /**
     * 选择卖出时的收款方式
     */
    private void showDialogPayTypeSell() {
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(CTCActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tvPayTypeSell.setText(payTypeBankList.get(options1));
                if(options1 == 0) {
                    sellPayType = "bank";
                }
                if(options1 == 1) {
                    sellPayType = "alipay";
                }
                if(options1 == 2) {
                    sellPayType = "wechatpay";
                }
            }
        }).setSubmitColor(Color.parseColor("#f0a70a"))//确定按钮文字颜色
                .setTitleText(WonderfulToastUtils.getString(this,R.string.text_clear_fu_kuan)).setTitleColor(Color.WHITE)
                .setCancelColor(Color.parseColor("#666666"))
                .setTextColorCenter(Color.parseColor("#f0a70a"))
                .setTitleBgColor(getResources().getColor(R.color.primaryBackNormal))
                .setBgColor(getResources().getColor(R.color.primaryBackNormal))
                .build();
        pvOptions.setPicker(payTypeBankList);//取消按钮文字颜色;
        pvOptions.show();
    }
    @Override
    public void setPresenter(CTCContract.Presenter presenter) {
        this.presenter = presenter;
    }
    private void fillCodeView(long time) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvSendCode.setText(WonderfulToastUtils.getString(CTCActivity.this,R.string.re_send)+"(" + millisUntilFinished / 1000 + "s)");
                tvSendCode.setEnabled(false);
            }

            @Override
            public void onFinish() {
                tvSendCode.setText(WonderfulToastUtils.getString(CTCActivity.this,R.string.send_code));
                tvSendCode.setEnabled(true);
                timer.cancel();
                timer = null;
            }
        };
        timer.start();
    }
    @Override
    public void safeSettingSuccess(SafeSetting obj) {

    }


    @Override
    public void ctcOrderListSuccess(CTCOrder obj) {
        if (refreshLayout == null) {
            return;
        }
        adapter.setEnableLoadMore(true);
        adapter.loadMoreComplete();
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        if (obj == null) {
            return;
        }
        if (pageNo == 1) {
            this.orderDetailList.clear();
        } else if (obj.getContent().size() == 0) {
            adapter.loadMoreEnd();
        }
        this.orderDetailList.addAll(obj.getContent());
        adapter.notifyDataSetChanged();
        adapter.disableLoadMoreIfNotFullPage();

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CTCOrderDetailActivity.show(CTCActivity.this, orderDetailList.get(position));
            }
        });
    }


    @Override
    public void ctcOrderDetailSuccess(CTCOrderDetail obj) {

    }


    @Override
    public void ctcOrderPaySuccess(CTCOrderDetail obj) {

    }


    @Override
    public void ctcOrderCancelSuccess(CTCOrderDetail obj) {

    }

    @Override
    public void ctcPriceSuccess(CTCPrice obj) {
        buyPrice.setText(obj.getBuy().setScale(2).toString());
        sellPrice.setText(obj.getSell().setScale(2).toString());
    }

    @Override
    public void ctcNewOrderSuccess(CTCOrderDetail obj) {
        pageNo = 1;
        presenter.ctcOrderList(SharedPreferenceInstance.getInstance().getTOKEN(), pageNo, pageSize);
        CTCOrderDetailActivity.show(CTCActivity.this, obj);
    }

    @Override
    public void ctcSendNewOrderPhoneCodeSuccess(String obj) {
        fillCodeView(90L * 1000);
    }

    // 错误处理
    @Override
    public void ctcSendNewOrderPhoneCodeFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
        tvSendCode.setEnabled(true);
    }
    @Override
    public void ctcNewOrderFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
    @Override
    public void ctcPriceFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
    @Override
    public void ctcOrderCancelFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
    @Override
    public void ctcOrderPayFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
    @Override
    public void ctcOrderDetailFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
    @Override
    public void ctcOrderListFail(Integer code, String toastMessage) {
        if (refreshLayout==null){
            return;
        }
        adapter.setEnableLoadMore(true);
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
    @Override
    public void safeSettingFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

}
