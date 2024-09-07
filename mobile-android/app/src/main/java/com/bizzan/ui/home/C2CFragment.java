package com.bizzan.ui.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bizzan.R;
import com.bizzan.ui.buy_or_sell.C2CBuyOrSellActivity;
import com.bizzan.ui.home.presenter.C2CPresenter;
import com.bizzan.adapter.C2CAdapter;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseLazyFragment;
import com.bizzan.entity.C2C;
import com.bizzan.entity.CoinInfo;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.bizzan.app.Injection;

/**
 * Created by Administrator on 2018/2/28.
 */

public class C2CFragment extends BaseLazyFragment implements MainContract.C2CView {
    @BindView(R.id.rvAds)
    RecyclerView rvAds;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ArrayList<C2C.C2CBean> c2cs = new ArrayList<>();
    private C2CAdapter adapter;
    private C2C c2c;
    private CoinInfo coinInfo;
    private MainContract.C2CPresenter presenter;
    private int pageNo = 1;
    private int pageSize = 40;
    private String advertiseType = "SELL";

    public static C2CFragment getInstance(CoinInfo coinInfo) {
        C2CFragment c2CFragment = new C2CFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("coinInfo", coinInfo);
        c2CFragment.setArguments(bundle);
        return c2CFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ad;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        coinInfo = (CoinInfo) getArguments().getSerializable("coinInfo");
        presenter = new C2CPresenter(Injection.provideTasksRepository(getActivity().getApplicationContext()), this);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        if (coinInfo != null) {
            presenter.advertise(pageNo, pageSize, advertiseType, coinInfo.getId());
            adapter.setEnableLoadMore(false);
            adapter.loadMoreEnd(false);
            adapter.notifyDataSetChanged();
           // pageNo++;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (coinInfo != null) {
           // presenter.advertise(pageNo, pageSize, advertiseType, coinInfo.getId());
           // pageNo++;
        }
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        initRvAds();
    }

    private void initRvAds() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvAds.setLayoutManager(manager);
        adapter = new C2CAdapter(getActivity(), R.layout.adapter_c2c, c2cs);
        adapter.bindToRecyclerView(rvAds);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.isFirstOnly(true);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (MyApplication.getApp().isLogin()) {
//                    if (currency != null) {
                    if (MyApplication.realVerified == 1) {
                        C2CBuyOrSellActivity.actionStart(getActivity(), c2cs.get(position));
                    }else {
                        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.realname)+"");
                    }
//                    }
                } else {
                    WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_xian_login)+"");
                }

            }
        });
        /*adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, rvAds);*/
        adapter.setEmptyView(R.layout.empty_rv_ad);
    }

    private void loadMore() {
        refreshLayout.setEnabled(false);
        presenter.advertise(pageNo, pageSize, advertiseType, coinInfo.getId());
    }

    @Override
    protected void loadData() {
        if (coinInfo != null)
            presenter.advertise(pageNo, pageSize, advertiseType, coinInfo.getId());
    }

    @Override
    public void setPresenter(MainContract.C2CPresenter presenter) {
        this.presenter = presenter;
    }

    public void setAdvertiseType(String advertiseType) {
        this.advertiseType = advertiseType;
    }

    @Override
    public void advertiseSuccess(C2C obj) {
        adapter.setEnableLoadMore(true);
        adapter.loadMoreComplete();
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        if (obj == null) return;
        List<C2C.C2CBean> c2cs = obj.getContext();
        /*if (++pageNo > obj.getTotalPage()) {
            //this.c2cs.clear();
            //this.c2cs.addAll(c2cs);
            //adapter.notifyDataSetChanged();
        }else {
            this.c2cs.addAll(c2cs);
        }*/
        if (c2cs == null || c2cs.size() == 0) {
            if (pageNo == 1) {
                this.c2cs.clear();
                adapter.notifyDataSetChanged();
            }
            return;
        }
        if (pageNo == 1) {
            this.c2cs.clear();
            this.c2cs.addAll(c2cs);
        } else {
            this.c2cs.addAll(c2cs);
        }
        adapter.notifyDataSetChanged();
        adapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void advertiseFail(Integer code, String toastMessage) {
        adapter.setEnableLoadMore(true);
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
