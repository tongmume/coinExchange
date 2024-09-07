package com.bizzan.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bizzan.R;


/**
 * Created by Haocxx
 * on 2019/8/12
 */
public class SixPopEntrustView extends PopupWindow {

    private View view;

    private final LinearLayout ll_limit_entrust,ll_Market_entrust,ll_plan_entrust;
    public SixPopEntrustView(final Activity activity, View.OnClickListener itemsOnClick) {
        this.view = LayoutInflater.from(activity).inflate(R.layout.pop_six_entrust, null);
        ll_limit_entrust = view.findViewById(R.id.ll_limit_entrust);
        ll_Market_entrust = view.findViewById(R.id.ll_Market_entrust);
        ll_plan_entrust = view.findViewById(R.id.ll_plan_entrust);

        // 设置按钮监听
        ll_limit_entrust.setOnClickListener(itemsOnClick);
        ll_Market_entrust.setOnClickListener(itemsOnClick);
        ll_plan_entrust.setOnClickListener(itemsOnClick);
        // 设置外部可点击
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = (float) 1.0; //0.0-1.0
                activity.getWindow().setAttributes(lp);
            }
        });

        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(activity.findViewById(R.id.rl_entrust_spinner).getWidth());
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = (float) 0.6; //0.0-1.0
        activity.getWindow().setAttributes(lp);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 设置弹出窗体的背景
        this.setBackgroundDrawable(Drawable.createFromPath("#00000000"));
    }
}
