package com.bizzan.ui.entrust;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.adapter.TrustAdapterNewHistory;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.EntrustHistory_constract;
import com.bizzan.ui.dialog.EntrustOperateDialogFragment;
import com.bizzan.entity.Currency;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import org.angmarch.views.NiceSpinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * 当前委托的界面
 */
public class TrustListActivity extends BaseActivity implements View.OnClickListener, ITrustContract.View {

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, TrustListActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.iv_filter)
    ImageView iv_filter;
    @BindView(R.id.ibBack)
    ImageView ibBack;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rvAds)
    RecyclerView rvAds;
    @BindView(R.id.tv_no_data)
    TextView tv_no_data;
    //    @BindView(R.id.vp_trust)
//    ViewPager vp_trust;
    @BindView(R.id.tv_title_current_trust)
    TextView tv_title_current_trust;
    @BindView(R.id.tv_title_history_trust)
    TextView tv_title_history_trust;
    @BindView(R.id.current_trust_underline)
    View current_trust_underline;
    @BindView(R.id.history_trust_underline)
    View history_trust_underline;
    @BindView(R.id.dropdown_layout)
    DropdownLayout dropdown_layout;
    @BindView(R.id.sp_symbol)
    NiceSpinner sp_symbol;
    @BindView(R.id.sp_type)
    NiceSpinner sp_type;
    @BindView(R.id.sp_direction)
    NiceSpinner sp_direction;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;
    @BindView(R.id.tv_end_time)
    TextView tv_end_time;
    @BindView(R.id.tv_reset)
    TextView tv_reset;
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;
    private List<EntrustHistory> entrusts = new ArrayList<>();
    TrustAdapterNewHistory adapter_2;
    private List<EntrustHistory> entrustHistories = new ArrayList<>();
    TrustAdapterNewHistory adapter;
    private int currentPage = 1;
    private int historyPage = 1;
    private ITrustContract.Presenter mPresenter;
    private String symbol = "";
    String startTime = "";
    String endTime = "";
    String type = "";
    String direction = "";
    boolean isCurrent = true;
    @BindView(R.id.line_2)
    LinearLayout line_2;
    @BindView(R.id.view_zhe)
    View view_zhe;
    int pageSize=30;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_current_trust;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mPresenter = new TrustPresentImpl(this);
        ibBack.setOnClickListener(this);
        iv_filter.setOnClickListener(this);
        tv_title_current_trust.setOnClickListener(this);
        tv_title_history_trust.setOnClickListener(this);

        tv_reset.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
        tv_start_time.setOnClickListener(this);
        tv_end_time.setOnClickListener(this);
        initSpinners();
