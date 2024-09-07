package com.bizzan.ui.entrust;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.bizzan.R;
import com.bizzan.adapter.TrustAdapterHistory_constract;
import com.bizzan.adapter.TrustAdapterNewHistory;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.entity.EntrustHistory_constract;
import com.bizzan.ui.dialog.EntrustOperateDialogFragment;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulToastUtils;

/**
 * 当前委托的界面   合约
 */
public class TrustListConstractActivity extends BaseActivity implements View.OnClickListener, ITrustContract.View {

    private int id;

    public static void show(Activity activity, int id) {
        Intent intent = new Intent(activity, TrustListConstractActivity.class);
        intent.putExtra("id", id);
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
    @BindView(R.id.tv_title_history_trust)
    TextView tv_title_history_trust;
    @BindView(R.id.tv_title_now_trust)
    TextView tv_title_now_trust;
    @BindView(R.id.history_trust_underline)
    View history_trust_underline;
    private List<EntrustHistory_constract.ContentBean> entrustHistories = new ArrayList<>();
    TrustAdapterHistory_constract adapter;
    private int currentPage = 1;
    private int historyPage = 1;
    private ITrustContract.Presenter mPresenter;
    private String symbol = "";
    String startTime = "";
    String endTime = "";
    String type = "";
    String direction = "";
    boolean isCurrent = true;
    int pageSize = 50;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_current_constract;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mPresenter = new TrustPresentImpl(this);
        ibBack.setOnClickListener(this);
        iv_filter.setOnClickListener(this);
        tv_title_history_trust.setOnClickListener(this);
        tv_title_now_trust.setOnClickListener(this);
        id = getIntent().getIntExtra("id", 0);

        setHistorySelected();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                displayLoadingPopup();
                historyPage = 1;
                //String contractCoinId, String pageNo, String pageSize
                mPresenter.getOrderHistory_constract(getToken(), id + "", historyPage + "", pageSize + "");
                if (adapter != null) {
                    adapter.setEnableLoadMore(false);
                    adapter.loadMoreEnd(false);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void loadMore() {
        displayLoadingPopup();
        refreshLayout.setEnabled(false);
        historyPage = historyPage + 1;
        mPresenter.getOrderHistory_constract(getToken(), id + "", historyPage + "", pageSize + "");
    }

    @Override
    protected void obtainData() {

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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                break;
            case R.id.tv_title_history_trust:
                displayLoadingPopup();
                setHistorySelected();
                break;
            default:
        }
    }

    private void setHistorySelected() {
        historyPage = 1;
        tv_title_history_trust.setSelected(true);
        history_trust_underline.setVisibility(View.VISIBLE);
        isCurrent = false;
        mPresenter.getOrderHistory_constract(getToken(), id + "", historyPage + "", pageSize + "");
    }

    private void setCurrentSelected() {
        currentPage = 1;
        tv_title_history_trust.setSelected(false);
        history_trust_underline.setVisibility(View.GONE);
        isCurrent = true;
        WonderfulLogUtils.logi("miao", "444");
    }

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
    }

    @Override
    public void getHistoryConstractSuccess(List<EntrustHistory_constract.ContentBean> success) {
        hideLoadingPopup();
        if (refreshLayout == null) {
            return;
        }
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        if (success == null || success.size() == 0) {
            if (historyPage == 1) {
                tv_no_data.setVisibility(View.VISIBLE);
                rvAds.setVisibility(View.GONE);
            }
            return;
        }
        setListData(success);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void setListData(List<EntrustHistory_constract.ContentBean> list) {
        if (list != null && list.size() > 0) {
            refreshLayout.setVisibility(View.VISIBLE);
            rvAds.setVisibility(View.VISIBLE);
            tv_no_data.setVisibility(View.GONE);
            if (historyPage == 1) {
                entrustHistories.clear();
                adapter = new TrustAdapterHistory_constract(entrustHistories, false);
                rvAds.setLayoutManager(new LinearLayoutManager(TrustListConstractActivity.this));
                rvAds.setAdapter(adapter);
                adapter.setEnableLoadMore(true);
                adapter.loadMoreEnd(false);
            }
            entrustHistories.addAll(list);
            adapter.notifyDataSetChanged();
            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    TrustlistconstractdataActivity.show(TrustListConstractActivity.this, entrustHistories.get(position));
                }
            });
            if (list.size() < pageSize) {
                Log.i("Trust", "PageSize大于listsize");
                adapter.setEnableLoadMore(false);
                adapter.loadMoreEnd(true);
            } else {
                adapter.setEnableLoadMore(true);
                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        loadMore();
                    }
                }, rvAds);
            }
            adapter.disableLoadMoreIfNotFullPage();
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

    }

}
