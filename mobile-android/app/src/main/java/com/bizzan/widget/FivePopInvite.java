package com.bizzan.widget;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

import com.bizzan.R;
import com.bizzan.adapter.shareApkImageAdapter;
import com.bizzan.base.CenterLayoutManager;


/**
 * Created by Haocxx
 * on 2019/8/12
 */
public class FivePopInvite extends PopupWindow {

    private View view;
    private final TextView tv_cancel, tv_copy, tv_save;
    private RecyclerView rl_select_image;
    public int index = 1;

    public FivePopInvite(final Activity activity, shareApkImageAdapter adapter, View.OnClickListener itemsOnClick) {
        this.view = LayoutInflater.from(activity).inflate(R.layout.pop_five_invite, null);
        tv_cancel = view.findViewById(R.id.tv_cancel);
        rl_select_image = view.findViewById(R.id.rl_select_image);
        tv_copy = view.findViewById(R.id.tv_copy);
        tv_save = view.findViewById(R.id.tv_save);


        tv_cancel.setOnClickListener(itemsOnClick);
        tv_copy.setOnClickListener(itemsOnClick);
        tv_save.setOnClickListener(itemsOnClick);

        final CenterLayoutManager manager = new CenterLayoutManager(activity);
//        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        manager.setAutoMeasureEnabled(true);
        rl_select_image.setLayoutManager(manager);
        rl_select_image.setHasFixedSize(true);
        rl_select_image.setNestedScrollingEnabled(false);
        rl_select_image.setAdapter(adapter);
        manager.smoothScrollToPosition(rl_select_image, new RecyclerView.State(), index);


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RelativeLayout rl_img = view.findViewById(R.id.rl_img);
                TextView tv_view = view.findViewById(R.id.tv_view);
                if (index == position) {
                    if (rl_img.getAlpha() == (float) 1.0) {
                        rl_img.setAlpha((float) 0.5);
                        tv_view.setVisibility(View.VISIBLE);
                        index = 9;
                    } else {
                        rl_img.setAlpha((float) 1.0);
                        tv_view.setVisibility(View.GONE);
                        index = position;
                    }
                } else {
                    for (int i = 0; i < adapter.getItemCount(); i++) {
                        if (position == i) {
                            rl_img.setAlpha((float) 1.0);
                            tv_view.setVisibility(View.GONE);
                        } else {
                            RelativeLayout relativeLayout = (RelativeLayout) adapter.getViewByPosition(rl_select_image, i, R.id.rl_img);
                            TextView tv_view2 = (TextView) adapter.getViewByPosition(rl_select_image, i, R.id.tv_view);
                            if (relativeLayout != null && tv_view2 != null) {
                                if (relativeLayout != null) {
                                    relativeLayout.setAlpha((float) 0.5);
                                    tv_view2.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        index = position;
                    }
                    manager.smoothScrollToPosition(rl_select_image, new RecyclerView.State(), index);
                }

            }
        });

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
        this.setAnimationStyle(R.style.bottomDialog);
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = (float) 0.6; //0.0-1.0
        activity.getWindow().setAttributes(lp);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 设置弹出窗体的背景
        this.setBackgroundDrawable(Drawable.createFromPath("#00000000"));
    }


    public Bitmap createQRCodeBitmap(String content, int width, int height,
                                     String character_set, String error_correction_level,
                                     String margin, int color_black, int color_white) {
        // 字符串内容判空
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        // 宽和高>=0
        if (width < 0 || height < 0) {
            return null;
        }
        try {
            /** 1.设置二维码相关配置 */
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            // 字符转码格式设置
            if (!TextUtils.isEmpty(character_set)) {
                hints.put(EncodeHintType.CHARACTER_SET, character_set);
            }
            // 容错率设置
            if (!TextUtils.isEmpty(error_correction_level)) {
                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
            }
            // 空白边距设置
            if (!TextUtils.isEmpty(margin)) {
                hints.put(EncodeHintType.MARGIN, margin);
            }
            /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = color_black;//黑色色块像素设置
                    } else {
                        pixels[y * width + x] = color_white;// 白色色块像素设置
                    }
                }
            }
            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}
