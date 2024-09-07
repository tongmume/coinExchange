package com.bizzan.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.adapter.AddMultiples;
import com.bizzan.app.MyApplication;
import com.bizzan.ui.home.ISixContract;
import com.bizzan.ui.home.SixFragment;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.xw.repo.BubbleSeekBar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Haocxx
 * on 2019/8/12
 */
public class SixPopFlat extends PopupWindow {

    private View view;
    private TabLayout tab;
    private final Button btnOneBuy;
    private final LinearLayout mOneXian;
    private final LinearLayout mOnetrigger;
    private final LinearLayout mOneSeekBarLin;
    private final EditText mOneTCP;
    private final EditText mOnePrice;
    private final EditText mOnetriggerPrice;
    private final TextView mOneShi;

    private int Position = 0;
    private int isinput = 0;
    private String price;
    private String amout;
    private final BubbleSeekBar mOneSeekBar;


    public SixPopFlat(final Activity activity, final String number, final int sendtype, final int id, final ISixContract.Presenter mPresenter, View.OnClickListener itemsOnClick) {
        this.view = LayoutInflater.from(activity).inflate(R.layout.pop_six_flat, null);
        tab = view.findViewById(R.id.tab);
        btnOneBuy = view.findViewById(R.id.btnOneBuy);
        mOneXian = view.findViewById(R.id.mOneXian);
        mOnetrigger = view.findViewById(R.id.mOnetrigger);
        mOneSeekBarLin = view.findViewById(R.id.mOneSeekBarLin);
        mOneTCP = view.findViewById(R.id.mOneTCP);
        mOnePrice = view.findViewById(R.id.mOnePrice);
        mOnetriggerPrice = view.findViewById(R.id.mOnetriggerPrice);
        mOneShi = view.findViewById(R.id.mOneShi);
        mOneSeekBar = view.findViewById(R.id.mOneSeekBar);

        mOneTCP.setText(number);

        mOneTCP.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    if (mOneTCP.getText().toString().contains("%")) {
                        isinput = 1;
                        mOneTCP.setText("");
                        mOneSeekBar.setProgress(0);
                    }
                } else {
                    // 此处为失去焦点时的处理内容
                    isinput = 0;
                }
            }
        });
        mOneSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                if (fromUser) {  //是否用户设置progress
                    mOneTCP.setText(progress + "%");
                    mOneTCP.clearFocus();
                }
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
            }
        });

        mOneSeekBar.setCustomSectionTextArray(new BubbleSeekBar.CustomSectionTextArray() {
            @NonNull
            @Override
            public SparseArray<String> onCustomize(int sectionCount, @NonNull SparseArray<String> array) {
                array.clear();
                array.put(0, "0%");
                array.put(1, "25%");
                array.put(2, "50%");
                array.put(3, "75%");
                array.put(4, "100%");
                return array;
            }
        });

        tab.addTab(tab.newTab().setText(activity.getResources().getText(R.string.text_limit_entrust)));
        tab.addTab(tab.newTab().setText(activity.getResources().getText(R.string.text_Market_entrust)));
        tab.addTab(tab.newTab().setText(activity.getResources().getText(R.string.text_plan_entrust)));
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    //点击限价委托
                    Position = 0;
                    mOnePrice.setHint(activity.getResources().getText(R.string.text_enter_price_constract));
                    mOnePrice.setText("");
                    mOnetriggerPrice.setText("");
                    mOneXian.setVisibility(View.VISIBLE);
                    mOneShi.setVisibility(View.GONE);
                    mOneTCP.setHint(activity.getResources().getText(R.string.text_number));
                    mOneSeekBarLin.setVisibility(View.VISIBLE);
                    mOnetrigger.setVisibility(View.GONE);
                } else if (tab.getPosition() == 1) {
                    //点击市价委托
                    Position = 1;
                    mOnePrice.setHint(activity.getResources().getText(R.string.text_enter_price_constract));
                    mOnePrice.setText("");
                    mOnetriggerPrice.setText("");
                    mOneXian.setVisibility(View.GONE);
                    mOneShi.setVisibility(View.VISIBLE);
                    mOneTCP.setHint(activity.getResources().getText(R.string.text_number));
                    mOneSeekBarLin.setVisibility(View.VISIBLE);
                    mOnetrigger.setVisibility(View.GONE);
                } else if (tab.getPosition() == 2) {
                    Position = 2;
                    mOnePrice.setHint(activity.getResources().getText(R.string.default_market));
                    mOnePrice.setText("");
                    mOnetriggerPrice.setText("");
                    mOneXian.setVisibility(View.VISIBLE);
                    mOneShi.setVisibility(View.GONE);
                    mOnetrigger.setVisibility(View.VISIBLE);
                    mOneSeekBarLin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        if (sendtype == 1){
            btnOneBuy.setText(activity.getResources().getText(R.string.sellflatmore));
            btnOneBuy.setBackgroundResource(R.color.typeRed);
        }else if (sendtype == 2){
            btnOneBuy.setText(activity.getResources().getText(R.string.buyflatnull));
            btnOneBuy.setBackgroundResource(R.color.typeGreen);
        }


        btnOneBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sale(Position, mOneTCP, mOnePrice, mOnetriggerPrice, number, sendtype, activity,mPresenter,id);
            }
        });


        // 设置按钮监听
