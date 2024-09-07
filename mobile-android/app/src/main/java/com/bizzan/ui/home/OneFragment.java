package com.bizzan.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.adapter.BannerImageLoader;
import com.bizzan.adapter.HomeAdapter;
import com.bizzan.adapter.HomeOneAdapter;
import com.bizzan.adapter.shareApkImageAdapter;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.Injection;
import com.bizzan.app.MyApplication;
import com.bizzan.app.UrlFactory;
import com.bizzan.base.BaseTransFragment;
import com.bizzan.config.UrlConfig;
import com.bizzan.customview.intercept.WonderfulScrollView;
import com.bizzan.entity.BannerEntity;
import com.bizzan.entity.Coin;
import com.bizzan.entity.Currency;
import com.bizzan.entity.LunBoBean;
import com.bizzan.entity.Message;
import com.bizzan.entity.User;
import com.bizzan.entity.WalletConstract;
import com.bizzan.ui.aboutus.AddUrlActivity;
import com.bizzan.ui.activity.ActivityList;
import com.bizzan.ui.chatlist.ChatListActivity;
import com.bizzan.ui.common.ActivityShowFragment;
import com.bizzan.ui.home.presenter.CommonPresenter;
import com.bizzan.ui.home.presenter.FourPresenter;
import com.bizzan.ui.home.presenter.ICommonView;
import com.bizzan.ui.kline.KlineActivity;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.ui.message_detail.MessageDetailActivity;
import com.bizzan.ui.myinfo.MyInfoActivity;
import com.bizzan.ui.setting.GongGaoActivity;
import com.bizzan.ui.setting.HelpActivity;
import com.bizzan.ui.wallet.WalletActivity;
import com.bizzan.ui.wallet.WalletListActivity;
import com.bizzan.utils.SavePhoto;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulBitmapUtils;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulCommonUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulMathUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import com.bizzan.widget.FivePopInvite;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/1/7.
 */

