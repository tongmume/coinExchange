package com.bizzan.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bizzan.R;
import com.bizzan.adapter.PagerAdapter;
import com.bizzan.base.BaseNestingTransFragment;
import com.bizzan.customview.intercept.WonderfulViewPager;
import com.bizzan.entity.Currency;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/29.
 */

public class TwoFragment extends BaseNestingTransFragment implements MainContract.TwoView {
    public static final String TAG = TwoFragment.class.getSimpleName();
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ibSearch)
    ImageButton ibSearch;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vpPager)
    WonderfulViewPager vpPager;
    @BindArray(R.array.home_two_top_tab)
    String[] tabs;

    private List<Currency> baseUsdt = new ArrayList<>();
    private List<Currency> baseBtc = new ArrayList<>();
    private List<Currency> baseEth = new ArrayList<>();
    private List<Currency> favoriteCoin = new ArrayList<>();

    private USDTMarketFragment usdtMarketFragment;
    private BTCMarketFragment btcMarketFragment;
    private ETHMarketFragment ethMarketFragment;
    private FavoriteFragment favoriteFragment;

    private MainContract.TwoPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (savedInstanceState != null) recoveryFragments();
        vpPager.setOffscreenPageLimit(3);
        List<String> tabs = Arrays.asList(this.tabs);
        vpPager.setAdapter(new PagerAdapter(getChildFragmentManager(), fragments, tabs));
        tab.setupWithViewPager(vpPager, true);
        tab.getTabAt(1).select();
        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getmActivity(), SearchCurrencyActivity.class));
            }
        });
    }

    private boolean isLoad = false;

    private void changeLoad(boolean isLoads) {
        isLoad = !isLoads;
        if (usdtMarketFragment != null) usdtMarketFragment.isChange(isLoad);
        if (btcMarketFragment != null) btcMarketFragment.isChange(isLoad);
        if (favoriteFragment != null) favoriteFragment.isChange(isLoad);
    }

    @Override
    protected void addFragments() {
        int type = MarketBaseFragment.MarketOperateCallback.TYPE_TO_KLINE;
        if (favoriteFragment == null)
            fragments.add(favoriteFragment = FavoriteFragment.getInstance(type));
        if (usdtMarketFragment == null)
            fragments.add(usdtMarketFragment = USDTMarketFragment.getInstance(type));
        if (btcMarketFragment == null)
            fragments.add(btcMarketFragment = BTCMarketFragment.getInstance(type));
        if (ethMarketFragment == null)
            fragments.add(ethMarketFragment = ETHMarketFragment.getInstance(type));
    }

    @Override
    protected void recoveryFragments() {
        favoriteFragment = (FavoriteFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(vpPager.getId(), 0));
        usdtMarketFragment = (USDTMarketFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(vpPager.getId(), 1));
        btcMarketFragment = (BTCMarketFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(vpPager.getId(), 2));
        ethMarketFragment = (ETHMarketFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(vpPager.getId(), 3));

        fragments.add(favoriteFragment);
        fragments.add(usdtMarketFragment);
        fragments.add(btcMarketFragment);
        fragments.add(ethMarketFragment);

    }

    @Override
    protected void obtainData() {
    }

    @Override
    protected void fillWidget() {
    }

    @Override
    protected void loadData() {
        usdtMarketFragment.dataLoaded(baseUsdt);
        btcMarketFragment.dataLoaded(baseBtc);
        ethMarketFragment.dataLoaded(baseEth);
        favoriteFragment.dataLoaded(favoriteCoin);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(getActivity(), llTitle);
            isSetTitle = true;
        }
    }

    @Override
    public void setPresenter(MainContract.TwoPresenter presenter) {
        this.presenter = presenter;
    }

    public void dataLoaded(final List<Currency> baseUsdt, final List<Currency> baseBtc, final List<Currency> baseEth, final List<Currency> favoriteCoin) {
        this.baseUsdt.clear();
        this.baseUsdt.addAll(baseUsdt);

        this.baseBtc.clear();
        this.baseBtc.addAll(baseBtc);

        this.baseEth.clear();
        this.baseEth.addAll(baseEth);

        this.favoriteCoin.clear();
        this.favoriteCoin.addAll(favoriteCoin);

        try {
//            Log.d("jiejie","-------" + this.favoriteCoin.size());
            favoriteFragment.dataLoaded(this.favoriteCoin);
        } catch (Exception e) {
        }
        try {
            usdtMarketFragment.dataLoaded(baseUsdt);
            btcMarketFragment.dataLoaded(baseBtc);
            ethMarketFragment.dataLoaded(baseEth);

        } catch (NullPointerException ex) {
            //do nothing
        }
    }

    @Override
    protected String getmTag() {
        return TAG;
    }

    public void tcpNotify() {
        if (usdtMarketFragment == null) return;
        usdtMarketFragment.tcpNotify();
        btcMarketFragment.tcpNotify();
        ethMarketFragment.tcpNotify();
        favoriteFragment.tcpNotify();
    }
}