//        bu_true2.setOnClickListener(itemsOnClick);
//        bu_close2.setOnClickListener(itemsOnClick);

        // 设置外部可点击
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = (float) 1.0; //0.0-1.0
                activity.getWindow().setAttributes(lp);
            }
        });
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = (float) 0.6; //0.0-1.0
        activity.getWindow().setAttributes(lp);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 设置弹出窗体的背景
        this.setBackgroundDrawable(Drawable.createFromPath("#00000000"));

        this.setAnimationStyle(R.style.bottomDialog);
    }



    /**
     * @param type             类型
     * @param mOneTCP          数量
     * @param mOnePrice        单价
     * @param mOnetriggerPrice 触发价
     * @param number
     * @param sendtype
     * @param activity
     * @param mPresenter
     * @param id
     */
    private void Sale(int type, EditText mOneTCP, EditText mOnePrice, EditText mOnetriggerPrice, String number, int sendtype, Activity activity, ISixContract.Presenter mPresenter, int id) {
        switch (type) {
            case 0:
                price = mOnePrice.getText().toString();
                amout = mOneTCP.getText().toString();
                if (WonderfulStringUtils.isEmpty(price, amout)) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.input_entrust_price_number) + "");
                    return;
                }
                if (amout.contains("%")) {
                    String substring = amout.substring(0, amout.length() - 1);
                    if (substring.equals("0")) {
                        WonderfulToastUtils.showToast(activity.getResources().getText(R.string.number_big_zero) + "");
                        return;
                    }
                    double su = (Double.parseDouble(substring) / 100) * Integer.parseInt(number);
                    if (su < 1) {
                        amout = 1 + "";
                    } else {
                        amout = (int) su + "";
                    }
                }

                if (new BigDecimal(amout).compareTo(BigDecimal.ZERO) <= 0) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.number_big_zero) + "");
                    return;
                }
                if (new BigDecimal(price).compareTo(BigDecimal.ZERO) <= 0) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.input_ok_entrust_price) + "");
                    return;
                }
                if (Integer.parseInt(amout) > Integer.parseInt(number)) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.input_entrust_price_number) + "");
                    return;
                }
                if (sendtype == 1) {
                    //  1:token 2:市价，限价，计划委托   3：买  0  卖  1 4：id  5：计划委托里触发价   6：价格  7：倍数  8  数量
                    mPresenter.getcloseOrder(SharedPreferenceInstance.getInstance().getTOKEN(),
                            "1" + "", "1", id + "", "0", price,
                            "0",
                            amout);
                } else if (sendtype == 2) {
                    mPresenter.getcloseOrder(SharedPreferenceInstance.getInstance().getTOKEN(),
                            "1" + "", "0", id + "", "0", price,
                            "0",
                            amout);
                }

                break;
            case 1:
                price = "0.0";
                amout = mOneTCP.getText().toString();
                if (WonderfulStringUtils.isEmpty(price, amout)) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.input_number) + "");
                    return;
                }
                if (amout.contains("%")) {
                    String substring = amout.substring(0, amout.length() - 1);
                    if (substring.equals("0")) {
                        WonderfulToastUtils.showToast(activity.getResources().getText(R.string.number_big_zero) + "");
                        return;
                    }
                    double su = (Double.parseDouble(substring) / 100) * Integer.parseInt(number);
                    if (su < 1) {
                        amout = 1 + "";
                    } else {
                        amout = (int) su + "";
                    }
                }

                if (new BigDecimal(amout).compareTo(BigDecimal.ZERO) <= 0) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.input_ok_entrust_price) + "");
                    return;
                }
                if (Integer.parseInt(amout) > Integer.parseInt(number)) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.input_entrust_price_number) + "");
                    return;
                }
                if (sendtype == 1) {
                    //  1:token 2:市价，限价，计划委托   3：买  0  卖  1 4：id  5：计划委托里触发价   6：价格  7：倍数  8  数量
                    mPresenter.getcloseOrder(SharedPreferenceInstance.getInstance().getTOKEN(),
                            "0" + "", "1", id + "", "0", price,
                            "0",
                            amout);
                } else if (sendtype == 2) {
                    mPresenter.getcloseOrder(SharedPreferenceInstance.getInstance().getTOKEN(),
                            "0" + "", "0", id + "", "0", price,
                            "0",
                            amout);
                }
                break;
            case 2:
                String plan = "";
                price = mOnePrice.getText().toString();
                amout = mOneTCP.getText().toString();
                plan = mOnetriggerPrice.getText().toString();
                if (price.equals("")) {
                    price = "0";
                }
                if (WonderfulStringUtils.isEmpty(price, amout)) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.input_entrust_price_number) + "");
                    return;
                }
                if (new BigDecimal(amout).compareTo(BigDecimal.ZERO) <= 0) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.number_big_zero) + "");
                    return;
                }
                if (new BigDecimal(price).compareTo(BigDecimal.ZERO) < 0) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.input_ok_entrust_price) + "");
                    return;
                }
                if (Integer.parseInt(amout) > Integer.parseInt(number)) {
                    WonderfulToastUtils.showToast(activity.getResources().getText(R.string.input_entrust_price_number) + "");
                    return;
                }
                if (sendtype == 1) {
                    //  1:token 2:市价，限价，计划委托   3：买  0  卖  1 4：id  5：计划委托里触发价   6：价格  7：倍数  8  数量
                    mPresenter.getcloseOrder(SharedPreferenceInstance.getInstance().getTOKEN(),
                            "2" + "", "1", id + "", plan, price,
                            "0",
                            amout);
                } else if (sendtype == 2) {
                    mPresenter.getcloseOrder(SharedPreferenceInstance.getInstance().getTOKEN(),
                            "2" + "", "0", id + "", plan, price,
                            "0",
                            amout);
                }
                break;
            default:
        }
    }

}
