package com.bizzan.ui.option;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.bizzan.R;
import com.bizzan.adapter.AddAmount;
import com.bizzan.adapter.OptionHistoryAdapter;
import com.bizzan.adapter.OptionIconAdapter;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseFragment;
import com.bizzan.base.BaseTransFragment;
import com.bizzan.entity.Currency;
import com.bizzan.entity.CurrentBean;
import com.bizzan.entity.ForecaseBean;
import com.bizzan.entity.Money;
import com.bizzan.entity.OptionBean;
import com.bizzan.entity.OptionIconBean;
import com.bizzan.entity.OptionOrderHistoryBean;
import com.bizzan.serivce.SocketResponse;
import com.bizzan.socket.ISocket;
import com.bizzan.ui.home.ConstractFragment;
import com.bizzan.ui.home.MainActivity;
import com.bizzan.ui.home.MainContract;
import com.bizzan.ui.home.MarketBaseFragment;
import com.bizzan.ui.kline.kline.KData;
import com.bizzan.ui.kline.kline.KLineView;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.ui.mychart.DataParse;
import com.bizzan.ui.mychart.KLineBean;
import com.bizzan.ui.option.presenter.SevenImpl;
import com.bizzan.utils.LoadDialog;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulDateUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.widget.SevenPopIconView;

public class SevenFragment extends BaseTransFragment implements MainContract.SevenView, ISevenContract.View, View.OnClickListener {
    public static final String TAG = SevenFragment.class.getSimpleName();

