package com.bizzan.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import com.bizzan.R;
import com.bizzan.entity.OptionIconBean;
import com.bizzan.utils.WonderfulDateUtils;

/**
 * data: 2020/10/14.
 */
public class SevenPopIconView extends PopupWindow {
    private View view;
    private TextView tv_title, tv_open_time, tv_open_price, tv_up, tv_close_time, tvclose_price, tv_down;
    private Button bu_close;

    public SevenPopIconView(final Activity activity, OptionIconBean.DataBean.ContentBean contentBean, View.OnClickListener itemsOnClick) {
        this.view = LayoutInflater.from(activity).inflate(R.layout.pop_seven_icon, null);
        tv_title = view.findViewById(R.id.tv_title);
        tv_open_time = view.findViewById(R.id.tv_open_time);
        tv_open_price = view.findViewById(R.id.tv_open_price);
        tv_up = view.findViewById(R.id.tv_up);
        tv_close_time = view.findViewById(R.id.tv_close_time);
        tvclose_price = view.findViewById(R.id.tvclose_price);
        tv_down = view.findViewById(R.id.tv_down);
        bu_close = view.findViewById(R.id.bu_close);

        tv_title.setText(activity.getResources().getText(R.string.nos)+"" + contentBean.getOptionNo() + ""+activity.getResources().getText(R.string.expect));
        tv_open_time.setText(WonderfulDateUtils.getFormatTime("yyyy-MM-dd\nHH:mm:ss", new Date(contentBean.getOpenTime())));
        tv_open_price.setText(contentBean.getOpenPrice() + " USDT");
        tv_up.setText(contentBean.getTotalBuy() + " USDT");
        tv_close_time.setText(WonderfulDateUtils.getFormatTime("yyyy-MM-dd\nHH:mm:ss", new Date(contentBean.getCloseTime())));
        tvclose_price.setText(contentBean.getClosePrice() + " USDT");
        tv_down.setText(contentBean.getTotalSell() + " USDT");
        bu_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        // 设置外部可点击

        this.setOnDismissListener(new PopupWindow.OnDismissListener() {
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

}
