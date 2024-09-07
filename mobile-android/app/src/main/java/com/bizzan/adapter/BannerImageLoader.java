package com.bizzan.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bizzan.ui.home.GlideRoundTransform;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2018/1/8.
 */

public class BannerImageLoader extends ImageLoader {
    private int w;
    private int h;

    public BannerImageLoader(int w, int h) {
        if (w<=0){
            this.w = 500;
        }
        if (h<=0){
            this.h = 100;
        }
        this.w = w;
        this.h = h;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (w<=0){
            w = 500;
        }
        if (h<=0){
            h = 100;
        }

        Glide.with(context).load(path).transform(new CenterCrop(context),new GlideRoundTransform(context,5)).override(w, h).into(imageView);
    }
}
