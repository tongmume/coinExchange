package com.bizzan.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.entrust.TrustListActivity;
import com.bizzan.ui.home.presenter.ThreeTextImpl;
import com.bizzan.ui.kline.KlineActivity;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.adapter.SellAdapter;
import com.bizzan.adapter.TrustCurrentAdapter;
import com.bizzan.adapter.TrustHistoryAdapter;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseTransFragment;
import com.bizzan.ui.dialog.BBConfirmDialogFragment;
import com.bizzan.ui.dialog.EntrustOperateDialogFragment;
import com.bizzan.entity.Currency;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.entity.Exchange;
import com.bizzan.entity.Favorite;
import com.bizzan.entity.Money;
import com.bizzan.entity.TextItems;
import com.bizzan.app.UrlFactory;
import com.bizzan.socket.ISocket;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.serivce.SocketMessage;
import com.bizzan.serivce.SocketResponse;
import com.bizzan.utils.IMyTextChange;
import com.bizzan.utils.LoadDialog;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulMathUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import com.xw.repo.BubbleSeekBar;

import org.angmarch.views.NiceSpinner;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * @author: wuzongjie
 * time  : 2018/4/17 0017 15:20
 */


public class ThreeTextFragment extends BaseTransFragment implements com.bizzan.ui.home.MainContract.ThreeView, RadioGroup.OnCheckedChangeListener, IThreeTextContract.View, View.OnClickListener {
    public static final String TAG = ThreeTextFragment.class.getSimpleName();
    @BindView(R.id.ibOpen)
    ImageButton ibOpen;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.text_title_price)
    TextView text_title_price;
    @BindView(R.id.text_title_amount)
    TextView text_title_amount;
    @BindView(R.id.ibMessage)
    ImageView ibMessage;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.mRadioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.nice_spinner)
    NiceSpinner mNiceSpinner; // 下拉
    @BindView(R.id.text_to_all)
    RelativeLayout mToAllLayout; // 全部
    @BindView(R.id.recyclerOne)
    RecyclerView mOneRecycler; // 卖出的
    @BindView(R.id.recyclerTwo)
    RecyclerView mTwoRecycler; //买进
    @BindView(R.id.recyclerThree)
    RecyclerView mThreeRecycler; // 当前委托
    @BindView(R.id.mTvThree)
    TextView mTvThree;
    @BindView(R.id.mOneLayout)
    LinearLayout mOneLayout; // 买入
    @BindView(R.id.mTwoLayout)
    LinearLayout mTwoLayout; // 卖出
    @BindView(R.id.mOneShi)
    TextView mOneShi;
    @BindView(R.id.mOneXian)
    LinearLayout mOneXian;
    @BindView(R.id.mTwoShi)
    TextView mTwoShi;
    @BindView(R.id.mTwoXian)
    LinearLayout mTwoXian;
    @BindView(R.id.mOnePrice)
    EditText mOnePriceEdit;
    @BindView(R.id.mOneAdd)
    TextView mOneAdd; // +
    @BindView(R.id.mOneSum)
    TextView mOneSub; // -
    @BindView(R.id.mTwoPrice)
    EditText mTwoPriceEdit;
    @BindView(R.id.mTwoAdd)
    TextView mTwoAdd; // +
    @BindView(R.id.mTwoSub)
    TextView mTwoSub; // -
    @BindView(R.id.mTvPanJia)
    TextView mPanJia;
    @BindView(R.id.mTvMoney)
    TextView mPanMoney;
    @BindView(R.id.mOneYuE)
    TextView mOneYuE;
    @BindView(R.id.mTwoYuE)
    TextView mTwoYuE;
    @BindView(R.id.mTvOneBuy)
    TextView mOneBuy;
    @BindView(R.id.mOneTCP)
    EditText mOneTcpEdit;
    @BindView(R.id.mTwoTCP)
    EditText mTwoTcpEdit;
    @BindView(R.id.mTvTwoBuy)
    TextView mTwoBuy;
    @BindView(R.id.btnOneBuy)
    Button btnBuy;
    @BindView(R.id.btnTwoPost)
    Button btnSale;
    @BindView(R.id.mOneDeal)
    TextView mOneDeal; // 交易额
    @BindView(R.id.mTwoDeal)
    TextView mTwoDeal;
    @BindView(R.id.btn_toLogin)
    TextView btnLogin;
    @BindView(R.id.mOneTextType)
    TextView mOneTextType;
    @BindView(R.id.mTwoTextType)
    TextView mTwoTextType;
    @BindView(R.id.mOneJiaoYi)
    LinearLayout mOneJiaoYi;
    @BindView(R.id.mTwoJiaoYi)
    LinearLayout mTwoJiaoYi;
    @BindView(R.id.mOneSeekBar)
    BubbleSeekBar mOneSeekBar;
    @BindView(R.id.mTwoSeekBar)
    BubbleSeekBar mTwoSeekBar;
    Unbinder unbinder;
    @BindView(R.id.ivCollect)
    ImageView ivCollect;
    @BindView(R.id.text_one_kaishi)
    TextView text_one_kaishi;
    @BindView(R.id.text_one_jieshu)
    TextView text_one_jieshu;
    @BindView(R.id.text_two_kaishi)
    TextView text_two_kaishi;
    @BindView(R.id.text_two_jieshu)
    TextView text_two_jieshu;
    @BindView(R.id.mTabOne)
    RadioButton mTabOne;
    @BindView(R.id.mTabTwo)
    RadioButton mTabTwo;
    @BindView(R.id.ll_current_trust)
    LinearLayout llCurrentTrust;
    @BindView(R.id.ll_history_trust)
    LinearLayout llHistoryTrust;
    @BindView(R.id.tv_current_trust)
    TextView tvCurrentTrust;
    @BindView(R.id.tv_history_trust)
    TextView tvHistoryTrust;
    @BindView(R.id.current_trust_underline)
    View currentTrustUnderline;
    @BindView(R.id.history_trust_underline)
    View historyTrustUnderline;
    /* @BindView(R.id.vp_trust)
     ViewPager vpTrust;*/
    @BindView(R.id.iv_check_box_one)
    ImageView iv_check_box_one;
    @BindView(R.id.iv_check_box_two)
    ImageView iv_check_box_two;
    private String maichu;
    int pageSize = 5;
    int currentPage = 1;
    int historyPage = 1;
    int startPage = 1;
    private List<Currency> currencies = new ArrayList<>();
    private Currency currency;
    private List<Exchange> mOne = new ArrayList<>();
    private List<Exchange> mTow = new ArrayList<>();
    private SellAdapter mOneAdapter; // 买入适配器
    private SellAdapter mTwoAdapter; // 卖出适配器
    private IThreeTextContract.Presenter mPresenter;
    private int oneAccuracy = 2; // 价格
    private int twoAccuracy = 2; // 数量
    private String oldSymbol; // 上个订阅的币种
    private Boolean isSeekBar = false; // 上个订阅的币种
    private boolean isFace = false;

    String buttonTextBuy = "";
    String buttonTextSell = "";

    private MainContract.ThreePresenter presenter;

    public ThreeTextFragment() {
    }

    @Override
    public void getAccuracy(final int one, final int two) {
        twoAccuracy = one; // 数量
        oneAccuracy = two; // 价格
        if (mOneAdapter == null || mTwoAdapter == null) {
            return;
        }
        mOneAdapter.setText(new SellAdapter.myText() {
            @Override
            public int one() {
                return one;
            }

            @Override
            public int two() {
                return two;
            }
        });
        mOneAdapter.notifyDataSetChanged();
        mTwoAdapter.setText(new SellAdapter.myText() {
            @Override
            public int one() {
                return one;
            }

            @Override
            public int two() {
                return two;
            }
        });
        mTwoAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frgment_text_three;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        tvCurrentTrust.setSelected(true);
        if (mPresenter == null) {
            mPresenter = new ThreeTextImpl(this);
        }
        // 打开左侧的滑动
        ibOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).show_coins();
                ((MainActivity) getActivity()).getDlRoot().openDrawer(Gravity.LEFT);
            }
        });
        for (int i = 0; i < 5; i++) {
            mOne.add(new Exchange(5 - i, "--", "--"));
            mTow.add(new Exchange(i, "--", "--"));
        }
        final LinkedList<CharSequence> linkedList = new LinkedList<>(Arrays.asList(getActivity().getResources().getTextArray(R.array.text_type)));
        mNiceSpinner.attachDataSource(linkedList);
        mNiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) { // 限价
                    type = "LIMIT_PRICE";
                    mOneXian.setVisibility(View.VISIBLE);
                    mTwoXian.setVisibility(View.VISIBLE);
                    mTwoShi.setVisibility(View.GONE);
                    mOneShi.setVisibility(View.GONE);
                    mOneJiaoYi.setVisibility(View.VISIBLE);
                    mTwoJiaoYi.setVisibility(View.VISIBLE);
                    if (currency != null)
                        mOneTextType.setText(currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
                    mTwoTcpEdit.setHint(getActivity().getResources().getText(R.string.text_number));
                    mOneTcpEdit.setHint(getActivity().getResources().getText(R.string.text_number));
                    mOneBuy.setVisibility(View.VISIBLE);
                    mTwoBuy.setVisibility(View.VISIBLE);
                } else { // 市价
                    type = "MARKET_PRICE";
                    mOneXian.setVisibility(View.GONE);
                    mTwoXian.setVisibility(View.GONE);
                    mTwoShi.setVisibility(View.VISIBLE);
                    mOneShi.setVisibility(View.VISIBLE);
                    mOneJiaoYi.setVisibility(View.INVISIBLE);
                    mTwoJiaoYi.setVisibility(View.INVISIBLE);
                    if (currency != null)
                        mOneTextType.setText(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()));
                    mTwoTcpEdit.setHint(getActivity().getResources().getText(R.string.text_number));
                    mOneTcpEdit.setHint(getActivity().getResources().getText(R.string.text_entrust));
                    mOneBuy.setVisibility(View.INVISIBLE);
                    mTwoBuy.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mToAllLayout.setOnClickListener(this);
        mRadioGroup.setOnCheckedChangeListener(this);
        mOneAdd.setOnClickListener(this);
        mOneSub.setOnClickListener(this);
        mTwoAdd.setOnClickListener(this);
        mTwoSub.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
        btnSale.setOnClickListener(this);
        ibMessage.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        llCurrentTrust.setOnClickListener(this);
        llHistoryTrust.setOnClickListener(this);
        //iv_check_box_one.setOnClickListener(this);
        //iv_check_box_two.setOnClickListener(this);
        // 买入价格的变化
        mOnePriceEdit.addTextChangedListener(new IMyTextChange() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (currency == null) return;
                try {
                    mOneTextPrice = Double.valueOf(mOnePriceEdit.getText().toString());
                } catch (Exception e) {
                    mOneTextPrice = 0;
                }
                if (type.equals("LIMIT_PRICE")) { // 限价
                    if ("CNY".equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                        mOneMyText = mOneText * mOneTextPrice;
                        mOneBuy.setText(String.valueOf("≈" + WonderfulMathUtils.getRundNumber(mOneTextPrice * 1,
                                2, null) + "CNY"));
                    } else {
                        mOneMyText = mOneText * mOneTextPrice * MainActivity.rate;
                        mOneBuy.setText(String.valueOf("≈" + WonderfulMathUtils.getRundNumber(mOneTextPrice * 1 * MainActivity.rate * currency.getBaseUsdRate(),
                                2, null) + "CNY"));
                    }
                    if (!TextUtils.isEmpty(mOneTcpEdit.getText())) {
                        mOneDeal.setText(String.valueOf(WonderfulMathUtils.getRundNumber(mul(mOneTextPrice * mOneText, 1), 5, null)
                                + " " + currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length())));
                    }
                }
                String temp = editable.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - (posDot + 1) > oneAccuracy) {
                    editable.delete(posDot + 1 + oneAccuracy, posDot + 2 + oneAccuracy);
                }
            }
        });

        // 卖出价格的变化
        mTwoPriceEdit.addTextChangedListener(new IMyTextChange() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (currency == null) return;
                try {
                    mTwoTextPrice = Double.valueOf(mTwoPriceEdit.getText().toString());

                } catch (Exception e) {
                    mTwoTextPrice = 0;
                }
                if (type.equals("LIMIT_PRICE")) {
                    if ("CNY".equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                        mTwoBuy.setText(String.valueOf("≈" + WonderfulMathUtils.getRundNumber(mTwoTextPrice * 1,
                                2, null) + "CNY"));
                        mTwoMyText = mTwoText * mTwoTextPrice;
                    } else {
                        mTwoBuy.setText(String.valueOf("≈" + WonderfulMathUtils.getRundNumber(mTwoTextPrice * 1 * MainActivity.rate * currency.getBaseUsdRate(),
                                2, null) + "CNY"));
                        mTwoMyText = mTwoText * mTwoTextPrice * MainActivity.rate;
                    }
                    if (!TextUtils.isEmpty(mTwoTcpEdit.getText())) {
                        mTwoDeal.setText(String.valueOf(WonderfulMathUtils.getRundNumber(mul(mTwoText * mTwoTextPrice, 1), 5, null) + " " +
                                currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length())));
                    }
                }
                String temp = editable.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - (posDot + 1) > oneAccuracy) {
                    editable.delete(posDot + 1 + oneAccuracy, posDot + 2 + oneAccuracy);
                }
            }
        });
        // 买入数量的变化
        mOneTcpEdit.addTextChangedListener(new IMyTextChange() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (currency == null) return;
                try {
                    mOneText = Double.valueOf(mOneTcpEdit.getText().toString());
                    if (mOneText <= 0) {
                        text_one_kaishi.setText("0.00");
                    } else {
                        text_one_kaishi.setText(mOneTcpEdit.getText().toString());
                    }

                } catch (Exception e) {
                    mOneText = 0;
                }
                if (type.equals("LIMIT_PRICE")) { // 限价
                    if ("CNY".equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                        mOneMyText = mOneText * mOneTextPrice * currency.getBaseUsdRate();
                    } else {
                        mOneMyText = mOneText * mOneTextPrice * MainActivity.rate * currency.getBaseUsdRate();
                    }
                    mOneDeal.setText(String.valueOf(WonderfulMathUtils.getRundNumber(mul(mOneTextPrice * mOneText, 1), 5, null)
                            + " " + currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length())));
                    if (!WonderfulStringUtils.isEmpty(mOneTcpEdit.getText().toString()) && !WonderfulStringUtils.isEmpty(mOnePriceEdit.getText().toString()) && mOneTcpEdit.isFocused()) {
                        try {
                            Double d = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null)) / Double.valueOf(mOnePriceEdit.getText().toString());
                            Double d2 = Double.valueOf(mOneTcpEdit.getText().toString());
                            Float aFloat = Float.valueOf(WonderfulMathUtils.getRundNumber(d2 / d, 2, null)) * 100;
                            if (aFloat < 0) {
                                aFloat = 0f;
                            }
                            isSeekBar = false;
                            if (aFloat > 100) {
                                WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_big_buy) + "");
                            }
                            mOneSeekBar.setProgress(aFloat >= 100 ? 100 : aFloat);
                            text_one_jieshu.setText(mOneSeekBar.getProgress() + "%");
                        } catch (Exception e) {

                        }

                    }
                } else {
                    mOneDeal.setText(String.valueOf("--"));
                    if (!WonderfulStringUtils.isEmpty(mOneTcpEdit.getText().toString())) {
                        try {
                            Double d = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null));
                            Double d2 = Double.valueOf(mOneTcpEdit.getText().toString());
                            Float aFloat = Float.valueOf(WonderfulMathUtils.getRundNumber(d2 / d, 2, null)) * 100;
                            if (aFloat < 0) {
                                aFloat = 0f;
                            }
                            isSeekBar = false;
                            mOneSeekBar.setProgress(aFloat >= 100 ? 100 : aFloat);
                            text_one_jieshu.setText(mOneSeekBar.getProgress() + "%");
                        } catch (Exception e) {

                        }

                    }
                }
                String temp = editable.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - (posDot + 1) > twoAccuracy) {
                    editable.delete(posDot + 1 + twoAccuracy, posDot + 2 + twoAccuracy);
                }
            }
        });

        //mOneSeekBar.requestFocus();
        mOneSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
