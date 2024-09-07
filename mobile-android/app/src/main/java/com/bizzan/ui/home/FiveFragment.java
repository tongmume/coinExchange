package com.bizzan.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.adapter.shareApkImageAdapter;
import com.bizzan.app.MyApplication;
import com.bizzan.app.UrlFactory;
import com.bizzan.base.BaseTransFragment;
import com.bizzan.entity.Coin;
import com.bizzan.entity.SafeSetting;
import com.bizzan.entity.User;
import com.bizzan.ui.account_pwd.AccountPwdActivity;
import com.bizzan.ui.account_pwd.EditAccountPwdActivity;
import com.bizzan.ui.activity.MyAttendActivity;
import com.bizzan.ui.bind_account.BindAccountActivity;
import com.bizzan.ui.ctc.CTCActivity;
import com.bizzan.ui.entrust.TrustListActivity;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.ui.my_ads.AdsActivity;
import com.bizzan.ui.my_order.MyOrderActivity;
import com.bizzan.ui.myinfo.MyInfoActivity;
import com.bizzan.ui.setting.SettingActivity;
import com.bizzan.ui.wallet.WalletActivity;
import com.bizzan.ui.wallet_detail.WalletDetailActivity;
import com.bizzan.utils.SavePhoto;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulBitmapUtils;
import com.bizzan.utils.WonderfulCommonUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import com.bizzan.widget.FivePopInvite;
import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/1/29.
 */

public class FiveFragment extends BaseTransFragment implements MainContract.FiveView, View.OnClickListener {
    public static final String TAG = FiveFragment.class.getSimpleName();
    @BindView(R.id.llTop)
    LinearLayout llTop;
    @BindView(R.id.llAccount)
    LinearLayout llAccount;
    @BindView(R.id.llOrder)
    LinearLayout llOrder;
    @BindView(R.id.line_lab)
    LinearLayout line_lab;
    @BindView(R.id.llAds)
    LinearLayout llAds;
    @BindView(R.id.line_top)
    LinearLayout line_top;
    @BindView(R.id.line_zican)
    LinearLayout line_zican;
    @BindView(R.id.line_shoukuan)
    LinearLayout line_shoukuan;
    @BindView(R.id.line_jypwd)
    LinearLayout line_jypwd;
    @BindView(R.id.line_bibi)
    LinearLayout line_bibi;
    @BindView(R.id.line_ctc)
    LinearLayout line_ctc;
    @BindView(R.id.line_security)
    LinearLayout line_security;
    @BindView(R.id.llSafe)
    ImageView llSafe;
    @BindView(R.id.llSettings)
    ImageView llSettings;
    @BindView(R.id.llMyinfo)
    LinearLayout llMyinfo;
    @BindView(R.id.tvNickName)
    TextView tvNickName;
    @BindView(R.id.tvAccount)
    TextView tvAccount;

    @BindView(R.id.tvLevelOneCount)
    TextView tvLevelOneCount;
    @BindView(R.id.tvLevelTwoCount)
    TextView tvLevelTwoCount;
    @BindView(R.id.tvEstimatedReward)
    TextView tvEstimatedReward;
    @BindView(R.id.tvCurrentLevel)
    TextView tvCurrentLevel;
    @BindView(R.id.tvMyPromotionCode)
    TextView tvMyPromotionCode;

