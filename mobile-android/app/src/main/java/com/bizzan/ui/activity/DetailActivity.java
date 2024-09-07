package com.bizzan.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.app.UrlFactory;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.ActivityBean;
import com.bizzan.entity.Coin;
import com.bizzan.entity.SafeSetting;
import com.bizzan.ui.dialog.SafeValidateDialog;
import com.bizzan.ui.kline.KlineActivity;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.utils.WonderfulCommonUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.barlibrary.ImmersionBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * 创新实验室详情
 */
public class DetailActivity extends BaseActivity {

    @BindView(R.id.llTitle)
    View llTitle;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    ActivityBean.DataBean.ContentBean mData;
    private android.widget.ImageView imageview;
    private android.widget.ProgressBar progressbar;
    private TextView tvprogress;
    private TextView tvAmount;
    private TextView tvstep;
    private TextView tvType;
    private TextView tvlimttimes;
    private TextView tvlimitamount;
    private TextView leveloneCount;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private TextView button;
    @BindView(R.id.tvdetail)
    TextView tvdetail;
    @BindView(R.id.contentlayout)
    LinearLayout contentLayout;
    @BindView(R.id.amountlayout)
    View amountlayout;
    @BindView(R.id.alreadyamount)
    TextView alreadyamount;
    @BindView(R.id.leftamount)
    TextView leftamount;
    @BindView(R.id.holderlayout)
    View holderlayout;
    @BindView(R.id.myalreadyholdamount)
    TextView myalreadyholdamount;
    @BindView(R.id.alreadyholdamount)
    TextView alreadyholdamount;
    @BindView(R.id.freezeAmount)
    TextView freezeAmount;
    @BindView(R.id.inputlayout)
    View inputlayout;
    @BindView(R.id.inputlabel)
    TextView inputlabel;
    @BindView(R.id.inputview)
    EditText inputview;
    @BindView(R.id.inputunit)
    TextView inputunit;
    @BindView(R.id.tvbalance)
    TextView tvbalance;
    double myHoldAmount = 0, mybalance;
    SafeValidateDialog safeValidateDialog;
    List<ActivityBean.DataBean.ContentBean> recordList;


    public static void start(Context context, ActivityBean.DataBean.ContentBean data) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
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
    protected int getActivityLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        findViewById(R.id.ibBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imageview = findViewById(R.id.imageview);
        progressbar = findViewById(R.id.progressbar);
        tvprogress = findViewById(R.id.tvprogress);
        tvAmount = findViewById(R.id.tvAmount);
        tvstep = findViewById(R.id.tvstep);
        tvType = findViewById(R.id.tvType);
        tvlimttimes = findViewById(R.id.tvlimttimes);
        tvlimitamount = findViewById(R.id.tvlimitamount);
        leveloneCount = findViewById(R.id.leveloneCount);
        tvStartTime = findViewById(R.id.tv_start_time);
        tvEndTime = findViewById(R.id.tv_end_time);
        button = findViewById(R.id.button);
        button.setOnClickListener(view -> apply());
    }

    private void apply() {
        if (!MyApplication.getApp().isLogin()) {
            startActivityForResult(new Intent(this, LoginActivity.class), LoginActivity.RETURN_LOGIN);
            return;
        }
        if (WonderfulCommonUtils.isFastDoubleClick()) {
            return;
        }
        if ("1".equals(mData.getType())
                || "2".equals(mData.getType())) {
            KlineActivity.actionStart(this, mData.getUnit() + "/" + mData.getAcceptUnit(), "1");
            return;
        }
        String inputAmount = inputview.getText().toString();
        if (WonderfulStringUtils.parseInt(inputAmount, 0) <= 0) {
            if ("3".equals(mData.getType())) {
                WonderfulToastUtils.showToast(R.string.pleaseinputholdamount);
                return;
            } else if ("4".equals(mData.getType())) {
                WonderfulToastUtils.showToast(R.string.pleaseinputamount);
                return;
            }
        }
        //余额检查
        if ("4".equals(mData.getType())) {
            double input = WonderfulStringUtils.parseDouble(inputAmount, 0);
            double price = WonderfulStringUtils.parseDouble(mData.getPrice(), 0);
            if (input * price > mybalance) {
                WonderfulToastUtils.showToast(mData.getAcceptUnit() + getString(R.string.balancenotenough));
                return;
            }
        }
        //限购次数\限购数量
        double temAlreadyAttendAmount = 0;
        int temAlreadyAttendTimes = recordList == null ? 0 : recordList.size();
        if (recordList != null) {
            if ("3".equals(mData.getType())) {
                for (ActivityBean.DataBean.ContentBean data : recordList) {
                    temAlreadyAttendAmount += WonderfulStringUtils.parseDouble(data.getFreezeAmount(), 0);
                }
            } else if ("4".equals(mData.getType())) {
                for (ActivityBean.DataBean.ContentBean data : recordList) {
                    temAlreadyAttendAmount += WonderfulStringUtils.parseDouble(data.getAmount(), 0);
                }
            }
        }
        double minlimit = WonderfulStringUtils.parseDouble(mData.getMinLimitAmout(), 0);
        double maxlimit = WonderfulStringUtils.parseDouble(mData.getMaxLimitAmout(), 0);
        double input = WonderfulStringUtils.parseDouble(inputAmount, 0);
        if (minlimit > 0 && input < minlimit) {
            WonderfulToastUtils.showToast(R.string.minlimitamountfailed);
            return;
        }
        if (maxlimit > 0 && (temAlreadyAttendAmount + input) > maxlimit) {
            WonderfulToastUtils.showToast(R.string.maxlimitamountfailed);
            return;
        }
        //个人限购次数检查
        int limitTimes = WonderfulStringUtils.parseInt(mData.getLimitTimes(), 0);
        if (limitTimes > 0 && (temAlreadyAttendTimes + 1) > limitTimes) {
            WonderfulToastUtils.showToast(R.string.limittimesfailed);
            return;
        }
        //获取安全验证
        getMember();
    }

