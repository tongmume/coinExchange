package com.bizzan.ui.wallet;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.ui.extract.ExtractActivity;
import com.bizzan.ui.recharge.RechargeActivity;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseDialogFragment;
import com.bizzan.entity.Coin;
import com.bizzan.app.UrlFactory;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulDpPxUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class WalletDialogFragment extends BaseDialogFragment {

    private static Context context1;
    @BindView(R.id.tvRecharge)
    TextView tvRecharge;
    @BindView(R.id.tvExtract)
    TextView tvExtract;
    Unbinder unbinder;
    @BindView(R.id.llContainer)
    LinearLayout llContainer;
    @BindView(R.id.tvMatch)
    TextView tvMatch;
    Unbinder unbinder1;
    private Coin coin;
    private boolean isMatch = false;

    public static WalletDialogFragment getInstance(Coin coins,Boolean isMatch,Context context) {
        WalletDialogFragment fragment = new WalletDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("coins", coins);
        bundle.putBoolean("isMatch",isMatch);
        fragment.setArguments(bundle);
        WonderfulLogUtils.logi("WalletDialogFragment  ", "getInstance");
        context1=context;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        WonderfulLogUtils.logi("WalletDialogFragment  ", "getLayoutId");
        return R.layout.dialog_fragment_wallet;
    }

    private int i=0;

    @Override
    protected void initLayout() {
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottomDialog);
        if (isMatch) {

        }else{
            i=i+50;
        }
        if (coin.getCoin().getCanRecharge() == 0) {
            i=i+50;
        }
        if (coin.getCoin().getCanWithdraw() == 0) {
            i=i+50;
        }
        window.setLayout(MyApplication.getApp().getmWidth(), (WonderfulDpPxUtils.dip2px(getActivity(), (150-i)))+getBottomStatusHeight(context1));
    }
    /**
     * 获取 虚拟按键的高度
     *
     * @param context
     * @return
     */
    public static int getBottomStatusHeight(Context context) {
        int totalHeight = getDpi(context);

        int contentHeight = getScreenHeight(context);

        return totalHeight - contentHeight;
    }

    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    @Override
    protected void initView() {
        tvRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (WonderfulStringUtils.isEmpty(coin.getAddress())) {
                    huoQu();
                } else {
                    dismiss();
                    RechargeActivity.actionStart(getActivity(), coin);
                }
            }
        });

        tvExtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                ExtractActivity.actionStart(getActivity(), coin);
            }
        });

        tvMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OperateCallback) getActivity()).toMatch();
                dismiss();
            }
        });
    }

    public void huoQu(){
        if(coin.getCoin().getAccountType() != 1) {
            WonderfulOkhttpUtils.post().url(UrlFactory.getChongbi())
                    .addHeader("x-auth-token", SharedPreferenceInstance.getInstance().getTOKEN())
                    .addParams("unit", coin.getCoin().getUnit())
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    super.onError(request, e);
                }

                @Override
                public void onResponse(String response) {
                    // WonderfulToastUtils.showToast(WonderfulToastUtils.getString(R.string.noAddAddressTip));
                    WonderfulLogUtils.logi("miao", response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        int code = jsonObject.optInt("code");
                        if (code == 0) {
                            dismiss();
                            RechargeActivity.actionStart(getActivity(), coin);
                        } else {
                            WonderfulToastUtils.showToast("" + jsonObject.optString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else{
            dismiss();
            RechargeActivity.actionStart(getActivity(), coin);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        dismiss();
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        coin = (Coin) getArguments().getSerializable("coins");
        isMatch = getArguments().getBoolean("isMatch");
        if (isMatch) {
            tvMatch.setVisibility(View.VISIBLE);
        }else tvMatch.setVisibility(View.GONE);
        if (coin.getCoin().getCanRecharge() == 0) {
            tvRecharge.setVisibility(View.GONE);
        }
        if (coin.getCoin().getCanWithdraw() == 0) {
            tvExtract.setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    public interface OperateCallback {
        void toMatch();
    }
}