//        initViewPager();
        setCurrentSelected();
        line_2.setVisibility(View.GONE);
        view_zhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdown_layout.hide();
                line_2.setVisibility(View.GONE);
                Drawable drawable = getResources().getDrawable(R.drawable.icon_filter_no);
                iv_filter.setBackgroundDrawable(drawable);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isCurrent) {
                    displayLoadingPopup();
                    currentPage = 1;
                    WonderfulLogUtils.logi("miao", "2333");
                    mPresenter.getCurrentOrder(getToken(), currentPage, pageSize, symbol, type, direction, startTime, endTime);
                    if(adapter_2 != null) {
                        adapter_2.setEnableLoadMore(false);
                        adapter_2.loadMoreEnd(false);
                        adapter_2.notifyDataSetChanged();
                    }
                } else {
                    displayLoadingPopup();
                    historyPage = 1;
                    mPresenter.getOrderHistory(getToken(), historyPage, pageSize, symbol, type, direction, startTime, endTime);
                    if(adapter != null) {
                        adapter.setEnableLoadMore(false);
                        adapter.loadMoreEnd(false);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void loadMore() {
        displayLoadingPopup();
        refreshLayout.setEnabled(false);
        if (isCurrent) {
            currentPage = currentPage + 1;
            mPresenter.getCurrentOrder(getToken(), currentPage, pageSize, symbol, type, direction, startTime, endTime);
        } else {
            historyPage = historyPage + 1;
            mPresenter.getOrderHistory(getToken(), historyPage, pageSize, symbol, type, direction, startTime, endTime);
        }
    }
//    private void initViewPager() {
//        final TrustFragmentNew trustFragmentCurrent = TrustFragmentNew.getInstance(TrustFragment.TRUST_TYPE_CURRENT);
//        TrustFragmentNew trustFragmentHistory = TrustFragmentNew.getInstance(TrustFragment.TRUST_TYPE_HISTORY);
//        fragmentNewList.add(trustFragmentCurrent);
//        fragmentNewList.add(trustFragmentHistory);
//        TrustPagerAdapterNew pagerAdapter = new TrustPagerAdapterNew(getSupportFragmentManager(), fragmentNewList);
//        vp_trust.setAdapter(pagerAdapter);
//    }

    private void initSpinners() {
        //类型选择（市价-MARKET_PRICE，限价-LIMIT_PRICE）
        line_2.setVisibility(View.VISIBLE);
        sp_type.attachDataSource(new LinkedList<>(Arrays.asList(getResources().getTextArray((R.array.text_type_array)))));
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    type = "";
                    sp_type.setText("");
                } else {
                    CharSequence[] textArray = getResources().getTextArray(R.array.text_type_param);
                    type = textArray[position]+"";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //方向选择（买0，卖1）
        sp_direction.attachDataSource(new LinkedList<>(Arrays.asList(getResources().getTextArray(R.array.text_direction))));
        sp_direction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    direction = "";
                    sp_direction.setText("");
                } else {
                    direction = (position - 1) + "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final List<String> currencyNames = getAllCurrencyName(MyApplication.list);
        //交易对选择
        sp_symbol.attachDataSource(currencyNames);
        sp_symbol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    symbol = "";
                    sp_symbol.setText("");
                } else {
                    symbol = currencyNames.get(position);
                }

                Log.i("sp_symbol", symbol + "--" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_type.setText("");
        sp_direction.setText("");
        sp_symbol.setText("");


    }

    private List<String> getAllCurrencyName(List<Currency> list) {
        List<String> names = new ArrayList<>();
        names.add(WonderfulToastUtils.getString(this,R.string.no_select));
        for (Currency currency : list) {
            names.add(currency.getSymbol());
        }
        return names;
    }

    @Override
    protected void obtainData() {
//        mPresenter.getCurrentOrder(getToken(), currentPage, 10, symbol, type, direction, startTime, endTime);
//        Log.i("params", symbol + "" + type + "--" + direction + "--" + startTime + "--" + endTime);
//        mPresenter.getOrderHistory(getToken(), historyPage, 10, symbol, type, direction, startTime, endTime);
    }

    private void showBottomFragment(EntrustHistory entrust) {
        EntrustOperateDialogFragment entrustOperateFragment =
                EntrustOperateDialogFragment.getInstance(entrust);
        entrustOperateFragment.show(getSupportFragmentManager(), "bottom");
        entrustOperateFragment.setCallback(new EntrustOperateDialogFragment.OperateCallback() {
            @Override
            public void cancleOrder(String orderId) {
                // 撤消
                if (mPresenter != null) {
                    mPresenter.getCancelEntrust(getToken(), orderId);
                }
            }
        });
    }

    @Override
    protected void fillWidget() {

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
    protected void loadData() {
//        mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currentPage, 10, "", "", "", "", "");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                break;
            case R.id.iv_filter://展开/收起筛选窗口
                if (dropdown_layout.isOpen()) {
                    dropdown_layout.hide();
                    line_2.setVisibility(View.GONE);
                    Drawable drawable = getResources().getDrawable(R.drawable.icon_filter_no);
                    iv_filter.setBackgroundDrawable(drawable);
                } else {
                    dropdown_layout.show();
                    line_2.setVisibility(View.VISIBLE);
                    Drawable drawable = getResources().getDrawable(R.drawable.icon_filter_orange);
                    iv_filter.setBackgroundDrawable(drawable);
                }
                break;
            case R.id.tv_reset://清空筛选条件
                clearFilters();
                break;
            case R.id.tv_confirm://确认筛选条件
                Log.i("params", symbol + "" + type + "--" + direction + "--" + startTime + "--" + endTime);
                if (checkFilter()) {
                    displayLoadingPopup();
                    if (isCurrent) {
                        WonderfulLogUtils.logi("当前委托", "999");
                        mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currentPage, pageSize, symbol, type, direction, startTime, endTime);
                    } else {
                        mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), historyPage, pageSize, symbol, type, direction, startTime, endTime);
                    }
                    dropdown_layout.hide();
                    line_2.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_title_current_trust:
                displayLoadingPopup();
                setCurrentSelected();
                break;
            case R.id.tv_title_history_trust:
                displayLoadingPopup();
                setHistorySelected();
                break;
            case R.id.tv_start_time:
                showTimePickerView(tv_start_time, true);
                break;
            case R.id.tv_end_time:
                showTimePickerView(tv_end_time, false);
                break;
            default:
        }
    }

    private void setHistorySelected() {
        historyPage = 1;
        tv_title_current_trust.setSelected(false);
        current_trust_underline.setVisibility(View.GONE);
        tv_title_history_trust.setSelected(true);
        history_trust_underline.setVisibility(View.VISIBLE);
        isCurrent = false;
        mPresenter.getOrderHistory(SharedPreferenceInstance.getInstance().getTOKEN(), historyPage, pageSize, "", "", "", "", "");
    }

    private void setCurrentSelected() {
        currentPage = 1;
        tv_title_current_trust.setSelected(true);
        current_trust_underline.setVisibility(View.VISIBLE);
        tv_title_history_trust.setSelected(false);
        history_trust_underline.setVisibility(View.GONE);
        isCurrent = true;
        WonderfulLogUtils.logi("miao", "444");
        mPresenter.getCurrentOrder(SharedPreferenceInstance.getInstance().getTOKEN(), currentPage, pageSize, "", "", "", "", "");
    }

    public void showTimePickerView(final TextView tvTime, final boolean isStart) {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 4, 1, 0, 0, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.setTimeInMillis(System.currentTimeMillis());
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:00");
                tvTime.setText(df.format(date));
                if (isStart) {
                    startTime = WonderfulStringUtils.dateToLong(date) + "";
                } else {
                    endTime = WonderfulStringUtils.dateToLong(date) + "";
                }
            }
        }).setType(new boolean[]{true, true, true, true, false, false})
                .setCancelText(WonderfulToastUtils.getString(this,R.string.cancle))//取消按钮文字
                .setSubmitText(WonderfulToastUtils.getString(this,R.string.tv_affirm))//确认按钮文字
                .setTitleText(WonderfulToastUtils.getString(this,R.string.star_time))//标题文字
                .setSubmitColor(Color.parseColor("#0b0b0b"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#787878"))//取消按钮文字颜色
                .setRangDate(startDate, endDate)//起始终止年月日设定

                .build();
        pvTime.show();
    }

    private void clearFilters() {
        symbol = "";
        type = "";
        direction = "";
        startTime = "";
        endTime = "";
        sp_symbol.setText("");
        sp_direction.setText("");
        sp_type.setText("");
        tv_start_time.setText("");
        tv_end_time.setText("");
    }

    private boolean checkFilter() {
        //如果筛选条件全为空
        if (TextUtils.isEmpty(symbol) && TextUtils.isEmpty(type) && TextUtils.isEmpty(direction) && TextUtils.isEmpty(startTime) && TextUtils.isEmpty(endTime)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.tv_select_condition));
            return false;
        }
        //开始时间和结束时间
        if (TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.tv_select_start_time));
            return false;
        }
        if (!TextUtils.isEmpty(startTime) && TextUtils.isEmpty(endTime)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.tv_select_finish_time));
            return false;
        }
        return true;
    }


    /**
     * 刷新
     */