    @BindArray(R.array.k_line_tab_option)
    String[] titles;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindArray(R.array.home_seven_top_tab)
    String[] tabs;
    @BindView(R.id.ibOpen)
    ImageButton ibOpen;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ibMessage)
    ImageView ibMessage;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.tv_current_trust)
    TextView tvCurrentTrust;
    @BindView(R.id.current_trust_underline)
    View currentTrustUnderline;
    @BindView(R.id.ll_current_trust)
    LinearLayout llCurrentTrust;
    @BindView(R.id.tv_history_trust)
    TextView tvHistoryTrust;
    @BindView(R.id.history_trust_underline)
    View historyTrustUnderline;
    @BindView(R.id.ll_history_trust)
    LinearLayout llHistoryTrust;
    @BindView(R.id.text_to_all)
    RelativeLayout textToAll;
    @BindView(R.id.mTvseven)
    TextView mTvseven;
    @BindView(R.id.btn_toLogin)
    TextView btnToLogin;
    @BindView(R.id.smartrefreshLayout)
    SmartRefreshLayout smartrefreshLayout;
    @BindView(R.id.llRoot)
    LinearLayout llRoot;
    @BindView(R.id.tab_time)
    LinearLayout tabTime;
    @BindView(R.id.kline_view)
    KLineView klineView;
    @BindView(R.id.ll_k)
    LinearLayout llK;
    @BindView(R.id.rv_option)
    RecyclerView rvOption;
    @BindView(R.id.tv_open_price)
    TextView tvOpenPrice;
    @BindView(R.id.tv_now_price)
    TextView tvNowPrice;
    @BindView(R.id.tv_expect)
    TextView tvExpect;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.tv_sell)
    TextView tvSell;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.rv_amount)
    RecyclerView rvAmount;
    @BindView(R.id.tv_use_money)
    TextView tvUseMoney;
    @BindView(R.id.bu_rise)
    Button buRise;
    @BindView(R.id.bu_fall)
    Button buFall;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.ll_deal)
    LinearLayout llDeal;
    @BindView(R.id.tv_deal_trust)
    TextView tvDealTrust;
    @BindView(R.id.deal_trust_underline)
    View dealTrustUnderline;
    @BindView(R.id.ll_deal_trust)
    LinearLayout llDealTrust;
    @BindView(R.id.ll_current)
    LinearLayout llCurrent;
    @BindView(R.id.tv_expect2)
    TextView tvExpect2;
    @BindView(R.id.tv_buy2)
    TextView tvBuy2;
    @BindView(R.id.tv_sell2)
    TextView tvSell2;
    @BindView(R.id.tv_amount2)
    TextView tvAmount2;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.tv_percent)
    TextView tvPercent;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.installments)
    TextView installments;
    @BindView(R.id.tv_time2)
    TextView tvTime2;
    @BindView(R.id.now_one)
    TextView nowOne;
    @BindView(R.id.now_two)
    TextView nowTwo;
    @BindView(R.id.now_three)
    TextView nowThree;
    @BindView(R.id.now_four)
    TextView nowFour;
    @BindView(R.id.now_five)
    TextView nowFive;
    @BindView(R.id.now_six)
    TextView nowSix;
    @BindView(R.id.ll_one_current)
    LinearLayout llOneCurrent;
    @BindView(R.id.tv_time_current)
    TextView tvTimeCurrent;

    private ArrayList<TextView> textViews;
    private MainContract.SevenPresenter presenter;
    private List<BaseFragment> menusFragments = new ArrayList<>();
    private OptionBean.DataBean optionBean;
    private List<OptionBean.DataBean> optionBeans = new ArrayList<>();
    private List<OptionIconBean.DataBean.ContentBean> contentBean = new ArrayList<>();
    private OptionIconAdapter iconAdapter; // 往期结果图标适配器
    private ISevenContract.Presenter mPresenter;

    private KData lastKline = new KData();
    private ConstractFragment constractframent;
    private Gson gson = new Gson();
    private Unbinder unbinder1;
    private long to;
    private Date endDate;
    private long from;
    private Date startDate;
    private String resolution;
    private int type = GlobalConstant.TAG_THIRTY_MINUTE;
    private TextView selectedTextView;
    private ArrayList<KLineBean> kLineDatas;
    private ArrayList<KData> kLineEntities;
    private SevenPopIconView sevenPopIconView;
    private AddAmount adapter;
    private OptionHistoryAdapter historyAdapter;
    private List<String> amountlist;
    private List<OptionOrderHistoryBean.DataBean.ContentBean> historybean;
    private int currentPosition = -1;
    private long nowtime;
    private Money.DataBean data;
    private int historySize = 10;
    private int historyPage = 0;
    private List<ForecaseBean.DataBean> Starting;
    private List<ForecaseBean.DataBean> Opening;
    private Timer timer;
    private OptionOrderHistoryBean.DataBean OrderHistory;
    private int history = 0;
    private Timer timer2;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_seven;
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        tvDealTrust.setSelected(true);
        tvUseMoney.setText(getActivity().getResources().getText(R.string.text_use_balance) + "：0.00 USDT");
        if (mPresenter == null) {
            mPresenter = new SevenImpl(this);
        }
        if (constractframent == null) {
            int type = MarketBaseFragment.MarketOperateCallback.TYPE_SWITCH_SYMBOL;
            menusFragments.add(constractframent = ConstractFragment.getInstance(type));
        }
        // 打开左侧的滑动
        ibOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).show_option();
                ((MainActivity) getActivity()).getDlRoot().openDrawer(Gravity.LEFT);
            }
        });
        smartrefreshLayout.setEnableRefresh(false);//禁用下拉刷新
        smartrefreshLayout.setEnableLoadMore(true);
        smartrefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        smartrefreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        smartrefreshLayout.setEnablePureScrollMode(false);//是否启用纯滚动模式
        smartrefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
                smartrefreshLayout.finishLoadMore(true);
            }
        });
        List<String> tabs = Arrays.asList(this.tabs);
        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {  //往期结果
                    llK.setVisibility(View.GONE);
                    rvOption.setVisibility(View.VISIBLE);
                } else if (tab.getPosition() == 1) {  // 实时行情
                    llK.setVisibility(View.VISIBLE);
                    rvOption.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        amountlist = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvAmount.setLayoutManager(manager);
        adapter = new AddAmount(R.layout.adapter_addmultiples, amountlist);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //这里赋值s
                currentPosition = position;
                //每点击一次item就刷新适配器
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setItemSelectedCallBack(new AddAmount.ItemSelectedCallBack() {
            @Override
            public void convert(BaseViewHolder holder, int position) {
                //初始化组件
                TextView tv = holder.getView(R.id.tv_add);
                //判断传入的position是否和当前一致
                if (position == currentPosition) {
                    tv.setBackgroundResource(R.drawable.shape_six_multiples_item_color_orange);
                } else {
                    tv.setBackgroundResource(R.color.transparent);
                }
            }
        });
        rvAmount.setAdapter(adapter);
        historybean = new ArrayList<>();
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        historyAdapter = new OptionHistoryAdapter(historybean);
        rvHistory.setAdapter(historyAdapter);
        initPlate();
        llDealTrust.setOnClickListener(this);
        llCurrentTrust.setOnClickListener(this);
        llHistoryTrust.setOnClickListener(this);
        buRise.setOnClickListener(this);
        buFall.setOnClickListener(this);
    }


    double mTwoText, mOneTextPrice, mTwoTextPrice, mTwoMyText;

    @Override
    protected void obtainData() {
        rvOption.setLayoutManager(new GridLayoutManager(getActivity(), 9));
        iconAdapter = new OptionIconAdapter(R.layout.item_option_icon, contentBean);
        rvOption.setAdapter(iconAdapter);
        rvOption.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()) {
                    case R.id.ll_backgrount:
                        showIconData(position);
                        break;
                }
            }
        });