    private void getMember() {
        displayLoadingPopup();
        WonderfulOkhttpUtils.post().url(UrlFactory.getSafeSettingUrl()).addHeader("x-auth-token", getToken()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                hideLoadingPopup();
//                WonderfulToastUtils.showToast("获取安全验证失败 " + (e == null ? null : e.getMessage()));
                WonderfulLogUtils.logi("账户设置出错", "账户设置出错：" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                hideLoadingPopup();
                WonderfulLogUtils.logi("账户设置回执：", "账户设置回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        SafeSetting obj = gson.fromJson(object.getJSONObject("data").toString(), SafeSetting.class);
                        if (safeValidateDialog == null)
                            safeValidateDialog = SafeValidateDialog.getInstance(DetailActivity.this);
                        safeValidateDialog.setData(obj, new SafeValidateDialog.OperateCallback() {
                            @Override
                            public void onMobileSuccess(String mobile, String code) {
                                submit(mobile, code);
                            }

                            @Override
                            public void onEmailSuccess(String email, String code) {
                                submit(email, code);
                            }
                        });
                        safeValidateDialog.show(getSupportFragmentManager(), "safeValidateDialog");
                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void submit(String aims, String code) {
        displayLoadingPopup();
        WonderfulOkhttpUtils.post().url(UrlFactory.getALbAttend())
                .addHeader("x-auth-token", getToken())
                .addParams("amount", inputview.getText().toString())
                .addParams("activityId", mData.getId())
                .addParams("aims", aims)
                .addParams("code", code)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                hideLoadingPopup();
            }

            @Override
            public void onResponse(String response) {
                hideLoadingPopup();
                Log.e("ddd", "提交  " + response);
                try {
                    JSONObject object = new JSONObject(response);
                    WonderfulToastUtils.showToast(object.getString("message"));
                } catch (JSONException e) {
                    Log.e("ddd", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void obtainData() {
        mData = getIntent().getParcelableExtra("data");
        getDetail();
    }


    void addItem(String title, String value) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_activitydetail_item, contentLayout, false);
        TextView tvtitle = view.findViewById(R.id.tvlabel);
        TextView tvvalue = view.findViewById(R.id.tvvalue);
        tvtitle.setText(title);
        tvvalue.setText(value);
        contentLayout.addView(view);
    }

    @Override
    protected void fillWidget() {
        if (mData == null) {
            finish();
            return;
        }
        tvdetail.setText(Html.fromHtml(mData.getContent()));
        contentLayout.removeAllViews();
        tvTitle.setText(mData.getTitle());
        tvStartTime.setText(mData.getStartTime());
        tvEndTime.setText(mData.getEndTime());
        tvprogress.setText(WonderfulStringUtils.parseFloatString(mData.getProgress(), WonderfulStringUtils.parseInt(mData.getAmountScale(), 2)) + "%");
        tvAmount.setText(WonderfulStringUtils.parseFloatString(mData.getTotalSupply(), WonderfulStringUtils.parseInt(mData.getAmountScale(), 2)) + " " + mData.getUnit());
        progressbar.setProgress(WonderfulStringUtils.parseInt(mData.getProgress(), 0));
        Glide.with(this).load(mData.getSmallImageUrl()).error(R.color.colorTextNormal).into(imageview);
        if ("0".equals(mData.getStep())) {
            tvstep.setText(R.string.step_prepare);
        } else if ("1".equals(mData.getStep())) {
            tvstep.setText(R.string.step_ongoing);
        } else if ("2".equals(mData.getStep())) {
            tvstep.setText(R.string.step_distributing);
        } else {
            tvstep.setText(R.string.step_completed);
        }
        if ("1".equals(mData.getType())) {
            tvType.setText(R.string.activitytype1);
        } else if ("2".equals(mData.getType())) {
            tvType.setText(R.string.activitytype2);
        } else if ("3".equals(mData.getType())) {
            tvType.setText(R.string.activitytype3);
        } else if ("4".equals(mData.getType())) {
            tvType.setText(R.string.activitytype4);
        } else if ("5".equals(mData.getType())) {
            tvType.setText(R.string.activitytype5);
        } else if ("6".equals(mData.getType())) {
            tvType.setText(R.string.activitytype6);

            if ("0".equals(mData.getReleaseType())) {
                addItem(getString(R.string.releaseType), getString(R.string.releaseType0));
            } else {
                addItem(getString(R.string.releaseType), getString(R.string.releaseType1));
                addItem(getString(R.string.releaseType1), (WonderfulStringUtils.parseInt(mData.getReleasePercent(), 0) * 100) + "%");
            }
            addItem(getString(R.string.releaseDetail), mData.getLockedDays() + getString(R.string.lock));
            addItem(getString(R.string.lockFee), mData.getLockedFee() + mData.getLockedUnit());
            addItem(getString(R.string.releaseTimes), mData.getReleaseTimes() + getString(R.string.times));
        } else {
            tvType.setText(R.string.activitytype0);
        }
        if (!"3".equals(mData.getType()) && !"6".equals(mData.getType())) {
            addItem(getString(R.string.publishprice), WonderfulStringUtils.parseFloatString(mData.getPrice(), WonderfulStringUtils.parseInt(mData.getPriceScale(), 2)) + " " + mData.getAcceptUnit());
        }
        if (!"5".equals(mData.getType()) && !"6".equals(mData.getType())) {
            addItem(getString(R.string.activitycoin), mData.getUnit());
        }
        String title = null;
        if (!"3".equals(mData.getType()) && !"6".equals(mData.getType())) {
            title = getString(R.string.acceptcoin);
        }
        if ("3".equals(mData.getType()) || "6".equals(mData.getType())) {
            title = getString(R.string.holdcoin);
        }
        addItem(title, mData.getAcceptUnit());
        //限购次数
        tvlimttimes.setText(WonderfulStringUtils.parseInt(mData.getLimitTimes(), 0) > 0 ? mData.getLimitTimes()  : getString(R.string.unlimited));
        //一级好友数
        leveloneCount.setText(WonderfulStringUtils.parseDouble(mData.getLeveloneCount(), 0) > 0 ? mData.getLeveloneCount() : getString(R.string.unlimited));
        //限购数量
        int min = WonderfulStringUtils.parseInt(mData.getMinLimitAmout(), 0);
        int max = WonderfulStringUtils.parseInt(mData.getMaxLimitAmout(), 0);
        if (min > 0 && max > 0) {
            tvlimitamount.setText(format(mData.getMinLimitAmout(), mData.getAmountScale()) + " ~ " + format(mData.getMaxLimitAmout(), mData.getAmountScale()));
        } else if (min > 0 && max == 0) {
            tvlimitamount.setText(" ≥ " + format(mData.getMinLimitAmout(), mData.getAmountScale()));
        } else if (min == 0 && max > 0) {
            tvlimitamount.setText(" ≤ " + format(mData.getMaxLimitAmout(), mData.getAmountScale()));
        } else {
            tvlimitamount.setText(getString(R.string.unlimited));
        }
//        <!-- 仅自由申购类型/矿机显示已兑/剩余数量 -->
        if ("4".equals(mData.getType())
                || "5".equals(mData.getType())) {
            amountlayout.setVisibility(View.VISIBLE);
            alreadyamount.setText(format(mData.getTradedAmount(), mData.getAmountScale()) + " " + mData.getUnit());
            double total = WonderfulStringUtils.parseDouble(mData.getTotalSupply(), 0);
            double traded = WonderfulStringUtils.parseDouble(mData.getTradedAmount(), 0);
            leftamount.setText(format(String.valueOf(total - traded), mData.getAmountScale()) + " " + mData.getUnit());
        }
        showHoldAmount();
        showbalance();
        if ("3".equals(mData.getType())
                || "4".equals(mData.getType())
                || "5".equals(mData.getType())
                || "6".equals(mData.getType())) {
            inputlayout.setVisibility(View.VISIBLE);
            if ("3".equals(mData.getType())
                    || "6".equals(mData.getType())) {
                inputlabel.setText(R.string.inputholdamount);
                if ("3".equals(mData.getType())) {
                    inputunit.setText(mData.getAcceptUnit());
                } else {
                    inputunit.setText(mData.getLockedUnit());
                }
            } else if ("4".equals(mData.getType())) {
                inputlabel.setText(R.string.inputamount);
                inputunit.setText(mData.getUnit());
            } else {
                inputlabel.setText(R.string.inputminingamount);
                inputunit.setText(mData.getUnit());
            }
        }
        button.setEnabled("1".equals(mData.getStep()));
    }

    private void showbalance() {
        if ("3".equals(mData.getType())
                || "4".equals(mData.getType())
                || "5".equals(mData.getType())
                || "6".equals(mData.getType())) {
            tvbalance.setText(format(String.valueOf(mybalance), mData.getPriceScale()) + " " + mData.getAcceptUnit());
        }
    }

    private void showHoldAmount() {
        //        <!-- 持仓瓜分类型显示冻结参与者 -->
        if ("3".equals(mData.getType()) && MyApplication.getApp().isLogin()) {
            holderlayout.setVisibility(View.VISIBLE);
            myalreadyholdamount.setText(format(String.valueOf(myHoldAmount), mData.getAmountScale()) + " " + mData.getAcceptUnit());
            double freeze = WonderfulStringUtils.parseDouble(mData.getFreezeAmount(), 1);
            double total = WonderfulStringUtils.parseDouble(mData.getTotalSupply(), 0);
            int value = (int) (myHoldAmount / freeze * total);
            freezeAmount.setText(String.valueOf(value) + " " + mData.getUnit());
            alreadyholdamount.setText(format(mData.getFreezeAmount(), mData.getAmountScale()) + " " + mData.getAcceptUnit());
        }
    }

    private String format(String value, String scale) {
        return WonderfulStringUtils.parseFloatString(value, WonderfulStringUtils.parseInt(scale, 2));
    }


    private void getDetail() {
        if (mData == null) {
            return;
        }
        WonderfulOkhttpUtils.post().url(UrlFactory.getALabDetail())
                .addHeader("x-auth-token", getToken())
                .addParams("id", mData.getId())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulToastUtils.showToast(e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.show("ddd", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject != null && jsonObject.has("data")) {
                        ActivityBean.DataBean.ContentBean data = new Gson().fromJson(jsonObject.getString("data"), ActivityBean.DataBean.ContentBean.class);
                        if (data != null) {
                            mData = data;
                            fillWidget();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if ("3".equals(mData.getType()) && MyApplication.getApp().isLogin()) {
            //参与活动记录
            WonderfulOkhttpUtils.post().url(UrlFactory.getMemberRecords())
                    .addHeader("x-auth-token", getToken())
                    .addParams("activityId", mData.getId())
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Request request, Exception e) {
                    super.onError(request, e);
                    WonderfulToastUtils.showToast(e.getMessage());
                }

                @Override
                public void onResponse(String response) {
                    WonderfulLogUtils.show("ddd", "参与记录 " + response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        TypeToken<List<ActivityBean.DataBean.ContentBean>> type = new TypeToken<List<ActivityBean.DataBean.ContentBean>>() {
                        };
                        recordList = new Gson().fromJson(jsonObject.getString("data"), type.getType());
                        for (int i = 0; recordList != null && i < recordList.size(); i++) {
                            myHoldAmount += WonderfulStringUtils.parseDouble(recordList.get(i).getFreezeAmount(), 0);
                            showHoldAmount();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        //余额
        if (MyApplication.getApp().isLogin()) {
            WonderfulOkhttpUtils.post().url(UrlFactory.getWalletUrl() + mData.getAcceptUnit())
                    .addHeader("x-auth-token", getToken()).build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    super.onError(request, e);
                    WonderfulLogUtils.logi("获取单个钱包出错", "获取单个钱包出错：" + e.getMessage());
                }

                @Override
                public void onResponse(String response) {
                    //WonderfulLogUtils.logi("获取单个钱包回执：", "获取单个钱包回执：" + response.toString());
                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.optInt("code") == 0) {
                            Coin obj = gson.fromJson(object.getJSONObject("data").toString(), Coin.class);
                            mybalance = obj.getBalance();
                        }
                        showbalance();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    @Override
    protected void loadData() {

    }

}