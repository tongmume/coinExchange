package com.bizzan.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;

import java.text.DecimalFormat;

import com.bizzan.R;


/**
 * Created by Haocxx
 * on 2019/8/12
 */
public class SixPopMultiplesView extends PopupWindow {

    private View view;
    private BubbleSeekBar mOneSeekBar;
    private TextView tv_add, tv_subtract, tv_multiple;
    private Button bu_close,bu_true;
    //获取当前倍数
    public String getprogress() {
        if (mOneSeekBar != null){
            return mOneSeekBar.getProgress()+"";
        }else if (tv_multiple != null){
            return tv_multiple.getText().toString();
        }else {
            return "1";
        }
    }

    public SixPopMultiplesView(final Activity activity,String progress,String leverage, View.OnClickListener itemsOnClick) {
        this.view = LayoutInflater.from(activity).inflate(R.layout.pop_six_multiples, null);
        mOneSeekBar = view.findViewById(R.id.mOneSeekBar);
        tv_multiple = view.findViewById(R.id.tv_multiple);
        tv_add = view.findViewById(R.id.tv_add);
        tv_subtract = view.findViewById(R.id.tv_subtract);
        bu_close = view.findViewById(R.id.bu_close);
        bu_true = view.findViewById(R.id.bu_true);

        final String[] split = leverage.split(",");
        mOneSeekBar.getConfigBuilder()
                .min(Float.parseFloat(split[0]))
                .max(Float.parseFloat(split[split.length-1]))
                .progress(Float.parseFloat(split[0]))
                .build();

        if (Integer.parseInt(progress) > mOneSeekBar.getMax()){
//            progress = mOneSeekBar.getMax()+"x";
            tv_multiple.setText(mOneSeekBar.getProgress()+"");
//            mOneSeekBar.setProgress(Float.parseFloat(progress));
        }else if (Integer.parseInt(progress) < mOneSeekBar.getMin()){
            tv_multiple.setText(mOneSeekBar.getProgress()+"");
//            mOneSeekBar.setProgress(Float.parseFloat(progress));
        }else {
            tv_multiple.setText(progress);
            mOneSeekBar.setProgress(Float.parseFloat(progress));
        }

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double num = Double.valueOf(tv_multiple.getText().toString());
                    if ((num + 1) <= 100) {
                        tv_multiple.setText(String.valueOf(new DecimalFormat("#0").format(num + 1)));
                        mOneSeekBar.setProgress(Float.parseFloat(tv_multiple.getText().toString()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        tv_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double num1 = Double.valueOf(tv_multiple.getText().toString());
                    if ((num1 - 1) > 0) {
                        tv_multiple.setText(String.valueOf(new DecimalFormat("#0").format(num1 - 1)));
                        mOneSeekBar.setProgress(Float.parseFloat(tv_multiple.getText().toString()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mOneSeekBar.setCustomSectionTextArray(new BubbleSeekBar.CustomSectionTextArray() {
            @NonNull
            @Override
            public SparseArray<String> onCustomize(int sectionCount, @NonNull SparseArray<String> array) {
                array.clear();
                int max = Integer.parseInt(split[split.length - 1]);
                int min = Integer.parseInt(split[0]);
                int pro =  (max - min)/4;
                array.put(0, (int)(min)+"x");
                array.put(1, (int)(min+pro)+"x");
                array.put(2, (int)(min+pro+pro)+"x");
                array.put(3, (int)(min+pro+pro+pro)+"x");
                array.put(4, (int)(max)+"x");
                return array;
            }
        });
        mOneSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                tv_multiple.setText(progress + "");
            }
            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }
            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
            }
        });
        // 设置按钮监听
        bu_true.setOnClickListener(itemsOnClick);
        bu_close.setOnClickListener(itemsOnClick);

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