//        iconAdapter.openLoadAnimation(new BaseAnimation() {
//            @Override
//            public Animator[] getAnimators(View view) {
//
//                return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", 0.5f, 1.0f),
//                        ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1.0f)};
//            }
//        });
//        iconAdapter.openLoadAnimation(new BaseAnimation[]{
//
//                new BaseAnimation() {
//
//                    @Override
//
//                    public Animator[] getAnimators(View view) {
//
//                        return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", 0.5f, 1.0f),
//
//                                ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1.0f)};
//
//                    }
//
//                }
//
//        });


        textViews = new ArrayList<>();
        loadData2();
        initTextView();


        selectedTextView = textViews.get(3);
        Drawable home_zhang_no = getResources().getDrawable(
                R.drawable.tab);
        selectedTextView.setCompoundDrawablesWithIntrinsicBounds(null,
                null, null, home_zhang_no);
        this.type = (int) selectedTextView.getTag();
    }

    //点击往期结果里icon弹出当前的数据
    private void showIconData(int position) {
        sevenPopIconView = new SevenPopIconView(getActivity(), contentBean.get(position), this);
        sevenPopIconView.showAtLocation(getActivity().findViewById(R.id.llRoot), Gravity.BOTTOM, 0, 0);
    }


    @Override
    protected void fillWidget() {

    }

    public void setCurrencyInfo(List<OptionBean.DataBean> dataBeans) {

        this.optionBeans.clear();
        this.optionBeans.addAll(dataBeans);

        this.optionBean = optionBeans.get(0);

    }

    /**
     * 这个是从主界面(左滑动栏目)来的，表示选择了某个币种
     */
    public void resetSymbol(OptionBean.DataBean optionBean) {
        this.optionBean = optionBean;
        Log.i("six", "执行函数：resetSymbol" + this.optionBean.getSymbol());
        if (this.optionBean != null) {
            mPresenter.OptionHistory(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
            mPresenter.OptionStarting(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
            mPresenter.OptionOpening(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
            if (MyApplication.getApp().isLogin()) {
                mPresenter.OrderHistory(optionBean.getSymbol(), historyPage + "", historySize + "", SharedPreferenceInstance.getInstance().getTOKEN());
            }
            if (timer != null) {
                timer.cancel();
            }
            tvTitle.setText(this.optionBean.getSymbol() + " " + getActivity().getResources().getText(R.string.Option_contract));

            loadData2();
        }
    }

    @Override
    protected void loadData() {
        if (MyApplication.getApp().isLogin() && TextUtils.isEmpty(SharedPreferenceInstance.getInstance().getTOKEN())) {
            MyApplication.getApp().loginAgain(this);
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_login_disabled) + "");
            return;
        }
        // 获取往期行情信息
        mPresenter.OptionHistory(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
        mPresenter.OptionStarting(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
        mPresenter.OptionOpening(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
        if (MyApplication.getApp().isLogin()) {
            mPresenter.getWallet(SharedPreferenceInstance.getInstance().getTOKEN(), "usdt");
            mPresenter.OrderHistory(optionBean.getSymbol(), historyPage + "", historySize + "", SharedPreferenceInstance.getInstance().getTOKEN());
        }
        if (optionBean != null) {
            tvTitle.setText(this.optionBean.getSymbol() + " " + getActivity().getResources().getText(R.string.Option_contract));
        }
        llDeal.setVisibility(View.VISIBLE);
        llCurrent.setVisibility(View.GONE);
        llHistory.setVisibility(View.GONE);
        tvDealTrust.setSelected(true);
        tvCurrentTrust.setSelected(false);
        tvHistoryTrust.setSelected(false);
        dealTrustUnderline.setVisibility(View.VISIBLE);
        currentTrustUnderline.setVisibility(View.GONE);
        historyTrustUnderline.setVisibility(View.GONE);
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
     * 这个是刚开始
     */
    public void initPlate() {
        if (this.optionBean != null) {
            // 获取盘口
//            mPresenter.getExchange(this.currency.getSymbol());
            // 获取盘口精确度
//            mPresenter.getSymbolInfo(this.currency.getSymbol());
//            if (!TextUtils.isEmpty(oldSymbol)) stop(oldSymbol, getmActivity().getId());
//            startTCP(currency.getSymbol(), getmActivity().getId());
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
    }


    private void toLoginOrCenter() {
        startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toLogin: // 点击登录
                toLoginOrCenter();
                break;
            case R.id.ll_deal_trust: // 点击交易
                if (MyApplication.getApp().isLogin()) {
                    llDeal.setVisibility(View.VISIBLE);
                    llCurrent.setVisibility(View.GONE);
                    llHistory.setVisibility(View.GONE);
                    tvDealTrust.setSelected(true);
                    tvCurrentTrust.setSelected(false);
                    tvHistoryTrust.setSelected(false);
                    dealTrustUnderline.setVisibility(View.VISIBLE);
                    currentTrustUnderline.setVisibility(View.GONE);
                    historyTrustUnderline.setVisibility(View.GONE);
                } else {
                    llDeal.setVisibility(View.VISIBLE);
                    llCurrent.setVisibility(View.GONE);
                    llHistory.setVisibility(View.GONE);
                    tvDealTrust.setSelected(true);
                    tvCurrentTrust.setSelected(false);
                    tvHistoryTrust.setSelected(false);
                    dealTrustUnderline.setVisibility(View.VISIBLE);
                    currentTrustUnderline.setVisibility(View.GONE);
                    historyTrustUnderline.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_current_trust: // 点击当前委托
                if (MyApplication.getApp().isLogin()) {
                    llDeal.setVisibility(View.GONE);
                    llCurrent.setVisibility(View.VISIBLE);
                    llHistory.setVisibility(View.GONE);
                    tvDealTrust.setSelected(false);
                    tvCurrentTrust.setSelected(true);
                    tvHistoryTrust.setSelected(false);
                    dealTrustUnderline.setVisibility(View.GONE);
                    currentTrustUnderline.setVisibility(View.VISIBLE);
                    historyTrustUnderline.setVisibility(View.GONE);
                } else {
                    llDeal.setVisibility(View.VISIBLE);
                    llCurrent.setVisibility(View.GONE);
                    llHistory.setVisibility(View.GONE);
                    tvDealTrust.setSelected(true);
                    tvCurrentTrust.setSelected(false);
                    tvHistoryTrust.setSelected(false);
                    dealTrustUnderline.setVisibility(View.VISIBLE);
                    currentTrustUnderline.setVisibility(View.GONE);
                    historyTrustUnderline.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_history_trust: // 点击历史委托
                if (MyApplication.getApp().isLogin()) {
                    llDeal.setVisibility(View.GONE);
                    llCurrent.setVisibility(View.GONE);
                    llHistory.setVisibility(View.VISIBLE);
                    tvDealTrust.setSelected(false);
                    tvCurrentTrust.setSelected(false);
                    tvHistoryTrust.setSelected(true);
                    dealTrustUnderline.setVisibility(View.GONE);
                    currentTrustUnderline.setVisibility(View.GONE);
                    historyTrustUnderline.setVisibility(View.VISIBLE);
                } else {
                    llDeal.setVisibility(View.VISIBLE);
                    llCurrent.setVisibility(View.GONE);
                    llHistory.setVisibility(View.GONE);
                    tvDealTrust.setSelected(true);
                    tvCurrentTrust.setSelected(false);
                    tvHistoryTrust.setSelected(false);
                    dealTrustUnderline.setVisibility(View.VISIBLE);
                    currentTrustUnderline.setVisibility(View.GONE);
                    historyTrustUnderline.setVisibility(View.GONE);
                }
                break;
            case R.id.bu_rise:
                look(1);
                break;
            case R.id.bu_fall:
                look(2);
                break;
            default:
        }
    }

    //点击  1看涨  2看跌
    private void look(int type) {
        if (!MyApplication.getApp().isLogin()) {
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_xian_login) + "");
            return;
        }
        if (currentPosition == -1) {
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.please_select_number) + "");
            return;
        }
        if (data.getBalance() < Integer.parseInt(amountlist.get(currentPosition))) {
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.use_money_dissatisfy) + "");
            return;
        }
        if (type == 1) {
            mPresenter.Add(optionBean.getSymbol(), "0", Starting.get(0).getId() + "", amountlist.get(currentPosition), SharedPreferenceInstance.getInstance().getTOKEN());
        } else if (type == 2) {
            mPresenter.Add(optionBean.getSymbol(), "1", Starting.get(0).getId() + "", amountlist.get(currentPosition), SharedPreferenceInstance.getInstance().getTOKEN());
        }
    }


    /**
     * socket 推送过来的信息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSocketMessage(SocketResponse response) {
        if (response.getCmd() == ISocket.CMD.CONTRACT_PUSH_SYMBOL_THUMB) {
            // 如果是合约盘口返回的信息
            try {
                Currency temp = new Gson().fromJson(response.getResponse(), Currency.class);
                for (OptionBean.DataBean bean : optionBeans) {
                    if (temp.getSymbol().equals(bean.getSymbol())) {
                        Currency currency = temp;
//                        Currency.shallowClone(currency, temp);
                        if (currency.getSymbol().equals(optionBean.getSymbol())) {
                            if (currency.getClose() > this.lastKline.getMaxPrice()) {
                                this.lastKline.setMaxPrice(currency.getClose().floatValue());
                            }
                            if (currency.getClose() < this.lastKline.getMinPrice()) {
                                this.lastKline.setMinPrice(currency.getClose().floatValue());
                            }

                            this.lastKline.setClosePrice(currency.getClose().floatValue());
//                            System.out.println("lase:" + this.lastKline.getClosePrice());
                            if (klineView != null)
                                klineView.addSingleData(lastKline);
                            if (tvNowPrice != null)
                                tvNowPrice.setText(temp.getClose() + " USDT");
                        }

                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    /**
     * 设置tab栏显示内容
     */
    private void initTextView() {
        List<String> titles = Arrays.asList(this.titles);
        for (int i = 0; i < titles.size(); i++) {
            TextView textView = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.textview_pop, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            textView.setLayoutParams(layoutParams);
            textView.setText(titles.get(i));
            if (titles.get(i).equals("30分钟")) {
                textView.setTag(5);
            } else if (titles.get(i).equals("1小时")) {
                textView.setTag(3);
            } else if (titles.get(i).equals("1天")) {
                textView.setTag(4);
            } else {
                textView.setTag(i);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedTextView = (TextView) view;
                    int selectedTag = (int) selectedTextView.getTag();
                    type = selectedTag;
                    setPagerView();
                }
            });
            textViews.add(textView);
            tabTime.addView(textView);
        }
    }

    /**
     * viewpager和textview的点击事件
     */
    private void setPagerView() {
        for (int j = 0; j < textViews.size(); j++) {
            textViews.get(j).setSelected(false);
            textViews.get(j).setCompoundDrawablesWithIntrinsicBounds(null,
                    null, null, null);
            int tag = (int) textViews.get(j).getTag();
            if (tag == type) {
                textViews.get(j).setSelected(true);
                Drawable home_zhang_no1 = getResources().getDrawable(
                        R.drawable.tab);
                textViews.get(j).setCompoundDrawablesWithIntrinsicBounds(null,
                        null, null, home_zhang_no1);

                if (type != GlobalConstant.TAG_DIVIDE_TIME) {
                    if (klineView.isShowInstant()) {
                        klineView.setShowInstant(!klineView.isShowInstant());
                    } else {

                    }

                    loadData2();
                } else {
                    if (klineView.isShowInstant()) {
                    } else {
                        klineView.setShowInstant(!klineView.isShowInstant());
                    }
                    loadData2();
                }
            }
        }
    }

    private void loadData2() {
        to = System.currentTimeMillis();
        endDate = WonderfulDateUtils.getDate("HH:mm", to);
        from = to;
        WonderfulLogUtils.logi("miao", "type==" + type);

        switch (type) {
            case GlobalConstant.TAG_DIVIDE_TIME:
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY) - 1;
                c.set(Calendar.HOUR_OF_DAY, hour);
                String strDate = WonderfulDateUtils.getFormatTime("HH:mm", c.getTime());
                startDate = WonderfulDateUtils.getDateTransformString(strDate, "HH:mm");
                resolution = 1 + "";
                String str = WonderfulDateUtils.getFormatTime(null, c.getTime());
                from = WonderfulDateUtils.getTimeMillis(null, str);
                klineView.setTime_Type(GlobalConstant.TAG_DIVIDE_TIME);
                break;
            case GlobalConstant.TAG_ONE_MINUTE:
                from = to - 24L * 60 * 60 * 1000;//前一天数据
                resolution = 1 + "";
                klineView.setTime_Type(GlobalConstant.TAG_ONE_MINUTE);
                break;
            case GlobalConstant.TAG_FIVE_MINUTE:
                from = to - 2 * 24L * 60 * 60 * 1000;//前两天数据
                resolution = 5 + "";
                klineView.setTime_Type(GlobalConstant.TAG_FIVE_MINUTE);
                break;
            case GlobalConstant.TAG_THIRTY_MINUTE:
                from = to - 12 * 24L * 60 * 60 * 1000; //前12天数据
                resolution = 30 + "";
                klineView.setTime_Type(GlobalConstant.TAG_THIRTY_MINUTE);
                break;
            case GlobalConstant.TAG_AN_HOUR:
                from = to - 24 * 24L * 60 * 60 * 1000;//前 24天数据
                resolution = 60 + "";
                klineView.setTime_Type(GlobalConstant.TAG_AN_HOUR);
                break;
            case GlobalConstant.TAG_DAY:
                from = to - 60 * 24L * 60 * 60 * 1000 * 3; //前60天数据*3
                resolution = 1 + "D";
                klineView.setTime_Type(GlobalConstant.TAG_DAY);
                break;
            case GlobalConstant.TAG_WEEK:
                from = to - 730 * 24L * 60 * 60 * 1000 * 3; //前两年数据*3
                resolution = 1 + "W";
                klineView.setTime_Type(GlobalConstant.TAG_WEEK);
                break;
            case GlobalConstant.TAG_MONTH:
                from = to - 1095 * 24L * 60 * 60 * 1000 * 3; //前三年数据*3
                resolution = 1 + "M";
                klineView.setTime_Type(GlobalConstant.TAG_MONTH);
                break;
            default:
        }
        mPresenter.KData(optionBean.getSymbol(), from, to, resolution, "1");
        if (timer2 != null) {
            timer2.cancel();
        }
        timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                if (lastKline.getTime() != 0) {
                    switch (type) {
                        case GlobalConstant.TAG_DIVIDE_TIME:
                            if (time >= (lastKline.getTime() + (1000 * 60 + 1000))) {
                                mPresenter.KData(optionBean.getSymbol(), from, time, resolution, "1");
                            }
                            break;
                        case GlobalConstant.TAG_ONE_MINUTE:
                            if (time >= (lastKline.getTime() + (1000 * 60 + 1000))) {
                                mPresenter.KData(optionBean.getSymbol(), from, time, resolution, "1");
                            }
                            break;
                        case GlobalConstant.TAG_FIVE_MINUTE:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 5 + 1000))) {
                                mPresenter.KData(optionBean.getSymbol(), from, time, resolution, "1");
                            }
                            break;
                        case GlobalConstant.TAG_THIRTY_MINUTE:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 30 + 1000))) {
                                mPresenter.KData(optionBean.getSymbol(), from, time, resolution, "1");
                            }
                            break;
                        case GlobalConstant.TAG_AN_HOUR:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 60 + 1000))) {
                                mPresenter.KData(optionBean.getSymbol(), from, time, resolution, "1");
                            }
                            break;
                        case GlobalConstant.TAG_DAY:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 60 * 24 + 1000))) {
                                mPresenter.KData(optionBean.getSymbol(), from, time, resolution, "1");
                            }
                            break;
                        case GlobalConstant.TAG_WEEK:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 60 * 24 * 7 + 1000))) {
                                mPresenter.KData(optionBean.getSymbol(), from, time, resolution, "1");
                            }
                            break;
                        case GlobalConstant.TAG_MONTH:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 60 * 24 * 30 + 1000))) {
                                mPresenter.KData(optionBean.getSymbol(), from, time, resolution, "1");
                            }
                            break;
                        default:
                    }
                }
            }
        }, 1000, 1000);
    }

    @Override
    public void errorMes(int e, String meg) {
        WonderfulCodeUtils.checkedErrorCode(this, e, meg);
    }

    @Override
    public void option_History(OptionIconBean.DataBean beans) {
//        if (contentBean.size() == 0) {
        this.contentBean.clear();
        this.contentBean.addAll(beans.getContent());
        Collections.reverse(contentBean);
        iconAdapter.notifyDataSetChanged();
//        } else {
//            this.contentBean.add(beans.getContent().get(beans.getSize() - 1));
//            iconAdapter.notifyItemChanged(beans.getSize() - 1);
//        }

    }

    @Override
    public void option_Order_History(OptionOrderHistoryBean.DataBean objs) {
        OrderHistory = objs;
        smartrefreshLayout.setEnableLoadMore(true);
        setListData(objs);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void setListData(OptionOrderHistoryBean.DataBean list) {
        if (historyPage == 0) {
            historybean.clear();
            historyAdapter = new OptionHistoryAdapter(historybean);
            rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvHistory.setAdapter(historyAdapter);
            historyAdapter.setEnableLoadMore(true);
            historyAdapter.loadMoreEnd(false);
        }
        for (int i = 0; i < list.getContent().size(); i++) {
            if (!list.getContent().get(i).getResult().equals("WAIT")) {
                historybean.add(list.getContent().get(i));
            }
        }
//        historybean.addAll(list.getContent());
        historyAdapter.notifyDataSetChanged();

        if (list.getContent().size() < historyPage) {
            smartrefreshLayout.setEnableLoadMore(false);
        } else {
            smartrefreshLayout.setEnableLoadMore(true);
        }
    }

    private void loadMore() {
        historyPage = historyPage + 1;
        if (MyApplication.getApp().isLogin()) {
            mPresenter.OrderHistory(optionBean.getSymbol(), historyPage + "", historySize + "", SharedPreferenceInstance.getInstance().getTOKEN());
        }
    }

    @Override
    public void option_Starting(List<ForecaseBean.DataBean> objs) {
        if (objs == null || objs.isEmpty()) {
            return;
        }
        Starting = objs;
        if (MyApplication.getApp().isLogin()) {
            mPresenter.OptionCurrent(optionBean.getSymbol(), objs.get(0).getId() + "", SharedPreferenceInstance.getInstance().getTOKEN(), "1");
        }
        tvExpect.setText(getActivity().getResources().getText(R.string.nos) + "" + objs.get(0).getOptionNo() + "" + getActivity().getResources().getText(R.string.expect));
        tvTime.setText(getActivity().getResources().getText(R.string.forecast_cycle) +
                WonderfulDateUtils.getFormatTime("yyyy-MM-dd HH:mm", new Date(objs.get(0).getCreateTime() + optionBeans.get(0).getOpenTimeGap() * 1000)) +
                " ~ " + WonderfulDateUtils.getFormatTime("yyyy-MM-dd HH:mm", new Date(objs.get(0).getCreateTime() + optionBeans.get(0).getOpenTimeGap() * 1000 + optionBeans.get(0).getCloseTimeGap() * 1000)));
        tvBuy.setText(processAmount(objs.get(0).getTotalBuy(), "2") + " USDT");
        tvSell.setText(processAmount(objs.get(0).getTotalSell(), "2") + " USDT");

        String amount = optionBean.getAmount();
        String[] split = amount.split(",");
        amountlist.clear();
        for (int i = 0; i < split.length; i++) {
            amountlist.add(split[i]);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void option_Add(String msg) {
        WonderfulToastUtils.showToast(msg);
        mPresenter.OptionStarting(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
        if (MyApplication.getApp().isLogin()) {
            mPresenter.getWallet(SharedPreferenceInstance.getInstance().getTOKEN(), "usdt");
            mPresenter.OrderHistory(optionBean.getSymbol(), historyPage + "", historySize + "", SharedPreferenceInstance.getInstance().getTOKEN());
        }
    }

    @Override
    public void option_Opening(final List<ForecaseBean.DataBean> objs) {
        Opening = objs;

        tvExpect2.setText(getActivity().getResources().getText(R.string.nos) + "" + objs.get(0).getOptionNo() + "" + getActivity().getResources().getText(R.string.expect));
        tvBuy2.setText(processAmount(objs.get(0).getTotalBuy(), "2") + " USDT");
        tvSell2.setText(processAmount(objs.get(0).getTotalSell(), "2") + " USDT");
        tvOpenPrice.setText(processAmount(objs.get(0).getOpenPrice(), "2") + " USDT");
        tvTimeCurrent.setText(getActivity().getResources().getText(R.string.forecast_cycle) +
                WonderfulDateUtils.getFormatTime("yyyy-MM-dd HH:mm", new Date(objs.get(0).getCreateTime() + optionBeans.get(0).getOpenTimeGap() * 1000)) +
                " ~ " + WonderfulDateUtils.getFormatTime("yyyy-MM-dd HH:mm", new Date(objs.get(0).getCreateTime() + optionBeans.get(0).getOpenTimeGap() * 1000 + optionBeans.get(0).getCloseTimeGap() * 1000)));
        if (MyApplication.getApp().isLogin()) {
            mPresenter.OptionCurrent(optionBean.getSymbol(), objs.get(0).getId() + "", SharedPreferenceInstance.getInstance().getTOKEN(), "2");
        }
//        if (OrderHistory)
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                nowtime = System.currentTimeMillis();
                System.out.println("time:" + nowtime);
                double d = ((double) (nowtime - objs.get(0).getOpenTime())) / (optionBeans.get(0).getOpenTimeGap() * 1000);
                final double time = (d * 100);
                if (getActivity() == null) {
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (time < 100) {
                            System.out.println("time:" + time);
                            tvPercent.setText(processAmount(time, "2") + "%");
                            seekbar.setProgress((int) time);
                        } else {
                            System.out.println("time:" + time);
                            tvPercent.setText("100%");
                            seekbar.setProgress(100);
                            mPresenter.OptionHistory(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
                            mPresenter.OptionStarting(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
                            mPresenter.OptionOpening(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
                            if (MyApplication.getApp().isLogin()) {
                                mPresenter.OrderHistory(optionBean.getSymbol(), historyPage + "", historySize + "", SharedPreferenceInstance.getInstance().getTOKEN());
                            }
                            timer.cancel();
                        }
                    }
                });
            }
        }, 0, 1000);
        llOneCurrent.setVisibility(View.GONE);
        if (OrderHistory == null) {//如果OrderHistory表示该Opening先请求到的，在请求一个， 5次还为空就不请求了
            if (MyApplication.getApp().isLogin()) {
                history++;
                if (history < 5) {
                    mPresenter.OptionOpening(optionBean.getSymbol(), SharedPreferenceInstance.getInstance().getTOKEN());
                }
            }
        } else {
            for (int i = 0; i < OrderHistory.getContent().size(); i++) {
                if (OrderHistory.getContent().get(i).getOptionNo() == objs.get(0).getOptionNo()) {
                    llOneCurrent.setVisibility(View.VISIBLE);
                    installments.setText(getActivity().getResources().getText(R.string.nos) + "" + OrderHistory.getContent().get(i).getOptionNo() + "" + getActivity().getResources().getText(R.string.expect));
                    tvTime2.setText(WonderfulDateUtils.getFormatTime("yyyy-MM-dd HH:mm:ss", new Date(OrderHistory.getContent().get(i).getCreateTime())));
                    nowOne.setText((int) OrderHistory.getContent().get(i).getBetAmount() + "");
//                    nowFour.setText(getPoint(OrderHistory.getContent().get(i).getRewardAmount()) + "");
//                    nowFive.setText(getPoint(OrderHistory.getContent().get(i).getFee()) + "");
//                    nowSix.setText(getPoint(OrderHistory.getContent().get(i).getWinFee()) + "");
                    nowThree.setText(getActivity().getResources().getText(R.string.underway));
                    if (OrderHistory.getContent().get(i).getDirection().equals("BUY")) {//涨
                        nowTwo.setText(getActivity().getResources().getText(R.string.text_look_up));
                        nowTwo.setTextColor(getActivity().getResources().getColor(R.color.typeGreen));
                    } else if (OrderHistory.getContent().get(i).getDirection().equals("SELL")) {//跌
                        nowTwo.setText(getActivity().getResources().getText(R.string.text_look_down));
                        nowTwo.setTextColor(getActivity().getResources().getColor(R.color.typeRed));
                    }
                    return;
                }
            }
        }
    }

    @Override
    public void option_Current(List<CurrentBean.DataBean> objs) {
        tvAmount.setText("0.00 USDT");
        if (objs.size() != 0) {
            if (objs.get(0).getOptionNo() == Starting.get(0).getOptionNo()) {
                tvAmount.setText((int) objs.get(0).getBetAmount() + " USDT");
            }
        }
    }

    @Override
    public void option_Current2(List<CurrentBean.DataBean> objs) {
        tvAmount2.setText("0.00 USDT");
        if (objs.size() != 0) {
            if (objs.get(0).getOptionNo() == Opening.get(0).getOptionNo()) {
                tvAmount2.setText((int) objs.get(0).getBetAmount() + " USDT");
            }
        }
    }

    @Override
    public void option_WalletUrl(Money money) {
        data = money.getData();

        tvUseMoney.setText(getActivity().getResources().getText(R.string.text_use_balance) + "：" + processAmount(data.getBalance(), "2") + " USDT");
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
    public void KDataSuccess(JSONArray obj) {
        DataParse kData = new DataParse();
        try {
            kData.parseKLine(obj, type);
            kLineDatas = kData.getKLineDatas();
            if (kLineDatas != null && kLineDatas.size() > 0) {
                klineView.setVisibility(View.VISIBLE);
                kLineEntities = new ArrayList<>();
                kLineEntities.clear();
                for (int i = 0; i < kLineDatas.size(); i++) {
                    KData lineEntity = new KData();
                    KLineBean kLineBean = kLineDatas.get(i);
                    lineEntity.setTime(Long.parseLong(kLineBean.getDate()));
                    lineEntity.setOpenPrice(kLineBean.getOpen());
                    lineEntity.setClosePrice(kLineBean.getClose());
                    lineEntity.setMaxPrice(kLineBean.getHigh());
                    lineEntity.setMinPrice(kLineBean.getLow());
                    lineEntity.setVolume(kLineBean.getVol());
                    kLineEntities.add(lineEntity);
                    if (i == kLineDatas.size() - 1) {
                        this.lastKline.setTime(Long.parseLong(kLineBean.getDate()));
                        this.lastKline.setOpenPrice(kLineBean.getOpen());
                        this.lastKline.setClosePrice(kLineBean.getClose());
                        this.lastKline.setMaxPrice(kLineBean.getHigh());
                        this.lastKline.setMinPrice(kLineBean.getLow());
                        this.lastKline.setVolume(kLineBean.getVol());
                    }
                }

                WonderfulLogUtils.logi("miao", kLineDatas.get(0).getClose() + "--" + kLineDatas.get(0).getHigh() + "--" + kLineDatas.get(0).getLow() + "--" + kLineDatas.get(0).getOpen() + "--" + kLineDatas.get(0).getVol());
                WonderfulLogUtils.logi("miao", kLineEntities.size() + "--");
                if (klineView.getTotalDataList().size() != 0) {
                    klineView.resetDataList(kLineEntities);
                } else {
                    klineView.initKDataList(kLineEntities);
                }
            } else {
                klineView.setVisibility(View.INVISIBLE);
            }

            klineView.setOnRequestDataListListener(new KLineView.OnRequestDataListListener() {
                @Override
                public void requestData() {
                    to = from;
                    switch (type) {
                        case GlobalConstant.TAG_DIVIDE_TIME:
                            Calendar c = Calendar.getInstance();
                            int hour = c.get(Calendar.HOUR_OF_DAY) - 1;
                            c.set(Calendar.HOUR_OF_DAY, hour);
                            String strDate = WonderfulDateUtils.getFormatTime("HH:mm", c.getTime());
                            startDate = WonderfulDateUtils.getDateTransformString(strDate, "HH:mm");
                            resolution = 1 + "";
                            String str = WonderfulDateUtils.getFormatTime(null, c.getTime());
                            from = WonderfulDateUtils.getTimeMillis(null, str);
                            klineView.setTime_Type(GlobalConstant.TAG_DIVIDE_TIME);
                            break;
                        case GlobalConstant.TAG_ONE_MINUTE:
                            from = to - 24L * 60 * 60 * 1000;//前一天数据
                            resolution = 1 + "";
                            klineView.setTime_Type(GlobalConstant.TAG_ONE_MINUTE);
                            break;
                        case GlobalConstant.TAG_FIVE_MINUTE:
                            from = to - 2 * 24L * 60 * 60 * 1000;//前两天数据
                            resolution = 5 + "";
                            klineView.setTime_Type(GlobalConstant.TAG_FIVE_MINUTE);
                            break;
                        case GlobalConstant.TAG_THIRTY_MINUTE:
                            from = to - 12 * 24L * 60 * 60 * 1000; //前12天数据
                            resolution = 30 + "";
                            klineView.setTime_Type(GlobalConstant.TAG_THIRTY_MINUTE);
                            break;
                        case GlobalConstant.TAG_AN_HOUR:
                            from = to - 24 * 24L * 60 * 60 * 1000;//前 24天数据
                            resolution = 60 + "";
                            klineView.setTime_Type(GlobalConstant.TAG_AN_HOUR);
                            break;
                        case GlobalConstant.TAG_DAY:
                            from = to - 60 * 24L * 60 * 60 * 1000; //前60天数据
                            resolution = 1 + "D";
                            klineView.setTime_Type(GlobalConstant.TAG_DAY);
                            break;
                        case GlobalConstant.TAG_WEEK:
                            from = to - 730 * 24L * 60 * 60 * 1000; //前两年数据
                            resolution = 1 + "W";
                            klineView.setTime_Type(GlobalConstant.TAG_WEEK);
                            break;
                        case GlobalConstant.TAG_MONTH:
                            from = to - 1095 * 24L * 60 * 60 * 1000; //前三年数据
                            resolution = 1 + "M";
                            klineView.setTime_Type(GlobalConstant.TAG_MONTH);
                            break;
                        default:
                    }
                    mPresenter.KData(optionBean.getSymbol(), from, to, resolution, "2");
                }
            });
        } catch (Exception e) {
//            WonderfulToastUtils.showToast(getString(R.string.parse_error));
        }
    }

    @Override
    public void KDataSuccess2(JSONArray obj) {
        DataParse kData = new DataParse();
        try {
            kData.parseKLine(obj, type);
            kLineDatas = kData.getKLineDatas();
            if (kLineDatas != null && kLineDatas.size() > 0) {
                kLineEntities = new ArrayList<>();
                kLineEntities.clear();
                for (int i = 0; i < kLineDatas.size(); i++) {
                    KData lineEntity = new KData();
                    KLineBean kLineBean = kLineDatas.get(i);
                    lineEntity.setTime(Long.parseLong(kLineBean.getDate()));
                    lineEntity.setOpenPrice(kLineBean.getOpen());
                    lineEntity.setClosePrice(kLineBean.getClose());
                    lineEntity.setMaxPrice(kLineBean.getHigh());
                    lineEntity.setMinPrice(kLineBean.getLow());
                    lineEntity.setVolume(kLineBean.getVol());
                    kLineEntities.add(lineEntity);
                }
                WonderfulLogUtils.logi("miao", kLineDatas.get(0).getClose() + "--" + kLineDatas.get(0).getHigh() + "--" + kLineDatas.get(0).getLow() + "--" + kLineDatas.get(0).getOpen() + "--" + kLineDatas.get(0).getVol());
                WonderfulLogUtils.logi("miao", kLineEntities.size() + "--");
                klineView.addPreDataList(kLineEntities, true);
            } else {
            }
        } catch (Exception e) {
            WonderfulToastUtils.showToast(getString(R.string.parse_error));
        }
    }

    @Override
    public void setPresenter(MainContract.SevenPresenter presenter) {
        this.presenter = presenter;
    }

    private String processAmount(Double d, String s) {
        BigDecimal bg = new BigDecimal(d);
        String v1 = "0.00";
        if (s.equals("2")) {
            v1 = bg.setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        return v1;
    }

    public String getPoint(double data) {
        String format = new DecimalFormat("#").format(data);
        BigDecimal bg = new BigDecimal(format);
        String s = bg.setScale(0, BigDecimal.ROUND_DOWN).toPlainString();
        return s;
    }
}