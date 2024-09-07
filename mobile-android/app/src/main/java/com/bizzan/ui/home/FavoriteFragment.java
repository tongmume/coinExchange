package com.bizzan.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bizzan.R;
import com.bizzan.ui.home.presenter.CommonPresenter;
import com.bizzan.ui.home.presenter.ICommonView;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.adapter.HomesAdapter;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.MyApplication;
import com.bizzan.entity.Currency;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.bizzan.app.Injection;

/**
 * Created by Administrator on 2018/1/29.
 */

public class FavoriteFragment extends MarketBaseFragment implements ICommonView {

    @BindView(R.id.rvContent)
    RecyclerView rvContent;
    @BindView(R.id.textNoRecords)
    TextView textNoRecords;
    private HomesAdapter adapter;
    private List<Currency> currencies = new ArrayList<>();
    private List<Currency> favorites = new ArrayList<>();
    private CommonPresenter commonPresenter;
    private int type;

    public static FavoriteFragment getInstance(int type) {
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        favoriteFragment.setArguments(bundle);
        return favoriteFragment;
    }
    public void isChange(boolean isLoad){
        if(adapter!=null){
            adapter.setLoad(isLoad);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_favorite_market;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        type = getArguments().getInt("type");
        new CommonPresenter(Injection.provideTasksRepository(getActivity().getApplicationContext()), this);
    }

    @Override
    protected void obtainData() {
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    protected void fillWidget() {
        initRvContent();
    }

    private void initRvContent() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(manager);
        adapter = new HomesAdapter( favorites, 2);
//        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                collectClick(position);
//            }
//        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WonderfulLogUtils.logi("jiejie","  FavoriteFragment  symble    "+FavoriteFragment.this.adapter.getItem(position).getSymbol());
                ((MarketOperateCallback) getActivity()).itemClick(FavoriteFragment.this.adapter.getItem(position), type);
            }
        });
        adapter.isFirstOnly(true);
        rvContent.setAdapter(adapter);
    }

    private void collectClick(int position) {
        if (!MyApplication.getApp().isLogin()) {
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.text_xian_login)+"！");
            return;
        }
        String symbol = favorites.get(position).getSymbol();
        if (favorites.get(position).isCollect()) commonPresenter.delete(getmActivity().getToken(), symbol, position);
        else commonPresenter.add(getmActivity().getToken(), symbol, position);
    }

    @Override
    protected void loadData() {
        notifyData();
    }

    private void notifyData() {
        if (adapter == null) return;
        this.favorites.clear();
        for (Currency currency : currencies) {
            if (currency.isCollect()) this.favorites.add(currency);
        }
        if(this.favorites == null || this.favorites.size() == 0){
            textNoRecords.setVisibility(View.VISIBLE);
            rvContent.setVisibility(View.GONE);
        }else{
            textNoRecords.setVisibility(View.GONE);
            rvContent.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    public void dataLoaded(List<Currency> currencies) {
        this.currencies.clear();
        this.currencies.addAll(currencies);
        this.favorites.clear();
        for (Currency currency : currencies) {
            if (currency.isCollect()) this.favorites.add(currency);
        }
//        Log.d("jiejie","234---" + favorites.size());
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(CommonPresenter presenter) {
        this.commonPresenter = presenter;
    }

    @Override
    public void deleteFail(Integer code, String toastMessage) {
        if (code == GlobalConstant.TOKEN_DISABLE1) LoginActivity.actionStart(getActivity());
        else WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
    }

    @Override
    public void deleteSuccess(String obj, int position) {
        this.favorites.get(position).setCollect(false);
        notifyData();
    }

    @Override
    public void addFail(Integer code, String toastMessage) {
        if (code == GlobalConstant.TOKEN_DISABLE1) LoginActivity.actionStart(getActivity());
        else WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
    }

    @Override
    public void addSuccess(String obj, int position) {
        this.favorites.get(position).setCollect(true);
        notifyData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getActivity().getSupportFragmentManager().putFragment(outState, "USDT", this);
    }

    public void tcpNotify() {
        if (getUserVisibleHint() && adapter != null) adapter.notifyDataSetChanged();
    }
}
