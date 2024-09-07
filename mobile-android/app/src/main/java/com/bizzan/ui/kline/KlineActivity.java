package com.bizzan.ui.kline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import com.bizzan.R;
import com.bizzan.adapter.PagerAdapter;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.Injection;
import com.bizzan.app.MyApplication;
import com.bizzan.app.UrlFactory;
import com.bizzan.base.BaseActivity;
import com.bizzan.base.BaseFragment;
import com.bizzan.customview.CustomViewPager;
import com.bizzan.customview.intercept.WonderfulScrollView;
import com.bizzan.entity.Currency;
import com.bizzan.entity.Favorite;
import com.bizzan.serivce.SocketMessage;
import com.bizzan.serivce.SocketResponse;
import com.bizzan.socket.ISocket;
import com.bizzan.ui.home.MainActivity;
import com.bizzan.ui.kline.kline.KData;
import com.bizzan.ui.kline.kline.KLineView;
import com.bizzan.ui.mychart.DataParse;
import com.bizzan.ui.mychart.KLineBean;
import com.bizzan.utils.LoadDialog;
import com.bizzan.utils.WonderfulDateUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulMathUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import okhttp3.Request;

public class KlineActivity extends BaseActivity implements KlineContract.View, View.OnClickListener {
    @BindView(R.id.tvCurrencyName)
    TextView tvCurrencyName;
    @BindView(R.id.llLandText)
    LinearLayout llLandText;
    @BindView(R.id.kDataText)
    TextView mDataText;
    @BindView(R.id.kDataOne)
    TextView mDataOne;
    @BindView(R.id.kCount)
    TextView kCount;
    @BindView(R.id.kUp)
    TextView kUp;
    @BindView(R.id.kLow)
    TextView kLow;
    @BindView(R.id.tab)
    LinearLayout tab;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.kRange)
    TextView kRange;
    @BindArray(R.array.k_line_tab)
    String[] titles;
    @BindView(R.id.tv_collect)
    TextView mTvCollect; // 收藏的意思
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvMore)
    TextView tvMore;
    @BindView(R.id.llAllTab)
    LinearLayout llAllTab;
    @BindView(R.id.llVertical)
    LinearLayout llVertical;
    @BindView(R.id.llState)
    LinearLayout llState;
    @BindView(R.id.tvSell)
    TextView tvSell;
    @BindView(R.id.tvBuy)
    TextView tvBuy;
    @BindView(R.id.vpDepth)
    CustomViewPager depthPager;
    @BindView(R.id.llDepthTab)
    TabLayout depthTab;
    @BindView(R.id.scrollView)
    WonderfulScrollView scrollView;
    @BindView(R.id.kline_view)
    KLineView klineView;
    private ArrayList<TextView> textViews;

    private ArrayList<View> views;
    private TextView selectedTextView;
    private int type = GlobalConstant.TAG_FIVE_MINUTE;//默认K线数据选择，配合326行设置

    ;
    private String symbol = "BTC/USDT";
    private String resolution;
    private KlineContract.Presenter presenter;
    private Activity activity;
    private ArrayList<KLineBean> kLineDatas;     // K线图数据
    private Currency mCurrency;
    private List<Currency> currencies = new ArrayList<>();
    private boolean isStart = false;
    private Date startDate;
    private Date endDate;
    private ProgressBar mProgressBar;
    private boolean isFace = false;
    private LoadDialog mDialog;
    private boolean isPopClick;
    private TextView maView;
    private TextView bollView;
    private TextView macdView;
    private TextView kdjView;
    private TextView rsiView;
    private TextView hideChildView;
    private TextView hideMainView;
    private int childType = 0;
    private boolean isVertical;
    private boolean isFirstLoad = true;
    private List<BaseFragment> fragments = new ArrayList<>();
    private PagerAdapter adapter;
    private List<String> tabs;
    private String type_constract;  //1币币  2合约

    private KData lastKline = new KData();
    private ArrayList<KData> kLineEntities;
    private Long from;
    private Long to;
    private Timer timer;

    public static void actionStart(Context context, String symbol, String type) {
        Intent intent = new Intent(context, KlineActivity.class);
        intent.putExtra("symbol", symbol);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.fragment_kline;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isVertical) {
                finish();
            } else {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 切换横竖屏
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ViewGroup.LayoutParams layoutParams = klineView.getLayoutParams();
        tab.removeAllViews();
        moreTabLayout.removeAllViews();
        textViews = new ArrayList<>();
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { // 横屏
            isVertical = false;
            llState.setVisibility(View.GONE);
            llLandText.setVisibility(View.VISIBLE);
            llVertical.setVisibility(View.GONE);
            ibBack.setVisibility(View.GONE);
            depthTab.setVisibility(View.GONE);
            depthPager.setVisibility(View.GONE);
            layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
            klineView.setLayoutParams(layoutParams);
            initTextView(6);
            intMoreTab(6);
            if (type == GlobalConstant.TAG_THIRTY_MINUTE) {
                isPopClick = false;
            }
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            isVertical = true;
            llState.setVisibility(View.VISIBLE);
            llLandText.setVisibility(View.INVISIBLE);
            llVertical.setVisibility(View.VISIBLE);
            ibBack.setVisibility(View.VISIBLE);
            depthTab.setVisibility(View.VISIBLE);
            depthPager.setVisibility(View.VISIBLE);
            layoutParams.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 340, getResources().getDisplayMetrics()));
            klineView.setLayoutParams(layoutParams);
            initTextView(5);
            intMoreTab(5);
            if (type == GlobalConstant.TAG_THIRTY_MINUTE) {
                isPopClick = true;
            }
        }
        setPagerView();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (type_constract.equals("1")) {
            EventBus.getDefault().post(new SocketMessage(0, ISocket.CMD.UNSUBSCRIBE_SYMBOL_THUMB, null)); //  取消订阅
        } else if (type_constract.equals("2")) {
            EventBus.getDefault().post(new SocketMessage(1, ISocket.CMD.CONTRACT_UNSUBSCRIBE_SYMBOL_THUMB, null)); //  取消订阅
        }

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        tvCurrencyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
    protected void fillWidget() {

    }


    @Override
    protected void obtainData() {
        symbol = getIntent().getStringExtra("symbol");
        type_constract = getIntent().getStringExtra("type");
        tvCurrencyName.setText(symbol);

        isVertical = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
        activity = this;
        new KlinePresenter(Injection.provideTasksRepository(activity.getApplicationContext()), this);
        textViews = new ArrayList<>();
        views = new ArrayList<>();

        List<String> titles = Arrays.asList(this.titles);
        if (titles != null) {
            loadData2();
            initTextView(5);
            initPopWindow(5);
        }
        isFace = addFace();
        Log.i("Kline", "是否收藏：" + symbol + "-" + isFace);
        if (isFace) { // 已经收藏
            mTvCollect.setText(WonderfulToastUtils.getString(this, R.string.text_collected));
            Drawable yisoucang = getResources().getDrawable(
                    R.drawable.icon_collect_yes);
            mTvCollect.setCompoundDrawablesWithIntrinsicBounds(null,
                    yisoucang, null, null);
        } else {
            mTvCollect.setText(WonderfulToastUtils.getString(this, R.string.text_add_favorite));
            Drawable yisoucang = getResources().getDrawable(
                    R.drawable.icon_collect_no);
            mTvCollect.setCompoundDrawablesWithIntrinsicBounds(null,
                    yisoucang, null, null);
        }

        if (symbol != null) {
            String[] s = symbol.split("/");
            tvBuy.setText(String.valueOf(WonderfulToastUtils.getString(this, R.string.text_buy) + s[0]));
            tvSell.setText(String.valueOf(WonderfulToastUtils.getString(this, R.string.text_sale) + s[0]));
        }

        selectedTextView = textViews.get(2);
        Drawable home_zhang_no = getResources().getDrawable(
                R.drawable.tab);
        selectedTextView.setCompoundDrawablesWithIntrinsicBounds(null,
                null, null, home_zhang_no);
        this.type = (int) selectedTextView.getTag();
        initDepthData();
        if (type_constract.equals("1")) {//1 币币  2  合约
            getCurrent();
        } else if (type_constract.equals("2")) {//1 币币  2  合约
            getCurrent_constract();
        }

    }

    /**
     * 初始化深度图数据
     */
    private void initDepthData() {
        fragments.add(DepthFragment.getInstance(symbol, type_constract));
        fragments.add(VolumeFragment.getInstance(symbol, type_constract));
        String[] tabArray = getResources().getStringArray(R.array.k_line_depth);
        tabs = new ArrayList<>();
        for (int i = 0; i < tabArray.length; i++) {
            tabs.add(tabArray[i]);
        }
        depthPager.setAdapter(adapter = new PagerAdapter(getSupportFragmentManager(), fragments, tabs));
        depthTab.setTabMode(TabLayout.MODE_FIXED);
        depthTab.setupWithViewPager(depthPager);
        depthPager.setCurrentItem(0);
    }


    @OnClick({R.id.ibBack, R.id.ivFullScreen, R.id.tvSell, R.id.tvBuy, R.id.tv_collect, R.id.tvMore, R.id.tvIndex})
    void setListener(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                return;
            case R.id.ivFullScreen:
                if (isVertical) {
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                }
                return;
            case R.id.tvSell:
                if (type_constract.equals("1")) {//币币
                    MainActivity.actionStart(activity, 2, symbol, 2);
                } else if (type_constract.equals("2")) {//合约
                    MainActivity.actionStart(activity, 2, symbol, 4);
                }
//                MainActivity.actionStart(activity, 2, symbol);
                return;
            case R.id.tvBuy:
                if (type_constract.equals("1")) {//币币
                    MainActivity.actionStart(activity, 1, symbol, 2);
                } else if (type_constract.equals("2")) {//合约
                    MainActivity.actionStart(activity, 1, symbol, 4);
                }
                return;
            case R.id.tv_collect:
                MainActivity.isAgain = true;
                if (isFace) { // 已经收藏 则删除
                    delete();
                } else {
                    getCollect();
                }
                return;
            case R.id.tvMore:
                moreTabLayout.setVisibility(View.VISIBLE);
                indexLayout.setVisibility(View.GONE);
                break;
            case R.id.tvIndex:
                moreTabLayout.setVisibility(View.GONE);
                indexLayout.setVisibility(View.VISIBLE);
                break;
            default:
        }
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(llAllTab);
        }
    }

    /**
     * 删除收藏
     */
    private void delete() {
        if (!MyApplication.getApp().isLogin()) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this, R.string.text_xian_login));
            return;
        }
        showDialog();
        WonderfulOkhttpUtils.post().url(UrlFactory.getDeleteUrl()).addHeader("x-auth-token", getToken())
                .addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                hideDialog();
                WonderfulToastUtils.showToast(WonderfulToastUtils.getString(KlineActivity.this, R.string.text_cancel_fail));
            }

            @Override
            public void onResponse(String response) {
                hideDialog();
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(KlineActivity.this, R.string.text_cancel_success));
                        mTvCollect.setText(WonderfulToastUtils.getString(KlineActivity.this, R.string.text_add_favorite));
                        isFace = false;
                        Drawable yisoucang = getResources().getDrawable(
                                R.drawable.icon_collect_no);
                        mTvCollect.setCompoundDrawablesWithIntrinsicBounds(null,
                                yisoucang, null, null);
                    } else {
                        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(KlineActivity.this, R.string.text_cancel_fail));
                    }
                } catch (JSONException e) {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(KlineActivity.this, R.string.text_cancel_fail));
                }
            }
        });
    }

    /**
     * 添加收藏
     */
    private void getCollect() {
        if (!MyApplication.getApp().isLogin()) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this, R.string.text_xian_login));
            return;
        }
        showDialog();
        WonderfulOkhttpUtils.post().url(UrlFactory.getAddUrl()).addHeader("x-auth-token", getToken())
                .addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                hideDialog();
                WonderfulToastUtils.showToast(WonderfulToastUtils.getString(KlineActivity.this, R.string.text_add_fail));
            }

            @Override
            public void onResponse(String response) {
                hideDialog();
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(KlineActivity.this, R.string.text_add_success));
                        isFace = true;
                        mTvCollect.setText(WonderfulToastUtils.getString(KlineActivity.this, R.string.text_collected));
                        Drawable yisoucang = getResources().getDrawable(
                                R.drawable.icon_collect_yes);
                        mTvCollect.setCompoundDrawablesWithIntrinsicBounds(null,
                                yisoucang, null, null);
                    } else {
                        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(KlineActivity.this, R.string.text_add_fail));
                    }
                } catch (JSONException e) {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(KlineActivity.this, R.string.text_add_fail));
                }
            }
        });
    }


    private PopupWindow popupWindow;
    private LinearLayout moreTabLayout;
    private LinearLayout indexLayout;

    /**
     * 初始化popwindow
     *
     * @param count
     */
    private void initPopWindow(int count) {
        View contentView = LayoutInflater.from(activity).inflate(R.layout.layout_kline_popwindow, null);
        initPopChidView(contentView);
        intMoreTab(count);
        popupWindow = new PopupWindow(activity);
        popupWindow.setContentView(contentView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
    }

    /**
     * 设置more显示内容
     *
     * @param count
     */
    private void intMoreTab(int count) {
        List<String> titles = Arrays.asList(this.titles);
        for (int i = count; i < titles.size(); i++) {
            TextView textView = (TextView) LayoutInflater.from(activity).inflate(R.layout.textview_pop, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            textView.setLayoutParams(layoutParams);
            textView.setText(titles.get(i));
            textView.setTag(i);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isPopClick = true;
                    selectedTextView = (TextView) view;
                    int selectedTag = (int) selectedTextView.getTag();
                    type = selectedTag;
                    setPagerView();
                    popupWindow.dismiss();
                }
            });
            moreTabLayout.addView(textView);
            textViews.add(textView);
        }
    }

    /**
     * 设置tab栏显示内容
     *
     * @param count
     */
    private void initTextView(int count) {
        List<String> titles = Arrays.asList(this.titles);
        for (int i = 0; i < titles.size(); i++) {
            if (i < count) {
                TextView textView = (TextView) LayoutInflater.from(activity).inflate(R.layout.textview_pop, null);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.weight = 1;
                textView.setLayoutParams(layoutParams);
                textView.setText(titles.get(i));
                textView.setTag(i);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isPopClick = false;
                        selectedTextView = (TextView) view;
                        int selectedTag = (int) selectedTextView.getTag();
                        type = selectedTag;
                        setPagerView();
                    }
                });
                textViews.add(textView);
                tab.addView(textView);
            }
        }
    }

    /**
     * 初始化popwindow里的控件
     *
     * @param contentView
     */
    private void initPopChidView(View contentView) {
        moreTabLayout = contentView.findViewById(R.id.tabPop);
        indexLayout = contentView.findViewById(R.id.llIndex);
        maView = contentView.findViewById(R.id.tvMA);
        maView.setSelected(true);
        maView.setOnClickListener(this);
        bollView = contentView.findViewById(R.id.tvBOLL);
        bollView.setOnClickListener(this);
        macdView = contentView.findViewById(R.id.tvMACD);
        kdjView = contentView.findViewById(R.id.tvKDJ);
        rsiView = contentView.findViewById(R.id.tvRSI);
        hideMainView = contentView.findViewById(R.id.tvMainHide);
        hideMainView.setOnClickListener(this);
        macdView = contentView.findViewById(R.id.tvMACD);
        macdView.setOnClickListener(this);
        kdjView = contentView.findViewById(R.id.tvKDJ);
        kdjView.setOnClickListener(this);
        rsiView = contentView.findViewById(R.id.tvRSI);
        rsiView.setOnClickListener(this);
        hideChildView = contentView.findViewById(R.id.tvChildHide);
        hideChildView.setSelected(true);
        hideChildView.setOnClickListener(this);
    }

    /**
     * socket 推送过来的信息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSocketMessage(SocketResponse response) {
        if (type_constract.equals("1")) {
            if (response.getCmd() == ISocket.CMD.PUSH_SYMBOL_THUMB) {    // 如果是盘口返回的信息
                try {
                    Currency temp = new Gson().fromJson(response.getResponse(), Currency.class);
                    for (Currency currency : currencies) {
                        if (temp.getSymbol().equals(currency.getSymbol())) {
                            Currency.shallowClone(currency, temp);
                            if (currency.getSymbol().equals(symbol)) {
                                if (currency.getClose() > this.lastKline.getMaxPrice()) {
                                    this.lastKline.setMaxPrice(currency.getClose().floatValue());
                                }
                                if (currency.getClose() < this.lastKline.getMinPrice()) {
                                    this.lastKline.setMinPrice(currency.getClose().floatValue());
                                }


                                this.lastKline.setClosePrice(currency.getClose().floatValue());
                                System.out.println(this.lastKline.getClosePrice());
                                klineView.addSingleData(lastKline);
                            }
                            break;
                        }
                    }
                    setCurrentcy(currencies);
                } catch (Exception e) {
                    e.printStackTrace();
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this, R.string.Parse_error));
                }
            }
        } else if (type_constract.equals("2")) {
            if (response.getCmd() == ISocket.CMD.CONTRACT_PUSH_SYMBOL_THUMB) {
                // 如果是合约盘口返回的信息
                try {
                    Currency temp = new Gson().fromJson(response.getResponse(), Currency.class);
                    for (Currency currency : currencies) {
                        if (temp.getSymbol().equals(currency.getSymbol())) {
                            Currency.shallowClone(currency, temp);
                            if (currency.getSymbol().equals(symbol)) {
                                if (currency.getClose() > this.lastKline.getMaxPrice()) {
                                    this.lastKline.setMaxPrice(currency.getClose().floatValue());
                                }
                                if (currency.getClose() < this.lastKline.getMinPrice()) {
                                    this.lastKline.setMinPrice(currency.getClose().floatValue());
                                }

                                this.lastKline.setClosePrice(currency.getClose().floatValue());
                                System.out.println("lase:" + this.lastKline.getClosePrice());
                                klineView.addSingleData(lastKline);
                            }

                            break;
                        }
                    }
                    setCurrentcy(currencies);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 加载数据
     */
    @Override
    protected void loadData() {

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
        //type 1:第一次请求数据 2加载数据
        if (type_constract.equals("1")) {
            presenter.KData(symbol, from, to, resolution, "1");
        } else if (type_constract.equals("2")) {
            presenter.KData_Constract(symbol, from, to, resolution, "1");
        }
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                if (lastKline.getTime() != 0) {
                    switch (type) {
                        case GlobalConstant.TAG_DIVIDE_TIME:
                            if (time >= (lastKline.getTime() + (1000 * 60 + 1000))) {
                                if (type_constract.equals("1")) {
                                    presenter.KData(symbol, from, time, resolution, "1");
                                } else if (type_constract.equals("2")) {
                                    presenter.KData_Constract(symbol, from, time, resolution, "1");
                                }
                            }
                            break;
                        case GlobalConstant.TAG_ONE_MINUTE:
                            if (time >= (lastKline.getTime() + (1000 * 60 + 1000))) {
                                if (type_constract.equals("1")) {
                                    presenter.KData(symbol, from, time, resolution, "1");
                                } else if (type_constract.equals("2")) {
                                    presenter.KData_Constract(symbol, from, time, resolution, "1");
                                }
                            }
                            break;
                        case GlobalConstant.TAG_FIVE_MINUTE:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 5 + 1000))) {
                                if (type_constract.equals("1")) {
                                    presenter.KData(symbol, from, time, resolution, "1");
                                } else if (type_constract.equals("2")) {
                                    presenter.KData_Constract(symbol, from, time, resolution, "1");
                                }
                            }
                            break;
                        case GlobalConstant.TAG_THIRTY_MINUTE:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 30 + 1000))) {
                                if (type_constract.equals("1")) {
                                    presenter.KData(symbol, from, time, resolution, "1");
                                } else if (type_constract.equals("2")) {
                                    presenter.KData_Constract(symbol, from, time, resolution, "1");
                                }
                            }
                            break;
                        case GlobalConstant.TAG_AN_HOUR:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 60 + 1000))) {
                                if (type_constract.equals("1")) {
                                    presenter.KData(symbol, from, time, resolution, "1");
                                } else if (type_constract.equals("2")) {
                                    presenter.KData_Constract(symbol, from, time, resolution, "1");
                                }
                            }
                            break;
                        case GlobalConstant.TAG_DAY:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 60 * 24 + 1000))) {
                                if (type_constract.equals("1")) {
                                    presenter.KData(symbol, from, time, resolution, "1");
                                } else if (type_constract.equals("2")) {
                                    presenter.KData_Constract(symbol, from, time, resolution, "1");
                                }
                            }
                            break;
                        case GlobalConstant.TAG_WEEK:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 60 * 24 * 7 + 1000))) {
                                if (type_constract.equals("1")) {
                                    presenter.KData(symbol, from, time, resolution, "1");
                                } else if (type_constract.equals("2")) {
                                    presenter.KData_Constract(symbol, from, time, resolution, "1");
                                }
                            }
                            break;
                        case GlobalConstant.TAG_MONTH:
                            if (time >= (lastKline.getTime() + (1000 * 60 * 60 * 24 * 30 + 1000))) {
                                if (type_constract.equals("1")) {
                                    presenter.KData(symbol, from, time, resolution, "1");
                                } else if (type_constract.equals("2")) {
                                    presenter.KData_Constract(symbol, from, time, resolution, "1");
                                }
                            }
                            break;
                        default:
                    }
