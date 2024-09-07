package com.bizzan.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.adapter.PagerAdapter;
import com.bizzan.app.MyApplication;
import com.bizzan.app.UrlFactory;
import com.bizzan.base.BaseFragment;
import com.bizzan.base.BaseNestingTransFragment;
import com.bizzan.entity.CoinInfo;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.ui.my_order.MyOrderActivity;
import com.bizzan.ui.releaseAd.ReleaseAdsActivity;
import com.bizzan.ui.seller.SellerApplyActivity;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulDpPxUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import com.gyf.barlibrary.ImmersionBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/1/29.
 */

public class FourFragment extends BaseNestingTransFragment implements MainContract.FourView {
    public static final String TAG = FourFragment.class.getSimpleName();

    @BindView(R.id.ibBack)
    View ivBack;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tvBuy)
    TextView tvBuy;
    @BindView(R.id.tvSell)
    TextView tvSell;
    Unbinder unbinder;
    @BindView(R.id.ivGoOrder)
    ImageView ivGoOrder;
    @BindView(R.id.ivReleseAd)
    ImageView ivReleseAd;
    @BindView(R.id.iv_seller_apply)
    ImageView iv_seller_apply;

    private MainContract.FourPresenter presenter;
    private List<CoinInfo> coinInfos = new ArrayList<>();
    private PagerAdapter adapter;
    private ArrayList<String> tabs = new ArrayList<>();
    private List<BaseFragment> fragments = new ArrayList<>();
    private List<CoinInfo> saveCoinInfos;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_four;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (getActivity() instanceof MainActivity) {
            ivBack.setVisibility(View.INVISIBLE);
        } else {
            ivBack.setVisibility(View.VISIBLE);
        }
        ivBack.setOnClickListener(view -> getActivity().finish());
        tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleBuyClick();
            }
        });
        tvSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleSellClick();
            }
        });
        ivGoOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrderActivity.actionStart(getActivity(), 0);
            }
        });
        iv_seller_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    if (MyApplication.realVerified == 1) {
                        WonderfulOkhttpUtils.get().url(UrlFactory.getShangjia())
                                .addHeader("x-auth-token", SharedPreferenceInstance.getInstance().getTOKEN())
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int code = jsonObject.optInt("code");
                                    if (code == 0) {
                                        JSONObject data = jsonObject.optJSONObject("data");
                                        int certifiedBusinessStatus = data.optInt("certifiedBusinessStatus");
                                        String reason = data.getString("detail");
                                        SellerApplyActivity.actionStart(getActivity(), certifiedBusinessStatus + "", reason);
                                    } else {
                                        WonderfulToastUtils.showToast(jsonObject.getString("message"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else {
                        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.realname) + "");
                    }
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }

            }
        });
        ivReleseAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    if (MyApplication.realVerified == 1) {
                        WonderfulOkhttpUtils.get().url(UrlFactory.getShangjia())
                                .addHeader("x-auth-token", SharedPreferenceInstance.getInstance().getTOKEN())
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {
                                Log.i("miao", "商家认证" + response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int code = jsonObject.optInt("code");
                                    if (code == 0) {
                                        JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                                        int certifiedBusinessStatus = jsonObject1.optInt("certifiedBusinessStatus");
                                        if (certifiedBusinessStatus == 2) {
                                            showBottomDialog();
                                        } else {
                                            JSONObject data = jsonObject.optJSONObject("data");
                                            String reason = data.getString("detail");
                                            SellerApplyActivity.actionStart(getActivity(), certifiedBusinessStatus + "", reason);
                                        }
                                    } else {
                                        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.unknown_error) + "");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else {
                        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.realname) + "");
                    }
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }

            }
        });
        if (savedInstanceState == null) presenter.all();
        else {
            saveCoinInfos = (List<CoinInfo>) savedInstanceState.getSerializable("coinInfos");
            recoveryFragments();
        }
    }


    private void showBottomDialog() {
//        if (buyOrSellDialogFragment == null)
//            buyOrSellDialogFragment = new BuyOrSellDialogFragment();
//        buyOrSellDialogFragment.show(getActivity().getSupportFragmentManager(), "bottom");
        View popupView = getmActivity().getLayoutInflater().inflate(R.layout.popupwindow_fabi, null);
        // ReleaseAdsActivity.actionStart(getActivity(), "SELL", null);
        LinearLayout my_buy = popupView.findViewById(R.id.my_buy);
        my_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReleaseAdsActivity.actionStart(getActivity(), "BUY", null);
            }
        });
        LinearLayout my_sell = popupView.findViewById(R.id.my_sell);
        my_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReleaseAdsActivity.actionStart(getActivity(), "SELL", null);
            }
        });
        PopupWindow window = new PopupWindow(popupView, WonderfulDpPxUtils.dip2px(getmActivity(), 120), WonderfulDpPxUtils.dip2px(getmActivity(), 80));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.setFocusable(true);
        window.setBackgroundDrawable(getmActivity().getResources().getDrawable(R.drawable.my_bg));
        window.update();
        window.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int i = window.getWidth();
        int width = ivReleseAd.getWidth();
        window.showAsDropDown(ivReleseAd, -(i - width), 0);
    }

    private void titleSellClick() {
        tvBuy.setEnabled(true);
        tvSell.setEnabled(false);
        for (BaseFragment fragment : fragments) ((C2CFragment) fragment).setAdvertiseType("BUY");
        if (fragments.size() != 0) ((C2CFragment) fragments.get(vp.getCurrentItem())).loadData();
    }

    private void titleBuyClick() {
        tvBuy.setEnabled(false);
        tvSell.setEnabled(true);
        for (BaseFragment fragment : fragments) ((C2CFragment) fragment).setAdvertiseType("SELL");
        if (fragments.size() != 0) ((C2CFragment) fragments.get(vp.getCurrentItem())).loadData();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

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
    public void setPresenter(MainContract.FourPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void allSuccess(List<CoinInfo> obj) {
        if (tabLayout == null) {
            return;
        }
        if (obj == null || obj.size() == 0) return;
        coinInfos.clear();
        coinInfos.addAll(obj);
        tabs.clear();
        fragments.clear();
        for (CoinInfo coinInfo : coinInfos) {
            tabs.add(coinInfo.getUnit());
            fragments.add(C2CFragment.getInstance(coinInfo));
        }
        if (fragments.size() > 4) tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        else tabLayout.setTabMode(TabLayout.MODE_FIXED);
        if (adapter == null) {
            vp.setAdapter(adapter = new PagerAdapter(getChildFragmentManager(), fragments, tabs));
            tabLayout.setupWithViewPager(vp);
            vp.setOffscreenPageLimit(fragments.size() - 1);
        } else adapter.notifyDataSetChanged();
        isNeedLoad = false;
    }

    @Override
    public void allFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
    }

    @Override
    protected String getmTag() {
        return TAG;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("coinInfos", (Serializable) coinInfos);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void addFragments() {
        //do  nothing
    }

    @Override
    protected void recoveryFragments() {
        if (saveCoinInfos == null || saveCoinInfos.size() == 0) return;
        coinInfos.clear();
        coinInfos.addAll(saveCoinInfos);
        tabs.clear();
        fragments.clear();
        for (int i = 0; i < coinInfos.size(); i++) {
            tabs.add(coinInfos.get(i).getUnit());
            fragments.add((C2CFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(vp.getId(), i)));
        }
        if (fragments.size() > 4) tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        else tabLayout.setTabMode(TabLayout.MODE_FIXED);
        if (adapter == null) {
            vp.setAdapter(adapter = new PagerAdapter(getChildFragmentManager(), fragments, tabs));
            tabLayout.setupWithViewPager(vp);
            vp.setOffscreenPageLimit(fragments.size() - 1);
        } else adapter.notifyDataSetChanged();
        isNeedLoad = false;
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
