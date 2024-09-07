package com.bizzan.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.app.UrlFactory;
import com.bizzan.base.BaseTransFragment;
import com.bizzan.entity.DataListResponse;
import com.bizzan.entity.MyLocked;
import com.bizzan.entity.MyLockedList;
import com.bizzan.entity.MyMining;
import com.bizzan.entity.MyMiningList;
import com.bizzan.entity.MyOrder;
import com.bizzan.entity.MyOrderList;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import butterknife.BindView;
import okhttp3.Request;

/**
 * 我的参与
 */
public class MyActivityFragment extends BaseTransFragment {

    @BindView(R.id.refreshLayout)
    com.bizzan.customview.VpSwipeRefreshLayout refreshLayout;
    @BindView(R.id.listview)
    android.support.v7.widget.RecyclerView listview;
    int type;
    int pageNo = 1, pageSize = 10;
    BaseQuickAdapter mAdapter;

    public static MyActivityFragment newInstance(int step) {
        MyActivityFragment fragment = new MyActivityFragment();
        Bundle argument = new Bundle();
        argument.putInt("type", step);
        fragment.setArguments(argument);
        return fragment;
    }


    @Override
    protected String getmTag() {
        return MyActivityFragment.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        type = getArguments().getInt("type");
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(1);
            }
        });
        int padding = getResources().getDimensionPixelSize(R.dimen.dp_4);
        listview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(padding, padding, padding, padding);
            }
        });
        if (type == 0) {//我参与的活动
            mAdapter = new BaseQuickAdapter<MyOrder, BaseViewHolder>(R.layout.layout_myorder_item) {
                @Override
                protected void convert(BaseViewHolder helper, MyOrder item) {
                    helper.setText(R.id.tvName, item.getActivityName());
                    helper.setText(R.id.tvType, item.getTypeStr(getResources()));
                    helper.setText(R.id.tvStatus, item.getSatusStr(getResources()));
                    int color;
                    if (item.getState() == 1) {
                        color = ContextCompat.getColor(getContext(), R.color.typeRed);
                    } else if (item.getState() == 2) {
                        color = ContextCompat.getColor(getContext(), R.color.Green);
                    } else {
                        color = ContextCompat.getColor(getContext(), R.color.typeGray);
                    }
                    helper.setTextColor(R.id.tvStatus, color);
                    helper.setText(R.id.cointype, item.getBaseSymbol());
                    helper.setText(R.id.tvAmount, item.getAmount() + "");
                    helper.setText(R.id.tvunit, item.getCoinSymbol());
                    helper.setText(R.id.tvMoney, item.getTurnover() + " " + item.getBaseSymbol());
                    helper.setText(R.id.tvdate, item.getCreateTime());
                }
            };
        } else if (type == 1) {
            //我的矿机
            mAdapter = new BaseQuickAdapter<MyMining, BaseViewHolder>(R.layout.layout_mymining_item) {
                @Override
                protected void convert(BaseViewHolder helper, MyMining item) {
                    helper.setText(R.id.tvName, item.getTitle());
                    helper.setText(R.id.tvStatus, item.getStatusStr(getResources()));
                    helper.setText(R.id.cointype, item.getMiningUnit());
                    helper.setText(R.id.miningPeriod, item.getPeriodStr(getResources()));
                    helper.setText(R.id.miningDays, item.getMiningDays() + item.getPeriodStr(getResources()));
                    helper.setText(R.id.miningedDays, item.getMiningedDays() + item.getPeriodStr(getResources()));
                    helper.setText(R.id.miningDaysprofit, item.getMiningDaysprofit() + " " + item.getMiningUnit() + "/" + item.getPeriodStr(getResources()));
                    helper.setText(R.id.miningCurrentDaysProfit, item.getCurrentDaysprofit() + " " + item.getMiningUnit() + "/" + item.getPeriodStr(getResources()));
                    String tip = getString(R.string.invitetip1) +
                            WonderfulStringUtils.parseFloatString(String.valueOf(item.getMiningInvite() * 100), 0) + getString(R.string.invitetip2) +
                            WonderfulStringUtils.parseFloatString(String.valueOf(item.getMiningInvitelimit() * 100), 0) + "%";
                    helper.setText(R.id.tvTips, tip);
                }
            };
        } else {
            //我的锁仓
            mAdapter = new BaseQuickAdapter<MyLocked, BaseViewHolder>(R.layout.layout_mylocked_item) {
                @Override
                protected void convert(BaseViewHolder helper, MyLocked item) {
                    helper.setText(R.id.tvName, item.getTitle());
                    helper.setText(R.id.tvStatus, item.getStatusStr(getResources()));
                    helper.setText(R.id.releaseUnit, item.getReleaseUnit());
                    helper.setText(R.id.totalLocked, item.getTotalLocked() + "");
                    helper.setText(R.id.lockedDays, item.getLockedDays() + item.getPeriodStr(getResources()));
                    helper.setText(R.id.releasedDays, item.getReleasedDays() + item.getPeriodStr(getResources()));
                    if (item.getReleaseType() == 0) {
                        helper.setText(R.id.miningDaysProfit, item.getOriginReleaseamount() + " " + item.getReleaseUnit() + "/" + item.getPeriodStr(getResources()));
                        helper.setText(R.id.currentReleaseamount, item.getCurrentReleaseamount() + " " + item.getReleaseUnit());
                    } else {
                        helper.setText(R.id.miningDaysProfit,
                                WonderfulStringUtils.parseFloatString(String.valueOf(item.getReleasePercent() * 100), 0) + "%/" + item.getPeriodStr(getResources()));
                        helper.setText(R.id.currentReleaseamount,
                                WonderfulStringUtils.parseFloatString(String.valueOf(item.getReleaseCurrentpercent() * 100), 0) + "%/" + item.getPeriodStr(getResources()));
                    }
                }
            };
        }
        mAdapter.bindToRecyclerView(listview);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(++pageNo);
            }
        }, listview);
    }


    @Override
    protected void obtainData() {
        refreshLayout.setRefreshing(true);
        getData(pageNo);
    }

    public String getToken() {
        if (MyApplication.getApp().getCurrentUser() != null)
            return MyApplication.getApp().getCurrentUser().getToken();
        return "";
    }

    private void getData(int page) {
        String url = null;
        if (0 == type) {
            url = UrlFactory.getMyAttendRecords();
        } else if (1 == type) {
            url = UrlFactory.getMyMinings();
        } else {
            url = UrlFactory.getMylocked();
        }
        WonderfulOkhttpUtils.post().url(url)
                .addHeader("x-auth-token", getToken())
                .addParams("pageNo", String.valueOf(page))
                .addParams("pageSize", String.valueOf(pageSize))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (pageNo > 1) {
                    pageNo--;
                }
                refreshLayout.setRefreshing(false);
                mAdapter.loadMoreComplete();
//                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                refreshLayout.setRefreshing(false);
                try {
                    if (0 == type) {
//                        tag = "我的参与";
                        Type type = new TypeToken<DataListResponse<MyOrderList>>() {
                        }.getType();
                        DataListResponse<MyOrderList> dataResponse = new Gson().fromJson(response, type);
                        dataResponse.setAdapter(mAdapter);
                    } else if (1 == type) {
//                        tag = "我的矿机";
                        Type type = new TypeToken<DataListResponse<MyMiningList>>() {
                        }.getType();
                        DataListResponse<MyMiningList> dataResponse = new Gson().fromJson(response, type);
                        dataResponse.setAdapter(mAdapter);
                    } else {
//                        tag = "我的锁仓";
                        Type type = new TypeToken<DataListResponse<MyLockedList>>() {
                        }.getType();
                        DataListResponse<MyLockedList> dataResponse = new Gson().fromJson(response, type);
                        dataResponse.setAdapter(mAdapter);
                    }

                } catch (Exception e) {
                    mAdapter.loadMoreEnd();
                    Log.e("ddd", e.getMessage());
                    e.printStackTrace();
//                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

}