//                WonderfulLogUtils.logi("ThreeTextFragment", "    onProgressChanged");
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                isSeekBar = true;
                if (currency != null) {
                    if (type.equals("LIMIT_PRICE")) {
                        if (!mOneTextType.getText().equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                            if (!WonderfulStringUtils.isEmpty(mOnePriceEdit.getText().toString()) && isSeekBar) {
                                try {
                                    Double aDouble = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null));
                                    aDouble = Double.valueOf(new BigDecimal(aDouble).setScale(5, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                                    Double d = aDouble / Double.valueOf(mOnePriceEdit.getText().toString());
                                    d = Double.valueOf(new BigDecimal(d).setScale(twoAccuracy, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                                    if (aDouble <= 0) {
                                        return;
                                    }


                                    String s = WonderfulMathUtils.getRundNumber(d * progress / 100.3, twoAccuracy, null);
                                    if (twoAccuracy == 0) {
                                        s = s.substring(0, s.length() - 1);
                                        mOneTcpEdit.setText(s);
                                    } else {
                                        mOneTcpEdit.setText("" + new BigDecimal(d * progress / 100.5).setScale(twoAccuracy, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                                    }
                                    mOneSeekBar.setProgress(progress);

                                    text_one_jieshu.setText(progress + "%");
                                    WonderfulLogUtils.logi("ThreeTextFragment", "限价dddd3333   " + progress);
                                    WonderfulLogUtils.logi("ThreeTextFragment", "限价dddd3333   " + Double.valueOf(new BigDecimal(d * progress / 100.5).setScale(twoAccuracy, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString()));
                                } catch (Exception e) {

                                }

                            } else {
                                try {
                                    Double d = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null));
                                    d = Double.valueOf(new BigDecimal(d).setScale(twoAccuracy, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                                    mOneTcpEdit.setText(new BigDecimal(d * progress / 100.5).setScale(twoAccuracy, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "");
                                    text_one_jieshu.setText(progress + "%");
                                    WonderfulLogUtils.logi("ThreeTextFragment", "限价dddd4444   " + String.valueOf(d));
                                } catch (Exception e) {

                                }

                            }
                        }
                    } else {
                        try {
                            Double d = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null));
                            d = Double.valueOf(new BigDecimal(d).setScale(twoAccuracy, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                            mOneTcpEdit.setText(new BigDecimal(d * progress / 100.5).setScale(twoAccuracy, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "");
                            text_one_jieshu.setText(progress + "%");
                        } catch (Exception e) {

                        }

                        WonderfulLogUtils.logi("ThreeTextFragment", "    市价ddddd6666");
                    }
                    WonderfulLogUtils.logi("ThreeTextFragment", " currency != null");
                }
                WonderfulLogUtils.logi("ThreeTextFragment", "    getProgressOnActionUp");
            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
//                WonderfulLogUtils.logi("ThreeTextFragment", "    getProgressOnFinally");
                //super.getProgressOnFinally(bubbleSeekBar, progress, progressFloat);
                /*if (currency != null) {
                    if (!WonderfulStringUtils.isEmpty(mOnePriceEdit.getText().toString()) && mOneSeekBar.isFocused()) {
                        if (!mOneTextType.getText().equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                            Double aDouble = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null));
                            Double d = aDouble / Double.valueOf(mOnePriceEdit.getText().toString());
                            String s = WonderfulMathUtils.getRundNumber(d * progress / 100, 4, null);
                            mOneTcpEdit.setText(s);
                            WonderfulLogUtils.logi("ThreeTextFragment", "dddd3333   " +String.valueOf(d));
                        }else {
                            Double d = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null));
                            mOneTcpEdit.setText(WonderfulMathUtils.getRundNumber(d * progress / 100, 4, null));
                            WonderfulLogUtils.logi("ThreeTextFragment", "dddd4444   " +String.valueOf(d));
                        }
                    }
                }*/
            }
        });

        // 卖出数量的变化
        mTwoTcpEdit.addTextChangedListener(new IMyTextChange() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (currency == null) return;
                try {
                    mTwoText = Double.valueOf(mTwoTcpEdit.getText().toString());
                    if (mTwoText <= 0) {
                        text_two_kaishi.setText("0.0");
                    } else {
                        text_two_kaishi.setText(mTwoTcpEdit.getText().toString());
                    }
                } catch (Exception e) {
                    mTwoText = 0;
                }
                if (type.equals("LIMIT_PRICE")) {
                    if ("CNY".equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                        mTwoMyText = mTwoText * mTwoTextPrice * currency.getBaseUsdRate();
                    } else {
                        mTwoMyText = mTwoText * mTwoTextPrice * MainActivity.rate * currency.getBaseUsdRate();
                    }
                    mTwoDeal.setText(String.valueOf(WonderfulMathUtils.getRundNumber(mul(mTwoText * mTwoTextPrice, 1), 5, null)
                            + currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length())));
                    if (!WonderfulStringUtils.isEmpty(mTwoTcpEdit.getText().toString()) && !WonderfulStringUtils.isEmpty(mTwoPriceEdit.getText().toString()) && mTwoTcpEdit.isFocused()) {
                        try {
                            Double d = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null)) / Double.valueOf(mTwoPriceEdit.getText().toString());
                            Double d2 = Double.valueOf(mTwoTcpEdit.getText().toString());
//                            Float aFloat = Float.valueOf(WonderfulMathUtils.getRundNumber(d2 / d, 2, null)) * 100;
                            double v = Double.valueOf(new BigDecimal(d2).toPlainString().toString()) / Double.valueOf(new BigDecimal(maichu).toPlainString().toString());
                            Float aFloat = Float.valueOf(new BigDecimal(v).setScale(2, BigDecimal.ROUND_DOWN).toPlainString().toString()) * 100;
                            //String.valueOf(WonderfulMathUtils.getRundNumber(obj.getData().getBalance(), 8, null)
                            WonderfulLogUtils.logi("miao1", "aFloat:" + new BigDecimal(maichu).toPlainString().toString());
                            WonderfulLogUtils.logi("miao1", "aFloat2:" + new BigDecimal(v).setScale(2, BigDecimal.ROUND_DOWN).toPlainString().toString());
                            if (aFloat < 0) {
                                aFloat = 0f;
                            } else if (aFloat > 100) {
                                WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_big_buy) + "");
                                aFloat = 100f;
                            }
                            isSeekBar = false;
                            mTwoSeekBar.setProgress(aFloat);
                            text_two_jieshu.setText(mTwoSeekBar.getProgress() + "%");
                            WonderfulLogUtils.logi("miao1", "mTwoSeekBar:" + mTwoSeekBar.getProgress());
                        } catch (Exception e) {

                        }

                    }
                } else {
                    mTwoDeal.setText(String.valueOf("--"));
                    if (!WonderfulStringUtils.isEmpty(mTwoTcpEdit.getText().toString())) {
                        try {
                            Double d = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null));
                            Double d2 = Double.valueOf(mTwoTcpEdit.getText().toString());
                            Float aFloat = Float.valueOf(WonderfulMathUtils.getRundNumber(d2 / d, 2, null)) * 100;
                            if (aFloat < 0) {
                                aFloat = 0f;
                            }
                            isSeekBar = false;
                            mTwoSeekBar.setProgress(aFloat >= 100 ? 100 : aFloat);
                        } catch (Exception e) {

                        }

                    }
                }
                String temp = editable.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - (posDot + 1) > twoAccuracy) {
                    editable.delete(posDot + 1 + twoAccuracy, posDot + 2 + twoAccuracy);
                }
            }
        });

        mTwoSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                WonderfulLogUtils.logi("ThreeTextFragment", "    progress" + progress);
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                isSeekBar = true;
                if (currency != null) {
                    if (type.equals("LIMIT_PRICE")) {
                        if (!mTwoTextType.getText().equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                            if (!WonderfulStringUtils.isEmpty(mTwoPriceEdit.getText().toString()) && isSeekBar) {
                                try {

                                    Double aDouble = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance2, 5, null));
                                    Double d = aDouble / Double.valueOf(mTwoPriceEdit.getText().toString());
                                    if (aDouble <= 0) {
                                        mTwoSeekBar.setProgress(0f);
                                        return;
                                    }


                                    String s = WonderfulMathUtils.getRundNumber(aDouble * progress / 100, twoAccuracy, null);
                                    if (twoAccuracy == 0) {
                                        s = s.substring(0, s.length() - 1);
                                    }

                                    WonderfulLogUtils.logd("miao", new BigDecimal(aDouble * progress / 100).setScale(twoAccuracy, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
//                                    WonderfulLogUtils.logd("miao",Double.parseDouble(s.substring(0,s.indexOf(".")+twoAccuracy))+"---");
                                    mTwoTcpEdit.setText(new BigDecimal(aDouble * progress / 100).setScale(twoAccuracy, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                                    mTwoSeekBar.setProgress(progress);
                                    text_two_jieshu.setText(progress + "%");
                                } catch (Exception e) {

                                }


                            } else {
                                try {
                                    Double d = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance2, 5, null));
                                    mTwoTcpEdit.setText(WonderfulMathUtils.getRundNumber(d * progress / 100, twoAccuracy, null));
                                    mTwoSeekBar.setProgress(progress);
                                    text_two_jieshu.setText(progress + "%");
                                } catch (Exception e) {

                                }


                            }
                        }
                    } else {
                        try {
                            Double d = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null));
                            mTwoTcpEdit.setText(WonderfulMathUtils.getRundNumber(d * progress / 100, twoAccuracy, null));
                            text_two_jieshu.setText(progress + "%");
                        } catch (Exception e) {

                        }


                    }

                }

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
//                WonderfulLogUtils.logi("ThreeTextFragment", "    getProgressOnFinally");
                //super.getProgressOnFinally(bubbleSeekBar, progress, progressFloat);
                /*if (currency != null) {
                    if (!WonderfulStringUtils.isEmpty(mOnePriceEdit.getText().toString()) && mOneSeekBar.isFocused()) {
                        if (!mOneTextType.getText().equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                            Double aDouble = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null));
                            Double d = aDouble / Double.valueOf(mOnePriceEdit.getText().toString());
                            String s = WonderfulMathUtils.getRundNumber(d * progress / 100, 4, null);
                            mOneTcpEdit.setText(s);
                            WonderfulLogUtils.logi("ThreeTextFragment", "dddd3333   " +String.valueOf(d));
                        }else {
                            Double d = Double.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 5, null));
                            mOneTcpEdit.setText(WonderfulMathUtils.getRundNumber(d * progress / 100, 4, null));
                            WonderfulLogUtils.logi("ThreeTextFragment", "dddd4444   " +String.valueOf(d));
                        }
                    }
                }*/
            }
        });
        mPanJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == 0) {
                    mOnePriceEdit.setText(mPanJia.getText());
                } else {
                    mTwoPriceEdit.setText(mPanJia.getText());
                }
            }
        });

        ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.isAgain = true;
                if (isFace) { // 已经收藏 则删除
                    delete(SharedPreferenceInstance.getInstance().getTOKEN(), currency.getSymbol());
                } else {
                    if (MyApplication.getApp().isConnect()) {
                        getCollect(SharedPreferenceInstance.getInstance().getTOKEN(), currency.getSymbol());
                    } else {

                    }

                }
            }
        });
        mTabOne.setSelected(true);
        initRecyclerView();
        initPlate();
    }

    private List<EntrustHistory> mCurrentData = new ArrayList<>();
    private List<EntrustHistory> mHistoryData = new ArrayList<>();
    TrustCurrentAdapter trustCurrentAdapter = new TrustCurrentAdapter(mCurrentData);
    TrustHistoryAdapter trustHistoryAdapter = new TrustHistoryAdapter(mHistoryData);

    private void initRecyclerView() {
        mThreeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mThreeRecycler.setAdapter(trustCurrentAdapter);
        mThreeRecycler.setNestedScrollingEnabled(false);
        trustCurrentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showBottomFragment((EntrustHistory) adapter.getData().get(position));
            }
        });

    }

    private boolean addFace(String symbol) {
        for (Favorite favorite : MainActivity.mFavorte) {
            if (symbol.equals(favorite.getSymbol())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除收藏
     *
     * @param token
     * @param symbol
     */
    private void delete(String token, String symbol) {
        if (!MyApplication.getApp().isLogin()) {
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_xian_login) + "");
            return;
        }
        showDialog();
        WonderfulOkhttpUtils.post().url(UrlFactory.getDeleteUrl()).addHeader("x-auth-token", token)
                .addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                hideDialog();
                WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_cancel_fail) + "");
            }

            @Override
            public void onResponse(String response) {
                hideDialog();
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_cancel_success) + "");
                        isFace = false;
                        ivCollect.setSelected(isFace);
                        ((MainActivity) getmActivity()).Find();
                    } else {
                        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_cancel_fail) + "");
                    }
                } catch (JSONException e) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_cancel_fail) + "");
                }
            }
        });
    }

    /**
     * 添加收藏
     *
     * @param token
     * @param symbol
     */
    private void getCollect(String token, String symbol) {
        if (!MyApplication.getApp().isLogin()) {
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_xian_login) + "");
            return;
        }
        showDialog();
        WonderfulOkhttpUtils.post().url(UrlFactory.getAddUrl()).addHeader("x-auth-token", token)
                .addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                hideDialog();
                WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_add_fail) + "");
            }

            @Override
            public void onResponse(String response) {
                hideDialog();
                try {
                    JSONObject object = new JSONObject(response);
                    WonderfulLogUtils.logi("jiejie", "  getCollect  " + response);
                    if (object.optInt("code") == 0) {
                        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_add_success) + "");
                        isFace = true;
                        ivCollect.setSelected(isFace);
                        ((MainActivity) getmActivity()).Find();
                    } else {
                        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_add_fail) + "");
                    }
                } catch (JSONException e) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_add_fail) + "");
                }
            }
        });
    }


    private void setPriceNull() {
        mOnePriceEdit.setText(null);
        mTwoPriceEdit.setText(null);
        mOneTcpEdit.setText(null);
        mTwoTcpEdit.setText(null);
        this.mOne.clear();
        this.mTow.clear();
        for (int i = 0; i < 5; i++) {
            mOne.add(new Exchange(5 - i, "--", "--"));
            mTow.add(new Exchange(i, "--", "--"));
        }
        mOneAdapter.setList(mOne);
        mOneAdapter.notifyDataSetChanged();

        mTwoAdapter.setList(mTow);
        mTwoAdapter.notifyDataSetChanged();
    }

    double mOneText, mTwoText, mOneTextPrice, mTwoTextPrice, mOneMyText, mTwoMyText;

    @Override
    protected void obtainData() {
        mOneRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTwoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOneAdapter = new SellAdapter(mOne, 0);
        mTwoAdapter = new SellAdapter(mTow, 1);
        mOneRecycler.setAdapter(mOneAdapter);
        mTwoRecycler.setAdapter(mTwoAdapter);
        mOneAdapter.setOnItemClickListener(listenerOne);
        mTwoAdapter.setOnItemClickListener(listenerTwo);
    }

    // 第一个Item的点击事件
    BaseQuickAdapter.OnItemClickListener listenerOne = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Exchange e = (Exchange) adapter.getData().get(position);
            if (type.equals("LIMIT_PRICE")) {
                if (!"--".equals(e.getPrice())) {
                    if (check == 0) {
                        mOnePriceEdit.setText(String.valueOf(WonderfulMathUtils.getRundNumber(Double.valueOf(e.getPrice()), oneAccuracy, null)));
                    } else {
                        mTwoPriceEdit.setText(String.valueOf(WonderfulMathUtils.getRundNumber(Double.valueOf(e.getPrice()), oneAccuracy, null)));
                    }
                }
            }
        }
    };
    // 第二个Item的点击事件
    BaseQuickAdapter.OnItemClickListener listenerTwo = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Exchange e = (Exchange) adapter.getData().get(position);
            if (type.equals("LIMIT_PRICE")) {
                if (!"--".equals(e.getPrice())) {
                    if (check == 0) {
                        mOnePriceEdit.setText(String.valueOf(WonderfulMathUtils.getRundNumber(Double.valueOf(e.getPrice()), oneAccuracy, null)));
                    } else {
                        mTwoPriceEdit.setText(String.valueOf(WonderfulMathUtils.getRundNumber(Double.valueOf(e.getPrice()), oneAccuracy, null)));
                    }
                }
            }
        }
    };

    private void showBottomFragment(EntrustHistory entrust) {
        EntrustOperateDialogFragment entrustOperateFragment =
                EntrustOperateDialogFragment.getInstance(entrust);
        entrustOperateFragment.show(getActivity().getSupportFragmentManager(), "bottom");
        entrustOperateFragment.setCallback(new EntrustOperateDialogFragment.OperateCallback() {
            @Override
            public void cancleOrder(String orderId) {
                // 撤消
                if (mPresenter != null) {
                    displayLoadingPopup();
                    mPresenter.getCancelEntrust(SharedPreferenceInstance.getInstance().getTOKEN(), orderId);
                }
            }
        });
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        if (MyApplication.getApp().isLogin() && TextUtils.isEmpty(SharedPreferenceInstance.getInstance().getTOKEN())) {
            MyApplication.getApp().loginAgain(this);
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_login_disabled) + "");
            return;
        }
        // 获取盘口的信息
        if (this.currency != null && !TextUtils.isEmpty(SharedPreferenceInstance.getInstance().getTOKEN())) {
            mPresenter.getExchange(currency.getSymbol());
            Log.i("Three", "获取钱包");
            mPresenter.getWallet(1, SharedPreferenceInstance.getInstance().getTOKEN(), this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
            mPresenter.getWallet(2, SharedPreferenceInstance.getInstance().getTOKEN(), currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()));
        }
        if (!MyApplication.getApp().isLogin()) {
            btnLogin.setVisibility(View.VISIBLE);
            //vpTrust.setVisibility(View.GONE);
            mThreeRecycler.setVisibility(View.GONE);
            mTvThree.setVisibility(View.GONE);
            SharedPreferenceInstance.getInstance().saveaToken("");
            SharedPreferenceInstance.getInstance().saveTOKEN("");
            mTwoYuE.setText("0.000000");
            mOneYuE.setText("0.000000");
        } else {
            System.out.println("currency:" + this.currency);
            if (MyApplication.getApp().isLogin() && this.currency != null) {
                currentPage = startPage;
                historyPage = startPage;
                setCurrentSelected();
//                mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), startPage, pageSize, this.currency.getSymbol(), "", "", "", "");
//                mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), startPage, pageSize, this.currency.getSymbol(), "", "", "", "");
            } else {
                SharedPreferenceInstance.getInstance().saveaToken("");
                SharedPreferenceInstance.getInstance().saveTOKEN("");
            }
        }
        if (currency != null) {
            isFace = addFace(this.currency.getSymbol());
            ivCollect.setSelected(isFace);
            try {
                String format = new DecimalFormat("#0.00000000").format(currency.getClose());
                BigDecimal bg = new BigDecimal(format);
                String v = bg.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
                mOnePriceEdit.setText(v);
                mTwoPriceEdit.setText(v);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tvTitle.setText(this.currency.getSymbol());
            text_title_amount.setText(getActivity().getResources().getText(R.string.number) + "\n" + "(" + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")) + ")");
            text_title_price.setText(getActivity().getResources().getText(R.string.price) + "\n" + "(" + this.currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1) + ")");
            if ("LIMIT_PRICE".equals(type))
                mOneTextType.setText(this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
            mTwoTextType.setText(this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
            buttonTextBuy = String.valueOf(getActivity().getResources().getText(R.string.text_buy) + "(" + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")) + ")");
            buttonTextSell = String.valueOf(getActivity().getResources().getText(R.string.text_sale) + "(" + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")) + ")");
            btnBuy.setText(buttonTextBuy);
            btnSale.setText(buttonTextSell);
        }
        if (currency != null) {
            try {
                String format = new DecimalFormat("#0.00000000").format(currency.getClose());
                BigDecimal bg = new BigDecimal(format);
                String v = bg.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
                mPanJia.setText(String.valueOf(v));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (currency != null) {
            mPanJia.setTextColor(currency.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) :
                    ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        }
        if (currency != null) {
            if ("CNY".equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                mPanMoney.setText(String.valueOf("≈ " + WonderfulMathUtils.getRundNumber(currency.getClose() * 1 * currency.getBaseUsdRate(),
                        2, null) + " CNY"));
            } else {
                mPanMoney.setText(String.valueOf("≈ " + WonderfulMathUtils.getRundNumber(currency.getClose() * MainActivity.rate * currency.getBaseUsdRate(),
                        2, null) + " CNY"));
            }
        }
    }


    private double mul(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.multiply(b2).doubleValue();
    }

    private JSONObject buildGetBodyJson(String symbol) {
        JSONObject obj = new JSONObject();
//        Log.i("miao","TCP："+symbol);
        try {
            obj.put("symbol", symbol);
            return obj;
        } catch (Exception ex) {
            return null;
        }
    }

    private JSONObject buildGetBodyJson(String symbol, int id) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("symbol", symbol);
            obj.put("uid", id);
            return obj;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 这里开始订阅某个币盘口的信息
     */
    private void startTCP(String symbol, int id) {
        if (id == 0) {
            // 不需要iD
            EventBus.getDefault().post(new SocketMessage(0, ISocket.CMD.SUBSCRIBE_EXCHANGE_TRADE,
                    buildGetBodyJson(symbol).toString().getBytes()));
        } else {
            // 需要ID
            EventBus.getDefault().post(new SocketMessage(0, ISocket.CMD.SUBSCRIBE_EXCHANGE_TRADE,
                    buildGetBodyJson(symbol, id).toString().getBytes()));
        }
        oldSymbol = symbol;
    }

    /**
     * 这里取消某个币盘口的信息
     */
    private void stop(String symbol, int id) {
        if (id == 0) {
            EventBus.getDefault().post(new SocketMessage(0, ISocket.CMD.UNSUBSCRIBE_EXCHANGE_TRADE,
                    buildGetBodyJson(symbol).toString().getBytes()));
        } else {
            EventBus.getDefault().post(new SocketMessage(0, ISocket.CMD.UNSUBSCRIBE_EXCHANGE_TRADE,
                    buildGetBodyJson(symbol, id).toString().getBytes()));
        }

    }

    @Override
    protected String getmTag() {
        return TAG;
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(getActivity(), llTitle);
            isSetTitle = true;
        }
    }

    /**
     * 这个是从主界面(左滑动栏目)来的，表示选择了某个币种
     */
    public void resetSymbol(Currency currency) {
        this.currency = currency;
        Log.i("Three", "执行函数：resetSymbol" + this.currency.getSymbol());
        if (this.currency != null) {
            setCurrentSelected();
            text_one_kaishi.setText("0.00");
            mOneDeal.setText("--");
            text_one_jieshu.setText("0");
            mOneSeekBar.setProgress(0f);
            mTwoSeekBar.setProgress(0f);
            text_two_kaishi.setText("0");
            text_two_jieshu.setText("0");
            mTwoDeal.setText("--");


            setPriceNull();
            String format2 = new DecimalFormat("#0.00000000").format(currency.getClose());
            BigDecimal bg2 = new BigDecimal(format2);
            String v2 = bg2.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            mOnePriceEdit.setText(v2);
            mTwoPriceEdit.setText(v2);
            // 获取盘口信息
            mPresenter.getExchange(this.currency.getSymbol());
//            Log.i("miao","币种："+currency.getSymbol());
            // 获取当前币种精确度
            mPresenter.getSymbolInfo(this.currency.getSymbol());
            // 如果是登录状态
            if (!WonderfulStringUtils.isEmpty(SharedPreferenceInstance.getInstance().getTOKEN())) {
                // 获取这个币的多少
                mPresenter.getWallet(1, SharedPreferenceInstance.getInstance().getTOKEN(), this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
                // 这里请求USDT多少，但是不用每次都请求故取消了
                if (usdeBalance != -1 && "USDT".equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                    // 如果是USDT的并且usdeBalance
                    mPresenter.getWallet(2, SharedPreferenceInstance.getInstance().getTOKEN(), this.currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, this.currency.getSymbol().length()));
//                    mOneYuE.setText(String.valueOf(WonderfulMathUtils.getRundNumber(usdeBalance, 8, null) + currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length())));
                    WonderfulLogUtils.logi("miao", "mOneYuE17" + mOneYuE.getText().toString());
//                    text_one_jieshu.setText(mOneYuE.getText().toString());
                } else {
                    WonderfulLogUtils.logi("miao", "mOneYuE6");
                    mPresenter.getWallet(2, SharedPreferenceInstance.getInstance().getTOKEN(), this.currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, this.currency.getSymbol().length()));
                }
                // 获取该币的委托信息
                currentPage = startPage;
                historyPage = startPage;
                mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), startPage, pageSize, this.currency.getSymbol(), "", "", "", "");
//                mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), startPage, pageSize, this.currency.getSymbol(), "", "", "", "");
            }
            // 修改标题
            isFace = addFace(this.currency.getSymbol());
            ivCollect.setSelected(isFace);
            tvTitle.setText(this.currency.getSymbol());
            text_title_amount.setText(getActivity().getResources().getText(R.string.number) + "\n" + "(" + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")) + ")");
            text_title_price.setText(getActivity().getResources().getText(R.string.price) + "\n" + "(" + this.currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1) + ")");
            if ("LIMIT_PRICE".equals(type))
                mOneTextType.setText(this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
            mTwoTextType.setText(this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
            buttonTextBuy = String.valueOf(getActivity().getResources().getText(R.string.text_buy) + "(" + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")) + ")");
            buttonTextSell = String.valueOf(getActivity().getResources().getText(R.string.text_sale) + "(" + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")) + ")");
            btnBuy.setText(buttonTextBuy);
            btnSale.setText(buttonTextSell);
            String format = new DecimalFormat("#0.00000000").format(currency.getClose());
            BigDecimal bg = new BigDecimal(format);
            String v = bg.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            mPanJia.setText(String.valueOf(v));
//            mPanJia.setText(String.valueOf(currency.getClose()));
            mPanJia.setTextColor(currency.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) :
                    ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
            if ("CNY".equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                mPanMoney.setText(String.valueOf("≈" + WonderfulMathUtils.getRundNumber(currency.getClose() * 1 * currency.getBaseUsdRate(),
                        2, null) + "CNY"));
            } else {
                mPanMoney.setText(String.valueOf("≈" + WonderfulMathUtils.getRundNumber(currency.getClose() * MainActivity.rate * currency.getBaseUsdRate(),
                        2, null) + "CNY"));
            }
            // 代表选择了哪个币种，需要重新订阅 应该先取消原来的再订阅现在的
            if (!TextUtils.isEmpty(oldSymbol)) {
                stop(oldSymbol, getmActivity().getId());
            }
            startTCP(currency.getSymbol(), getmActivity().getId());
        }
    }

    // K线页面跳转
    public void showPageFragment(String symbol, int pageNo) {
        if (this.currencies == null) {
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.find_error) + "");
            return;
        }

        // this.page = pageNo;
        for (Currency currency : currencies) {
            if (symbol.equals(currency.getSymbol())) {
                this.currency = currency;
                break;
            }
        }
        if (this.currency != null) {
            try {
                tvTitle.setText(symbol);
                text_title_amount.setText(getActivity().getResources().getText(R.string.number) + "\n" + "(" + symbol.substring(0, symbol.indexOf("/")) + ")");
                text_title_price.setText(getActivity().getResources().getText(R.string.price) + "\n" + "(" + symbol.substring(symbol.indexOf("/") + 1) + ")");
                if ("LIMIT_PRICE".equals(type))
                    mOneTextType.setText(symbol.substring(0, currency.getSymbol().indexOf("/")));
                mTwoTextType.setText(symbol.substring(0, currency.getSymbol().indexOf("/")));

                mOneTcpEdit.setText(null);
                mTwoTcpEdit.setText(null);
                mOneSeekBar.setProgress(0f);
                text_one_jieshu.setText("0.00%");
                text_one_kaishi.setText("0.00");

                mTwoSeekBar.setProgress(0f);
                text_two_jieshu.setText("0.00%");
                text_two_kaishi.setText("0.00");

                //btnBuy.setText(String.valueOf(getActivity().getResources().getText(R.string.text_buy) + symbol.substring(0, currency.getSymbol().indexOf("/"))));
                //btnSale.setText(String.valueOf(getActivity().getResources().getText(R.string.text_sale) + symbol.substring(0, currency.getSymbol().indexOf("/"))));
                // 获取盘口
                mPresenter.getExchange(this.currency.getSymbol());
                // 获取盘口精确度
                mPresenter.getSymbolInfo(this.currency.getSymbol());

                // 代表选择了哪个币种，需要重新订阅 应该先取消原来的再订阅现在的
                if (!TextUtils.isEmpty(oldSymbol)) {
                    stop(oldSymbol, getmActivity().getId());
                }
                startTCP(this.currency.getSymbol(), getmActivity().getId());
                if (pageNo == 0) { // 买入
                    mRadioGroup.check(R.id.mTabOne);
                } else { //卖出
                    mRadioGroup.check(R.id.mTabTwo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        Log.i("Three", "执行结束：showPageFragement:"+this.currency.getSymbol());
//        vpExchange.setCurrentItem(pageNo);
    }

    public void setCurrencyInfo(List<Currency> currencies) {
        this.currencies.clear();
        this.currencies.addAll(currencies);
        this.currency = currencies.get(0);
    }

    /**
     * 这个是刚开始
     */
    public void initPlate() {
        if (this.currency != null) {
            // 获取盘口
            mPresenter.getExchange(this.currency.getSymbol());
            // 获取盘口精确度
            mPresenter.getSymbolInfo(this.currency.getSymbol());
            if (!TextUtils.isEmpty(oldSymbol)) stop(oldSymbol, getmActivity().getId());
            startTCP(currency.getSymbol(), getmActivity().getId());
        }
    }

    public void tcpNotify() {
        if (currency != null) {
            String format = new DecimalFormat("#0.00000000").format(currency.getClose());
            BigDecimal bg = new BigDecimal(format);
            String v = bg.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            if (mPanJia != null) {
                mPanJia.setText(String.valueOf(v));
//            mPanJia.setText(String.valueOf(currency.getClose()));
                mPanJia.setTextColor(currency.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) :
                        ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
            }
            if (mPanMoney != null) {
                if ("CNY".equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
                    mPanMoney.setText(String.valueOf("≈ " + WonderfulMathUtils.getRundNumber(currency.getClose() * 1 * currency.getBaseUsdRate(),
                            2, null) + " CNY"));
                } else {
                    mPanMoney.setText(String.valueOf("≈ " + WonderfulMathUtils.getRundNumber(currency.getClose() * MainActivity.rate * currency.getBaseUsdRate(),
                            2, null) + " CNY"));
                }
            }
        }
    }

    private int check = 0;

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.mTabOne: // 点击买入
                check = 0;
                mOneLayout.setVisibility(View.VISIBLE);
                mTwoLayout.setVisibility(View.GONE);
                mTabOne.setSelected(true);
                mTabTwo.setSelected(false);
                break;
            case R.id.mTabTwo: // 点击卖出
                check = 1;
                mOneLayout.setVisibility(View.GONE);
                mTwoLayout.setVisibility(View.VISIBLE);
                mTabOne.setSelected(false);
                mTabTwo.setSelected(true);
                break;
            default:
        }
    }

    @Override
    public void errorMes(int e, String msg) {

        //mTvThree.setVisibility(View.INVISIBLE);
        if (e == 4000) {
            MyApplication.getApp().setCurrentUser(null);
            if (!MyApplication.getApp().isLogin()) {
                btnLogin.setVisibility(View.VISIBLE);
                //vpTrust.setVisibility(View.GONE);
                mThreeRecycler.setVisibility(View.GONE);
                mTvThree.setVisibility(View.GONE);
                mTwoYuE.setText("0.000000");
                mOneYuE.setText("0.000000");
                SharedPreferenceInstance.getInstance().saveaToken("");
                SharedPreferenceInstance.getInstance().saveTOKEN("");

            } else {
                if (MyApplication.getApp().isLogin() && this.currency != null) {
                    currentPage = startPage;
                    historyPage = startPage;
                    mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), startPage, pageSize, this.currency.getSymbol(), "", "", "", "");
//                mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), startPage, pageSize, this.currency.getSymbol(), "", "", "", "");
                }
            }
        } else {
            WonderfulCodeUtils.checkedErrorCode(getmActivity(), e, msg);
        }

        //mTvThree.setVisibility(this.mHistoryData.size() == 0 ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 提交委托后返回的信息(买入或者卖出成功)
     */
    @Override
    public void getDataLoad(int code, String message) {
        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.put_list_ok) + "");
        if (code == 0) {
//            setPriceNull();
            mOneTcpEdit.setText(null);
            mTwoTcpEdit.setText(null);
            mOneSeekBar.setProgress(0f);
            text_one_jieshu.setText("0.00%");
            text_one_kaishi.setText("0.00");

            mTwoSeekBar.setProgress(0f);
            text_two_jieshu.setText("0.00%");
            text_two_kaishi.setText("0.00");
            // 提交委托成功
            if (MyApplication.getApp().isLogin() && this.currency != null) {
                // 重新获取当前订单
                currentPage = startPage;
                historyPage = startPage;
                if (isCurrent) {
                    mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currentPage, pageSize, this.currency.getSymbol(), "", "", "", "");
                } else {
                    mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), historyPage, pageSize, this.currency.getSymbol(), "", "", "", "");
                }

                // 重新刷新下盘口信息  也许是不用的
                mPresenter.getExchange(currency.getSymbol());
                // 应该还需要刷新下钱包的接口
                mPresenter.getWallet(1, SharedPreferenceInstance.getInstance().getTOKEN(), this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
                mPresenter.getWallet(2, SharedPreferenceInstance.getInstance().getTOKEN(), currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()));
            }
        }
    }

    private LoadDialog mDialog;

    @Override
    public void showDialog() {
        if (mDialog == null) {
            mDialog = new LoadDialog(getmActivity());
        }
        mDialog.show();
    }

    @Override
    public void hideDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void getCancelFail() {
        hideLoadingPopup();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideDialog();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
    }


    /**
     * 盘口的数据
     */
    @Override
    public void getSuccess(List<Exchange> ask, List<Exchange> bid) {
        this.mOne.clear();
        this.mTow.clear();
        for (int i = 0; i < 5; i++) {
            mOne.add(new Exchange(5 - i, "--", "--"));
            mTow.add(new Exchange(i, "--", "--"));
        }
        if (ask.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                mOne.set(4 - i, new Exchange(i + 1, ask.get(i).getPrice(), ask.get(i).getAmount()));
            }
        } else {
            for (int i = 0; i < ask.size(); i++) {
                mOne.set(4 - i, new Exchange(i + 1, ask.get(i).getPrice(), ask.get(i).getAmount()));
            }
        }
        mOneAdapter.setList(mOne);
        mOneAdapter.notifyDataSetChanged();
        if (bid.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                mTow.set(i, new Exchange(i, bid.get(i).getPrice(), bid.get(i).getAmount()));
            }
        } else {
            for (int i = 0; i < bid.size(); i++) {
                mTow.set(i, new Exchange(i, bid.get(i).getPrice(), bid.get(i).getAmount()));
            }
        }
        mTwoAdapter.setList(mTow);
        mTwoAdapter.notifyDataSetChanged();
    }

    static int TYPE_CURRENT = 1;
    static int TYPE_HISTORY = 2;

    /**
     * 当前委托
     */
    @Override
    public void getDataLoaded(List<EntrustHistory> entrusts) {
        btnLogin.setVisibility(View.GONE);
        if (entrusts == null) {
            return;
        }
        setListData(entrusts);
        WonderfulLogUtils.logd("jiejie", "--当前委托啊--" + entrusts.size());
        //trustFragments.get(0).setListData(TrustFragment.TRUST_TYPE_CURRENT, entrusts);
    }

    /**
     * 历史委托
     */
    @Override
    public void getHistorySuccess(List<EntrustHistory> entrustHistories) {
        if (entrustHistories == null) {
            return;
        }
        setListData(entrustHistories);
        //trustFragments.get(1).setListData(TrustFragment.TRUST_TYPE_HISTORY, entrustHistories);
    }

    public void setListData(List list) {
        if (list != null && list.size() > 0) {
            mThreeRecycler.setVisibility(View.VISIBLE);
            mTvThree.setVisibility(View.GONE);
            if (isCurrent) {
                mCurrentData.clear();
                mCurrentData.addAll(list);
                mThreeRecycler.setAdapter(trustCurrentAdapter);
                trustCurrentAdapter.notifyDataSetChanged();
            } else {
                mHistoryData.clear();
                mHistoryData.addAll(list);
                mThreeRecycler.setAdapter(trustHistoryAdapter);
                trustHistoryAdapter.notifyDataSetChanged();
            }
        } else {
            mThreeRecycler.setVisibility(View.GONE);
            mTvThree.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 取消某个委托成功
     */
    @Override
    public void getCancelSuccess(String success) {
        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.remove_entrust) + "");
        hideLoadingPopup();
        if (MyApplication.getApp().isLogin() && this.currency != null) {
            // 重新获取当前订单
            currentPage = startPage;
            historyPage = startPage;
            if (isCurrent) {
                mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currentPage, pageSize, this.currency.getSymbol(), "", "", "", "");
            } else {
                mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), historyPage, pageSize, this.currency.getSymbol(), "", "", "", "");
            }
            // mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currentPage, pageSize, this.currency.getSymbol(), "", "", "", "");
            // mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), historyPage, pageSize, this.currency.getSymbol(), "", "", "", "");
            // 重新刷新下盘口信息  也许是不用的
            // mPresenter.getExchange(currency.getSymbol());
            // 应该还需要刷新下钱包的接口
            mPresenter.getWallet(1, SharedPreferenceInstance.getInstance().getTOKEN(), this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
            mPresenter.getWallet(2, SharedPreferenceInstance.getInstance().getTOKEN(), currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()));
        }
    }

    /**
     * 获取钱包成功
     */
    @Override
    public void getWalletSuccess(Money obj, int type) {
//        Log.d("jiejie", type + "");
        switch (type) {
            case 1: // 可卖
                if (obj.getCode() == 0 && obj.getData() != null) {
                    Log.i("wallet", "获取可卖总数");
                    if (mTwoYuE == null) {
                        Log.i("wallet", "mTwoYuE为空");
                        return;
                    }
                    usdeBalance2 = Double.valueOf(WonderfulMathUtils.getRundNumber(obj.getData().getBalance(), 5, null));
                    mTwoYuE.setText(formatYue(BigDecimal.valueOf(obj.getData().getBalance())) +
                            this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
                    maichu = String.valueOf(WonderfulMathUtils.getRundNumber(obj.getData().getBalance(), 8, null));
//                    text_two_jieshu.setText(mTwoYuE.getText().toString());
                } else {
                    Log.i("wallet", "获取可卖总数失败");
                    mTwoYuE.setText(String.valueOf("0.000000" +
                            this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/"))));
                    maichu = "0";
//                    text_two_jieshu.setText(mTwoYuE.getText().toString());
                }
                break;
            case 2: // 可用
                if (obj.getCode() == 0 && obj.getData() != null) {
                    Log.i("wallet", "获取可用总数");
//                    if ("USDT".equals(currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()))) {
//                        usdeBalance = obj.getData().getBalance();
//                    }
                    if (mOneYuE == null) {
                        Log.i("wallet", "mOneYuE为空");
                        return;
                    }
                    usdeBalance = Double.valueOf(WonderfulMathUtils.getRundNumber(obj.getData().getBalance(), 5, null));
                    mOneYuE.setText(formatYue(BigDecimal.valueOf(obj.getData().getBalance())) + currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()));
                    WonderfulLogUtils.logi("miao", "mOneYuE1" + mOneYuE.getText().toString());
//                    text_one_jieshu.setText(mOneYuE.getText().toString());
                } else {
                    Log.i("wallet", "获取可用总数失败");
                    mOneYuE.setText(String.valueOf("0.000000" + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/"))));
//                    text_one_jieshu.setText(mOneYuE.getText().toString());
                }
                break;
            case 3:
                if (mTwoYuE == null) {
                    return;
                }
                mTwoYuE.setText(String.valueOf("0.000000" +
                        this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/"))));
                mOneYuE.setText(String.valueOf("0.000000" + currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length())));
                WonderfulLogUtils.logi("miao", "mOneYuE3" + mOneYuE.getText().toString());
//                text_one_jieshu.setText(mOneYuE.getText().toString());
//                text_two_jieshu.setText(mTwoYuE.getText().toString());
//                SharedPreferenceInstance.getInstance().saveIsNeedShowLock(false);
//                SharedPreferenceInstance.getInstance().saveLockPwd("");
//                toLoginOrCenter();
//                Log.i("miao","断开登录");
                break;
            default:
        }
    }

    // 格式化余额及可卖显示数据
    private String formatYue(BigDecimal bYuE) {
        String tem = bYuE.toPlainString();
        if (tem.length() > 8) {
            if (bYuE.compareTo(BigDecimal.valueOf(1000000)) >= 0) {
                return bYuE.setScale(0, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if (bYuE.compareTo(BigDecimal.valueOf(100000)) >= 0) {
                return bYuE.setScale(1, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if (bYuE.compareTo(BigDecimal.valueOf(10000)) >= 0) {
                return bYuE.setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if (bYuE.compareTo(BigDecimal.valueOf(1000)) >= 0) {
                return bYuE.setScale(3, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if (bYuE.compareTo(BigDecimal.valueOf(100)) >= 0) {
                return bYuE.setScale(4, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if (bYuE.compareTo(BigDecimal.valueOf(10)) >= 0) {
                return bYuE.setScale(5, BigDecimal.ROUND_DOWN).toPlainString();
            }
            if (bYuE.compareTo(BigDecimal.valueOf(1)) >= 0) {
                return bYuE.setScale(6, BigDecimal.ROUND_DOWN).toPlainString();
            }
            return bYuE.setScale(7, BigDecimal.ROUND_DOWN).toPlainString();
        } else {
            return tem;
        }
    }

    private double usdeBalance = -1;
    private double usdeBalance2 = -1;

    private void toLoginOrCenter() {
        startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_current_trust:
                setCurrentSelected();
                if (MyApplication.getApp().isLogin()) {
                    btnLogin.setVisibility(View.GONE);
                    //vpTrust.setVisibility(View.VISIBLE);
                    mThreeRecycler.setVisibility(View.VISIBLE);
                    currentPage = startPage;
                    mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currentPage, pageSize, this.currency.getSymbol(), "", "", "", "");
                    //vpTrust.setCurrentItem(0);
                } else {
                    mThreeRecycler.setVisibility(View.GONE);
                    //vpTrust.setVisibility(View.GONE);
                    btnLogin.setVisibility(View.VISIBLE);
                    mTwoYuE.setText("0.000000");
                    mOneYuE.setText("0.000000");
                    SharedPreferenceInstance.getInstance().saveaToken("");
                    SharedPreferenceInstance.getInstance().saveTOKEN("");

                }
                break;
            case R.id.ll_history_trust:
                setHistorySelected();
                if (MyApplication.getApp().isLogin()) {
                    btnLogin.setVisibility(View.GONE);
                    mThreeRecycler.setVisibility(View.VISIBLE);
                    //vpTrust.setVisibility(View.VISIBLE);
                    historyPage = startPage;
                    mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), historyPage, pageSize, this.currency.getSymbol(), "", "", "", "");
                    //vpTrust.setCurrentItem(1);
                } else {
                    mThreeRecycler.setVisibility(View.GONE);
                    //vpTrust.setVisibility(View.GONE);
                    btnLogin.setVisibility(View.VISIBLE);
                    mTwoYuE.setText("0.000000");
                    mOneYuE.setText("0.000000");
                    SharedPreferenceInstance.getInstance().saveaToken("");
                    SharedPreferenceInstance.getInstance().saveTOKEN("");
                }
                break;
            case R.id.ibMessage:
                if (this.currency != null)
                    KlineActivity.actionStart(getActivity(), this.currency.getSymbol(), "1");
                break;
            case R.id.btn_toLogin: // 点击登录
                toLoginOrCenter();
                break;
            case R.id.mOneAdd: // +
                try {
                    double num = Double.valueOf(mOnePriceEdit.getText().toString());
                    mOnePriceEdit.setText(String.valueOf(new DecimalFormat("#0.000").format(num + 0.01)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.mOneSum: // -
                try {
                    double num1 = Double.valueOf(mOnePriceEdit.getText().toString());
                    if ((num1 - 0.01) > 0) {
                        mOnePriceEdit.setText(String.valueOf(new DecimalFormat("#0.000").format(num1 - 0.01)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.mTwoAdd: // +
                try {
                    double num1 = Double.valueOf(mTwoPriceEdit.getText().toString());
                    mTwoPriceEdit.setText(String.valueOf(new DecimalFormat("#0.000").format(num1 + 0.01)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.mTwoSub: // _
                try {
                    double num1 = Double.valueOf(mTwoPriceEdit.getText().toString());
                    if ((num1 - 0.01) > 0) {
                        mTwoPriceEdit.setText(String.valueOf(new DecimalFormat("#0.000").format(num1 - 0.01)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.text_to_all: // 查看更多委托
                if (MyApplication.getApp().isLogin()) {
//                    if (currency != null) {
                    TrustListActivity.show(getActivity());
//                    }
                } else {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_xian_login) + "");
                }
                break;
            case R.id.btnOneBuy: // 买入
                Buy();
                break;
            case R.id.btnTwoPost: // 卖出
                Sale();
                break;
            default:
        }
    }

    boolean isCurrent = true;

    private void setHistorySelected() {
        if (MyApplication.getApp().isLogin()) {
            mTvThree.setVisibility(View.GONE);
            isCurrent = false;
            tvCurrentTrust.setSelected(false);
            tvHistoryTrust.setSelected(true);
            currentTrustUnderline.setVisibility(View.GONE);
            historyTrustUnderline.setVisibility(View.VISIBLE);
            historyPage = startPage;

            mHistoryData.clear();
            mThreeRecycler.setAdapter(trustHistoryAdapter);
            trustHistoryAdapter.notifyDataSetChanged();

            mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), historyPage, pageSize, this.currency.getSymbol(), "", "", "", "");
        } else {
//            WonderfulToastUtils.showToast("请去登录");
//            MyApplication.getApp().loginAgain(getmActivity());
            mTwoYuE.setText("0.000000");
            mOneYuE.setText("0.000000");
            SharedPreferenceInstance.getInstance().saveaToken("");
            SharedPreferenceInstance.getInstance().saveTOKEN("");
        }

    }

    private void setCurrentSelected() {
        if (MyApplication.getApp().isLogin()) {
            mTvThree.setVisibility(View.GONE);
            isCurrent = true;
            tvCurrentTrust.setSelected(true);
            tvHistoryTrust.setSelected(false);
            currentTrustUnderline.setVisibility(View.VISIBLE);
            historyTrustUnderline.setVisibility(View.GONE);
            currentPage = startPage;

            mCurrentData.clear();
            mThreeRecycler.setAdapter(trustCurrentAdapter);
            trustCurrentAdapter.notifyDataSetChanged();

            mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currentPage, pageSize, this.currency.getSymbol(), "", "", "", "");
        } else {
//            WonderfulToastUtils.showToast("请去登录");
//            MyApplication.getApp().loginAgain(getmActivity());
            mTwoYuE.setText("0.000000");
            mOneYuE.setText("0.000000");
            SharedPreferenceInstance.getInstance().saveaToken("");
            SharedPreferenceInstance.getInstance().saveTOKEN("");
        }

    }

    private String price; // 买入的价格
    private String amout; // 卖出的价格
    private String type = "LIMIT_PRICE";

    private void Buy() {
        if (currency == null) {
            return;
        }
        if (!MyApplication.getApp().isLogin()) {
            mTwoYuE.setText("0.000000");
            mOneYuE.setText("0.000000");
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_xian_login) + "");
            return;
        }

        BBConfirmDialogFragment fragment = null;
        switch (type) {
            case "LIMIT_PRICE":
                price = mOnePriceEdit.getText().toString();
                amout = mOneTcpEdit.getText().toString();
                if (WonderfulStringUtils.isEmpty(price, amout)) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_price_number) + "");
                    return;
                }
                if (new BigDecimal(amout).compareTo(BigDecimal.ZERO) <= 0) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_ok_buy_number) + "");
                    return;
                }
                if (new BigDecimal(price).compareTo(BigDecimal.ZERO) <= 0) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_ok_buy_price) + "");
                    return;
                }
                fragment = BBConfirmDialogFragment.getInstance(new BigDecimal(price).setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + " " + currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length())
                        , new BigDecimal(amout).setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + " " + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")),
                        String.valueOf(mOneDeal.getText().toString()), "BUY");
                break;
            case "MARKET_PRICE":
                price = "0.0";
                amout = mOneTcpEdit.getText().toString();
                if (WonderfulStringUtils.isEmpty(price, amout)) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_price_number) + "");
                    return;
                }
                if (new BigDecimal(amout).compareTo(BigDecimal.ZERO) <= 0) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_ok_buy_sum) + "");
                    return;
                }
                fragment = BBConfirmDialogFragment.getInstance(getActivity().getResources().getText(R.string.text_best_prices) + "", "--" + " " + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/"))
                        , new BigDecimal(amout).setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + " " + currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()), "BUY");
                break;
            default:
        }
        //if (mPresenter != null) mPresenter.getAddOrder(getmActivity().getToken(), currency.getSymbol(), price, amout, "BUY", type);
        fragment.show(getFragmentManager(), "bb_exchange");
        fragment.setTargetFragment(this, Integer.MAX_VALUE);
        fragment.setCallback(new BBConfirmDialogFragment.OperateCallback() {
            @Override
            public void confirm() {
                // 买入
                if (mPresenter != null) {
                    mPresenter.getAddOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currency.getSymbol(), price, amout, "BUY", type, iv_check_box_one.isSelected() ? "1" : "0");
                }
            }
        });
    }

    private void Sale() {
        if (currency == null) return;
        if (!MyApplication.getApp().isLogin()) {
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_xian_login) + "");
            mTwoYuE.setText("0.000000");
            mOneYuE.setText("0.000000");
            return;
        }
        /*
        if (MyApplication.realVerified != 1) {//1是已实名认证
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.realname));
            return;
        }
         */
        BBConfirmDialogFragment fragment = null;
        switch (type) {
            case "LIMIT_PRICE":
                price = mTwoPriceEdit.getText().toString();
                amout = mTwoTcpEdit.getText().toString();
                if (WonderfulStringUtils.isEmpty(price, amout)) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_price_number) + "");
                    return;
                }
                if (new BigDecimal(amout).compareTo(BigDecimal.ZERO) <= 0) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_ok_sell_number) + "");
                    return;
                }
                if (new BigDecimal(price).compareTo(BigDecimal.ZERO) <= 0) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_ok_sell_price) + "");
                    return;
                }
                fragment = BBConfirmDialogFragment.getInstance(price + " " + currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()),
                        amout + " " + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")),
                        String.valueOf(mTwoDeal.getText().toString()), "SELL");
                break;
            case "MARKET_PRICE":
                price = "0.0";
                amout = mTwoTcpEdit.getText().toString();
                if (WonderfulStringUtils.isEmpty(price, amout)) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_price_number) + "");
                    return;
                }
                if (new BigDecimal(amout).compareTo(BigDecimal.ZERO) <= 0) {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.input_ok_sell_number) + "");
                    return;
                }
                fragment = BBConfirmDialogFragment.getInstance(getActivity().getResources().getText(R.string.text_best_prices) + "", amout + " " + this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")),
                        "--" + " " + currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()), "SELL");
                break;
            default:
        }
        // 卖出
        //if (mPresenter != null) mPresenter.getAddOrder(getmActivity().getToken(), currency.getSymbol(), price, amout, "SELL", type);
        fragment.show(getActivity().getSupportFragmentManager(), "bb_exchange");
        fragment.setTargetFragment(this, Integer.MAX_VALUE);
        fragment.setCallback(new BBConfirmDialogFragment.OperateCallback() {
            @Override
            public void confirm() {
                // 卖出
                if (mPresenter != null) {
                    mPresenter.getAddOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currency.getSymbol(), price, amout, "SELL", type, iv_check_box_two.isSelected() ? "1" : "0");
                }
            }
        });
    }

    /**
     * 盘口信息的返回 和 当前委托的返回
     *
     * @param response SocketResponse
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(SocketResponse response) {
        if (response.getCmd() == null) return;
        switch (response.getCmd()) {
            case PUSH_EXCHANGE_TRADE:
                WonderfulLogUtils.logd("jiejie", " 盘口变化的" + response.getResponse());
                try {
                    setResponse(response.getResponse());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case PUSH_EXCHANGE_ORDER_CANCELED:
            case PUSH_EXCHANGE_ORDER_COMPLETED:
            case PUSH_EXCHANGE_ORDER_TRADE:
                if (MyApplication.getApp().isLogin() && this.currency != null) {
                    currentPage = startPage;
                    historyPage = startPage;
                    if (isCurrent) {
                        mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currentPage, pageSize, this.currency.getSymbol(), "", "", "", "");
                    } else {
                        mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), historyPage, pageSize, this.currency.getSymbol(), "", "", "", "");
                    }
                    mPresenter.getWallet(1, SharedPreferenceInstance.getInstance().getTOKEN(), this.currency.getSymbol().substring(0, currency.getSymbol().indexOf("/")));
                    mPresenter.getWallet(2, SharedPreferenceInstance.getInstance().getTOKEN(), currency.getSymbol().substring(currency.getSymbol().indexOf("/") + 1, currency.getSymbol().length()));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 更新盘口买卖数据列表
     */
    private void setResponse(String response) {
        if (TextUtils.isEmpty(response)) return;
        TextItems items = new Gson().fromJson(response, TextItems.class);
        if (currency.getSymbol().equals(items.getSymbol())) {

        } else {
            return;
        }
//        Log.d("jiejie", "---" + items.getDirection() + " " + items.getItems().size());
        if ("SELL".equals(items.getDirection())) { // 卖
            Log.d("ddd_卖出", response);
            this.mOne.clear();
            for (int i = 0; i < 5; i++) {
                mOne.add(new Exchange(5 - i, "--", "--"));
            }
            List<Exchange> ask = items.getItems();
            if (ask.size() >= 5) {
                for (int i = 0; i < 5; i++) {
                    mOne.set(4 - i, new Exchange(i + 1, ask.get(i).getPrice(), ask.get(i).getAmount()));
                }
            } else {
                for (int i = 0; i < ask.size(); i++) {
                    mOne.set(4 - i, new Exchange(i + 1, ask.get(i).getPrice(), ask.get(i).getAmount()));
                }
            }
            mOneAdapter.setList(mOne);
            mOneAdapter.notifyDataSetChanged();
        } else { // 买
            Log.d("ddd_买入", response);
            this.mTow.clear();
            for (int i = 0; i < 5; i++) {
                mTow.add(new Exchange(5 - i, "--", "--"));
            }
            List<Exchange> bid = items.getItems();
            if (bid.size() >= 5) {
                for (int i = 0; i < 5; i++) {
                    mTow.set(i, new Exchange(i, bid.get(i).getPrice(), bid.get(i).getAmount()));
                }
            } else {
                for (int i = 0; i < bid.size(); i++) {
                    mTow.set(i, new Exchange(i, bid.get(i).getPrice(), bid.get(i).getAmount()));
                }
            }
            mTwoAdapter.setList(mTow);
            mTwoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setPresenter(MainContract.ThreePresenter presenter) {
        this.presenter = presenter;
    }
}
