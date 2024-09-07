package com.bizzan.ui.my_ads;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.ui.releaseAd.ReleaseAdsActivity;
import com.bizzan.adapter.AdsAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.Ads;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulDpPxUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class AdsActivity extends BaseActivity implements AdsContract.View, AdsDialogFragment.OperateCallback {
    public static final int RETURN_ADS = 0;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ivReleseAd)
    ImageView ivReleseAd;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.rvAds)
    RecyclerView rvAds;
    @BindView(R.id.llEmpty)
    LinearLayout llEmpty;
    private AdsContract.Presenter presenter;
    private List<Ads> ads = new ArrayList<>();
    private AdsAdapter adapter;
    private AdsDialogFragment adsDialogFragment;
    private String username;
    private String avatar;
    @BindView(R.id.view_back)
    View view_back;

    public static void actionStart(Context context, String username,String avatar) {
        Intent intent = new Intent(context, AdsActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("avatar",avatar);
        context.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            displayLoadingPopup();
            finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_ads;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new AdsPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLoadingPopup();
                finish();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLoadingPopup();
                finish();
            }
        });
        ivReleseAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.popupwindow_fabi, null);
                // ReleaseAdsActivity.actionStart(getActivity(), "SELL", null);
                LinearLayout my_buy=popupView.findViewById(R.id.my_buy);
                my_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ReleaseAdsActivity.actionStart(AdsActivity.this, "BUY", null);
                    }
                });
                LinearLayout my_sell=popupView.findViewById(R.id.my_sell);
                my_sell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ReleaseAdsActivity.actionStart(AdsActivity.this, "SELL", null);
                    }
                });
                PopupWindow window = new PopupWindow(popupView, WonderfulDpPxUtils.dip2px(AdsActivity.this, 150), WonderfulDpPxUtils.dip2px(AdsActivity.this, 80));
                window.setOutsideTouchable(true);
                window.setTouchable(true);
                window.setFocusable(true);
                window.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shouye_3));
                window.update();
                window.getContentView().measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
                int i = window.getWidth();
                int width = ivReleseAd.getWidth();
                WonderfulLogUtils.logi("miao",width+"便宜度");
                WonderfulLogUtils.logi("miao",i+"便宜度");
                window.showAsDropDown(ivReleseAd,-(i-width), 0);
            }
        });
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        initRvAds();
    }

    private void initRvAds() {
        username = getIntent().getStringExtra("username");
        avatar = getIntent().getStringExtra("avatar");
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAds.setLayoutManager(manager);
        adapter = new AdsAdapter(R.layout.adapter_ads, ads,username,avatar,AdsActivity.this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showAdsDialog(position);
            }
        });
        adapter.bindToRecyclerView(rvAds);
        adapter.setEmptyView(R.layout.empty_rv_ad);
    }

    private void showAdsDialog(int position) {
        adsDialogFragment = AdsDialogFragment.getInstance(ads.get(position));
        adsDialogFragment.show(getSupportFragmentManager(), "ADs");
    }

    @Override
    protected void loadData() {
        if (!WonderfulStringUtils.isEmpty(getToken())) {
            presenter.allAds(getToken());
            hideToLoginView();
        } else showToLoginView();
    }

    @Override
    protected ViewGroup getEmptyView() {
        return llEmpty;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LoginActivity.RETURN_LOGIN) {
            if (resultCode == RESULT_OK) loadData();
        } else if (requestCode == RETURN_ADS) {
            if (resultCode == RESULT_OK) presenter.allAds(getToken());
        }
    }

    @Override
    public void setPresenter(AdsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void allAdsFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
        if (code==4000){
          WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.unknown_error));
        }
    }

    @Override
    public void allAdsSuccess(List<Ads> obj) {
        if (obj == null || obj.size() == 0) {
            this.ads.clear();
            adapter.notifyDataSetChanged();
            return;
        }
        this.ads.clear();
        this.ads.addAll(obj);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void releaseSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        presenter.allAds(getToken());
    }

    @Override
    public void releaseFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void deleteSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        presenter.allAds(getToken());
    }

    @Override
    public void deleteFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void offShelfFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void offShelfSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        presenter.allAds(getToken());
    }

    @Override
    public void edit(Ads ads) {
        Intent intent = new Intent(this, ReleaseAdsActivity.class);
        intent.putExtra("type", ads.getAdvertiseType() == 0 ? "BUY" : "SELL");
        intent.putExtra("ads", ads);
        startActivityForResult(intent, RETURN_ADS);
    }

    @Override
    public void releaseAgain(Ads ads) {
        presenter.release(getToken(), ads.getId());
    }

    @Override
    public void offShelf(Ads ads) {
        presenter.offShelf(getToken(), ads.getId());
    }

    @Override
    public void delete(Ads ads) {
        presenter.delete(getToken(), ads.getId());
    }
}