//                    if ()

//                    lastKline
                }
            }
        }, 1000, 1000);


    }

    /**
     * 头部显示内容
     *
     * @param objs
     */
    private void setCurrentcy(List<Currency> objs) {
        try {
            for (Currency currency : objs) {
                if (symbol.equals(currency.getSymbol())) {
                    mCurrency = currency;
                    break;
                }
            }
            String strUp = String.valueOf(mCurrency.getHigh());
            String strLow = String.valueOf(mCurrency.getLow());
            String strCount = processAmount(mCurrency.getVolume());
            Double douChg = mCurrency.getChg();
            String strRang = WonderfulMathUtils.getRundNumber(mCurrency.getChg() * 100, 2, "########0.") + "%";
            String strDataText = "≈ " + WonderfulMathUtils.getRundNumber(mCurrency.getClose() * MainActivity.rate,
                    2, null) + " CNY";
            String strDataOne = String.valueOf(mCurrency.getClose());


            BigDecimal bg3 = new BigDecimal(strUp);
            String v3 = bg3.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            kUp.setText(v3);

            BigDecimal bg2 = new BigDecimal(strLow);
            String v2 = bg2.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            kLow.setText(v2);
            kCount.setText(strCount);
            kRange.setText(strRang);

            BigDecimal bg1 = new BigDecimal(strDataOne);
            String v1 = bg1.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            mDataOne.setText(v1);
            mDataText.setText(strDataText);
            if (douChg < 0) {
                mDataOne.setTextColor(getResources().getColor(R.color.typeRed));
                kRange.setTextColor(getResources().getColor(R.color.typeRed));
            } else {
                mDataOne.setTextColor(getResources().getColor(R.color.typeGreen));
                kRange.setTextColor(getResources().getColor(R.color.typeGreen));
            }
            if (!isStart) {
                isStart = true;
                startTCP();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String processAmount(BigDecimal bAmount) {
        String v1 = "0.00";
        if (bAmount.compareTo(BigDecimal.ZERO) >= 0) {
            v1 = bAmount.setScale(7, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }

        if (bAmount.compareTo(new BigDecimal(10)) >= 0) {
            v1 = bAmount.setScale(6, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        if (bAmount.compareTo(new BigDecimal(100)) >= 0) {
            v1 = bAmount.setScale(5, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        // 1100 将以 1.1k显示（千计数）
        if (bAmount.compareTo(new BigDecimal(1000)) >= 0) {
            v1 = bAmount.setScale(4, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        if (bAmount.compareTo(new BigDecimal(10000)) >= 0) {
            v1 = bAmount.setScale(3, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        if (bAmount.compareTo(new BigDecimal(100000)) >= 0) {
            v1 = bAmount.setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }
        // 1100000 将以 1.1m显示（百万计数）
        if (bAmount.compareTo(new BigDecimal(1000000)) >= 0) {
            v1 = bAmount.divide(new BigDecimal(1000000)).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "m";
        }
        // 1100000000 将以 1.1b显示（十亿计数）
        if (bAmount.compareTo(new BigDecimal(1000000000)) >= 0) {
            v1 = bAmount.divide(new BigDecimal(1000000000)).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "b";
        }
        return v1;
    }

    private boolean addFace() {
        for (Favorite favorite : MainActivity.mFavorte) {
            if (symbol.equals(favorite.getSymbol())) return true;
        }
        return false;
    }

    private void startTCP() {
        if (type_constract.equals("1")) {
            EventBus.getDefault().post(new SocketMessage(0, ISocket.CMD.SUBSCRIBE_SYMBOL_THUMB, null)); // 开始订阅
        } else if (type_constract.equals("2")) {
            EventBus.getDefault().post(new SocketMessage(1, ISocket.CMD.CONTRACT_SUBSCRIBE_SYMBOL_THUMB, null)); // 开始订阅
        }
    }

    /**
     * 获取头部信息
     */
    private void getCurrent() {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAllCurrency()).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        List<Currency> obj = new Gson().fromJson(response, new TypeToken<List<Currency>>() {
                        }.getType());
                        currencies.clear();
                        currencies.addAll(obj);
                        setCurrentcy(currencies);
                    }
                });
    }

    private void getCurrent_constract() {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAllCurrency_Constract()).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        List<Currency> obj = new Gson().fromJson(response, new TypeToken<List<Currency>>() {
                        }.getType());
                        currencies.clear();
                        currencies.addAll(obj);
                        setCurrentcy(currencies);
                    }
                });
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
                if (isPopClick) {
                    tvMore.setText(selectedTextView.getText());
                    tvMore.setSelected(true);
                } else {
                    tvMore.setText(getString(R.string.more));
                    tvMore.setSelected(false);
                    textViews.get(j).setSelected(true);
                    Drawable home_zhang_no1 = getResources().getDrawable(
                            R.drawable.tab);
                    textViews.get(j).setCompoundDrawablesWithIntrinsicBounds(null,
                            null, null, home_zhang_no1);
                }
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
            } else if (!isPopClick) {
                tvMore.setSelected(false);
            }
        }
    }


    @Override
    public void setPresenter(KlineContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void KDataFail(Integer code, String toastMessage) {

    }

    @Override
    public void KDataSuccess(JSONArray obj) {
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
                    if (type_constract.equals("1")) {
                        presenter.KData(symbol, from, to, resolution, "2");
                    } else if (type_constract.equals("2")) {
                        presenter.KData_Constract(symbol, from, to, resolution, "2");
                    }
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
    public void allCurrencySuccess(List<Currency> obj) {

    }

    @Override
    public void allCurrencyFail(Integer code, String toastMessage) {

    }

    private void showDialog() {
        if (mDialog == null) mDialog = new LoadDialog(activity);
        mDialog.show();
    }

    private void hideDialog() {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();
    }

    /**
     * 副图的点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMA:
            case R.id.tvBOLL:
            case R.id.tvMainHide:
                if (view.getId() == R.id.tvMA) {
                    klineView.setMainImgType(KLineView.MAIN_IMG_MA);
                    maView.setSelected(true);
                    bollView.setSelected(false);
                    hideMainView.setSelected(false);
                } else if (view.getId() == R.id.tvBOLL) {
                    klineView.setMainImgType(KLineView.MAIN_IMG_BOLL);
                    maView.setSelected(false);
                    bollView.setSelected(true);
                    hideMainView.setSelected(false);
                } else {
                    maView.setSelected(false);
                    bollView.setSelected(false);
                    hideMainView.setSelected(true);
                }
                popupWindow.dismiss();
                break;
            case R.id.tvMACD:
            case R.id.tvRSI:
            case R.id.tvKDJ:
            case R.id.tvChildHide:
                if (view.getId() == R.id.tvMACD) {
                    childType = 0;
                    klineView.setDeputyImgType(KLineView.DEPUTY_IMG_MACD);
                    macdView.setSelected(true);
                    rsiView.setSelected(false);
                    kdjView.setSelected(false);
                    hideChildView.setSelected(false);

                    if (!klineView.getVicePicShow()) {
                        klineView.setDeputyPicShow(!klineView.getVicePicShow());
                    }
                } else if (view.getId() == R.id.tvKDJ) {
                    childType = 1;
                    klineView.setDeputyImgType(KLineView.DEPUTY_IMG_KDJ);
                    macdView.setSelected(false);
                    rsiView.setSelected(false);
                    kdjView.setSelected(true);
                    hideChildView.setSelected(false);

                    if (!klineView.getVicePicShow()) {
                        klineView.setDeputyPicShow(!klineView.getVicePicShow());
                    }
                } else if (view.getId() == R.id.tvRSI) {
                    childType = 2;
                    klineView.setDeputyImgType(KLineView.DEPUTY_IMG_RSI);
                    macdView.setSelected(false);
                    rsiView.setSelected(true);
                    kdjView.setSelected(false);
                    hideChildView.setSelected(false);

                    if (!klineView.getVicePicShow()) {
                        klineView.setDeputyPicShow(!klineView.getVicePicShow());
                    }
                } else {
                    childType = -1;
                    macdView.setSelected(false);
                    rsiView.setSelected(false);
                    kdjView.setSelected(false);
                    hideChildView.setSelected(true);

                    if (klineView.getVicePicShow()) {
                        klineView.setDeputyPicShow(!klineView.getVicePicShow());
                    }
                }
                popupWindow.dismiss();
                break;
            default:
        }
    }

}