    @BindView(R.id.ivHeader)
    ImageView ivHeader;
    public static double sumUsd = 0;
    double sumCny = 0;
    @BindView(R.id.llEntrust)
    LinearLayout llEntrust;
    Unbinder unbinder;
    @BindView(R.id.img)
    ImageView img;
    private MainContract.FivePresenter presenter;
    private SafeSetting safeSetting;
    private PopupWindow loadingPopup;
    private FivePopInvite fivePopInvite;
    private String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ATC/";//图片/
    private shareApkImageAdapter adapter;
    private ArrayList<Integer> imagebitmap;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_five;
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
    protected void initViews(Bundle savedInstanceState) {
        biaoshi = -1;
        initLoadingPopup();

        line_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginOrCenter();
//                if (MyApplication.getApp().isLogin()) {
//                    displayLoadingPopup();
//                    accountClick();
//                } else {
//                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
//                }
            }
        });
        //我参与的创新实验室
        line_lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MyAttendActivity.class));
            }
        });
        line_shoukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    displayLoadingPopup();
                    accountClick();
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }
            }
        });
        line_jypwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    displayLoadingPopup();
                    jyPwdClick();
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }
            }
        });
        llAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    displayLoadingPopup();
                    WalletActivity.actionStart(getActivity());
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }

            }
        });

        line_zican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    displayLoadingPopup();
                    WalletDetailActivity.actionStart(getmActivity());
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }
            }
        });

        line_ctc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CTCActivity.show(getActivity());
            }
        });

        line_bibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    displayLoadingPopup();
                    TrustListActivity.show(getActivity());
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }
            }
        });
        line_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginOrCenter();
            }
        });
        llOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    displayLoadingPopup();
                    MyOrderActivity.actionStart(getActivity(), 0);
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }
            }
        });
        llAds.setOnClickListener(new View.OnClickListener() {
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
                                            displayLoadingPopup();
                                            AdsActivity.actionStart(getActivity(), MyApplication.getApp().getCurrentUser().getUsername(), MyApplication.getApp().getCurrentUser().getAvatar());
                                        } else {
                                            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.shangjia) + "");
                                        }
                                    } else {
                                        WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.unknown_error) + "");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }
            }
        });
        llSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginOrCenter();
            }
        });
        llSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getApp().isLogin()) {
                    displayLoadingPopup();
                    SettingActivity.actionStart(getActivity());
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }
            }
        });
        tvMyPromotionCode.setOnClickListener(this);
        imagebitmap = new ArrayList<>();
        imagebitmap.add(R.drawable.invite1);
        imagebitmap.add(R.drawable.invite2);
        imagebitmap.add(R.drawable.invite3);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideLoadingPopup();

    }

    @Override
    public void onStart() {
        super.onStart();
        hideLoadingPopup();
    }


    private void toLoginOrCenter() {
        if (MyApplication.getApp().isLogin()) {
            MyInfoActivity.actionStart(getActivity());
        } else {
            startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
        }
    }

    @Override
    protected void obtainData() {
    }

    @Override
    protected void fillWidget() {
    }

    @Override
    protected void loadData() {
        if (MyApplication.getApp().isLogin()) {
            loginingViewText();
        } else {
            notLoginViewText();
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(getActivity(), llMyinfo);
            isSetTitle = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LoginActivity.RETURN_LOGIN:
                if (resultCode == Activity.RESULT_OK && getUserVisibleHint() && MyApplication.getApp().isLogin()) {
                    loginingViewText();
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    notLoginViewText();
                }
                break;
            default:
        }
    }

    private void notLoginViewText() {
        try {
            sumCny = 0.00;
            sumUsd = 0.000000;
            tvNickName.setText(getActivity().getResources().getText(R.string.not_logged_in));
            tvLevelOneCount.setText("—");
            tvLevelTwoCount.setText("—");
            tvEstimatedReward.setText("—");
            tvCurrentLevel.setText("—");
            Glide.with(getActivity().getApplicationContext()).load(R.mipmap.icon_default_header).into(ivHeader);
        } catch (Exception e) {

        }
    }

    private void loginingViewText() {
        try {
            presenter.myWallet(getmActivity().getToken());
            presenter.safeSetting(getmActivity().getToken());
            User user = MyApplication.getApp().getCurrentUser();
            tvNickName.setText(user.getUsername());
            tvLevelOneCount.setText(String.valueOf(user.getFirstLevel()));
            tvLevelTwoCount.setText(String.valueOf(user.getSecondLevel()));
            tvCurrentLevel.setText(this.getPartnerNameByCount(user.getFirstLevel()));
            tvEstimatedReward.setText("0");
            if (!WonderfulStringUtils.isEmpty(user.getAvatar())) {
                Glide.with(getActivity().getApplicationContext()).load(user.getAvatar()).into(ivHeader);
            } else {
                Glide.with(getActivity().getApplicationContext()).load(R.mipmap.icon_default_header).into(ivHeader);
            }

            this.presenter.myPromotion(getmActivity().getToken());
        } catch (Exception e) {

        }

    }

    private String getPartnerNameByCount(int count) {
        if (count > 0) {
            return "L1";
        }
        if (count > 10) {
            return "L2";
        }
        if (count > 100) {
            return "L3";
        }
        if (count > 500) {
            return "L4";
        }
        if (count > 1500) {
            return "L5";
        }
        if (count > 3000) {
            return "L6";
        }
        return "L0";
    }

    @Override
    public void setPresenter(MainContract.FivePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void myPromotionSuccess(JSONObject ret) {
        if (ret == null) {
            return;
        }
        try {
            tvEstimatedReward.setText(ret.getString("estimatedReward"));
            tvLevelOneCount.setText(ret.getString("levelOne"));
            tvLevelTwoCount.setText(ret.getString("levelTwo"));
        } catch (JSONException e) {
            tvEstimatedReward.setText("—");
        }
    }

    @Override
    public void myPromotionFail(Integer code, String toastMessage) {
        tvEstimatedReward.setText("-");
    }

    @Override
    public void myWalletSuccess(List<Coin> obj) {
        if (obj == null) {
            return;
        }
        calcuTotal(obj);
    }

    private void calcuTotal(List<Coin> coins) {
        sumUsd = 0;
        sumCny = 0;
        for (Coin coin : coins) {
            sumUsd += (coin.getBalance() * coin.getCoin().getUsdRate());
            sumCny += (coin.getBalance() * coin.getCoin().getCnyRate());
        }
    }

    @Override
    public void myWalletFail(Integer code, String toastMessage) {
//        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
        biaoshi = 1;
//        SharedPreferenceInstance.getInstance().saveIsNeedShowLock(false);
//        SharedPreferenceInstance.getInstance().saveLockPwd("");
        MyApplication.getApp().setCurrentUser(null);
        notLoginViewText();
        if (code == 4000) {
//            MyApplication.getApp().loginAgain(getmActivity());
            SharedPreferenceInstance.getInstance().saveaToken("");
            SharedPreferenceInstance.getInstance().saveTOKEN("");
        }
    }

    private void accountClick() {
        if (safeSetting == null) {
            return;
        }
        hideLoadingPopup();
        if (safeSetting.getRealVerified() == 1 && safeSetting.getFundsVerified() == 1) {
            BindAccountActivity.actionStart(getmActivity());
        } else {
            WonderfulToastUtils.showToast(getActivity().getResources().getText(R.string.password_realname) + "");
        }
    }

    public void jyPwdClick() {
        if (safeSetting == null) return;
        if (safeSetting.getFundsVerified() == 0) AccountPwdActivity.actionStart(getmActivity());
        else if (safeSetting.getFundsVerified() == 1)
            EditAccountPwdActivity.actionStart(getmActivity());
    }

    private int biaoshi = -1;

    @Override
    public void safeSettingSuccess(SafeSetting obj) {
        if (obj == null) {
            return;
        }
        this.safeSetting = obj;
        MyApplication.number = safeSetting.getMobilePhone();

//        if (tvIdCredit==null){
//            return;
//        }
//        if (safeSetting.getRealVerified() == 1) {
//            tvIdCredit.setEnabled(false);
//            tvIdCredit.setText(R.string.verification);
//        } else if (safeSetting.getRealAuditing() == 1) {
//            tvIdCredit.setEnabled(false);
//            tvIdCredit.setText(R.string.creditting);
//        } else {
//            tvIdCredit.setEnabled(true);
//            tvIdCredit.setText(R.string.unverified);
//        }
    }

    @Override
    public void safeSettingFail(Integer code, String toastMessage) {
        if (code == 4000) {
            MyApplication.getApp().setCurrentUser(null);
            SharedPreferenceInstance.getInstance().saveaToken("");
            SharedPreferenceInstance.getInstance().saveTOKEN("");
            notLoginViewText();
        }
        //do nothing
    }

    @Override
    protected String getmTag() {
        return TAG;
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

    //user.getPromotionCode(), user.getPromotionPrefix()
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMyPromotionCode:
                if (MyApplication.getApp().isLogin()) {
                    User user = MyApplication.getApp().getCurrentUser();
                    adapter = new shareApkImageAdapter(R.layout.item_pop_five_invite, imagebitmap, user.getPromotionCode(), user.getPromotionPrefix());
                    fivePopInvite = new FivePopInvite(getActivity(), adapter, this);
                    fivePopInvite.showAtLocation(getActivity().findViewById(R.id.llRoot), Gravity.BOTTOM, 0, 0);

                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
                }
                break;
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

    private void saveImage() {
        img.setImageBitmap(selectimage());
        img.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建savephoto类保存图片
                    SavePhoto savePhoto = new SavePhoto(getActivity());
                    savePhoto.SaveBitmapFromView(img, dir);
                    img.setVisibility(View.GONE);
                    WonderfulToastUtils.showToast(R.string.savelocally);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }, 50);
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


}