//    private void refreshText() {
//        displayLoadingPopup();
//        currentPage = 1;
//        historyPage = 1;
//        int currentPage = vp_trust.getCurrentItem();
//        fragmentNewList.get(currentPage).getAdapter().setEnableLoadMore(false);
//        if (MyApplication.getApp().isLogin()) {
//            mPresenter.getCurrentOrder(getToken(), currentPage, 10, symbol, type, direction, startTime, endTime);
//            mPresenter.getOrderHistory(getToken(), historyPage, 10, symbol, type, direction, startTime, endTime);
//        } else {
//            refreshLayout.setRefreshing(false);
//        }
//    }

    /**
     * 下拉
     */
//    private void loadMoreText() {
//        if (MyApplication.getApp().isLogin()) {
//            int pagePosition=vp_trust.getCurrentItem();
//            if(pagePosition==0){
//                currentPage++;
//                refreshLayout.setEnabled(false);
//                mPresenter.getCurrentOrder(getToken(), currentPage, 10, symbol, type, direction, startTime, endTime);
//            }else {
//                historyPage++;
//                refreshLayout.setEnabled(false);
//                mPresenter.getOrderHistory(getToken(), historyPage, 10, symbol, type, direction, startTime, endTime);
//
//
//            }
//        }
//    }
    @Override
    public void errorMes(int e, String meg) {
        hideLoadingPopup();
        if (refreshLayout == null) {
            return;
        }
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onDataNotAvailable(int code, String message) {
        hideLoadingPopup();
        if (refreshLayout == null) {
            return;
        }
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        if (code == 4000) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.unknown_error));
        }
    }

    @Override
    public void getHistorySuccess(List<EntrustHistory> entrustHistories) {
        hideLoadingPopup();
        if (refreshLayout == null) {
            return;
        }
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        if (entrustHistories == null||entrustHistories.size()==0) {
            if (historyPage == 1) {
                tv_no_data.setVisibility(View.VISIBLE);
                rvAds.setVisibility(View.GONE);
            }
            return;
        }
        setListData(entrustHistories);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getHistoryConstractSuccess(List<EntrustHistory_constract.ContentBean> success) {

    }

    public void setListData(List list) {
        if (list != null && list.size() > 0) {
            refreshLayout.setVisibility(View.VISIBLE);
            rvAds.setVisibility(View.VISIBLE);
            tv_no_data.setVisibility(View.GONE);
            Log.i("Trust", "当前页面"+String.valueOf(historyPage));
            if (isCurrent) {
                if (currentPage == 1) {
                    entrusts.clear();
                    adapter_2 = new TrustAdapterNewHistory(entrusts, true);
                    rvAds.setLayoutManager(new LinearLayoutManager(TrustListActivity.this));
                    rvAds.setAdapter(adapter_2);
                }
                entrusts.addAll(list);
                adapter_2.notifyDataSetChanged();
                adapter_2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        showBottomFragment((EntrustHistory) adapter.getData().get(position));
                    }
                });
                if(list.size()<pageSize){
                    adapter_2.setEnableLoadMore(false);
                    adapter_2.loadMoreEnd(true);
                }else{
                    adapter_2.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            loadMore();
                        }
                    }, rvAds);
                }
            } else {
                if (historyPage == 1) {
                    entrustHistories.clear();
                    adapter = new TrustAdapterNewHistory(entrustHistories, false);
                    rvAds.setLayoutManager(new LinearLayoutManager(TrustListActivity.this));
                    rvAds.setAdapter(adapter);
                    adapter.setEnableLoadMore(true);
                    adapter.loadMoreEnd(false);
                }
                entrustHistories.addAll(list);
                adapter.notifyDataSetChanged();
                // 委托成交详情不显示
                /*
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if ("COMPLETED".equals(entrustHistories.get(position).getStatus())) {
                            TrustDetailActivity.show(TrustListActivity.this,entrustHistories.get(position).getSymbol(),entrustHistories.get(position));
                        }
                    }
                });
                 */
                if(list.size()<pageSize){
                    Log.i("Trust", "PageSize大于listsize");
                    adapter.setEnableLoadMore(false);
                    adapter.loadMoreEnd(true);
                }else{
                    adapter.setEnableLoadMore(true);
                    adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            loadMore();
                        }
                    }, rvAds);
                }
                adapter.disableLoadMoreIfNotFullPage();
            }
        } else {
            if (currentPage == 1) {
                tv_no_data.setVisibility(View.VISIBLE);
                rvAds.setVisibility(View.GONE);
            }
            if (historyPage == 1) {
                tv_no_data.setVisibility(View.VISIBLE);
                rvAds.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 取消委托成功的返回
     */
    @Override
    public void getCancelSuccess(String success) {
        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.text_cancel_success));
        setCurrentSelected();
    }

    @Override
    public void getCurrentSuccess(List<EntrustHistory> entrust) {
        hideLoadingPopup();
        if (refreshLayout == null) {
            return;
        }
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        if (entrust == null) {
            if (currentPage == 1) {
                tv_no_data.setVisibility(View.VISIBLE);
                rvAds.setVisibility(View.GONE);
            }
            return;
        }
        setListData(entrust);
        if (adapter_2 != null) {
            adapter_2.notifyDataSetChanged();
        }

    }

}
