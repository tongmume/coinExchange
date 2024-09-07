package com.bizzan.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;
import java.util.Hashtable;

import com.bizzan.R;

/**
 * Created by Haocxx
 * on 2019/8/12
 */
public class shareApkImageAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    String code;
    String url;

    public shareApkImageAdapter(int item_share_apk_image, ArrayList<Integer> imagebitmap, String code, String url) {
        super(item_share_apk_image, imagebitmap);
        this.code = code;
        this.url = url;
    }

    @Override
    protected void convert(final BaseViewHolder helper, Integer item) {
        if (item.equals(R.drawable.invite2)){
            helper.setImageResource(R.id.img,item).setAlpha(R.id.rl_img,1.0f);
            helper.setImageBitmap(R.id.iv_code_address, createQRCodeBitmap(url+code, 150, 150, "UTF-8", "H", "1", Color.BLACK, Color.WHITE));
            helper.setGone(R.id.tv_view,false);
        }else {
            helper.setImageResource(R.id.img,item).setAlpha(R.id.rl_img,0.5f);
            helper.setImageBitmap(R.id.iv_code_address, createQRCodeBitmap(url+code, 150, 150, "UTF-8", "H", "1", Color.BLACK, Color.WHITE));
            helper.setGone(R.id.tv_view,true);
        }
        helper.addOnClickListener(R.id.llRoot);

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
