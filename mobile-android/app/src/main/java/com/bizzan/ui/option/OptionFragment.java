package com.bizzan.ui.option;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.bizzan.R;
import com.bizzan.adapter.Homes_Contract_Adapter;
import com.bizzan.adapter.Homes_Option_Adapter;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.Injection;
import com.bizzan.app.MyApplication;
import com.bizzan.entity.Currency;
import com.bizzan.entity.OptionBean;
import com.bizzan.ui.home.MarketBaseFragment;
import com.bizzan.ui.home.presenter.CommonPresenter;
import com.bizzan.ui.home.presenter.ICommonView;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulToastUtils;

/**
 * Created by Administrator on 2018/1/29.
 */

public class OptionFragment extends MarketBaseFragment{

    @BindView(R.id.rvContent)
    RecyclerView rvContent;
    private Homes_Option_Adapter adapter;
    private List<OptionBean.DataBean> currencies = new ArrayList<>();
    private int type;

    public static OptionFragment getInstance(int type) {
        OptionFragment ethMarketFragment = new OptionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        ethMarketFragment.setArguments(bundle);
        return ethMarketFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contract;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        type = getArguments().getInt("type");
//        new CommonPresenter(Injection.provideTasksRepository(getActivity().getApplicationContext()), this);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        initRvContent();
    }

    private void initRvContent() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(manager);
        adapter = new Homes_Option_Adapter( currencies);
//        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                collectClick(position);
//            }
//        });
        rvContent.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()){
                    case R.id.ll_option:
                        ((MarketOperateCallback) getActivity()).itemClick2(OptionFragment.this.adapter.getItem(position), type);
                    break;
                }

            }
        });
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ((MarketOperateCallback) getActivity()).itemClick(ConstractFragment.this.adapter.getItem(position), type);
//            }
//        });
        adapter.isFirstOnly(true);
        rvContent.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    public void dataLoaded(List<OptionBean.DataBean> base) {
        this.currencies.clear();
        this.currencies.addAll(base);
        if (adapter != null) adapter.notifyDataSetChanged();
    }


    public void tcpNotify() {
        if (getUserVisibleHint() && adapter != null) adapter.notifyDataSetChanged();
    }
}