public class OneFragment extends BaseTransFragment implements com.bizzan.ui.home.MainContract.OneView, ICommonView {
    public static final String TAG = OneFragment.class.getSimpleName();
    @BindView(R.id.ibMessage)
    ImageButton ibMessage;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.line_zichan)
    LinearLayout line_zichan;
    @BindView(R.id.line_ctc)
    LinearLayout line_ctc;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.main_linear)
    LinearLayout main_linear;
    @BindView(R.id.line_help)
    LinearLayout line_help;
    @BindView(R.id.text_gengduo)
    TextView text_gengduo;
    @BindView(R.id.line_gonggao)
    LinearLayout line_gonggao;

    @BindView(R.id.text_login)
    TextView text_login;

    @BindView(R.id.text_loginbtn)
    TextView text_loginbtn;

    @BindView(R.id.line_lab)
    View line_lab;

    @BindView(R.id.ibService)
    View ivService;

    @BindView(R.id.text_totalassets)
    TextView text_totalassets;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView; // 涨幅榜
    private HomeAdapter mHomeAdapter; // 首页适配器
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.scrollView)
    WonderfulScrollView scrollView;
    @BindView(R.id.ivchatTip)
    ImageView ivchatTip;
    Unbinder unbinder;
    private List<String> imageUrls = new ArrayList<>();
    private List<LunBoBean> banners = new ArrayList<>();
    private List<Integer> localImageUrls = new ArrayList<Integer>() {{
        add(R.mipmap.icon_banner);
    }};
    private List<Currency> currencies = new ArrayList<>();
    private List<Currency> currenciesTwo = new ArrayList<>();
    private HomeOneAdapter adapter;
    private MainContract.OnePresenter presenter;

    private CommonPresenter commonPresenter;
    private String sysAdvertiseLocation = "1";

    private List<Coin> coins = new ArrayList<>();
    private List<WalletConstract> contract = new ArrayList<>();
    double sumUsd = 0;
    double sumCny = 0;
    double sumUsd_c = 0;
    double sumCny_c = 0;
    // 加载框
    private PopupWindow loadingPopup;
    private FivePopInvite fivePopInvite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @OnClick({R.id.btnCharge, R.id.btnInvite, R.id.btnMining, R.id.btnKefu, R.id.btnContract})
    public void onBtnClick(View view) {
        if (!MyApplication.getApp().isLogin()) {
            startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
            return;
        }
        switch (view.getId()) {
            case R.id.btnInvite:
                //邀请
                User user = MyApplication.getApp().getCurrentUser();
                ArrayList<Integer> imagebitmap = new ArrayList<>();
                imagebitmap.add(R.drawable.invite1);
                imagebitmap.add(R.drawable.invite2);
                imagebitmap.add(R.drawable.invite3);
                shareApkImageAdapter adapter = new shareApkImageAdapter(R.layout.item_pop_five_invite, imagebitmap, user.getPromotionCode(), user.getPromotionPrefix());
                fivePopInvite = new FivePopInvite(getActivity(), adapter, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()) {
                            case R.id.tv_copy:
                                User user = MyApplication.getApp().getCurrentUser();
                                String myInviteLink = user.getPromotionPrefix() + user.getPromotionCode();
                                WonderfulCommonUtils.copyText(getActivity(), myInviteLink);
                                WonderfulToastUtils.showToast(R.string.copy_success);
                                if (fivePopInvite.isShowing()) {
                                    fivePopInvite.dismiss();
                                }
                                break;
                            case R.id.tv_save:
//                WonderfulToastUtils.showToast(R.string.savelocally);
                                if (fivePopInvite.index == 9) {
                                    WonderfulToastUtils.showToast(R.string.posters);
                                    return;
                                }
                                saveImage();
                                if (fivePopInvite.isShowing()) {
                                    fivePopInvite.dismiss();
                                }
                                break;
                            case R.id.tv_cancel:
                                if (fivePopInvite.isShowing()) {
                                    fivePopInvite.dismiss();
                                }
                                break;
                        }
                    }
                });
                fivePopInvite.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.btnCharge:
                //冲币
                WalletListActivity.actionStart(getActivity(), coins, 1);
                break;
            case R.id.btnKefu://客服
                ivService.performClick();
                break;
            case R.id.btnMining://挖矿
                //创新实验室
                startActivity(new Intent(getActivity(), ActivityList.class));
                break;
            case R.id.btnContract://合约
                ((MainActivity) getmActivity()).changeToConstractPage();
                break;
        }
    }

    private void saveImage() {
        try {
            //创建savephoto类保存图片
            SavePhoto savePhoto = new SavePhoto(getActivity());
            savePhoto.saveBitmap(selectimage(), "share.png");
            WonderfulToastUtils.showToast(R.string.savelocally);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Bitmap selectimage() {
        int index = fivePopInvite.index;
        User user = MyApplication.getApp().getCurrentUser();
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.item_pop_five_invite, null);
        RelativeLayout rl_img = inflate.findViewById(R.id.rl_img);
        ImageView img = inflate.findViewById(R.id.img);
        ImageView iv_code_address = inflate.findViewById(R.id.iv_code_address);
        iv_code_address.setImageBitmap(WonderfulBitmapUtils.createQRCodeBitmap(user.getPromotionPrefix() + user.getPromotionCode(), 150, 150, "UTF-8", "H", "1", Color.BLACK, Color.WHITE));
        switch (index) {
            case 0:
                img.setImageResource(R.drawable.invite1);
                break;
            case 1:
                img.setImageResource(R.drawable.invite2);
                break;
            case 2:
                img.setImageResource(R.drawable.invite3);
                break;
            default:
                WonderfulToastUtils.showToast(R.string.posters);
                break;
        }
        return WonderfulBitmapUtils.generatBitmapFromView(rl_img);
    }

    /**
     * 初始化加载dialog
     */
    private void initLoadingPopup() {
        View loadingView = getLayoutInflater().inflate(R.layout.pop_loading, null);
        loadingPopup = new PopupWindow(loadingView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        loadingPopup.setFocusable(true);
        loadingPopup.setClippingEnabled(false);
        loadingPopup.setBackgroundDrawable(new ColorDrawable());
    }

    /**
     * 显示加载框
     */
    @Override
    public void displayLoadingPopup() {
        loadingPopup.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    /**
     * 隐藏加载框
     */
    @Override
    public void hideLoadingPopup() {
        if (loadingPopup != null) {
            loadingPopup.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
        loadData();
    }

    private void loginingViewText() {
        try {
            text_loginbtn.setVisibility(View.GONE);
            text_totalassets.setVisibility(View.VISIBLE);
            text_totalassets.setText("$ " + WonderfulMathUtils.getRundNumber(sumUsd + sumUsd_c, 4, null));
            text_login.setText(getActivity().getResources().getText(R.string.login_assets));
        } catch (Exception e) {

        }
    }

    private void notLoginViewText() {
        try {
            text_loginbtn.setVisibility(View.VISIBLE);
            text_totalassets.setVisibility(View.GONE);
            text_login.setText(getActivity().getResources().getText(R.string.login_view));
        } catch (Exception e) {

        }
    }

    @Override
    protected String getmTag() {
        return TAG;
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopAutoPlay();
    }

    private void dialogShow2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.custom_dialog);
        final Dialog dialog = builder.create();
        dialog.show();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_lock, null);
        dialog.getWindow().setContentView(v);
        TextView content = v.findViewById(R.id.text_quxiao);
        final CheckBox checkbox = v.findViewById(R.id.checkbox);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()) {
                    SharedPreferenceInstance.getInstance().saveTishi(checkbox.isChecked());
                }
                dialog.dismiss();
            }
        });
        TextView text_queding = v.findViewById(R.id.text_queding);
        text_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    if (checkbox.isChecked()) {
                        SharedPreferenceInstance.getInstance().saveTishi(checkbox.isChecked());
                    }
                    dialog.dismiss();
                    MyInfoActivity.actionStart(getActivity());
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }
            }
        });

    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        initLoadingPopup();
        getMessage();
        new CommonPresenter(Injection.provideTasksRepository(getActivity().getApplicationContext()), this);
        line_zichan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    WalletActivity.actionStart(getActivity());
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }
            }
        });
        //客服
        ivService.setOnClickListener(view -> {
            Uri uri = Uri.parse(UrlConfig.URL_SERVICE);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                AddUrlActivity.show(getActivity(), "客服", UrlConfig.URL_SERVICE);
            }
        });
        line_lab.setOnClickListener(view -> {
            //创新实验室
            startActivity(new Intent(getActivity(), ActivityList.class));
        });
        line_ctc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CTCActivity.actionStart(getmActivity());
                ActivityShowFragment.showFragment(getActivity(), new ActivityShowFragment.CallBack() {
                    @Override
                    public Fragment getFragment() {
                        FourFragment fourFragment = new FourFragment();
                        new FourPresenter(Injection.provideTasksRepository(getContext()), fourFragment);
                        return fourFragment;
                    }
                });
            }
        });
        line_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpActivity.actionStart(getmActivity());
            }
        });
        text_gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GongGaoActivity.actionStart(getmActivity());
            }
        });
        line_gonggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GongGaoActivity.actionStart(getmActivity());
            }
        });
        ibMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivchatTip.setVisibility(View.INVISIBLE);
                ChatListActivity.actionStart(getActivity());
            }
        });
        if (MyApplication.getApp().isLogin()) {
            if (WonderfulStringUtils.isEmpty(SharedPreferenceInstance.getInstance().getLockPwd())) {
                if (SharedPreferenceInstance.getInstance().getTishi()) {
                    return;
                }
                dialogShow2();
            }
        }
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.grgray, R.color.grgray, R.color.grgray, R.color.grgray);
        refreshLayout.setRefreshHeader(header);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData();
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected void obtainData() {
    }

    @Override
    protected void fillWidget() {
        initRvContent();

    }

    class MyAdapter extends PagerAdapter {
        private List<Currency> lists = new ArrayList<>();

        private int colorA = Color.parseColor("#1F2833");
        private int colorC = Color.parseColor("#1A212A");

        @Override
        public int getCount() {
            int size = currenciesTwo.size();
            if (size == 0) {
                return 0;
            }
            int i = size % 3;
            int a = size / 3;
            if (i > 0) {
                a = a + 1;
            }
            return a;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_home_viewpage, null, false);
            LinearLayout line = inflate.findViewById(R.id.line);
            //第一组
            LinearLayout line_one = inflate.findViewById(R.id.line_one);
            TextView tvCurrencyName = inflate.findViewById(R.id.tvCurrencyName);
            ImageView ivCollect = inflate.findViewById(R.id.ivCollect);
            TextView tvClose = inflate.findViewById(R.id.tvClose);
            String oldClose = tvClose.getText().toString();
            TextView tvAddPercent = inflate.findViewById(R.id.tvAddPercent);
            TextView tvVol = inflate.findViewById(R.id.tvVol);

            //第二组
            LinearLayout line_two = inflate.findViewById(R.id.line_two);
            TextView tvCurrencyName1 = inflate.findViewById(R.id.tvCurrencyName1);
            ImageView ivCollect1 = inflate.findViewById(R.id.ivCollect1);
            TextView tvClose1 = inflate.findViewById(R.id.tvClose1);
            String oldClose1 = tvClose1.getText().toString();
            TextView tvAddPercent1 = inflate.findViewById(R.id.tvAddPercent1);
            TextView tvVol1 = inflate.findViewById(R.id.tvVol1);
            //第三组
            LinearLayout line_three = inflate.findViewById(R.id.line_three);
            TextView tvCurrencyName2 = inflate.findViewById(R.id.tvCurrencyName2);
            ImageView ivCollect2 = inflate.findViewById(R.id.ivCollect2);
            TextView tvClose2 = inflate.findViewById(R.id.tvClose2);
            String oldClose2 = tvClose2.getText().toString();
            TextView tvAddPercent2 = inflate.findViewById(R.id.tvAddPercent2);
            TextView tvVol2 = inflate.findViewById(R.id.tvVol2);
            int star = position * 3;
            int end = (position + 1) * 3;
            lists.clear();
            for (int i = 0; i <= currenciesTwo.size(); i++) {
                if (i >= star && i < end && i < currenciesTwo.size()) {
                    lists.add(currenciesTwo.get(i));
                }
            }
            for (int i = 0; i < lists.size(); i++) {
                if (i == 0) {
                    final Currency currency = lists.get(i);
                    line_one.setVisibility(View.VISIBLE);
                    ivCollect.setImageResource(currency.isCollect() ? R.mipmap.icon_collect_yes : R.mipmap.icon_collect_no);
                    tvCurrencyName.setText(currency.getSymbol());
                    String newClose = WonderfulMathUtils.getRundNumber(currency.getClose(), 2, null);

//                    if (!newClose.equals(oldClose)) {
//                        line.animate().setDuration(300).start();
//                        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(line, "backgroundColor", colorA, colorC);
//                        objectAnimator.setDuration(300);
//                        objectAnimator.setEvaluator(new ArgbEvaluator());
//                        objectAnimator.start();
//                    }

                    tvClose.setText(newClose);
                    tvAddPercent.setText((currency.getChg() >= 0 ? "+" : "") + WonderfulMathUtils.getRundNumber(currency.getChg() * 100, 2, "########0.") + "%");
                    tvVol.setText("≈" + WonderfulMathUtils.getRundNumber(currency.getClose() * currency.getBaseUsdRate() * MainActivity.rate, 2, null) + "CNY");
                    tvClose.setTextColor(currency.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
                    tvAddPercent.setTextColor(currency.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
                    line_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            KlineActivity.actionStart(getActivity(), currency.getSymbol(), "1");
                        }
                    });
                }
                if (i == 1) {
                    final Currency currency = lists.get(i);
                    line_two.setVisibility(View.VISIBLE);
                    ivCollect1.setImageResource(currency.isCollect() ? R.mipmap.icon_collect_yes : R.mipmap.icon_collect_no);
                    tvCurrencyName1.setText(currency.getSymbol());
                    String newClose = WonderfulMathUtils.getRundNumber(currency.getClose(), 2, null);

//                    if (!newClose.equals(oldClose1)) {
//                        line.animate().setDuration(300).start();
//                        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(line, "backgroundColor", colorA, colorC);
//                        objectAnimator.setDuration(300);
//                        objectAnimator.setEvaluator(new ArgbEvaluator());
//                        objectAnimator.start();
//                    }

                    tvClose1.setText(newClose);
                    tvAddPercent1.setText((currency.getChg() >= 0 ? "+" : "") + WonderfulMathUtils.getRundNumber(currency.getChg() * 100, 2, "########0.") + "%");
                    tvVol1.setText("≈" + WonderfulMathUtils.getRundNumber(currency.getClose() * currency.getBaseUsdRate() * MainActivity.rate, 2, null) + "CNY");
                    tvClose1.setTextColor(currency.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
                    tvAddPercent1.setTextColor(currency.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
                    line_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            KlineActivity.actionStart(getActivity(), currency.getSymbol(), "1");
                        }
                    });
                }
                if (i == 2) {
                    final Currency currency = lists.get(i);
                    line_three.setVisibility(View.VISIBLE);
                    ivCollect2.setImageResource(currency.isCollect() ? R.mipmap.icon_collect_yes : R.mipmap.icon_collect_no);
                    tvCurrencyName2.setText(currency.getSymbol());
                    String newClose = WonderfulMathUtils.getRundNumber(currency.getClose(), 2, null);

//                    if (!newClose.equals(oldClose2)) {
//                        line.animate().setDuration(300).start();
//                        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(line, "backgroundColor", colorA, colorC);
//                        objectAnimator.setDuration(300);
//                        objectAnimator.setEvaluator(new ArgbEvaluator());
//                        objectAnimator.start();
//                    }

                    tvClose2.setText(newClose);
                    tvAddPercent2.setText((currency.getChg() >= 0 ? "+" : "") + WonderfulMathUtils.getRundNumber(currency.getChg() * 100, 2, "########0.") + "%");
                    tvVol2.setText("≈" + WonderfulMathUtils.getRundNumber(currency.getClose() * currency.getBaseUsdRate() * MainActivity.rate, 2, null) + "CNY");
                    tvClose2.setTextColor(currency.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
                    tvAddPercent2.setTextColor(currency.getChg() >= 0 ? ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen) : ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
                    line_three.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            KlineActivity.actionStart(getActivity(), currency.getSymbol(), "1");
                        }
                    });
                }

            }
            container.addView(inflate);
            return inflate;
        }

    }

    private void initRvContent() {
        // 涨幅榜的适配器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        System.out.println("currencies" + currencies.toString());
        mHomeAdapter = new HomeAdapter(currencies);
        mHomeAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mHomeAdapter.isFirstOnly(true);
        mHomeAdapter.setLoad(true);
        mRecyclerView.setAdapter(mHomeAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KlineActivity.actionStart(getActivity(), OneFragment.this.mHomeAdapter.getData().get(position).getSymbol(), "1");
            }
        });
    }

    @Override
    protected void loadData() {
        notifyData();
        if (imageUrls == null || imageUrls.size() == 0) {
            presenter.banners(sysAdvertiseLocation);
        }

        if (MyApplication.getApp().isLogin()) {
            presenter.myWallet(getmActivity().getToken());
            presenter.myWallet_Constract(getmActivity().getToken());
        } else {
            notLoginViewText();
        }
    }

    @Override
    public void myWalletSuccess(List<Coin> obj) {
        if (obj == null) return;
        this.coins.clear();
        this.coins.addAll(obj);
        calcuTotal();
    }

    @Override
    public void myWalletSuccess_Constract(List<WalletConstract> obj) {
        if (obj == null) return;
        this.contract.clear();
        this.contract.addAll(obj);
        calcuTotal();
    }

    @Override
    public void myWalletFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    private void calcuTotal() {
        if (coins.size() != 0 && contract.size() != 0) {
            sumUsd = 0;
            sumCny = 0;
            sumUsd_c = 0;
            sumCny_c = 0;
            for (Coin coin : coins) {
                // 总额计算包含可用余额与冻结余额的总和
                sumUsd += ((coin.getBalance() + coin.getFrozenBalance()) * coin.getCoin().getUsdRate());
                sumCny += ((coin.getBalance() + coin.getFrozenBalance()) * coin.getCoin().getCnyRate());
            }
            for (WalletConstract constract : contract) {
                // 总额计算包含可用余额与冻结余额的总和
                sumUsd_c += (constract.getUsdtBalance() + constract.getUsdtFrozenBalance() + constract.getUsdtBuyPrincipalAmount()
                        + constract.getUsdtSellPrincipalAmount() + constract.getUsdtTotalProfitAndLoss());

                sumCny_c += (constract.getUsdtBalance() + constract.getUsdtFrozenBalance() + constract.getUsdtBuyPrincipalAmount()
                        + constract.getUsdtSellPrincipalAmount() + constract.getUsdtTotalProfitAndLoss()) * constract.getContractCoin().getUsdtRate();
            }
            loginingViewText();
        }
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
    public void setPresenter(MainContract.OnePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setPresenter(CommonPresenter presenter) {
        this.commonPresenter = presenter;
    }

    private MyAdapter adapter2;

    public void dataLoaded(List<Currency> currencies, List<Currency> tow) {
        this.currencies.clear();
        this.currencies.addAll(currencies);
        this.currenciesTwo.clear();
        this.currenciesTwo.addAll(tow);
        adapter2 = new MyAdapter();
        viewPager.setAdapter(adapter2);
        int size = currenciesTwo.size();
        int i1 = size % 3;
        int a = size / 3;
        if (i1 > 0) {
            a = a + 1;
        }
        main_linear.removeAllViews();
        WonderfulLogUtils.logi("miao", "a:" + a);
        for (int c = 0; c < a; c++) {
            View view = new View(getActivity());
            view.setBackgroundResource(R.drawable.zhishiqi);
            if (c == 0) {
                view.setEnabled(true);
            } else {
                view.setEnabled(false);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(35, 3);
            layoutParams.leftMargin = 10;
            main_linear.addView(view, layoutParams);
        }
        mHomeAdapter.notifyDataSetChanged();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                main_linear.getChildAt(mNum).setEnabled(false);
                main_linear.getChildAt(position).setEnabled(true);
                mNum = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private int mNum = 0;

    public void setChatTip(boolean hasNew) {
        if (hasNew) {
            ivchatTip.setVisibility(View.VISIBLE);
        } else {
            ivchatTip.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void deleteFail(Integer code, String toastMessage) {
        if (code == GlobalConstant.TOKEN_DISABLE1) {
            LoginActivity.actionStart(getActivity());
        } else {
            WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
        }
    }

    @Override
    public void deleteSuccess(String obj, int position) {
        this.currencies.get(position).setCollect(false);
        adapter.notifyDataSetChanged();
        if (adapter2 != null) {
            adapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void addFail(Integer code, String toastMessage) {
        if (code == GlobalConstant.TOKEN_DISABLE1) {
            LoginActivity.actionStart(getActivity());
        } else {
            WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
        }
    }

    @Override
    public void addSuccess(String obj, int position) {
        this.currencies.get(position).setCollect(true);
        adapter.notifyDataSetChanged();
        if (adapter2 != null) {
            adapter2.notifyDataSetChanged();
        }
    }

    public void notifyData() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

        if (adapter2 != null) {
            adapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void bannersSuccess(final List<BannerEntity> obj) {
        if (obj == null) {
            return;
        }
        if (imageUrls != null && imageUrls.size() > 1) {
            return;
        }
        for (BannerEntity bannerEntity : obj) {
            imageUrls.add(bannerEntity.getUrl());
        }
        if (imageUrls.size() == 0) {
            banner = banner.setImages(localImageUrls);
        } else {
            if (banner == null) {
                return;
            }
            banner.setImages(imageUrls);
        }
        if (imageUrls.size() > 0) {
            // 设置图片集合
            banner.setImages(imageUrls);
        }
        // 设置样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR).setIndicatorGravity(BannerConfig.CENTER)
                .setImageLoader(new BannerImageLoader(banner.getWidth(), banner.getHeight()));
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (obj.size() == 0) {
                    return;
                }
                if (obj.get(position).getLinkUrl() == null) {
                    return;
                }
                if (!verifyUrl(obj.get(position).getLinkUrl())) {
                    return;
                }
                Intent intent = new Intent();
                intent.setData(Uri.parse(obj.get(position).getLinkUrl()));
                intent.setAction(Intent.ACTION_VIEW);
                getmActivity().startActivity(intent);
            }
        });
        //设置轮播时间
        banner.setDelayTime(3000);
        banner.setBannerAnimation(Transformer.Default);
        banner.start();
    }

    /**
     * 验证是否是URL
     *
     * @param url
     * @return
     */
    private boolean verifyUrl(String url) {
        // URL验证规则
        String regEx = "[a-zA-z]+://[^\\s]*";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        return rs;

    }


    @Override
    public void bannersFail(Integer code, String toastMessage) {
        //do nothing
    }

    public void tcpNotify() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        if (mHomeAdapter != null) {
            mHomeAdapter.notifyDataSetChanged();
        }

        if (adapter2 != null) {
            adapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

        hideLoadingPopup();
    }

    @Override
    public void onStart() {
        super.onStart();
        hideLoadingPopup();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (marqueeView != null) {
                marqueeView.stopFlipping();
            }
        } else {
            if (marqueeView != null) {
                if (!marqueeView.isFlipping()) {
                    marqueeView.startFlipping();
                }
            }
        }
    }

    private void getMessage() {
        WonderfulOkhttpUtils.post().url(UrlFactory.getMessageUrl())
                .addParams("pageNo", 1 + "").addParams("pageSize", "8")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<Message> messages = new Gson().fromJson(object.getJSONObject("data").getJSONArray("content").toString(), new TypeToken<List<Message>>() {
                        }.getType());
                        messageList.clear();
                        messageList.addAll(messages);
                        setMarqueeView(messageList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                MessageDetailActivity.actionStart(getActivity(), messageList.get(infoss.get(position)).getId());
            }
        });
    }

    private List<Message> messageList = new ArrayList<>();
    private List<String> info = new ArrayList<>();
    private List<Integer> infoss = new ArrayList<>();

    private void setMarqueeView(List<Message> messageList) {
        info.clear();
        int code = SharedPreferenceInstance.getInstance().getLanguageCode();
        if (code == 1) {
            //中文
            for (int i = 0; i < messageList.size(); i++) {
                Message message = messageList.get(i);
                if (isContainChinese(message.getTitle())) {
                    String str = "";
                    if (message.getTitle().length() > 20) {
                        str = message.getTitle();
                        str = str.substring(0, 20);
                        info.add(str + "...");
                    } else {
                        info.add(message.getTitle());
                    }

                    infoss.add(i);
                }
            }

        } else {
            for (int i = 0; i < messageList.size(); i++) {
                Message message = messageList.get(i);
                if (!isContainChinese(message.getTitle())) {
                    info.add(message.getTitle());
                    infoss.add(i);
                }
            }
        }
        marqueeView.startWithList(info);
    }

    static Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

    public static boolean isContainChinese(String str) {
        Matcher m = p.matcher(str);
        return m.find();
    }
}
